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
package org.talend.hadoop.distribution.test.hive;

import org.junit.Test;
import org.talend.hadoop.distribution.constants.mapr.IMapRDistribution;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class HiveMetadataHelper4MapRTest extends AbstractDistributionTest4HiveMetadataHelper {

    private static final String[] VERSIONS_NON_DYNAMIC = new String[] {
            "MapR 6.1.0 - MEP 6.1.1 (YARN mode)", "MapR 6.1.0(YARN mode)",
        "MapR 6.0.1(YARN mode)",
            "MapR 6.0.0(YARN mode)",
        "MapR 5.2.0 (Deprecated)",
        "MapR 5.1.0 (Deprecated)",
        "MapR 5.0.0 (Deprecated)"
       };

    @Override
    protected String getDistribution() {
        return IMapRDistribution.DISTRIBUTION_NAME;
    }

    @Override
    protected String getDistributionDisplay() {
        return IMapRDistribution.DISTRIBUTION_DISPLAY_NAME;
    }

    @Override
    protected String[] getDistributionVersionsDisplay() {
        return VERSIONS_NON_DYNAMIC;
    }

    //this empty test is here to make tycho runner not failed on initialization
    //because it does not found any test in the final class
    @Test
    public void emptyTest() {
    }    
}
