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
package org.talend.hadoop.distribution.mapr600;

public enum MapR600Constant {

    OJAI_MODULE_GROUP("OJAI-LIB-MAPR600"), //$NON-NLS-1$
    HDFS_MODULE_GROUP("HDFS-LIB-MAPR600"), //$NON-NLS-1$
    MAPREDUCE_MODULE_GROUP("MAPREDUCE-LIB-MAPR600"), //$NON-NLS-1$
    HBASE_MODULE_GROUP("HBASE-LIB-MAPR600"), //$NON-NLS-1$
    HIVE_MODULE_GROUP("HIVE-LIB-MAPR600"), //$NON-NLS-1$
    SQOOP_MODULE_GROUP("SQOOP-LIB-MAPR600"), //$NON-NLS-1$
    SPARK_MODULE_GROUP("SPARK-LIB-MAPR600"), //$NON-NLS-1$

    HIVE_HBASE_MODULE_GROUP("HIVE-HBASE-LIB-MAPR600"), //$NON-NLS-1$
    MAPREDUCE_PARQUET_MODULE_GROUP("MAPREDUCE-PARQUET-LIB-MAPR600"), //$NON-NLS-1$
    MAPREDUCE_AVRO_MODULE_GROUP("MAPREDUCE-AVRO-LIB-MAPR600"), //$NON-NLS-1$
    MAPRSTREAMS_MODULE_GROUP("MAPRSTREAMS-LIB-MAPR600"), //$NON-NLS-1$
    MAPRSTREAMS_CREATE_STREAM_MODULE_GROUP("MAPRSTREAMS-CREATESTREAM-LIB-MAPR600"), //$NON-NLS-1$
    // can be use for parquet, need to check
    // SPARK_MRREQUIRED_MODULE_GROUP("SPARK-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_YARN_CLUSTER_MRREQUIRED_MODULE_GROUP("SPARK-YARN-CLUSTER-LIB-MAPR600"), //$NON-NLS-1$
    SPARK_PARQUET_MRREQUIRED_MODULE_GROUP("SPARK-PARQUET-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_KINESIS_MRREQUIRED_MODULE_GROUP("SPARK-KINESIS-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_KAFKA_ASSEMBLY_MRREQUIRED_MODULE_GROUP("SPARK-KAFKA-ASSEMBLY-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_KAFKA_AVRO_MRREQUIRED_MODULE_GROUP("SPARK-KAFKA-AVRO-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_KAFKA_CLIENT_MRREQUIRED_MODULE_GROUP("SPARK-KAFKA-CLIENT-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_HIVE_MRREQUIRED_MODULE_GROUP("SPARK-HIVE-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_S3_MRREQUIRED_MODULE_GROUP("S3-LIB-MAPR600"), //$NON-NLS-1$
    GRAPHFRAMES_MRREQUIRED_MODULE_GROUP("SPARK-GRAPHFRAMES-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SQOOP_PARQUET_MODULE_GROUP("SQOOP-PARQUET-LIB-MAPR600"), //$NON-NLS-1$
    SPARK_MAPRSTREAMS_ASSEMBLY_MRREQUIRED_MODULE_GROUP("SPARK-MAPRSTREAMS-ASSEMBLY-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_MAPRSTREAMS_CLIENT_MRREQUIRED_MODULE_GROUP("SPARK-MAPRSTREAMS-CLIENT-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_FLUME_MRREQUIRED_MODULE_GROUP("SPARK-FLUME-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_AZURE_MRREQUIRED_MODULE_GROUP("SPARK-AZURE-LIB-MRREQUIRED-MAPR600"), //$NON-NLS-1$
    SPARK_DYNAMODB_MRREQUIRED_MODULE_GROUP("SPARK-DYNAMODB-LIB-MRREQUIRED-MAPR600"); //$NON-NLS-1$

    private String mModuleName;

    MapR600Constant(String moduleName) {
        this.mModuleName = moduleName;
    }

    public String getModuleName() {
        return this.mModuleName;
    }
}
