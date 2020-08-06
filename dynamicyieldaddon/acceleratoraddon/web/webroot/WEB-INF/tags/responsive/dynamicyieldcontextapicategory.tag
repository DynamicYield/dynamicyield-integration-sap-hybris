<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ attribute name="languageIso" required="true" type="java.lang.String"%>

<c:set var="categories" value="" />
<c:forEach items="${breadcrumbs}" var="breadcrumb">
    <c:set var="categories" value="${categories}${breadcrumb.name},|,|" />
</c:forEach>

<script type = "text/javascript" >
    // <![CDATA[
    window.DY = window.DY || {};
    var categoriesString = '${categories}';
    var categoriesList = categoriesString.split(",|,|");
    categoriesList.pop();
    if ('${languageIso}' != '') {
        DY.recommendationContext = {type: 'CATEGORY', data: categoriesList, lng: '${languageIso}'};
    } else {
        DY.recommendationContext = {type: 'CATEGORY', data: categoriesList};
    }
    // ]]>
</script>
