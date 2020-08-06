<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dynamicYieldIntegration" tagdir="/WEB-INF/tags/addons/dynamicyieldaddon/responsive" %>

<c:set var="dynamicYieldIntegration" value="${dynamicYieldIntegrationData}" />
<c:set var="useEuropeanScripts" value="${dynamicYieldIntegration.useEuropeanScripts}" />
<c:set var="euSuffix" value="${useEuropeanScripts ? '-eu' : ''}" />
<c:set var="standardURL" value="${useEuropeanScripts ? 'cdn-eu.dynamicyield.com' : 'cdn.dynamicyield.com'}" />
<c:set var="dynamicYieldURL" value="${dynamicYieldIntegration.cdnLocation eq 'CUSTOM' ? dynamicYieldIntegration.cdnLocationCustomURL : standardURL}" />

<link rel="preconnect" href="//cdn${euSuffix}.dynamicyield.com">
<link rel="preconnect" href="//st${euSuffix}.dynamicyield.com">
<link rel="preconnect" href="//rcom${euSuffix}.dynamicyield.com">
<link rel="dns-prefetch" href="//cdn${euSuffix}.dynamicyield.com">
<link rel="dns-prefetch" href="//st${euSuffix}.dynamicyield.com">
<link rel="dns-prefetch" href="//rcom${euSuffix}.dynamicyield.com">

<dynamicYieldIntegration:dynamicyieldcontextapi />

<script type="text/javascript" src="//${dynamicYieldURL}/api/${dynamicYieldIntegration.siteID}/api_dynamic.js"></script>
<script type="text/javascript" src="//${dynamicYieldURL}/api/${dynamicYieldIntegration.siteID}/api_static.js"></script>


<dynamicYieldIntegration:dynamicyieldjsvariables />