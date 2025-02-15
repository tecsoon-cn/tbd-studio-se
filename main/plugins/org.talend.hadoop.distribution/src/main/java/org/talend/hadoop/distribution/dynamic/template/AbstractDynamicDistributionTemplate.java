package org.talend.hadoop.distribution.dynamic.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ClassUtils;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.talend.commons.exception.CommonExceptionHandler;
import org.talend.commons.exception.ExceptionHandler;
import org.talend.core.model.general.ILibrariesService;
import org.talend.core.runtime.dynamic.DynamicServiceUtil;
import org.talend.core.runtime.dynamic.IDynamicPlugin;
import org.talend.core.runtime.dynamic.IDynamicPluginConfiguration;
import org.talend.hadoop.distribution.AbstractDistribution;
import org.talend.hadoop.distribution.ComponentType;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.EHadoopVersion;
import org.talend.hadoop.distribution.ESparkVersion;
import org.talend.hadoop.distribution.NodeComponentTypeBean;
import org.talend.hadoop.distribution.component.HBaseComponent;
import org.talend.hadoop.distribution.component.HCatalogComponent;
import org.talend.hadoop.distribution.component.HDFSComponent;
import org.talend.hadoop.distribution.component.HadoopComponent;
import org.talend.hadoop.distribution.component.HiveComponent;
import org.talend.hadoop.distribution.component.HiveOnSparkComponent;
import org.talend.hadoop.distribution.component.ImpalaComponent;
import org.talend.hadoop.distribution.component.MRComponent;
import org.talend.hadoop.distribution.component.MapRDBComponent;
import org.talend.hadoop.distribution.component.SparkBatchComponent;
import org.talend.hadoop.distribution.component.SparkStreamingComponent;
import org.talend.hadoop.distribution.component.SqoopComponent;
import org.talend.hadoop.distribution.condition.ComponentCondition;
import org.talend.hadoop.distribution.dynamic.DynamicConstants;
import org.talend.hadoop.distribution.dynamic.adapter.DynamicPluginAdapter;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.DynamicModuleGroupConstant;
import org.talend.hadoop.distribution.dynamic.util.DynamicDistributionUtils;
import org.talend.hadoop.distribution.helper.HadoopDistributionsHelper;
import org.talend.hadoop.distribution.kafka.SparkStreamingKafkaVersion;
import org.talend.hadoop.distribution.spark.SparkClassPathUtils;

public abstract class AbstractDynamicDistributionTemplate extends AbstractDistribution
        implements HadoopComponent, IDynamicDistributionTemplate {

    private static final String OPT_DEBUG_DYNAMIC_DISTRIBUTION_OSGI = "talend.studio.dynamicDistribution.debug.osgi";

    private Map<ComponentType, Set<DistributionModuleGroup>> moduleGroups;

    private Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> nodeModuleGroups;

    private Map<ComponentType, ComponentCondition> displayConditions;

    private Map<ComponentType, IDynamicModuleGroupTemplate> moduleGroupsTemplateMap;

    private DynamicPluginAdapter pluginAdapter;

    private ServiceRegistration osgiService;

    private final Object osgiServiceLock = new Object();

    private DynamicPluginAdapter registedPluginAdapter;

    private final Object registedPluginAdapterLock = new Object();

    private String versionId;

    private String versionDisplay;

    public AbstractDynamicDistributionTemplate(DynamicPluginAdapter pluginAdapter) throws Exception {
        this.pluginAdapter = pluginAdapter;
        IDynamicPluginConfiguration configuration = pluginAdapter.getPluginConfiguration();
        versionId = configuration.getId();
        versionDisplay = configuration.getName();
        if ("CDH6xDistributionTemplate".equals(configuration.getTemplateId())
                || "HDP2xxDistributionTemplate".equals(configuration.getTemplateId())
                || ("HDP3xxDistributionTemplate".equals(configuration.getTemplateId())
                        && configuration.getVersion().startsWith("3.0"))) {
            versionDisplay = versionDisplay.replaceAll("Dynamic", "Deprecated");
        }

        moduleGroupsTemplateMap = buildModuleGroupsTemplateMap();

        // Used to add a module group import for the components that have a HADOOP_DISTRIBUTION parameter, aka. the
        // components that have the distribution list.
        moduleGroups = buildModuleGroupsMap(pluginAdapter);

        // Used to add a module group import for a specific node. The given node must have a HADOOP_LIBRARIES parameter.
        nodeModuleGroups = buildNodeModuleGroupsMap(pluginAdapter);

        displayConditions = new HashMap<>();
    }

    protected Map<ComponentType, IDynamicModuleGroupTemplate> buildModuleGroupsTemplateMap() {
        Map<ComponentType, IDynamicModuleGroupTemplate> moduleGroupsTemplateMap = new HashMap<>();

        DynamicPluginAdapter pluginAdapter = getPluginAdapter();

        if (this instanceof HDFSComponent) {
            moduleGroupsTemplateMap.put(ComponentType.HDFS, new DynamicHDFSModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof HBaseComponent) {
            moduleGroupsTemplateMap.put(ComponentType.HBASE, new DynamicHBaseModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof HCatalogComponent) {
            moduleGroupsTemplateMap.put(ComponentType.HCATALOG, new DynamicHCatalogModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof HiveComponent) {
            moduleGroupsTemplateMap.put(ComponentType.HIVE, new DynamicHiveModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof HiveOnSparkComponent) {
            moduleGroupsTemplateMap.put(ComponentType.HIVEONSPARK, new DynamicHiveOnSparkModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof MapRDBComponent) {
            moduleGroupsTemplateMap.put(ComponentType.MAPRDB, new DynamicMapRDBModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof MRComponent) {
            moduleGroupsTemplateMap.put(ComponentType.MAPREDUCE, new DynamicMapReduceModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof SparkBatchComponent) {
            moduleGroupsTemplateMap.put(ComponentType.SPARKBATCH, new DynamicSparkBatchModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof SparkStreamingComponent) {
            moduleGroupsTemplateMap.put(ComponentType.SPARKSTREAMING,
                    new DynamicSparkStreamingModuleGroupTemplate(pluginAdapter));
        }
        if (this instanceof SqoopComponent) {
            moduleGroupsTemplateMap.put(ComponentType.SQOOP, new DynamicSqoopModuleGroupTemplate(pluginAdapter));
        }

        if (this instanceof ImpalaComponent) {
            moduleGroupsTemplateMap.put(ComponentType.IMPALA, new DynamicImpalaModuleGroupTemplate(pluginAdapter));
        }

        return moduleGroupsTemplateMap;
    }

    protected Map<ComponentType, Set<DistributionModuleGroup>> buildModuleGroupsMap(DynamicPluginAdapter pluginAdapter)
            throws Exception {
        Map<ComponentType, Set<DistributionModuleGroup>> moduleGroupsMap = new HashMap<>();

        for (Map.Entry<ComponentType, IDynamicModuleGroupTemplate> entry : moduleGroupsTemplateMap.entrySet()) {
            Map<ComponentType, Set<DistributionModuleGroup>> groupMap = entry.getValue().getModuleGroups();
            if (groupMap != null && !groupMap.isEmpty()) {
                for (Map.Entry<ComponentType, Set<DistributionModuleGroup>> groupEntry : groupMap.entrySet()) {
                    ComponentType key = groupEntry.getKey();
                    Set<DistributionModuleGroup> existingGroupSet = moduleGroupsMap.get(key);
                    if (existingGroupSet != null) {
                        String name = "";
                        try {
                            name = key.name();
                        } catch (Exception e) {
                            ExceptionHandler.process(e);
                        } finally {
                            CommonExceptionHandler
                                    .warn(this.getClass().getSimpleName() + " : multiple define of " + name + ", will merge it.");
                        }
                        existingGroupSet.addAll(groupEntry.getValue());
                    } else {
                        moduleGroupsMap.put(key, groupEntry.getValue());
                    }
                }
            }
        }

        return moduleGroupsMap;
    }

    protected Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> buildNodeModuleGroupsMap(
            DynamicPluginAdapter pluginAdapter) throws Exception {
        Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> nodeModuleGroupsMap = new HashMap<>();

        for (Map.Entry<ComponentType, IDynamicModuleGroupTemplate> entry : moduleGroupsTemplateMap.entrySet()) {
            Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> groupMap = entry.getValue().getNodeModuleGroups();
            if (groupMap != null && !groupMap.isEmpty()) {
                for (Map.Entry<NodeComponentTypeBean, Set<DistributionModuleGroup>> groupEntry : groupMap.entrySet()) {
                    NodeComponentTypeBean key = groupEntry.getKey();
                    Set<DistributionModuleGroup> existingGroupSet = nodeModuleGroupsMap.get(key);
                    if (existingGroupSet != null) {
                        String keyStr = "";
                        try {
                            ComponentType componentType = key.getComponentType();
                            if (componentType != null) {
                                keyStr = keyStr + componentType.name();
                            }
                            keyStr = keyStr + ", " + key.getComponentName();
                        } catch (Exception e) {
                            ExceptionHandler.process(e);
                        } finally {
                            CommonExceptionHandler.warn(
                                    this.getClass().getSimpleName() + " : multiple define of [" + keyStr + "], will merge it.");
                        }
                        existingGroupSet.addAll(groupEntry.getValue());
                    } else {
                        nodeModuleGroupsMap.put(key, groupEntry.getValue());
                    }
                }
            }
        }

        return nodeModuleGroupsMap;
    }

    @Override
    public List<String> getServices() {
        List<String> services = new ArrayList<>();
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(this.getClass());
        for (Class<?> clz : allInterfaces) {
            if (HadoopComponent.class.isAssignableFrom(clz)) {
                services.add(clz.getName());
            }
        }
        return services;
    }

    @Override
    public boolean registOsgiServices() {
        if (osgiService == null) {
            synchronized (osgiServiceLock) {
                if (osgiService == null) {
                    try {
                        BundleContext context = getPluginAdapter().getBundle().getBundleContext();
                        osgiService = DynamicServiceUtil.registOSGiService(context, getServices().toArray(new String[0]), this,
                                null);
                        return true;
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean unregistOsgiServices() {
        try {
            if (osgiService != null) {
                synchronized (osgiServiceLock) {
                    if (osgiService != null) {
                        DynamicServiceUtil.unregistOSGiService(osgiService);
                        osgiService = null;
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            ExceptionHandler.process(e);
        }
        return false;
    }

    @Override
    public boolean isPluginExtensionsRegisted() {
        return registedPluginAdapter != null;
    }

    @Override
    public boolean registPluginExtensions() {
        if (registedPluginAdapter == null) {
            synchronized (registedPluginAdapterLock) {
                if (registedPluginAdapter == null) {
                    DynamicPluginAdapter plugAdapter = getPluginAdapter();
                    IDynamicPlugin plugin = plugAdapter.getPlugin();
                    IDynamicPluginConfiguration pConfiguration = plugin.getPluginConfiguration();
                    try {
                        plugin.setPluginConfiguration(null);
                        DynamicServiceUtil.addContribution(plugAdapter.getBundle(), plugin);
                        registedPluginAdapter = plugAdapter;
                        ILibrariesService libService = ILibrariesService.get();
                        if (libService != null) {
                            libService.resetModulesNeeded();
                        }
                        HadoopDistributionsHelper.updatePluginExtensionCacheVersion();
                        return true;
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    } finally {
                        plugin.setPluginConfiguration(pConfiguration);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean unregistPluginExtensions(boolean reloadLibCache) {
        if (registedPluginAdapter != null) {
            synchronized (registedPluginAdapterLock) {
                if (registedPluginAdapter != null) {
                    try {
                        DynamicServiceUtil.removeContribution(registedPluginAdapter.getPlugin());
                        registedPluginAdapter = null;
                        if (reloadLibCache) {
                            ILibrariesService libService = ILibrariesService.get();
                            if (libService != null) {
                                libService.resetModulesNeeded();
                            }
                        }
                        HadoopDistributionsHelper.updatePluginExtensionCacheVersion();
                        return true;
                    } catch (Exception e) {
                        ExceptionHandler.process(e);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String getVersion() {
        return versionId;
    }

    public String getVersionDisplay() {
        return versionDisplay;
    }

    @Override
    public DynamicPluginAdapter getPluginAdapter() {
        return pluginAdapter;
    }

    protected Map<ComponentType, Set<DistributionModuleGroup>> getModuleGroupsMap() {
        return moduleGroups;
    }

    protected Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> getNodeModuleGroupsMap() {
        return nodeModuleGroups;
    }

    protected Map<ComponentType, ComponentCondition> getDisplayConditionsMap() {
        return displayConditions;
    }

    @Override
    public boolean isDynamicDistribution() {
        return true;
    }

    @Override
    public EHadoopVersion getHadoopVersion() {
        return EHadoopVersion.HADOOP_2;
    }

    @Override
    public Set<DistributionModuleGroup> getModuleGroups(ComponentType componentType) {
        return getModuleGroupsMap().get(componentType);
    }

    @Override
    public Set<DistributionModuleGroup> getModuleGroups(ComponentType componentType, String componentName) {
        return getNodeModuleGroupsMap().get(new NodeComponentTypeBean(componentType, componentName));
    }

    @Override
    public ComponentCondition getDisplayCondition(ComponentType componentType) {
        return getDisplayConditionsMap().get(componentType);
    }

    @Override
    public String getVersionName(ComponentType componentType) {
        return getVersionDisplay();
    }

    @Override
    public Set<ESparkVersion> getSparkVersions() {
        Set<ESparkVersion> version = new HashSet<>();

        IDynamicPluginConfiguration pluginConfiguration = pluginAdapter.getPluginConfiguration();
        List<String> selectedSparkVersions = (List<String>) pluginConfiguration
                .getAttribute(DynamicConstants.ATTR_SELECTED_SPARK_VERSIONS);
        if (selectedSparkVersions != null) {
            List<ESparkVersion> sparkVersions = DynamicDistributionUtils.convert2ESparkVersions(selectedSparkVersions);
            version.addAll(sparkVersions);
        }

        return version;
    }

    @Override
    public String generateSparkJarsPaths(List<String> commandLineJarsPaths) {
        return generateSparkJarsPaths(commandLineJarsPaths, false);
    }
    
    public String generateSparkJarsPaths(List<String> commandLineJarsPaths, boolean isLightWeight) {
    	if (isLightWeight) {
        	String customDependencies = getPluginAdapter()
                    .getRuntimeModuleGroupIdByTemplateId(DynamicModuleGroupConstant.LIGHTWEIGHT_DEPENDENCIES.getModuleName());
            return SparkClassPathUtils.generateSparkJarsPathsWithNames(commandLineJarsPaths, customDependencies);
    	} else {
	        String spark2RuntimeId = getPluginAdapter()
	                .getRuntimeModuleGroupIdByTemplateId(DynamicModuleGroupConstant.SPARK2_MODULE_GROUP.getModuleName());
	        if (StringUtils.isEmpty(SparkClassPathUtils.generateSparkJarsPathsWithNames(commandLineJarsPaths, spark2RuntimeId))) {
	            throw new RuntimeException(
	                    "Can't find configuration for " + DynamicModuleGroupConstant.SPARK2_MODULE_GROUP.getModuleName());
	        }
	        return SparkClassPathUtils.generateSparkJarsPathsWithNames(commandLineJarsPaths, spark2RuntimeId);
    	}
    }
    
    @Override
    public SparkStreamingKafkaVersion getSparkStreamingKafkaVersion(ESparkVersion sparkVersion) {
        // Using Kafka 0.10 for Spark 2
        if (ESparkVersion.SPARK_2_0.compareTo(sparkVersion) <= 0) {
            return SparkStreamingKafkaVersion.KAFKA_0_10;
        } else {
            return SparkStreamingKafkaVersion.KAFKA_0_8;
        }
    }

    @Override
    public boolean doSupportKerberos() {
        return true;
    }

    @Override
    public boolean doSupportUseDatanodeHostname() {
        return true;
    }

    @Override
    public boolean doSupportOldImportMode() {
        return false;
    }

    @Override
    public boolean doSupportS3() {
        return true;
    }

    @Override
    public boolean doSupportS3V4() {
        return true;
    }

    @Override
    public boolean doSupportHDFSEncryption() {
        return true;
    }

    @Override
    public boolean doSupportAzureDataLakeStorage() {
        return true;
    }
}
