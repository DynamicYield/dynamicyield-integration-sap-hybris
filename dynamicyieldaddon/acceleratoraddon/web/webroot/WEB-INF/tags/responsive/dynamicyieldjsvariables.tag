<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type = "text/javascript" >
    /*<![CDATA[*/

    // PURCHASE EVENT
    var dynamicyieldpurchase = { event: {} };
    dynamicyieldpurchase.event.orderCode = '${orderData.code}';
    dynamicyieldpurchase.event.orderPrice = parseFloat('${orderData.totalPrice.value}');
    dynamicyieldpurchase.event.orderCurrency = '${orderData.totalPrice.currencyIso}';
    dynamicyieldpurchase.event.entriesCount = parseInt('${orderData.entries.size()}');
    dynamicyieldpurchase.event.productCodes = [];
    dynamicyieldpurchase.event.productQuantities = [];
    dynamicyieldpurchase.event.productPrices = [];
    <c:forEach items="${orderData.entries}" var="entry">
        var productCode = '${entry.product.code}';
        dynamicyieldpurchase.event.productCodes.push(productCode);
        var entryQuantity = parseInt('${entry.quantity}');
        dynamicyieldpurchase.event.productQuantities.push(entryQuantity);
        var entryPrice = parseFloat('${entry.basePrice.value}');
        dynamicyieldpurchase.event.productPrices.push(entryPrice);
    </c:forEach>

    // ADD TO CART, REMOVE FROM CART, SYNC CART CONTENT EVENTS
    var dynamicyieldaddtocart = { event: {} };
    dynamicyieldaddtocart.event.productPrice = parseFloat('${product.price.value}');
    dynamicyieldaddtocart.event.productCurrency = '${product.price.currencyIso}';
    dynamicyieldaddtocart.event.productCode = '${product.code}';
    dynamicyieldaddtocart.event.entriesCount = '${cartData.entries.size()}';
    dynamicyieldaddtocart.event.productCodes = [];
    dynamicyieldaddtocart.event.productQuantities = [];
    dynamicyieldaddtocart.event.productPrices = [];
    <c:forEach items="${cartData.entries}" var="entry">
        var productCode = '${entry.product.code}';
        dynamicyieldaddtocart.event.productCodes.push(productCode);
        var entryQuantity = parseInt('${entry.quantity}');
        dynamicyieldaddtocart.event.productQuantities.push(entryQuantity);
        var entryPrice = parseFloat('${entry.basePrice.value}');
        dynamicyieldaddtocart.event.productPrices.push(entryPrice);
    </c:forEach>

    // SIGNUP, LOGIN EVENT
    var dynamicyieldsignup = { event: {} };
    dynamicyieldsignup.event.hashedUserEmail = '${hashedUserEmail}';

    // FILTER ITEMS EVENT
    var dynamicyieldfilteritems = { event: {} };
    dynamicyieldfilteritems.event.facetCodes = [];
    <c:forEach items="${searchPageData.facets}" var="facet">
        var facetCode = '${facet.code}';
        dynamicyieldfilteritems.event.facetCodes.push(facetCode);
    </c:forEach>

    // CHANGE ATTRIBUTE EVENT
    var dynamicyieldchangeattribute = { event: {} };
    dynamicyieldchangeattribute.event.selectedCodes = [];
    dynamicyieldchangeattribute.event.selectedValues = [];
    <c:forEach items="${product.baseOptions}" var="baseOption">
        var selectedCode = '${baseOption.selected.variantOptionQualifiers.iterator().next().qualifier}';
        var selectedValue = '${baseOption.selected.variantOptionQualifiers.iterator().next().value}';
        dynamicyieldchangeattribute.event.selectedCodes.push(selectedCode);
        dynamicyieldchangeattribute.event.selectedValues.push(selectedValue);
    </c:forEach>

    /*]]>*/
</script>