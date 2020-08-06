package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

public class DynamicYieldExportGroupIdValueProvider implements DynamicYieldExportValueProvider {

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {
        if (product instanceof VariantProductModel) {
            final ProductModel baseProduct = ((VariantProductModel) product).getBaseProduct();
            if (baseProduct != null) {
                return "\"" + baseProduct.getCode() + "\"";
            }
        }
        return "\"" + product.getCode() + "\"";
    }
}
