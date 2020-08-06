package com.dynamic.yield.service.impl;

import com.dynamic.yield.dao.DynamicYieldExportProductFeedDao;
import com.dynamic.yield.dao.DynamicYieldIntegrationDao;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.service.DynamicYieldIntegrationService;

import javax.annotation.Resource;

public class DefaultDynamicYieldIntegrationService implements DynamicYieldIntegrationService {

    @Resource
    private DynamicYieldIntegrationDao dynamicYieldIntegrationDao;

    @Resource
    private DynamicYieldExportProductFeedDao dynamicYieldExportProductFeedDao;

    @Override
    public DynamicYieldIntegrationModel getMainDynamicYieldConfiguration() {
        return dynamicYieldIntegrationDao.getMainDynamicYieldConfiguration();
    }
    @Override
    public DynamicYieldExportProductFeedCronJobModel getProductFeedExportJob() {
        return dynamicYieldExportProductFeedDao.getProductFeedExportJob();
    }
}
