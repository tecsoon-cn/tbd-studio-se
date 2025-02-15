// ============================================================================
//
// Copyright (C) 2006-2021 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.hadoop.distribution.dynamic.template.cdp;

import java.util.Map;
import java.util.Set;

import org.talend.core.runtime.dynamic.IDynamicPluginConfiguration;
import org.talend.hadoop.distribution.ComponentType;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.NodeComponentTypeBean;
import org.talend.hadoop.distribution.constants.SparkBatchConstant;
import org.talend.hadoop.distribution.constants.cdp.ICDPDistribution;
import org.talend.hadoop.distribution.constants.cdh.IClouderaDistribution;
import org.talend.hadoop.distribution.dynamic.adapter.DynamicPluginAdapter;
import org.talend.hadoop.distribution.dynamic.template.DynamicSparkBatchModuleGroupTemplate;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.DynamicModuleGroupConstant;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.cdp.DynamicCDPSparkBatchModuleGroup;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.node.spark.DynamicSparkNodeModuleGroup;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.node.sparkbatch.DynamicSparkBatchKuduNodeModuleGroup;
import org.talend.hadoop.distribution.dynamic.template.modulegroup.node.sparkbatch.cdp.DynamicCDPGraphFramesNodeModuleGroup;


/**
 * DOC rhaddou  class global comment. Detailled comment
 */
public class DynamicCDPSparkBatchModuleGroupTemplate extends DynamicSparkBatchModuleGroupTemplate {

    public DynamicCDPSparkBatchModuleGroupTemplate(DynamicPluginAdapter pluginAdapter) {
        super(pluginAdapter);
    }

    @Override
    protected Set<DistributionModuleGroup> buildModuleGroups4SparkBatch(DynamicPluginAdapter pluginAdapter) throws Exception {
        return new DynamicCDPSparkBatchModuleGroup(pluginAdapter).getModuleGroups();
    }

    @Override
    public Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> getNodeModuleGroups() throws Exception {
        Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> nodeModuleGroups = super.getNodeModuleGroups();
        DynamicPluginAdapter pluginAdapter = getPluginAdapter();
        IDynamicPluginConfiguration configuration = pluginAdapter.getPluginConfiguration();
        String distribution = ICDPDistribution.DISTRIBUTION_NAME;
        String version = configuration.getId();

        buildNodeModuleGroups4SparkBatch(pluginAdapter, nodeModuleGroups, distribution, version);

        return nodeModuleGroups;
    }

    @Override
    protected Set<DistributionModuleGroup> buildNodeModuleGroups4SparkBatch4GraphFrames(DynamicPluginAdapter pluginAdapter,
            String distribution, String version) throws Exception {
        return new DynamicCDPGraphFramesNodeModuleGroup(pluginAdapter).getModuleGroups(distribution, version);
    }

    @Override
    protected void buildNodeModuleGroups4SparkBatch4Kudu(DynamicPluginAdapter pluginAdapter,
            Map<NodeComponentTypeBean, Set<DistributionModuleGroup>> nodeModuleGroupsMap, String distribution, String version)
            throws Exception {
        // Kudu ...
        Set<DistributionModuleGroup> kuduNodeModuleGroups = new DynamicSparkBatchKuduNodeModuleGroup(pluginAdapter)
                .getModuleGroups(distribution, version, "USE_EXISTING_CONNECTION == 'false'"); //$NON-NLS-1$
        Set<DistributionModuleGroup> kuduConfigurationModuleGroups = new DynamicSparkBatchKuduNodeModuleGroup(pluginAdapter)
                .getModuleGroups(distribution, version, null);
        // ... in Spark batch
        nodeModuleGroupsMap.put(new NodeComponentTypeBean(ComponentType.SPARKBATCH, SparkBatchConstant.KUDU_INPUT_COMPONENT),
                kuduNodeModuleGroups);
        nodeModuleGroupsMap.put(new NodeComponentTypeBean(ComponentType.SPARKBATCH, SparkBatchConstant.KUDU_OUTPUT_COMPONENT),
                kuduNodeModuleGroups);
        nodeModuleGroupsMap.put(
                new NodeComponentTypeBean(ComponentType.SPARKBATCH, SparkBatchConstant.KUDU_CONFIGURATION_COMPONENT),
                kuduConfigurationModuleGroups);
    }

    /**
     * Specific CDP override to bypass inconsistency between "CDP" from ICDPDistribution and "CLOUDERA" used in UI
     */
    @Override
    protected  Set<DistributionModuleGroup> buildModuleGroups4SparkBatch4GCS(DynamicPluginAdapter pluginAdapter,
            String distribution, String version) throws Exception {
         return new DynamicSparkNodeModuleGroup(pluginAdapter).getModuleGroups(IClouderaDistribution.DISTRIBUTION_NAME, version, DynamicModuleGroupConstant.GCS_MODULE_GROUP, null);
     }

    /**
     * Specific CDP override to bypass inconsistency between "CDP" from ICDPDistribution and "CLOUDERA" used in UI
     */
    @Override
    protected  Set<DistributionModuleGroup> buildModuleGroups4SparkBatch4BigQuery(DynamicPluginAdapter pluginAdapter,
            String distribution, String version) throws Exception {
         return new DynamicSparkNodeModuleGroup(pluginAdapter).getModuleGroups(IClouderaDistribution.DISTRIBUTION_NAME, version, DynamicModuleGroupConstant.BIGQUERY_MODULE_GROUP, null);
     }


}
