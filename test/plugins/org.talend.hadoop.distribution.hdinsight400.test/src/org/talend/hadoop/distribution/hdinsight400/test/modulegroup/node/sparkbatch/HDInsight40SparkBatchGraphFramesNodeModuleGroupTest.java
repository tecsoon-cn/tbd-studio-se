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
package org.talend.hadoop.distribution.hdinsight400.test.modulegroup.node.sparkbatch;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.hdinsight400.HDInsight40Constant;
import org.talend.hadoop.distribution.hdinsight400.HDInsight40Distribution;
import org.talend.hadoop.distribution.hdinsight400.modulegroup.node.sparkbatch.HDInsight40SparkBatchGraphFramesNodeModuleGroup;

public class HDInsight40SparkBatchGraphFramesNodeModuleGroupTest {

    @Test
    public void testModuleGroups() throws Exception {
        Map<String, String> results = new HashMap<>();

        results.put(HDInsight40Constant.SPARK23_GRAPHFRAMES_MRREQUIRED_MODULE_GROUP.getModuleName(),"(SPARK_LOCAL_MODE=='false') AND (SUPPORTED_SPARK_VERSION=='SPARK_2_3_0')"); //$NON-NLS-1$
        results.put(HDInsight40Constant.SPARK24_GRAPHFRAMES_MRREQUIRED_MODULE_GROUP.getModuleName(),"(SPARK_LOCAL_MODE=='false') AND (SUPPORTED_SPARK_VERSION=='SPARK_2_4_0')"); //$NON-NLS-1$

        Set<DistributionModuleGroup> moduleGroups = HDInsight40SparkBatchGraphFramesNodeModuleGroup.getModuleGroups(
                HDInsight40Distribution.DISTRIBUTION_NAME, HDInsight40Distribution.VERSION);
        assertEquals(results.size(), moduleGroups.size());
        moduleGroups.iterator();
        for (DistributionModuleGroup module : moduleGroups) {
            assertTrue("Should contain module " + module.getModuleName(), results.containsKey(module.getModuleName())); //$NON-NLS-1$
            if (results.get(module.getModuleName()) == null) {
                assertTrue("The condition of the module " + module.getModuleName() + " is not null.", //$NON-NLS-1$ //$NON-NLS-2$
                        results.get(module.getModuleName()) == null);
            } else {
                assertTrue("The condition of the module " + module.getModuleName() + " is null, but it should be " //$NON-NLS-1$ //$NON-NLS-2$
                        + results.get(module.getModuleName()) + ".", results.get(module.getModuleName()) != null); //$NON-NLS-1$
                assertEquals(results.get(module.getModuleName()), module.getRequiredIf().getConditionString());
            }
        }
    }
}
