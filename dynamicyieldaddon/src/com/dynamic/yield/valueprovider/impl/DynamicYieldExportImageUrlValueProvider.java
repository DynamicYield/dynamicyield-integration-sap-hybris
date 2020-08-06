package com.dynamic.yield.valueprovider.impl;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;

public class DynamicYieldExportImageUrlValueProvider implements DynamicYieldExportValueProvider {

    @Resource
    private SiteBaseUrlResolutionService siteBaseUrlResolutionService;

    @Override
    public String getValue(ProductModel product, DynamicYieldExportPropertyModel property, String isocode) {
        DynamicYieldExportProductFeedCronJobModel exportFeedCronjob = property.getExportCronJob();
        if (exportFeedCronjob != null) {
            BaseSiteModel baseSite = exportFeedCronjob.getBaseSite();
            if (baseSite != null) {
                String websiteUrl = siteBaseUrlResolutionService.getWebsiteUrlForSite(baseSite, true, "");
                try {
                    String urlWithoutParameters = getUrlWithoutParameters(websiteUrl);
                    MediaModel picture = product.getPicture();
                    if (product instanceof VariantProductModel && picture == null) {
                        final ProductModel baseProduct = ((VariantProductModel) product).getBaseProduct();
                        if (baseProduct != null) {
                            picture = baseProduct.getPicture();
                        }
                    }
                    if (picture != null && picture.getURL() != null) {
                        return "\"" + urlWithoutParameters + picture.getURL() + "\"";
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    private String getUrlWithoutParameters(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return new URI(uri.getScheme(),
                uri.getAuthority(),
                uri.getPath(),
                null, // Ignore the query part of the input url
                uri.getFragment()).toString();
    }
}
