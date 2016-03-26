
<%@ page import="com.dbconfig.ConfigProperty" %>
%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="main.gsp">--}%
		%{--<g:set var="entityName" value="${message(code: 'configProperty.label', default: 'ConfigProperty')}" />--}%
		%{--<g:render template="head"/>--}%
	%{--</head>--}%
	%{--<body>--}%
		<div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all ui-resizable">

			<div id="show-configProperty" class="content scaffold-show" role="main">
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>


				<g:form>

					<f:display bean="configPropertyInstance" property="configKey"/>
					<f:display bean="configPropertyInstance" property="value"/>
					<f:display bean="configPropertyInstance" property="description"/>

					<g:hiddenField name="id" value="${configPropertyInstance?.id}" />

					<g:render template="/partials/btnShow"/>
				</g:form>
			</div>
		</div>
	%{--</body>--}%
%{--</html>--}%
