<%@ page import="com.dbconfig.ConfigProperty" %>
<g:render template="/partials/showHeader" />
			<ul class="ui-tabs-nav ui-helper-clearfix ui-widget-header">
				<li class="ui-state-default ui-corner-top">
					<g:link class="" controller="configProperty" action="list">Property Status</g:link>
				</li>
				<li class="ui-state-default ui-corner-top ui-tabs-selected ui-state-active">
					<g:link class="" controller="configProperty" action="create">Create Property</g:link>
				</li>
			</ul>
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>
				<g:hasErrors bean="${configPropertyInstance}">
				<ul class="errors" role="alert">
					<g:eachError bean="${configPropertyInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</g:hasErrors>
				<g:form method="post">
					<g:hiddenField name="id" value="${configPropertyInstance?.id}" />
					<g:hiddenField name="version" value="${configPropertyInstance?.version}" />
					<g:render template="form"/>
					<g:render template="/partials/btnEdit"/>

				</g:form>
