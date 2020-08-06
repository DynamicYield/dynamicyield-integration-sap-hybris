package com.dynamic.yield.cronjobs;

import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportPropertyModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static de.hybris.platform.cronjob.enums.CronJobResult.SUCCESS;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DynamicYieldExportProductFeedTest {

    private static final String NO_VALUE_PROVIDER = "";
    private static final String SKU = "sku";
    private static final String CODE = "code";
    private static final String CODE_VALUE = "1234";

    @Mock
    private ProductService productService;

    @Mock
    private CatalogVersionModel catalogVersionModel;

    @Mock
    private ProductModel product;

    @Mock
    private ModelService modelService;

    @Mock
    private DynamicYieldExportPropertyModel dynamicYieldExportPropertyModel;

    @InjectMocks
    private DynamicYieldExportProductFeed dynamicYieldExportProductFeed;

    @Test
    public void createExportText() {
        DynamicYieldExportProductFeedCronJobModel cronjob = new DynamicYieldExportProductFeedCronJobModel();
        Set<DynamicYieldExportPropertyModel> properties = new HashSet<>(1);
        properties.add(dynamicYieldExportPropertyModel);
        cronjob.setExportProperties(properties);
        cronjob.setProductCatalog(catalogVersionModel);

        when(productService.getAllProductsForCatalogVersion(catalogVersionModel)).thenReturn(singletonList(product));
        when(dynamicYieldExportPropertyModel.getBeanValueProvider()).thenReturn(NO_VALUE_PROVIDER);
        when(dynamicYieldExportPropertyModel.getDynamicYieldProperty()).thenReturn(SKU);
        when(dynamicYieldExportPropertyModel.getProductProperty()).thenReturn(CODE);
        when(modelService.getAttributeValue(product, CODE)).thenReturn(CODE_VALUE);
        PerformResult perform = dynamicYieldExportProductFeed.perform(cronjob);

        assertThat(perform.getResult(), is(SUCCESS));
    }

}
