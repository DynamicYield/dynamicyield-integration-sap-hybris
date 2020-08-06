package com.dynamic.yield.service;

import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;

public interface DynamicYieldIntegrationService {

    DynamicYieldIntegrationModel getMainDynamicYieldConfiguration();

    DynamicYieldExportProductFeedCronJobModel getProductFeedExportJob();
}
