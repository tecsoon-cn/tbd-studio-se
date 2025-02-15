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
package org.talend.hadoop.distribution.emr500.modulegroup;

import java.util.HashSet;
import java.util.Set;

import org.talend.hadoop.distribution.DistributionModuleGroup;

public class EMR500HiveOnSparkModuleGroup {

    public static final String MODULE_GROUP_NAME = "SPARK-HIVE-LIB-EMR_5_0_0_LATEST"; //$NON-NLS-1$

    public static final String MRREQUIRED_MODULE_GROUP_NAME = "SPARK-HIVE-LIB-MRREQUIRED-EMR_5_0_0_LATEST"; //$NON-NLS-1$

    public static Set<DistributionModuleGroup> getModuleGroups() {
        Set<DistributionModuleGroup> hs = new HashSet<>();
        hs.add(new DistributionModuleGroup(MODULE_GROUP_NAME, true));
        return hs;
    }

}
