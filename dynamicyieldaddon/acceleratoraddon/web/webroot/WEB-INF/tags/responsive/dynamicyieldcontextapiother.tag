<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>
<%@ attribute name="languageIso" required="true" type="java.lang.String"%>

<script type = "text/javascript" >
    // <![CDATA[
    window.DY = window.DY || {};
    if ('${languageIso}' != '') {
        DY.recommendationContext = {type: 'OTHER', lng: '${languageIso}'};
    } else {
        DY.recommendationContext = {type: 'OTHER'};
    }
    // ]]>
</script>
