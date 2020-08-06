# Dynamic Yield SAP Commerce Extension - Technical Documentation - v1.0.0

The Dynamic Yield Extension provides an easy and seamless integration with Dynamic Yield and enables an automatic implementation of Dynamic Yield’s SAP Commerce solution.

Once a Dynamic Yield account has been created for a specified SAP Commerce platform customer, the Dynamic Yield Extension can be installed (pointing to relevant section ID), automatically applying the following:

1. Dynamic Yield tracking script implementation

2. Context API implementation

3. SAP Commerce events implementation

4. Product Feed (catalog) export


## System Requirements

- SAP Commerce version 1905 or newer.

- Dynamic Yield account.

- Standard Spring MVC/JSP/JS hybris commerce application (Spartacus and Angular are not supported)

## Versioning

Current version of the addon is 1.0.0. This is saved within the property ```dynamicyieldintegration.version``` in ```dynamicyieldaddon/project.properties```, and will be updated with every new version.


## Installation Guide

The extension comes in a form of an addon, called dynamicyieldaddon.

- Copy the addon folder into /hybris/bin/custom/dynamicyieldintegration

- Include the addonsupport extension in your localextensions.xml

```
<extension name="addonsupport"/>
```

- Include the addon in your localextensions.xml

```
<extension name="dynamicyieldaddon"/>
```

- Install the addon with the command:
```
ant addoninstall -Daddonnames="dynamicyieldaddon" -DaddonStorefront.<storefrontTemplateName>="Storefront1"
```
where Storefront1 is the name of your storefront, and storefrontTemplateName is the template name you are using.


- Rebuild the SAP system by running ant clean all from the hybris/bin/platform directory.




## How To Configure

### Backoffice Configuration
After installing the dynamicyieldaddon extension and rebuilding the system, start the server and update the platform. Dynamic Yield Integration configuration item will be located in Backoffice, in System/Dynamic Yield Integration, together with two additional entries (Product Export Job and Product Export Properties, they will be mentioned later).

Within the Dynamic Yield Integration entry, a new item should be created. When creating a new DY item, two attribute values must be filled:

1. Site ID: This is your Dynamic Yield section ID (from your DY platform).

2. Use European scripts: Represents if European scripts should be used in the tracking script.

Additional attributes that should be entered after the creation of an item are:

1. Access Key ID: AWS S3 Access Key

2. Secret access key: AWS S3 Secret Key

3. Sync rate: Time interval of the sync of Product Feed Export cronjob (default is 1 day).

4. CDN Location: Can be Standard (default) and Custom, in which case CDN Location Custom URL property is shown and should be entered.

5. Product Feed Upload Location: Can be Standard (default) and Custom, in which case Product Feed Upload Location Custom URL property is shown and should be entered.




### Tracking Script Implementation
Tracking script is implemented within the DY addon.

To include this implementation into your storefront, the main DY tag file should be included within <head> tags in your storefront. This is by default done in master.tag, but can very depending of your implementation.

- Include taglib reference
```
<%@ taglib prefix="dynamicYieldIntegration" tagdir="/WEB-INF/tags/addons/dynamicyieldaddon/responsive" %>
```

- Add main DY tag file as the first element within head tag
```
<dynamicYieldIntegration:dynamicyieldintegration />
```


### Context API implementation
The Page Context API calls are placed automatically on every page, within the addon.

There are four page types.

All of the four conditions are implemented in the addon, within dynamicyieldcontextapi.tag.


#### Homepage
Homepage Context API call is placed only in Homepage. Is the page Homepage is determined by checking if pageBodyCssClasses has a class page-homepage. pageBodyCssClasses is filled with default page classes as OOTB on the commerce side. As default behaviour, Homepage has page-homepage class within its main body tag.




#### Category
Category Context API call is placed in all the Category pages. Is the page Category page is determined by checking if pageBodyCssClasses has a class pageType-CategoryPage (same logic as for the Homepage).




#### Product
Product Context API call is placed in all the Product pages. Is the page Product page is determined by checking if product attribute is within the model. This attribute is filled in the model in the backend Controller of commerce, only on Product Detail Pages. By default, this is done in ProductPageController.java.




#### Other
Other Context API call is placed in all the other pages.






### Events Implementation
Dynamic Yield Event API calls are implemented within the addon. They are triggered by corresponding user action. There are twelve types of events.

All the data bindings are done in dynamicyieldjsvariables.tag.

All the event logic is implemented in dynamicyieldaddon.js.




#### Purchase
This Event is fired upon successful purchase by the customer.

In Commerce, this means that the last step in the checkout process is submitted successfully and that customer is redirected to the order confirmation page.

All the necessary data is taken from orderData attribute from the model, on order confirmation page. By default, this attribute is filled in AccountPageController.java.

This event triggers in one of the following cases:

- There is a DIV element with the class place-order-form and a form within it. This form submits.

- There is a FORM element with the class place-order-form. This form submits.

Additionally, when the customer is redirected to Order Confirmation Page, the class page-orderConfirmationPage should be present in the page.




#### Add to cart
This Event is fired every time the customer successfully adds a product to cart.

In Commerce, this occurs on three types of pages by default:

- Product Details Page (Add to Cart button)

- Category Page (Add to Cart button next to a product)

- Search Results Page (Add to Cart button next to a product)

The data binding on Product Details Page is done from the product attribute from the model (ProductPageController.java) as well as from the cartData attribute from the model (custom DynamicYieldControllerAdvice.java). Additionally, quantity is taken from frontend element with the class js-qty-selector-input.

The data binding on other pages is done from the frontend elements.

For productCode, INPUT with the attribute name ="productCodePost" is used.

For productPrice, input with the attribute input[name ="productPostPrice" is used.

For productCurrency, MAIN element is used with the attribute data-currency-iso-code.

The Event triggers when any FORM with the class add_to_cart_form is submitted.




#### Signup
The Event is fired upon visitor registration to the site.

The data (hashed user email) is taken from DynamicYieldControllerAdvice.java.

The event is triggered in the following scenario:

1. User successfully registers and FORM with the id #registerForm is submitted.

2. User is automatically logged in.




#### Login
This Event is fired every time the visitor logs into the account.

The data (hashed user email) is taken from DynamicYieldControllerAdvice.java.

The event is triggered when the FORM with the id #loginForm is submitted and the user successfully logs in.




#### Newsletter subscription
This Event is fired every time the visitor sings up to the newsletter.

In Commerce, by default, this occurs in the example Consent Page (My Account/Consent Management), when the customer turns the example context element to true.

The data (hashed user email) is taken from DynamicYieldControllerAdvice.java.

The event is triggered when the subscription button is switched on - in the commerce example case, it is a LABEL that has a FOR attribute with the value MARKETING_NEWSLETTER and a class toggle-button__switch.




#### Remove from cart
This Event is fired every time a visitor removes an item from cart.

The data binding is done on the Cart page.

The event is triggered when the user clicks on the LI element with class js-execute-entry-action-button on Cart page (remove from cart button).

Product ID is taken from data-entry-product-code element within the mentioned LI element.

Quantity is taken from data-entry-initial-quantity element within the mentioned LI element.

For productCurrency, MAIN element is used with the attribute data-currency-iso-code.

The other values are taken from cartData attribute from the model.




#### Sync cart content
This Event is fired every time the visitor starts a new session.

For this, sessionStorage is used with custom attribute.

Data binding is done from cartData attribute from the model.




#### Sort items
This Event is fired every time the visitor changes the sorting options on a product listing page (category page).

The event is triggered when the form with id sortForm1 or sortForm2 (top and bottom commerce sorting forms) within the DIV element with the class sort-refine-baris changed (select box element within it is changed).

The data is taken from the select box elements. In default commerce accelerator, these select boxes are filled with SolrSort item values. Based on if the item in its code contains -asc or -desc, the sorting will be done in ascending or descending order. This logic is as well checked in the event, and based on that the sortOrder will be filled.

sortBy is taken from the value of the selected option within the form (select box).




#### Filter items
This Event is fired every time the visitor filters the items on a product listing page (category page).

The event is triggered when the checkbox with the class js-facet-checkbox within the DIV element with the class js-product-facet is triggered.

Data is bind from the searchPageData attribute within the model, filled in AbstractSearchPageController (searchPageData.facets).

facetElement is the element closest do the triggered checkbox, that has a class js-facet. Then, filterType is taken based on this element and all facet codes.

filterStringValue/filterNumericValue are taken from the element with the class  facet__list__text.




#### Change attribute
This Event is fired every time the visitor changes a product attribute on the Product page (selects different attribute for Variant Product).

In default hybris commerce accelerator, this occurs in two occasions:

1. Customer clicks on the picture icon of different variant (for example - style). The event for this case is triggered when user clicks on LI element within DIV that has class variant-selector.

2. Customer chooses different variant in the select box (for example - size). The event for this case is triggered when the select box with the class variant-select is changed.

The data binding is done from the product attribute within the model (product.baseOptions).




#### Promo code entered
This Event is fired every time the visitor successfully uses a promo code (Cart page).

The event is triggered when the INPUT with the ID js-voucher-code-textwithin the FORM with the ID applyVoucherForm is submitted, and the code is applied successfully (parent element of the element with the class js-voucher-validation-container does not have the class has-error).

Promo code is taken from the voucher element itself (input text with the id js-voucher-code-text).




#### Search
This Event is fired every time the visitor runs a free-style keyword search.

The event is triggered when the FORM with the NAME attribute search_form_SearchBox is submitted.

Search text is taken from the element with the ID js-site-search-input.






## Product Feed Export
Product Feed Export provides the functionality to export product data to DY.

There are two relevant item types that provide this functionality - Product Export Job and Product Export Properties. Both can be found and edited in Dynamic Yield Integration part in Backoffice (Backoffice/System/Dynamic Yield Integration).


### Product Export Job
This item represents the cronjob that performs the export of the product data. It consists of all standard cronjob attributes, as well as three additional attributes within Dynamic Yield tab in General section:

- Product Catalog Version - products from this catalog version will be exported.

- Base Store - Base Store of the products that are exported.

- Base Site - Base Site of the products that are exported.

- Properties - Product properties that will be exported. Its property UID will be used in identifying the URL of the product and product image.

All three attributes have to be selected and configured for the cronjob to work.

Default values for the attributes are set within project.properties file in the dynamicyieldaddon. They refer to Electronics catalog/store/site that is one of the default shops in the commerce. These parameters are then used within the impex file for filling the data (essentialdata-dynamicyieldintegration.impex). This impex will be performed as the part of system initialization. It can also be used for manual import of data.

These properties should be adjusted with the real data from the project and shop. 

```
dynamic.yield.product.catalog=%Catalog%
dynamic.yield.product.catalog.version=%Catalog version%
dynamic.yield.base.store=%Base store%
dynamic.yield.base.site=%Base site%
```

The values can be also configured manually, in the Backoffice, for Product Export Job item type.

### Product Export Properties
These items represent Product Properties that will be exported in the Product Export Job. The item is of type DynamicYieldExportProperty and consists of the following attributes:

- Product Property name - Represents the property name from the product. This name is relevant only if the mapping of the property Product - DY is done 1:1. This means that the value of this product property will be mapped directly to the DY property. In all other cases, this attribute is irrelevant.

- DY Property name - Represents the Dynamic Yield property name.

- Export activated - This determines if the property will be included in the export.

- Is Multi-Language - This determines if the property is multi-language. If it is, it will be exported for every language in the system that is Active (both in the header and the value row of the export csv file).

- Is Mandatory - This determines if the property is mandatory for the export. If the value is true, product will not be exported if this property is empty (null or ““).

- Value Provider bean ID - This contains the ID of the value provider bean that provides the custom logic for exporting the property. If this field is not empty, the specified value provider bean will be used. Since all properties have to be represented in the CSV report as either Strings or Integers, value provider beans are necessary if we want to map our custom property and logic and present it in the report. There are examples for some of the current properties that can be analysed.

The default properties that are included in the addon are:

- sku - the product ID

- name - the product name

- url - the url to the product details page

- group_id - the product id of the base product for variants

- price - the product price

- in_stock - is the product in stock

- categories - list of product categories

- image_url - the url to the product image

- keywords - list of product keywords

The addition of the new product export attribute is possible. The attribute should be created and its value filled, and then it should be added to ProductExportJob.properties. If the value provider must be used, it should be written in the same way the current providers are and referenced within the spring configuration.

#### Setting up the base site URL

To correctly determine the product URL and the product image URL values, the property in .properties is used, in the format ```website.{Base Site UID}.https```. This is the default SAP Commerce logic.

As one default example from the commerce and Electronics base site, the following property is used:
```
website.electronics.https=https://electronics.local:9002/dynamicyieldintegrationstorefront
```
So, to get the correct URL values, one property should be created for every website that is used for the DY, with the correct value of the URL.

### Performing the Export
To run the export, the Product Export Job should be performed.

If job finishes successfully, the CSV file productfeed.csv will be created and uploaded to the AWS S3 folder for the DY client. Access Key ID and Secret access key from Dynamic Yield Integration are used in communication and authentication towards S3. Also, the bucket name and location are determined automatically, based from other Dynamic Yield Integration parameters (Site ID, Use European Scripts). Additionally, productfeed.csv will be saved on server as well, in the location hybris/data/dynamicyieldintegration/export. Only the latest exported file will be saved.

If something goes wrong in the export process, it will be logged in the console log with the complete message. If product is not exported because of some empty field, it will be logged in console log in the form PRODUCT NOT EXPORTED! Product code: %productCode, empty attribute: %propertyName. Only the first empty mandatory property will be logged, for performance reasons.

If the file limit is exceeded, the export will finish without error and log the message Builder limit exceeded! Number of appended products %numberOfProducts.

There is also possible scenario that java heap memory runs out. This happened in test environment when the product count was more than 2200000. In this case, the process will fail and the log message will be written in console log. The recommended tomcat.generaloptions setting in the server for stable work should be at least -Xmx8G.

The period and trigger of the cronjob execution is set automatically, based on Sync Rate attribute in Dynamic Yield Integration. Default is 1 day (every 24h).

If the cronjob should not perform automatically, the Sync Rate should be set to n/a (empty value).
