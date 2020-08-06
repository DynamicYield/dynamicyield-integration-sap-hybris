package com.dynamic.yield.valueprovider;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import de.hybris.platform.core.model.product.ProductModel;

public interface DynamicYieldExportValueProvider {

    String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode);
}
