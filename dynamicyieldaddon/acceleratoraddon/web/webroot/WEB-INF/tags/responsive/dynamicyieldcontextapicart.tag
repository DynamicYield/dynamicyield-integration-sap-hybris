<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="languageIso" required="true" type="java.lang.String"%>

<c:set var="cartDataEntries" value="${cartData.entries}" />
<c:set var="productCodes" value="" />
<c:if test="${fn:length(cartDataEntries) > 0}">
    <c:forEach items="${cartDataEntries}" var="cartDataEntry">
        <c:set var="productCodes" value="${productCodes}${cartDataEntry.product.code},|,|" />
    </c:forEach>
</c:if>

<script type = "text/javascript" >
    // <![CDATA[
    window.DY = window.DY || {};
    var productCodesString = '${productCodes}';
    var productCodesList = [];
    if (productCodesString.includes(",|,|")) {
        productCodesList = productCodesString.split(",|,|");
        productCodesList.pop();
    } else {
        productCodesList.push(productCodesString);
    }
    if ('${languageIso}' != '') {
        DY.recommendationContext = {type: 'CART', data: productCodesList, lng: '${languageIso}'};
    } else {
        DY.recommendationContext = {type: 'CART', data: productCodesList};
    }
    // ]]>
</script>
