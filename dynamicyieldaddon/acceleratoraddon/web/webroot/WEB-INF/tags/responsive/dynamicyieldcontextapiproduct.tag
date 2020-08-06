<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="languageIso" required="true" type="java.lang.String"%>

<c:set var="productCode" value="${product.code}" />

<script type = "text/javascript" >
    // <![CDATA[
    window.DY = window.DY || {};
    var productCode = '${productCode}';
    var productList = [productCode];
    if ('${languageIso}' != '') {
        DY.recommendationContext = {type: 'PRODUCT', data: productList, lng: '${languageIso}'};
    } else {
        DY.recommendationContext = {type: 'PRODUCT', data: productList};
    }
    // ]]>
</script>
