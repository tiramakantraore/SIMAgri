
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
			<ul class="ui-tabs-nav ui-helper-clearfix ui-widget-header">
				<li class="ui-state-default ui-corner-top">
					<g:link class="" controller="configProperty" action="list">Property Status</g:link>
				</li>
				<li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
					<g:link class="" controller="configProperty" action="create">Create Property</g:link>
				</li>
			</ul>
			<div id="show-configProperty" class="content scaffold-show" role="main">
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>
				<ol class="property-list configProperty">
				
					<g:if test="${configPropertyInstance?.configKey}">
					<li class="fieldcontain">
						<span id="configKey-label" class="property-label"><g:message code="configProperty.configKey.label" default="configKey" /></span>
						
							<span class="property-value" aria-labelledby="configKey-label"><g:fieldValue bean="${configPropertyInstance}" field="configKey"/></span>
						
					</li>
					</g:if>
				
					<g:if test="${configPropertyInstance?.value}">
					<li class="fieldcontain">
						<span id="value-label" class="property-label"><g:message code="configProperty.value.label" default="Value" /></span>
						
							<span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${configPropertyInstance}" field="value"/></span>
						
					</li>
					</g:if>
					
					<g:if test="${configPropertyInstance?.description}">
					<li class="fieldcontain">
						<span id="description-label" class="property-label"><g:message code="configProperty.description.label" default="Description" /></span>
						
							<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${configPropertyInstance}" field="description"/></span>
						
					</li>
					</g:if>
				
				</ol>
				<g:form>
					<fieldset class="buttons">
						<g:hiddenField name="id" value="${configPropertyInstance?.id}" />
						<g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" />
						<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					</fieldset>
				</g:form>
			</div>
		</div>
	%{--</body>--}%
%{--</html>--}%
