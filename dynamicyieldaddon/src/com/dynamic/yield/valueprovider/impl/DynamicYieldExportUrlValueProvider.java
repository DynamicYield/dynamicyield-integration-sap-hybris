package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;

import javax.annotation.Resource;

public class DynamicYieldExportUrlValueProvider implements DynamicYieldExportValueProvider {

    @Resource(name = "productModelUrlResolver")
    private UrlResolver<ProductModel> productModelUrlResolver;

    @Resource
    private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {
        DynamicYieldExportProductFeedCronJobModel exportFeedCronjob = property.getExportCronJob();
        if (exportFeedCronjob != null) {
            BaseSiteModel baseSite = exportFeedCronjob.getBaseSite();
            if (baseSite != null) {
                String productUrl = siteBaseUrlResolutionService.getWebsiteUrlForSite(baseSite, true, productModelUrlResolver.resolve(product));
                return "\"" + productUrl + "\"";
            }
        }
        return "";
    }
}
