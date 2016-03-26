<%@ page import="org.springframework.validation.FieldError; simagri.SMSLogger" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'SMSLogger.label', default: 'SMSLogger')}" />
		<title><g:message code="edit.SMSLogger" /></title>
	</head>
	<body>
		<div class="row">

			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.SMSLogger" /></li>
						<li>
							<g:link class="list" action="list">
                                <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.SMSLogger" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.SMSLogger" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="edit.SMSLogger" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${SMSLoggerInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${SMSLoggerInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="well small form-horizontal" action="edit" id="${SMSLoggerInstance?.id}" >
						<g:hiddenField name="version" value="${SMSLoggerInstance?.version}" />
						<fieldset>
                            <g:render template="form"/>
						    <g:render template="/partials/btnEdit"/>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
