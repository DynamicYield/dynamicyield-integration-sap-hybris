package com.dynamic.yield.facade.impl;

import com.dynamic.yield.data.DynamicYieldIntegrationData;
import com.dynamic.yield.facade.DynamicYieldIntegrationFacade;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import com.dynamic.yield.service.DynamicYieldIntegrationService;
import de.hybris.platform.converters.impl.AbstractPopulatingConverter;

import javax.annotation.Resource;

public class DefaultDynamicYieldIntegrationFacade implements DynamicYieldIntegrationFacade {

    @Resource
    private DynamicYieldIntegrationService dynamicYieldIntegrationService;

    @Resource
    private AbstractPopulatingConverter<DynamicYieldIntegrationModel, DynamicYieldIntegrationData> dynamicYieldIntegrationConverter;


    @Override
    public DynamicYieldIntegrationData getMainDynamicYieldConfiguration() {
        DynamicYieldIntegrationModel dynamicYieldIntegrationModel =
                dynamicYieldIntegrationService.getMainDynamicYieldConfiguration();

        return dynamicYieldIntegrationConverter.convert(dynamicYieldIntegrationModel);
    }
}
