<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dynamicYieldIntegration" tagdir="/WEB-INF/tags/addons/dynamicyieldaddon/responsive" %>

<c:set var="currentLanguageIso" value="${currentLanguage.isocode}" />
<c:set var="defaultLanguageIso" value="${defaultLanguage.isocode}" />
<c:set var="currentLanguageIsDefault" value="${currentLanguageIso == defaultLanguageIso}" />
<c:set var="languageIso" value="${currentLanguageIsDefault ? '' : currentLanguageIso}" />

<c:choose>
    <%-- HOMEPAGE Context API --%>
    <c:when test="${fn:contains(pageBodyCssClasses, 'page-homepage')}">
        <dynamicYieldIntegration:dynamicyieldcontextapihomepage languageIso="${languageIso}"/>
    </c:when>

    <%-- CATEGORY Context API --%>
    <c:when test="${fn:contains(pageBodyCssClasses, 'pageType-CategoryPage')}">
        <dynamicYieldIntegration:dynamicyieldcontextapicategory languageIso="${languageIso}"/>
    </c:when>

    <%-- PRODUCT Context API --%>
    <c:when test="${not empty product}">
        <dynamicYieldIntegration:dynamicyieldcontextapiproduct languageIso="${languageIso}"/>
    </c:when>

    <%-- CART Context API --%>
    <c:when test="${fn:contains(pageBodyCssClasses, 'page-cartPage')}">
        <dynamicYieldIntegration:dynamicyieldcontextapicart languageIso="${languageIso}"/>
    </c:when>

    <%-- OTHER Context API --%>
    <c:otherwise>
        <dynamicYieldIntegration:dynamicyieldcontextapiother languageIso="${languageIso}"/>
    </c:otherwise>
</c:choose>