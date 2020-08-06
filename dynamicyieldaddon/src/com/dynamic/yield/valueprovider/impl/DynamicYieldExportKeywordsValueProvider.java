package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.catalog.model.KeywordModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class DynamicYieldExportKeywordsValueProvider implements DynamicYieldExportValueProvider {

    @Resource
    private CommonI18NService commonI18NService;

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {

        List<KeywordModel> keywords;
        if (isocode != null) {
            keywords = product.getKeywords(commonI18NService.getLocaleForIsoCode(isocode));
        } else {
            keywords = product.getKeywords();
        }
        if (CollectionUtils.isNotEmpty(keywords)) {
            StringBuilder keywordsText = new StringBuilder("\"");
            for (KeywordModel keyword : keywords) {
                keywordsText.append(keyword.getKeyword());
                keywordsText.append("|");
            }
            keywordsText.setLength(keywordsText.length() - 1);
            keywordsText.append("\"");
            return keywordsText.toString();
        }

        return "\"\"";
    }
}
