package com.dynamic.yield.populators;

import com.dynamic.yield.data.DynamicYieldIntegrationData;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DynamicYieldIntegrationPopulator implements Populator<DynamicYieldIntegrationModel, DynamicYieldIntegrationData> {

    @Override
    public void populate(DynamicYieldIntegrationModel source, DynamicYieldIntegrationData target)
            throws ConversionException {
        if (source != null) {
            target.setSiteID(source.getSiteID().toString());
            target.setAccessKeyID(source.getAccessKeyID());
            target.setSecretAccessKey(source.getSecretAccessKey());
            target.setSyncRate(source.getSyncRate() != null ? source.getSyncRate().getCode() : null);
            target.setCdnLocation(source.getCdnLocation().getCode());
            target.setCdnLocationCustomURL(source.getCdnLocationCustomURL());
            target.setProductFeedUploadLocation(source.getProductFeedUploadLocation().getCode());
            target.setProductFeedUploadLocationCustomURL(source.getProductFeedUploadLocationCustomURL());
            target.setUseEuropeanScripts(source.isUseEuropeanScripts());
        }
    }
}
