package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.europe1.model.PriceRowModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;

public class DynamicYieldExportPriceValueProvider implements DynamicYieldExportValueProvider {

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {

        Collection<PriceRowModel> prices = product.getEurope1Prices();
        if (CollectionUtils.isNotEmpty(prices)) {
            Double priceValue = prices.iterator().next().getPrice();
            if (priceValue != null) {
                return Double.toString(priceValue);
            }
        }

        return "";
    }
}
