<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;">
<title>VMT</title>
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootStrapCss" />
<link href="${bootStrapCss}" rel="stylesheet" />
<spring:url value="/resources/core/css/common.css" var="commonCss" />
<link href="${commonCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery-1.9.1.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<tiles:insertAttribute name="css" ignore="true" />
</head>

<body>
	<div class="container-fluid">
		<tiles:insertAttribute name="body" />
	</div>
	<script type="text/javascript">
		var contextPathRoot='${pageContext.request.contextPath}';
			
</script>
	<tiles:insertAttribute name="scripts" ignore="true" />
</body>
</html>