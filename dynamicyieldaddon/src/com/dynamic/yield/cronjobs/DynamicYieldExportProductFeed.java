package com.dynamic.yield.cronjobs;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import com.dynamic.yield.service.DynamicYieldIntegrationService;
import com.dynamic.yield.valueprovider.DynamicYieldExportValueProvider;
import de.hybris.platform.catalog.enums.ArticleApprovalStatus;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cmsfacades.languages.LanguageFacade;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.util.Config;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class DynamicYieldExportProductFeed extends AbstractJobPerformable<DynamicYieldExportProductFeedCronJobModel> {

    private static final Logger LOG = Logger.getLogger(DynamicYieldExportProductFeed.class);

    @Resource
    private ProductService productService;

    @Resource
    private LanguageFacade languageFacade;

    @Resource
    private CommonI18NService commonI18NService;

    @Resource
    DynamicYieldIntegrationService dynamicYieldIntegrationService;

    @Override
    public PerformResult perform(DynamicYieldExportProductFeedCronJobModel cronJob) {
        return getPerformResult(cronJob);
    }

    private PerformResult getPerformResult(DynamicYieldExportProductFeedCronJobModel cronJob) {
        CatalogVersionModel catalogVersion = cronJob.getProductCatalog();

        if (catalogVersion == null) {
            LOG.error("Dynamic Yield: cronjob has no catalog assigned");
            return returnFail();
        }

        BaseStoreModel baseStore = cronJob.getBaseStore();

        if (baseStore == null) {
            LOG.error("Dynamic Yield: cronjob has no base store assigned");
            return returnFail();
        }

        List<ProductModel> allProductsForCatalogVersion = productService.getAllProductsForCatalogVersion(catalogVersion);

        if (isEmpty(allProductsForCatalogVersion)) {
            LOG.error("Dynamic Yield: no products found for data feed export");
            return returnFail();
        }

        if (isEmpty(cronJob.getExportProperties())) {
            LOG.error("Dynamic Yield: no properties for export found on cronjob");
            return returnFail();
        }

        return createString(allProductsForCatalogVersion, cronJob);

    }

    private PerformResult returnFail() {
        return new PerformResult(CronJobResult.FAILURE, CronJobStatus.FINISHED);
    }

    private PerformResult createString(List<ProductModel> allProductsForCatalogVersion,
                                       DynamicYieldExportProductFeedCronJobModel cronJob) {
        StringBuilder builder = new StringBuilder();
        List<LanguageData> languages = languageFacade.getLanguages();
        languages.sort(Comparator.comparing(LanguageData::getIsocode));
        appendHeader(builder, cronJob, languages);
        int productCount = 0;
        for (ProductModel productModel : allProductsForCatalogVersion) {
            if (builder.length() < 1073740822) {
                if (ArticleApprovalStatus.APPROVED.equals(productModel.getApprovalStatus())) {
                    StringBuffer productRow = createProductRow(productModel, builder, cronJob, languages);
                    if (productRow != null) {
                        builder.append(productRow);
                        endRow(builder);
                        productCount++;
                    }
                }
            } else {
                LOG.warn("Builder limit exceeded! Number of appended products: " + productCount);
                break;
            }
        }

        File csvFile = writeBuilderToCSV(builder);
        if (csvFile == null) {
            LOG.error("CSV file not created!");
            return returnFail();
        }
        DynamicYieldIntegrationModel dynamicYieldIntegrationModel = dynamicYieldIntegrationService.getMainDynamicYieldConfiguration();
        return uploadFileToS3(dynamicYieldIntegrationModel, csvFile);
    }

    private PerformResult uploadFileToS3(DynamicYieldIntegrationModel dynamicYieldIntegrationModel, File csvFile) {
        if (dynamicYieldIntegrationModel != null) {
            AWSCredentials credentials = new BasicAWSCredentials(dynamicYieldIntegrationModel.getAccessKeyID(),
                    dynamicYieldIntegrationModel.getSecretAccessKey());
            boolean useEuropeanScripts = dynamicYieldIntegrationModel.isUseEuropeanScripts();
            try {
                AmazonS3 s3client =
                        AmazonS3ClientBuilder.standard().withCredentials
                                (new AWSStaticCredentialsProvider(credentials)).withRegion(useEuropeanScripts ? Regions.EU_CENTRAL_1 : Regions.US_EAST_1).build();
                String bucket = useEuropeanScripts ? "dy-datafeeds-eu/" : "com.dynamicyield.feeds/";

                PutObjectRequest request = new PutObjectRequest(bucket + dynamicYieldIntegrationModel.getSiteID().toString(),
                        csvFile.getName(), csvFile);
                ObjectMetadata
                        metadata = new ObjectMetadata();
                metadata.setContentType("text/csv");
                request.setMetadata(metadata);
                s3client.putObject(request);
            } catch (AmazonServiceException e) {
                LOG.error("The call was transmitted successfully, but Amazon S3 couldn't process it - error response.");
                e.printStackTrace();
                return returnFail();
            } catch (SdkClientException e) {
                LOG.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3.");
                e.printStackTrace();
                return returnFail();
            }

            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }

        LOG.error("Dynamic Yield Integration item is non-existent.");
        return returnFail();
    }

    private File writeBuilderToCSV(StringBuilder builder) {
        String path = Config.getString("dynamicyieldintegration.export.basefolder", "");
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(path + "/productfeed.csv");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(builder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return file;
    }

    private void appendHeader(StringBuilder builder, DynamicYieldExportProductFeedCronJobModel cronJob,
                              List<LanguageData> languages) {
        Set<DynamicYieldExportPropertyModel> properties = cronJob.getExportProperties();
        for (DynamicYieldExportPropertyModel property : properties) {
            if (property.isActive()) {
                appendPropertyName(property, builder, languages);
            }
        }
        endRow(builder);
    }

    private StringBuffer createProductRow(ProductModel productModel, StringBuilder builder, DynamicYieldExportProductFeedCronJobModel cronJob, List<LanguageData> languages) {
        Set<DynamicYieldExportPropertyModel> properties = cronJob.getExportProperties();
        StringBuffer productRow = new StringBuffer();
        for (DynamicYieldExportPropertyModel property : properties) {
            if (property.isActive()) {
                String propertyName = property.getDynamicYieldProperty();

                if (isBlank(propertyName)) {
                    LOG.warn("Dynamic Yield: no properties for export found on cronjob");
                    return null;
                }

                String value = getValue(property, productModel, null);
                if (StringUtils.isBlank(value) && property.isMandatory()) {
                    LOG.warn("PRODUCT NOT EXPORTED! Product code: " + productModel.getCode() + ", empty attribute: " + propertyName);
                    return null;
                }
                appendValue(value, productRow);

                if (property.isMultilanguage()) {
                    for (LanguageData language : languages) {
                        if (language.isActive()) {
                            String multiLanguageValue = getValue(property, productModel, language.getIsocode());
                            appendValue(multiLanguageValue, productRow);
                        }
                    }
                }
            }
        }
        return productRow;
    }

    private void appendPropertyName(DynamicYieldExportPropertyModel property, StringBuilder builder,
                                    List<LanguageData> languages) {
        String propertyName = property.getDynamicYieldProperty();

        if (isBlank(propertyName)) {
            LOG.warn("Dynamic Yield: no properties for export found on cronjob");
        }

        builder.append("\"");
        builder.append(propertyName);
        builder.append("\",");

        if (property.isMultilanguage()) {
            for (LanguageData language : languages) {
                if (language.isActive()) {
                    builder.append("\"lng:");
                    builder.append(language.getIsocode()).append(":");
                    builder.append(propertyName);
                    builder.append("\",");
                }
            }
        }

    }

    private void appendValue(String value, StringBuffer buffer) {
        buffer.append(value);
        buffer.append(",");
    }

    private String getValue(DynamicYieldExportPropertyModel property, ProductModel product, String isocode) {
        if (isNotBlank(property.getBeanValueProvider())) {
            Optional<DynamicYieldExportValueProvider> valueProvider = getBean(property);

            if (valueProvider.isPresent()) {
                return valueProvider.get().getValue(product, property, isocode);
            }
        }

        return getStringValue(property.getProductProperty(), product, isocode);
    }

    private Optional<DynamicYieldExportValueProvider> getBean(DynamicYieldExportPropertyModel property) {
        try {
            return Optional.of(Registry.getCoreApplicationContext().getBean(property.getBeanValueProvider(), DynamicYieldExportValueProvider.class));
        } catch (BeansException ex) {
            LOG.error("Dynamic Yield: Value Provider could not be found for bean id: " + property.getBeanValueProvider());
        }

        return Optional.empty();
    }

    private String getStringValue(String property, ProductModel product, String isocode) {
        String attributeValue = "";
        if (isocode != null) {
            attributeValue = modelService.getAttributeValue(product, property,
                    commonI18NService.getLocaleForIsoCode(isocode));
        } else {
            attributeValue = modelService.getAttributeValue(product, property);
        }

        return attributeValue != null ? "\"" + attributeValue + "\"" : "";
    }

    private void endRow(StringBuilder builder) {
        builder.setLength(builder.length() - 1);
        builder.append("\n");
    }

}
