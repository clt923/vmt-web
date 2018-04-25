<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="css">
		<spring:url value="/resources/customCss/login.css" var="loginCss" />
		<link href="${loginCss}" rel="stylesheet" />
	</tiles:putAttribute>

	<tiles:putAttribute name="scripts">

		<spring:url value="/resources/customScripts/login.js"
			var="loginScript" />
		<script src="${loginScript}"></script>

	</tiles:putAttribute>

	<tiles:putAttribute name="body">
		<c:url var="loginUrl" value="/login" />
		<div align="center" class="main_clss">
			<form:form method="POST" action="${loginUrl}" commandName="userForm"
				role="form">
				<div class="margin_h1_clss">
					<h1>WESTPORTS</h1>
					<label style="color: yellow;"> <c:out
							value="${sessionScope.VERSION_NUMBER}"></c:out>
					</label>
				</div>
				<input type="hidden" id="hdhActionLogin" name="actionLogin"
					value="1">
				<!-- <div class="margin_error_clss" id="divError">
					
				</div> -->
				<c:if test="${messageError!=null}">
					<div class="margin_error_clss" id="divError1">
						${messageError}</div>
				</c:if>
				<%-- <c:if test="${errorCode!=null && errorCode eq 'F5'}">
				 	<div class="row">
						<div class="col-sm-12">
							<input type="submit" class="btn btn-danger btn-md" id="btnFouceLogOut"  value='<spring:message code="LA0018" text=""/>'/>
						</div>
					</div>
				 </c:if> --%>
				<br />
				<%-- <c:if test="${messageError!=null}">
						<div class="form-group">
							<label class="col-sm-6 control-label">
								<strong>${messageError}</strong>
							</label>
						</div>
				</c:if> --%>
				<input type="hidden" name="lang" id="lang" value="${lang}" />
				<div class="row">
					<label for="username" class="col-sm-6 control-label text-right">
						<strong class="user_clss"><spring:message code="LA0001"
								text="" /></strong>
					</label>
					<!-- <div style="border-color: #81BEF7; border-width: 1px;">
							
						</div> -->
					<div class="col-sm-6 use_clss_martop">
						<input type="text" style="text-transform: uppercase;"
							maxlength="6" class="text_clss text_clss1 form-control btn_clss"
							id="username" name="username" value="${userForm.userId}">
					</div>
				</div>
				<div class="row">
					<label for="password"
						class="col-sm-6 control-label pass_label_martop_clss text-right">
						<strong class="user_clss"><spring:message code="LA0002"
								text="" /></strong>
					</label>
					<div class="col-sm-6">
						<input type="password" maxlength="6"
							class="text_clss text_clss1 form-control margin_pass_clss"
							id="password" name="password" value="${userForm.password}">
					</div>
				</div>
				<div class="row">
					<label for="ttNo"
						class="col-sm-6 control-label pass_label_martop_clss text-right">
						<strong class="user_clss"><spring:message code="LA0003"
								text="" /></strong>
					</label>
					<div class="col-sm-6">
						<input type="text" maxlength="10"
							style="text-transform: uppercase;"
							class="text_clss text_clss1 form-control margin_ttno_clss"
							id="ttNo" name="ttNo" value="${userForm.ttNo}">
					</div>
				</div>
				<div class="row">
					<label for="ttNo"
						class="col-sm-6 control-label pass_label_martop_clss text-right">
						<strong class="user_clss"><spring:message code="LA0022"
								text="" /></strong>
					</label>
					<div class="col-sm-6">
						<div class="trailerNo">CT</div>
						<div style="float: left;">
							<input type="text" maxlength="3"
								style="text-transform: uppercase;"
								class="trailerNoInput form-control margin_ttno_clss"
								id="trailerNo" name="trailerNo" value="${userForm.trailerNo}">
						</div>
					</div>
				</div>
				<div class="row">
					<label for="team"
						class="col-sm-6 control-label pass_label_martop_clss text-right">
						<strong class="user_clss"><spring:message code="LA0004"
								text="" /></strong>
					</label>
					<div class="col-sm-6" style="margin-top: -4px;">
						<select class="text_clss text_clss1 form-control margin_pass_clss"
							style="margin-right: 0" id="team" name="team">
						</select> <a href="#"
							style="float: left; margin-left: 10px; margin-top: 10px;"
							id="btnRefresh"> <img alt=""
							src="/resources/imgs/refresh-icon.png">
						</a>
					</div>
				</div>
				<div class="row">
					<label for="username" class="col-sm-6 control-label text-right">
						<strong class="user_clss">LANGUAGE</strong>
					</label>
					<div class="col-sm-6 ">
						<c:url value="/login" var="english">
							<c:param name="lang" value="en" />
						</c:url>
						<c:url value="/login" var="malaysia">
							<c:param name="lang" value="ml" />
						</c:url>
						<a href="<c:out value="${english}"/>"
							style="float: left; margin-left: 10px; margin-top: 10px;"> <img
							alt="" src="/resources/imgs/en.png">
						</a> <a href="<c:out value="${malaysia}"/>"
							style="float: left; margin-left: 10px; margin-top: 10px;"> <img
							alt="" src="/resources/imgs/ml.png">
						</a>
					</div>
				</div>
				</br>
				<div class="row">
					<div class="col-sm-12">

						<input type="submit" class="btn btn-primary btn-lg" id="btnLogin"
							value='<spring:message code="LA0011" text=""/>' />
					</div>
				</div>
			</form:form>
		</div>

		<%-- <c:if test="${userduplicator!=null}">
			<div id="divDuplicatorUser">
				<c:url var="logoutduplicatorUser" value="/logoutduplicatorUser" />
				<input type="hidden" name="username" value="${userForm.userId}" id="hdhUserName"/>
				<input type="submit" value="Click here logout" class="btn btn-primary"  id="btnLogoutDup"/>
			</div>
		</c:if> --%>
		<!-- <h2><a href="/vmt-web/download.do">Click here to download file</a></h2> -->
	</tiles:putAttribute>
</tiles:insertDefinition>