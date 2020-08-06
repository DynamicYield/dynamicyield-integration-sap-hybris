package com.dynamic.yield.interceptors;

import com.dynamic.yield.enums.SyncRateOptions;
import com.dynamic.yield.model.DynamicYieldIntegrationModel;
import com.dynamic.yield.model.cronjobs.DynamicYieldExportProductFeedCronJobModel;
import com.dynamic.yield.service.DynamicYieldIntegrationService;
import de.hybris.platform.cronjob.model.TriggerModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

public class DynamicYieldIntegrationPrepareInterceptor implements PrepareInterceptor {

    @Resource
    private DynamicYieldIntegrationService dynamicYieldIntegrationService;

    @Resource
    private ModelService modelService;

    @Override
    public void onPrepare(Object model, InterceptorContext interceptorContext) throws InterceptorException {
        if (model instanceof DynamicYieldIntegrationModel && interceptorContext.getDirtyAttributes(model).containsKey("syncRate")) {
            final DynamicYieldIntegrationModel dynamicYieldIntegrationModel = (DynamicYieldIntegrationModel) model;
            SyncRateOptions syncRate = dynamicYieldIntegrationModel.getSyncRate();
            DynamicYieldExportProductFeedCronJobModel dynamicYieldExportProductFeedCronJobModel
                    = dynamicYieldIntegrationService.getProductFeedExportJob();
            if (dynamicYieldExportProductFeedCronJobModel == null) {
                throw new InterceptorException("Product Feed Export job is missing!");
            }
            modifyTriggers(dynamicYieldExportProductFeedCronJobModel, syncRate);
        }
    }

    private void modifyTriggers(DynamicYieldExportProductFeedCronJobModel dynamicYieldExportProductFeedCronJobModel,
                                SyncRateOptions syncRate) {
        List<TriggerModel> triggers = dynamicYieldExportProductFeedCronJobModel.getTriggers();
        if (CollectionUtils.isNotEmpty(triggers)) {
            for (TriggerModel trigger : triggers) {
                if (syncRate == null) {
                    trigger.setActive(false);
                    modelService.save(trigger);
                } else {
                    modifyTrigger(trigger, syncRate, SyncRateOptions.ONE_DAY, 24);
                    modifyTrigger(trigger, syncRate, SyncRateOptions.TWELVE_HOURS, 12);
                    modifyTrigger(trigger, syncRate, SyncRateOptions.SIX_HOURS, 6);
                }
            }
        }
    }

    private void modifyTrigger(TriggerModel trigger, SyncRateOptions syncRate,
                               SyncRateOptions syncRateOption, int hours) {
        if (syncRateOption.equals(syncRate)) {
            trigger.setActive(trigger.getHour() == hours);
            modelService.save(trigger);
        }
    }
}
