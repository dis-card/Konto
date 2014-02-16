
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="css/stylesheet.css" />
<c:if test="${requestScope.browser == null}">
	<c:set var="browser" scope="request" value="${header[\"user-agent\"]}" />
</c:if>
<c:choose>
	<c:when test="${fn:contains(browser, 'Firefox')}">
	</c:when>
	<c:otherwise>
		<p class="errorMsg">Best viewed with Firefox or Chrome	<p>
	</c:otherwise>
</c:choose>
