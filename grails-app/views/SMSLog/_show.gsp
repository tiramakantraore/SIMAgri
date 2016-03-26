
<%@ page import="simagri.SMSLogger" %>
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="adminLayout">--}%
		%{--<g:set var="entityName" value="${message(code: 'SMSLogger.label', default: 'SMSLogger')}" />--}%
		%{--<title><g:message code="show.SMSLogger"/></title>--}%
	%{--</head>--}%
	%{--<body>--}%
<g:render template="/partials/showHeader" model="[canCreate:false,isList:null]"/>
		<div class="row">
			
			<div class="col-sm-12 col-md-12">
                <g:form >
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <f:display bean="SMSLoggerInstance" property="user"/>
                <f:display bean="SMSLoggerInstance" property="date"/>
                <f:display bean="SMSLoggerInstance" property="message"/>
                <f:display bean="SMSLoggerInstance" property="isIn"/>


					<g:hiddenField name="id" value="${SMSLoggerInstance?.id}" />

				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
