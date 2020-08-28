dynamicyieldaddon = {

    newAddedProducts: [],

    setCookie: function (name, value, days) {
        var expires = "";
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toUTCString();
        }
        document.cookie = name + "=" + (value || "") + expires + "; path=/";
    },

    getCookie: function (name) {
        var nameEQ = name + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) === ' ') c = c.substring(1, c.length);
            if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
        }
        return null;
    },

    eraseCookie: function (name) {
        document.cookie = name + '=; Max-Age=-99999999;';
    },

    bindPurchaseEvent: function () {
        var placeOrderForm = $('.place-order-form');
        if (placeOrderForm.length > 0) {
            dynamicyieldaddon.setCookie("isPurchaseEvent", "0", 1);
            for (var i = 0; i < placeOrderForm.length; i++) {
                var formElement = placeOrderForm[i];
                if ($(formElement).prop('nodeName') === 'DIV') {
                    var innerForm = $(formElement).find('form');
                    if (innerForm.length > 0) {
                        $(innerForm).submit(function () {
                            dynamicyieldaddon.setCookie("isPurchaseEvent", "1", 1);
                        });
                    }
                }
                if ($(formElement).prop('nodeName') === 'FORM') {
                    $(formElement).submit(function () {
                        dynamicyieldaddon.setCookie("isPurchaseEvent", "1", 1);
                    });
                }
            }
        }

        var orderConfirmationPage = $('.page-orderConfirmationPage');
        if (orderConfirmationPage.length > 0 && dynamicyieldaddon.getCookie("isPurchaseEvent") === "1") {
            dynamicyieldaddon.setCookie("isPurchaseEvent", "0", 1);
            dynamicyieldaddon.triggerPurchaseEvent();
        }
    },

    triggerPurchaseEvent: function () {
        var orderCode = dynamicyieldpurchase.event.orderCode;
        var orderPrice = dynamicyieldpurchase.event.orderPrice;
        var orderCurrency = dynamicyieldpurchase.event.orderCurrency;
        var entriesCount = dynamicyieldpurchase.event.entriesCount;
        var productCodes = dynamicyieldpurchase.event.productCodes;
        var productQuantities = dynamicyieldpurchase.event.productQuantities;
        var productPrices = dynamicyieldpurchase.event.productPrices;
        var products = [];
        for (var i = 0; i < entriesCount; i++) {
            var product = {productId: productCodes[i], quantity: productQuantities[i], itemPrice: productPrices[i]}
            products.push(product);
        }

        DY.API('event', {
            name: 'Purchase',
            properties: {
                dyType: 'purchase-v1',
                uniqueTransactionId: orderCode,
                value: orderPrice,
                currency: orderCurrency,
                cart: products
            }
        });
    },

    bindAddToCartEvent: function () {
        dynamicyieldaddon.bindAddToCartForm();
        var referenceItem = $('.js-reference-item');
        if (referenceItem.length > 0) {
            $(referenceItem).click(function () {
                setTimeout(dynamicyieldaddon.bindAddToCartForm, 1000);
            });
        }
    },

    bindAddToCartForm: function () {
        var addToCartForm = $('.add_to_cart_form');
        if (addToCartForm.length > 0) {
            $(addToCartForm).submit(function () {
                var formId = $(this).attr('id');
                dynamicyieldaddon.triggerAddToCartEvent(formId);
            });
        }
    },

    triggerAddToCartEvent: function (formId) {
        if (formId !== 'addToCartForm') {
            var formElement = $('#' + formId);
            var productCurrency = $('main').attr('data-currency-iso-code');
            if (formId === "command") {
                var linkValue = $('#cboxLoadedContent .product-details .name').find('a').attr('href')
                var productCode = linkValue.substring(linkValue.lastIndexOf('/') + 1);
                var productQuantity = parseInt($('#cboxLoadedContent .js-qty-selector-input').val());
                var productPrice = parseFloat($('#cboxLoadedContent .price').text().replace(/\s/g, '').replace(/[^0-9.-]+/g, ""));
                var entryPrice = productPrice * productQuantity;
            } else {
                var productCode = $(formElement).find('input[name ="productCodePost"]').val();
                var entryPrice, productPrice;
                entryPrice = productPrice = parseFloat($(formElement).find('input[name ="productPostPrice"]').val());
                var productQuantity = 1;
            }
        } else {
            var productPrice = dynamicyieldaddtocart.event.productPrice;
            var productQuantity = parseInt($('.js-qty-selector-input').val());
            var entryPrice = productPrice * productQuantity;
            var productCurrency = dynamicyieldaddtocart.event.productCurrency;
            var productCode = dynamicyieldaddtocart.event.productCode;
        }

        var newProduct = {productCode: productCode, productPrice: productPrice, productQuantity: productQuantity};

        var newAddedProducts = dynamicyieldaddon.newAddedProducts;

        let containsProduct = 0;

        for (let i = 0; i < newAddedProducts.length; i++) {
            let newAddedProduct = newAddedProducts[i];
            let quantity = newAddedProduct.productQuantity;
            if (newAddedProduct.productCode === productCode) {
                newAddedProduct.productQuantity = newAddedProduct.productQuantity + quantity;
                containsProduct = 1;
            }
        }
        if (containsProduct === 0) {
            newAddedProducts.push(newProduct);
        }

        var products = dynamicyieldaddon.getAddToCartProducts(newAddedProducts);

        dynamicyieldaddon.setCookie("addToCartEventCounter", 0, 1);

        var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();

        DY.API('event', {
            name: 'Add to Cart',
            properties: {
                dyType: 'add-to-cart-v1',
                uniqueRequestId: uniqueRequestId,
                value: entryPrice,
                currency: productCurrency,
                productId: productCode,
                quantity: productQuantity,
                cart: products
            }
        });
    },

    bindSignupEvent: function () {
        var registerForm = $('#registerForm');
        if (registerForm.length > 0) {
            dynamicyieldaddon.setCookie("isSignupEvent", "0", 1);
            $(registerForm).submit(function () {
                dynamicyieldaddon.setCookie("isSignupEvent", "1", 1);
            });
        }

        dynamicyieldaddon.triggerSignupEvent();
    },

    triggerSignupEvent: function () {
        if (dynamicyieldaddon.getCookie("isSignupEvent") === "1" && dynamicyieldsignup.event.hashedUserEmail != "") {
            dynamicyieldaddon.setCookie("isSignupEvent", "0", 1);
            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Signup',
                properties: {
                    dyType: 'signup-v1',
                    uniqueRequestId: uniqueRequestId,
                    hashedEmail: dynamicyieldsignup.event.hashedUserEmail
                }
            });
        }
    },

    bindLoginEvent: function () {
        var loginForm = $('#loginForm');
        if (loginForm.length > 0) {
            dynamicyieldaddon.setCookie("isLoginEvent", "0", 1);
            $(loginForm).submit(function () {
                dynamicyieldaddon.setCookie("isLoginEvent", "1", 1);
            });
        }

        dynamicyieldaddon.triggerLoginEvent();
    },

    triggerLoginEvent: function () {
        if (dynamicyieldaddon.getCookie("isLoginEvent") === "1" && dynamicyieldsignup.event.hashedUserEmail != "") {
            dynamicyieldaddon.setCookie("isLoginEvent", "0", 1);
            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Login',
                properties: {
                    dyType: 'login-v1',
                    uniqueRequestId: uniqueRequestId,
                    hashedEmail: dynamicyieldsignup.event.hashedUserEmail
                }
            });
        }
    },

    bindNewsletterSubscriptionEvent: function () {
        var subscriptionButton = $('label[for="MARKETING_NEWSLETTER"] .toggle-button__switch');
        if (subscriptionButton.length > 0 && !subscriptionButton.hasClass('is-checked')) {
            $(subscriptionButton).click(function () {
                dynamicyieldaddon.triggerNewsletterSubscriptionEvent();
            });
        }
    },

    triggerNewsletterSubscriptionEvent: function () {
        var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
        DY.API('event', {
            name: 'Newsletter Subscription',
            properties: {
                dyType: 'newsletter-subscription-v1',
                uniqueRequestId: uniqueRequestId,
                hashedEmail: dynamicyieldsignup.event.hashedUserEmail
            }
        });
    },

    bindRemoveFromCartEvent: function () {
        var cartEntryActionElement = $('.js-execute-entry-action-button');
        if (cartEntryActionElement.length > 0) {
            $(cartEntryActionElement).on("click", function () {
                dynamicyieldaddon.triggerRemoveFromCartEvent($(this));
            });
        }
    },

    triggerRemoveFromCartEvent: function (formElement) {
        var entryProductCode = $(formElement).data("entryProductCode");
        var entryInitialQuantity = $(formElement).data("entryInitialQuantity");
        var entryAction = $(formElement).data("entryAction");
        var productCurrency = $('main').attr('data-currency-iso-code');
        if (entryAction == 'REMOVE') {
            var products = [];
            var entryPrice;

            var entriesCount = dynamicyieldaddtocart.event.entriesCount;
            if (entriesCount > 0) {
                var productCodes = dynamicyieldaddtocart.event.productCodes;
                var productQuantities = dynamicyieldaddtocart.event.productQuantities;
                var productPrices = dynamicyieldaddtocart.event.productPrices;

                for (var i = 0; i < entriesCount; i++) {
                    if (productCodes[i] == entryProductCode) {
                        entryPrice = parseFloat((productPrices[i] * entryInitialQuantity).toFixed(2));
                    } else {
                        var product = {
                            productId: productCodes[i],
                            quantity: productQuantities[i],
                            itemPrice: productPrices[i]
                        }
                        products.push(product);
                    }
                }
            }

            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Remove from Cart',
                properties: {
                    dyType: 'remove-from-cart-v1',
                    uniqueRequestId: uniqueRequestId,
                    value: entryPrice,
                    currency: productCurrency,
                    productId: entryProductCode,
                    quantity: entryInitialQuantity,
                    cart: products
                }
            });
        }
    },

    bindSyncCartContentEvent: function () {
        if (!sessionStorage.newSession) {
            dynamicyieldaddon.triggerSyncCartContentEvent();
            sessionStorage.newSession = 1;
        }
    },

    triggerSyncCartContentEvent: function () {
        var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
        var products = dynamicyieldaddon.getProductsFromCart();
        DY.API('event', {
            name: 'Sync cart',
            properties: {
                dyType: 'sync-cart-v1',
                uniqueRequestId: uniqueRequestId,
                cart: products
            }
        });
    },

    bindSortItemsEvent: function () {
        var sortItemsForms = $('.sort-refine-bar #sortForm1, .sort-refine-bar #sortForm2');
        if (sortItemsForms.length > 0) {
            $(sortItemsForms).change(function () {
                dynamicyieldaddon.triggerSortItemsEvent($(this));
            });
        }
    },

    triggerSortItemsEvent: function (formElement) {
        var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
        var selectedOption = $(formElement).find('option:selected');
        var sortBy = $(selectedOption).val();
        var sortOrder = "DESC"
        if (sortBy.includes("asc")) {
            sortOrder = "ASC";
        }
        DY.API('event', {
            name: 'Sort Items',
            properties: {
                dyType: 'sort-items-v1',
                uniqueRequestId: uniqueRequestId,
                sortBy: sortBy,
                sortOrder: sortOrder
            }
        });
    },

    bindFilterItemsEvent: function () {
        var facetCheckboxes = $('.js-product-facet .js-facet-checkbox');
        if (facetCheckboxes.length > 0) {
            $(facetCheckboxes).change(function () {
                dynamicyieldaddon.triggerFilterItemsEvent($(this), null);
            });
        }
        var facetLinks = $('.facet__text a[href]');
        if (facetLinks.length > 0) {
            $(facetLinks).click(function () {
                dynamicyieldaddon.triggerFilterItemsEvent(null, $(this));
            });
        }
    },

    triggerFilterItemsEvent: function (facetCheckbox, facetLink) {
        var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();

        var facetValue = null;

        if (facetCheckbox === null && facetLink !== null) {
            facetValue = facetLink.text();
            var facetElement = $(facetLink).closest('.js-facet');
        } else {
            var facetElement = $(facetCheckbox).closest('.js-facet');
        }

        var facetIndex = $(facetElement).index();
        var filterType;
        if (facetIndex !== -1) {
            var filterFlag = 0;
            if (dynamicyieldfilteritems.event.facetCodes.length < $('.js-facet').length) {
                filterFlag = 1;
            }
            filterType = dynamicyieldfilteritems.event.facetCodes[facetIndex - filterFlag];
            if (facetValue === null) {
                var fullFacetValue = $(facetCheckbox).parent().find('.facet__list__text').text();
                var countFacetValue = $(facetCheckbox).parent().find('.facet__value__count').text();
                facetValue = fullFacetValue.replace(countFacetValue, '').trim();
            }

            var filterStringValue;
            var filterNumericValue;
            if (dynamicyieldaddon.isNumber(facetValue)) {
                filterNumericValue = parseFloat(facetValue);
            } else {
                filterStringValue = facetValue;
            }
            DY.API('event', {
                name: 'Filter Items',
                properties: {
                    dyType: 'filter-items-v1',
                    uniqueRequestId: uniqueRequestId,
                    filterType: filterType,
                    filterNumericValue: filterNumericValue,
                    filterStringValue: filterStringValue
                }
            });
        }
    },

    bindChangeAttributeEvent: function () {
        var variantSelectorOption = $('.variant-selector li');
        if (variantSelectorOption.length > 0) {
            $(variantSelectorOption).click(function () {
                dynamicyieldaddon.setCookie("attributeChanged", "1", 1);
            });
        }

        var variantSelectBoxes = $('.variant-select');
        if (variantSelectBoxes.length > 0) {
            $(variantSelectBoxes).change(function () {
                dynamicyieldaddon.setCookie("attributeChanged", "1", 1);
            });
        }

        if (dynamicyieldaddon.getCookie("attributeChanged") === "1") {
            dynamicyieldaddon.triggerChangeAttributeEvent();
        }
    },

    triggerChangeAttributeEvent: function () {
        dynamicyieldaddon.setCookie("attributeChanged", "0", 1);
        var selectedCodes = dynamicyieldchangeattribute.event.selectedCodes;
        var selectedValues = dynamicyieldchangeattribute.event.selectedValues;
        if (selectedCodes.length > 0 && selectedValues.length > 0) {
            var currentTypeCode = selectedCodes[0];
            var currentTypeValue = selectedValues[0];
            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Change Attribute',
                properties: {
                    dyType: 'change-attr-v1',
                    uniqueRequestId: uniqueRequestId,
                    attributeType: currentTypeCode,
                    attributeValue: currentTypeValue
                }
            });
        }
    },

    bindPromoCodeEnteredEvent: function () {
        var applyVoucherForm = $('#applyVoucherForm');
        if (applyVoucherForm.length > 0) {
            var voucherTextElement = $('#js-voucher-code-text');
            if (voucherTextElement.length > 0) {
                $(applyVoucherForm).submit(function () {
                    dynamicyieldaddon.setCookie("promoCode", voucherTextElement.val(), 1);
                });
            }

            if (dynamicyieldaddon.getCookie("promoCode") !== null && dynamicyieldaddon.getCookie("promoCode") !== "") {
                dynamicyieldaddon.triggerPromoCodeEnteredEvent();
            }
        }
    },

    triggerPromoCodeEnteredEvent: function () {
        var promoCode = dynamicyieldaddon.getCookie("promoCode");
        dynamicyieldaddon.setCookie("promoCode", "", 1);
        var validationContainer = $('.js-voucher-validation-container');
        if (validationContainer.length > 0 && !$(validationContainer).parent().hasClass('has-error')) {
            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Promo Code Entered',
                properties: {
                    dyType: 'enter-promo-code-v1',
                    uniqueRequestId: uniqueRequestId,
                    code: promoCode
                }
            });
        }
    },

    bindSearchEvent: function () {
        var searchForm = $('form[name="search_form_SearchBox"]');
        if (searchForm.length > 0) {
            $(searchForm).submit(function () {
                dynamicyieldaddon.triggerSearchEvent();
            });
        }
    },

    triggerSearchEvent: function () {
        var searchTextElement = $('#js-site-search-input');
        if (searchTextElement.length > 0) {
            var searchText = searchTextElement.val();
            var uniqueRequestId = dynamicyieldaddon.generateUniqueRequestId();
            DY.API('event', {
                name: 'Keyword Search',
                properties: {
                    dyType: 'keyword-search-v1',
                    uniqueRequestId: uniqueRequestId,
                    keywords: searchText
                }
            });
        }
    },

    generateUniqueRequestId: function () {
        var currentDate = new Date();
        var year = currentDate.getFullYear().toString();
        var month = currentDate.getMonth().toString();
        var day = currentDate.getDate().toString();
        var hour = currentDate.getHours().toString();
        var minute = currentDate.getMinutes().toString();
        var second = currentDate.getSeconds().toString();
        var millisecond = currentDate.getMilliseconds().toString();
        var randomNumber = (Math.floor(Math.random() * 100) + 1).toString();
        return year + month + day + hour + minute + second + millisecond + randomNumber;
    },

    getProductsFromCart: function () {
        var products = [];

        var entriesCount = dynamicyieldaddtocart.event.entriesCount;
        if (entriesCount > 0) {
            var productCodes = dynamicyieldaddtocart.event.productCodes;
            var productQuantities = dynamicyieldaddtocart.event.productQuantities;
            var productPrices = dynamicyieldaddtocart.event.productPrices;

            for (var i = 0; i < entriesCount; i++) {
                var product = {productId: productCodes[i], quantity: productQuantities[i], itemPrice: productPrices[i]}
                products.push(product);
            }
        }

        return products;
    },

    getAddToCartProducts: function (newAddedProducts) {
        var products = [];

        const newAddedProductsSize = newAddedProducts.length;

        for (let i = 0; i < newAddedProductsSize; i++) {
            let productCode = newAddedProducts[i].productCode;
            let productQuantity = newAddedProducts[i].productQuantity;
            let productPrice = newAddedProducts[i].productPrice;

            let entriesCount = dynamicyieldaddtocart.event.entriesCount;
            if (entriesCount > 0) {
                let productCodes = dynamicyieldaddtocart.event.productCodes;
                let productQuantities = dynamicyieldaddtocart.event.productQuantities;
                let productPrices = dynamicyieldaddtocart.event.productPrices;

                if (productCodes.indexOf(productCode) === -1) {
                    productCodes.push(productCode);
                    productQuantities.push(0);
                    productPrices.push(productPrice);
                    entriesCount++;
                }

                for (let j = 0; j < entriesCount; j++) {
                    if (productCodes[j] === productCode) {
                        productQuantities[j] = productQuantities[j] + productQuantity;
                    }
                    let product = {
                        productId: productCodes[j],
                        quantity: productQuantities[j],
                        itemPrice: productPrices[j]
                    }
                    products.push(product);
                }
            } else {
                let product = {productId: productCode, quantity: productQuantity, itemPrice: productPrice}
                products.push(product);
            }
        }

        return products;
    },

    isNumber: function (n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

};


$(document).ready(function () {
    dynamicyieldaddon.bindPurchaseEvent();
    dynamicyieldaddon.bindAddToCartEvent();
    dynamicyieldaddon.bindSignupEvent();
    dynamicyieldaddon.bindLoginEvent();
    dynamicyieldaddon.bindNewsletterSubscriptionEvent();
    dynamicyieldaddon.bindRemoveFromCartEvent();
    dynamicyieldaddon.bindSyncCartContentEvent();
    dynamicyieldaddon.bindSortItemsEvent();
    dynamicyieldaddon.bindFilterItemsEvent();
    dynamicyieldaddon.bindChangeAttributeEvent();
    dynamicyieldaddon.bindPromoCodeEnteredEvent();
    dynamicyieldaddon.bindSearchEvent();
});