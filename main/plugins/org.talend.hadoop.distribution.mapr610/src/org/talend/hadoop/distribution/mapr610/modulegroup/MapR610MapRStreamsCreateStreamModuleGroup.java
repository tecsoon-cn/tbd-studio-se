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
package org.talend.hadoop.distribution.mapr610.modulegroup;

import java.util.HashSet;
import java.util.Set;

import org.talend.hadoop.distribution.ComponentType;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.condition.BooleanOperator;
import org.talend.hadoop.distribution.condition.ComponentCondition;
import org.talend.hadoop.distribution.condition.EqualityOperator;
import org.talend.hadoop.distribution.condition.LinkedNodeExpression;
import org.talend.hadoop.distribution.condition.MultiComponentCondition;
import org.talend.hadoop.distribution.condition.NestedComponentCondition;
import org.talend.hadoop.distribution.condition.SimpleComponentCondition;
import org.talend.hadoop.distribution.mapr610.MapR610Constant;
import org.talend.hadoop.distribution.mapr610.MapR610Distribution;

public class MapR610MapRStreamsCreateStreamModuleGroup {

    public static Set<DistributionModuleGroup> getModuleGroups() {
        @SuppressWarnings("nls")
        final ComponentCondition isCurrentDistribution = new NestedComponentCondition(new MultiComponentCondition(
                new SimpleComponentCondition(new LinkedNodeExpression("CONNECTION",
                        ComponentType.MAPRSTREAMS.getDistributionParameter(), EqualityOperator.EQ,
                        MapR610Distribution.DISTRIBUTION_NAME)), 
                BooleanOperator.AND, 
                new SimpleComponentCondition(new LinkedNodeExpression("CONNECTION",
                        ComponentType.MAPRSTREAMS.getVersionParameter(), EqualityOperator.EQ, MapR610Distribution.VERSION))));

        Set<DistributionModuleGroup> hs = new HashSet<>();
        hs.add(new DistributionModuleGroup(MapR610Constant.MAPRSTREAMS_CREATE_STREAM_MODULE_GROUP.getModuleName(), false,
                isCurrentDistribution));
        return hs;
    }
}
