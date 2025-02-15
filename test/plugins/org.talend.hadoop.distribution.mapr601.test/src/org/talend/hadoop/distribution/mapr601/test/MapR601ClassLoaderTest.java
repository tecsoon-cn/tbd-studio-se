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
package org.talend.hadoop.distribution.mapr601.test;

import org.junit.Test;
import org.talend.core.hadoop.EHadoopCategory;
import org.talend.core.model.metadata.connection.hive.HiveModeInfo;
import org.talend.core.model.metadata.connection.hive.HiveServerVersionInfo;
import org.talend.hadoop.distribution.component.HadoopComponent;
import org.talend.hadoop.distribution.mapr601.MapR601Distribution;
import org.talend.hadoop.distribution.test.classloader.AbstractTest4ClassLoaderProvider;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class MapR601ClassLoaderTest extends AbstractTest4ClassLoaderProvider {

    @Override
    protected Class<? extends HadoopComponent> getHadoopComponentClass() {
        return MapR601Distribution.class;
    }

    @Test
    public void testHive1Standalone() {
        String libsStr = "antlr-runtime-3.4.jar;avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;curator-client-2.7.1.jar;curator-framework-2.7.1.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;hbase-client-1.1.8-mapr-1710.jar;hbase-common-1.1.8-mapr-1710.jar;hbase-hadoop2-compat-1.1.8-mapr-1710.jar;hbase-protocol-1.1.8-mapr-1710.jar;hbase-server-1.1.8-mapr-1803.jar;high-scale-lib-1.1.1.jar;hive-exec-2.1.1-mapr-1803.jar;hive-hbase-handler-2.1.1-mapr-1803.jar;hive-jdbc-2.1.1-mapr-1803.jar;hive-metastore-2.1.1-mapr-1803.jar;hive-service-2.1.1-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;httpclient-4.4.jar;httpcore-4.4.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;jdo-api-3.0.1.jar;json-20080701.jar;libfb303-0.9.3.jar;libthrift-0.9.3.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;netty-all-4.0.43.Final.jar;parquet-hadoop-bundle-1.8.2.jar;parquet-hive-bundle-1.6.0.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestHiveServerWithMode(HiveServerVersionInfo.HIVE_SERVER_1, HiveModeInfo.STANDALONE, libsStr);
    }

    @Test
    public void testHive1Embedded() {
        String libsStr = "antlr-runtime-3.4.jar;avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;curator-client-2.7.1.jar;curator-framework-2.7.1.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;hbase-client-1.1.8-mapr-1710.jar;hbase-common-1.1.8-mapr-1710.jar;hbase-hadoop2-compat-1.1.8-mapr-1710.jar;hbase-protocol-1.1.8-mapr-1710.jar;hbase-server-1.1.8-mapr-1803.jar;high-scale-lib-1.1.1.jar;hive-exec-2.1.1-mapr-1803.jar;hive-hbase-handler-2.1.1-mapr-1803.jar;hive-jdbc-2.1.1-mapr-1803.jar;hive-metastore-2.1.1-mapr-1803.jar;hive-service-2.1.1-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;httpclient-4.4.jar;httpcore-4.4.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;jdo-api-3.0.1.jar;json-20080701.jar;libfb303-0.9.3.jar;libthrift-0.9.3.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;netty-all-4.0.43.Final.jar;parquet-hadoop-bundle-1.8.2.jar;parquet-hive-bundle-1.6.0.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestHiveServerWithMode(HiveServerVersionInfo.HIVE_SERVER_1, HiveModeInfo.EMBEDDED, libsStr);
    }

    @Test
    public void testHive2Standalone() {
        String libsStr = "antlr-runtime-3.4.jar;avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;curator-client-2.7.1.jar;curator-framework-2.7.1.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;hbase-client-1.1.8-mapr-1710.jar;hbase-common-1.1.8-mapr-1710.jar;hbase-hadoop2-compat-1.1.8-mapr-1710.jar;hbase-protocol-1.1.8-mapr-1710.jar;hbase-server-1.1.8-mapr-1803.jar;high-scale-lib-1.1.1.jar;hive-exec-2.1.1-mapr-1803.jar;hive-hbase-handler-2.1.1-mapr-1803.jar;hive-jdbc-2.1.1-mapr-1803.jar;hive-metastore-2.1.1-mapr-1803.jar;hive-service-2.1.1-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;httpclient-4.4.jar;httpcore-4.4.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;jdo-api-3.0.1.jar;json-20080701.jar;libfb303-0.9.3.jar;libthrift-0.9.3.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;netty-all-4.0.43.Final.jar;parquet-hadoop-bundle-1.8.2.jar;parquet-hive-bundle-1.6.0.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestHiveServerWithMode(HiveServerVersionInfo.HIVE_SERVER_2, HiveModeInfo.STANDALONE, libsStr);
    }

    @Test
    public void testHive2Embedded() {
        String libsStr = "antlr-runtime-3.4.jar;avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;curator-client-2.7.1.jar;curator-framework-2.7.1.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;hbase-client-1.1.8-mapr-1710.jar;hbase-common-1.1.8-mapr-1710.jar;hbase-hadoop2-compat-1.1.8-mapr-1710.jar;hbase-protocol-1.1.8-mapr-1710.jar;hbase-server-1.1.8-mapr-1803.jar;high-scale-lib-1.1.1.jar;hive-exec-2.1.1-mapr-1803.jar;hive-hbase-handler-2.1.1-mapr-1803.jar;hive-jdbc-2.1.1-mapr-1803.jar;hive-metastore-2.1.1-mapr-1803.jar;hive-service-2.1.1-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;httpclient-4.4.jar;httpcore-4.4.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;jdo-api-3.0.1.jar;json-20080701.jar;libfb303-0.9.3.jar;libthrift-0.9.3.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;netty-all-4.0.43.Final.jar;parquet-hadoop-bundle-1.8.2.jar;parquet-hive-bundle-1.6.0.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestHiveServerWithMode(HiveServerVersionInfo.HIVE_SERVER_2, HiveModeInfo.EMBEDDED, libsStr);
    }

    @Test
    public void testHbase() {
        String libsStr = "commons-codec-1.9.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hbase-client-1.1.8-mapr-1710.jar;hbase-common-1.1.8-mapr-1710.jar;hbase-protocol-1.1.8-mapr-1710.jar;htrace-core-3.1.0-incubating.jar;json-20080701.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;mapr-hbase-6.0.1-mapr.jar;netty-all-4.0.43.Final.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;zookeeper-3.4.5-mapr-1710.jar;netty-3.9.9.Final.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestClassLoader(EHadoopCategory.HBASE.getName(), libsStr);
    }

    @Test
    public void testMapReduce() {
        String libsStr = "avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;json-20080701.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;parquet-hadoop-bundle-1.8.2.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestClassLoader(EHadoopCategory.MAP_REDUCE.getName(), libsStr);
    }

    @Test
    public void testHDFS() {
        String libsStr = "avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;json-20080701.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;parquet-hadoop-bundle-1.8.2.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestClassLoader(EHadoopCategory.HDFS.getName(), libsStr);
    }

    @Test
    public void testHDFSWithKerberos() {
        String libsStr = "avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;json-20080701.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;parquet-hadoop-bundle-1.8.2.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar;hadoop-conf-kerberos.jar";//$NON-NLS-1$
        doTestClassLoader(EHadoopCategory.HDFS.getName(), libsStr, "?USE_KRB");//$NON-NLS-1$
    }

    @Test
    public void testHCatalog() {
        String libsStr = "avro-1.7.7.jar;commons-cli-1.2.jar;commons-codec-1.5.jar;commons-collections-3.2.2.jar;commons-configuration-1.6.jar;commons-io-2.4.jar;commons-lang-2.6.jar;commons-logging-1.1.3.jar;guava-14.0.1.jar;hadoop-auth-2.7.0-mapr-1803.jar;hadoop-common-2.7.0-mapr-1803.jar;hadoop-hdfs-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-app-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-common-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-contrib-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-core-2.7.0-mapr-1803.jar;;hadoop-mapreduce-client-hs-plugins-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-jobclient-2.7.0-mapr-1803.jar;hadoop-mapreduce-client-shuffle-2.7.0-mapr-1803.jar;hadoop-yarn-api-2.7.0-mapr-1803.jar;hadoop-yarn-client-2.7.0-mapr-1803.jar;hadoop-yarn-common-2.7.0-mapr-1803.jar;hadoop-yarn-server-web-proxy-2.7.0-mapr-1803.jar;htrace-core-3.1.0-incubating.jar;jackson-core-asl-1.9.14-TALEND.jar;jackson-mapper-asl-1.9.14-TALEND.jar;json-20080701.jar;log4j-1.2.17.jar;maprfs-6.0.1-mapr.jar;parquet-hadoop-bundle-1.8.2.jar;protobuf-java-2.5.0.jar;slf4j-api-1.7.5.jar;slf4j-log4j12-1.7.5.jar;snappy-java-1.0.4.1.jar;zookeeper-3.4.5-mapr-1710.jar;flexjson-2.1.jar";//$NON-NLS-1$
        doTestClassLoader(EHadoopCategory.HCATALOG.getName(), libsStr);
    }
}
