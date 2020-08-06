package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.commerceservices.stock.CommerceStockService;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Resource;

public class DynamicYieldExportInStockValueProvider implements DynamicYieldExportValueProvider {

    @Resource
    CommerceStockService commerceStockService;

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {

        final Long stockLevel = commerceStockService.getStockLevelForProductAndBaseStore(product,
                property.getExportCronJob().getBaseStore());
        return stockLevel != null ? String.valueOf(stockLevel > 0).toLowerCase() : String.valueOf(false).toLowerCase();
    }
}
