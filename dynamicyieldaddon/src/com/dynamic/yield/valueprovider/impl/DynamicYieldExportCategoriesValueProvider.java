package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.variants.model.VariantProductModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;

public class DynamicYieldExportCategoriesValueProvider implements DynamicYieldExportValueProvider {

    @Resource
    private CommonI18NService commonI18NService;

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {
        Collection<CategoryModel> productCategories = product.getSupercategories();
        if (CollectionUtils.isEmpty(productCategories) && product instanceof VariantProductModel) {
            final ProductModel baseProduct = ((VariantProductModel) product).getBaseProduct();
            if (baseProduct != null) {
                productCategories = baseProduct.getSupercategories();
            }
        }
        if (CollectionUtils.isNotEmpty(productCategories)) {
            StringBuilder categoriesText = new StringBuilder("\"");
            for (CategoryModel category : productCategories) {
                String categoryName = "";
                if (isocode != null) {
                    categoryName = category.getName(commonI18NService.getLocaleForIsoCode(isocode));
                } else {
                    categoryName = category.getName();
                }
                if (StringUtils.isNotBlank(categoryName)) {
                    categoriesText.append(categoryName);
                    categoriesText.append("|");
                }
            }
            if (categoriesText.length() > 1) {
                categoriesText.setLength(categoriesText.length() - 1);
                categoriesText.append("\"");
                return categoriesText.toString();
            }
        }

        return "";
    }
}
