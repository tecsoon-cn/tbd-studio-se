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
package org.talend.hadoop.distribution.mapr611.modulegroup;

import java.util.HashSet;
import java.util.Set;

import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.mapr611.MapR611Constant;

public class MapR611HBaseModuleGroup {

    public static Set<DistributionModuleGroup> getModuleGroups() {
        Set<DistributionModuleGroup> hs = new HashSet<>();
        // The DistributionModuleGroup is mrrequired for the M/R components. It's not used for the DI components.
        DistributionModuleGroup dmg = new DistributionModuleGroup(MapR611Constant.HBASE_MODULE_GROUP.getModuleName(), true);
        hs.add(dmg);
        return hs;
    }

}
