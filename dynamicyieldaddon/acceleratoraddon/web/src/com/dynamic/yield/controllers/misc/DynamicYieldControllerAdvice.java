package com.dynamic.yield.controllers.misc;

import com.dynamic.yield.data.DynamicYieldIntegrationData;
import com.dynamic.yield.facade.DynamicYieldIntegrationFacade;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.commercefacades.storesession.data.LanguageData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.Resource;

@ControllerAdvice(assignableTypes = AbstractPageController.class)
public class DynamicYieldControllerAdvice {

    @Resource(name = "dynamicYieldIntegrationFacade")
    private DynamicYieldIntegrationFacade dynamicYieldIntegrationFacade;

    @Resource(name = "storeSessionFacade")
    private StoreSessionFacade storeSessionFacade;

    @Resource(name = "cartFacade")
    private CartFacade cartFacade;

    @Resource(name = "customerFacade")
    private CustomerFacade customerFacade;

    @ModelAttribute("dynamicYieldIntegrationData")
    public DynamicYieldIntegrationData getDynamicYieldIntegrationData() {
        return dynamicYieldIntegrationFacade.getMainDynamicYieldConfiguration();
    }

    @ModelAttribute("defaultLanguage")
    public LanguageData getDefaultLanguage() {
        return storeSessionFacade.getDefaultLanguage();
    }

    @ModelAttribute("cartData")
    public CartData getCartData() {
        return cartFacade.getSessionCart();
    }

    @ModelAttribute("hashedUserEmail")
    public String getHashedUserEmail() {
        final CustomerData user = customerFacade.getCurrentCustomer();
        if (user != null && user.getDisplayUid() != null) {
             return DigestUtils.sha256Hex(user.getDisplayUid().toLowerCase());
        }
        return "";
    }
}
