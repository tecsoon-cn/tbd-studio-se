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
package org.talend.hadoop.distribution.synapse.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.talend.hadoop.distribution.EHadoopVersion;
import org.talend.hadoop.distribution.component.HBaseComponent;
import org.talend.hadoop.distribution.component.HCatalogComponent;
import org.talend.hadoop.distribution.component.HDFSComponent;
import org.talend.hadoop.distribution.component.HadoopComponent;
import org.talend.hadoop.distribution.component.HiveComponent;
import org.talend.hadoop.distribution.component.ImpalaComponent;
import org.talend.hadoop.distribution.component.MRComponent;
import org.talend.hadoop.distribution.component.SqoopComponent;
import org.talend.hadoop.distribution.synapse.SynapseDistribution;

/**
 * Test class for the {@link SynapseDistribution} distribution.
 *
 */
public class SynapseDistributionTest {

    
 private final static String YARN_APPLICATION_CLASSPATH = "$HADOOP_CONF_DIR,$HADOOP_COMMON_HOME/*,$HADOOP_COMMON_HOME/lib/*,$HADOOP_HDFS_HOME/*,$HADOOP_HDFS_HOME/lib/*,$HADOOP_MAPRED_HOME/*,$HADOOP_MAPRED_HOME/lib/*,$YARN_HOME/*,$YARN_HOME/lib/*,$HADOOP_YARN_HOME/*,$HADOOP_YARN_HOME/lib/*,$HADOOP_COMMON_HOME/share/hadoop/common/*,$HADOOP_COMMON_HOME/share/hadoop/common/lib/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/*,$HADOOP_HDFS_HOME/share/hadoop/hdfs/lib/*,$HADOOP_YARN_HOME/share/hadoop/yarn/*,$HADOOP_YARN_HOME/share/hadoop/yarn/lib/*" ;

    @Test
    public void testSynapseDistribution() throws Exception {
        HadoopComponent distribution = new SynapseDistribution();
        assertNotNull(distribution.getDistributionName());
        assertNotNull(distribution.getVersionName(null));
        assertFalse(distribution.doSupportS3());
        assertEquals(SynapseDistribution.DISTRIBUTION_NAME, distribution.getDistribution());
        assertEquals(SynapseDistribution.VERSION, distribution.getVersion());
        assertEquals(EHadoopVersion.HADOOP_3, distribution.getHadoopVersion());
        
        assertFalse(distribution.doSupportKerberos());
        assertTrue(distribution.doSupportUseDatanodeHostname());
        assertFalse(distribution.doSupportGroup());
        assertFalse(distribution.doSupportOldImportMode());
        assertFalse(((HDFSComponent) distribution).doSupportSequenceFileShortType());
        assertFalse(((MRComponent) distribution).isExecutedThroughWebHCat());
        assertTrue(((MRComponent) distribution).doSupportCrossPlatformSubmission());
        assertFalse(((MRComponent) distribution).doSupportImpersonation());
        assertEquals(((MRComponent) distribution).getYarnApplicationClasspath(), YARN_APPLICATION_CLASSPATH);
        assertFalse(distribution instanceof HBaseComponent);
        assertFalse(distribution instanceof SqoopComponent);
        assertFalse(((HiveComponent) distribution).doSupportEmbeddedMode());
        assertFalse(((HiveComponent) distribution).doSupportStandaloneMode());
        assertFalse(((HiveComponent) distribution).doSupportHive1());
        assertFalse(((HiveComponent) distribution).doSupportHive2());
        assertFalse(((HiveComponent) distribution).doSupportTezForHive());
        assertFalse(((HiveComponent) distribution).doSupportHBaseForHive());
        assertFalse(((HiveComponent) distribution).doSupportSSL());
        assertFalse(((HiveComponent) distribution).doSupportORCFormat());
        assertTrue(((HiveComponent) distribution).doSupportAvroFormat());
        assertTrue(((HiveComponent) distribution).doSupportParquetFormat());
        assertFalse(((HiveComponent) distribution).doSupportStoreAsParquet());
        assertFalse(((HiveComponent) distribution).doSupportClouderaNavigator());
        assertFalse(distribution instanceof HCatalogComponent);
        assertFalse(distribution instanceof ImpalaComponent);
        assertFalse(((SqoopComponent) distribution).doJavaAPISqoopImportAllTablesSupportExcludeTable());
        assertFalse(((SqoopComponent) distribution).doJavaAPISqoopImportSupportDeleteTargetDir());
        assertFalse(((SqoopComponent) distribution).doJavaAPISupportStorePasswordInFile());
        assertFalse(((HBaseComponent) distribution).doSupportNewHBaseAPI());
        assertTrue(distribution.doSupportAzureDataLakeStorage());
        assertTrue(distribution.doSupportWebHDFS());
    }

}
