package com.dynamic.yield.dao;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;

public interface DynamicYieldExportProductFeedDao {
    DynamicYieldExportProductFeedCronJobModel getProductFeedExportJob();
}
