<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootStrapCss" />
<link href="${bootStrapCss}" rel="stylesheet" />
<spring:url value="/resources/core/js/jquery-1.9.1.min.js"
	var="jqueryJs" />
<script src="${jqueryJs}"></script>
<spring:url value="/resources/core/js/tether.min.js" var="tetherjs" />
<script src="${tetherjs}"></script>

<spring:url value="/resources/core/js/bootstrap.min.js"
	var="jqueryBootstrap" />
<script src="${jqueryBootstrap}"></script>
<style type="text/css">
.error {
	margin: 0 auto;
	text-align: center;
}

.error-code {
	bottom: 60%;
	color: #2d353c;
	font-size: 96px;
	line-height: 100px;
}

.error-desc {
	font-size: 12px;
	color: #647788;
}

.m-b-10 {
	margin-bottom: 10px !important;
}

.m-b-20 {
	margin-bottom: 20px !important;
}

.m-t-20 {
	margin-top: 20px !important;
}
</style>
<title>Home</title>
</head>
<header> </header>
<body>
	<div class="error">
		<div class="error-code m-b-10 m-t-20">${errorCode}
			<i class="fa fa-warning"></i>
		</div>

		<div class="error-desc">
			${errorMsg} <br /> Try refreshing the page or click the button below
			to go back to the Homepage.
			<div>
				<c:url var="logoutUrl" value="/logout"></c:url>
				<a class=" login-detail-panel-button btn"
					href="<c:out value="${logoutUrl}"/>"> <i
					class="fa fa-arrow-left"></i> Go back to Homepage
				</a>
			</div>
		</div>
	</div>

</body>
</html>