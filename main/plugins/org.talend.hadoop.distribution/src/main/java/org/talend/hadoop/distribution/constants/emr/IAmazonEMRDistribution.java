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
package org.talend.hadoop.distribution.constants.emr;

@SuppressWarnings("nls")
public interface IAmazonEMRDistribution {

    static final String DISTRIBUTION_NAME = "AMAZON_EMR";

    static final String DISTRIBUTION_DISPLAY_NAME = "Amazon EMR";

    public boolean doSupportEMRFS();
    
    public String getVersion();
}