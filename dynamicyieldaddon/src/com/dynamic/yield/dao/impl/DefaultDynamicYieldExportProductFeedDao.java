package com.dynamic.yield.dao.impl;

import com.dynamic.yield.dao.DynamicYieldExportProductFeedDao;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

public class DefaultDynamicYieldExportProductFeedDao extends DefaultGenericDao<DynamicYieldExportProductFeedCronJobModel>
        implements DynamicYieldExportProductFeedDao {

    public DefaultDynamicYieldExportProductFeedDao() {
        super(DynamicYieldExportProductFeedCronJobModel._TYPECODE);
    }

    @Override
    public DynamicYieldExportProductFeedCronJobModel getProductFeedExportJob() {
        final List<DynamicYieldExportProductFeedCronJobModel> dynamicYieldExportProductFeedCronJobModels = this.find();
        return dynamicYieldExportProductFeedCronJobModels.isEmpty() ? null : dynamicYieldExportProductFeedCronJobModels.get(0);
    }
}
