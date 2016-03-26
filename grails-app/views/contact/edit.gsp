<%@ page import="simagri.Contact" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}" />
		<title><g:message code="edit.contact" /></title>
	</head>
	<body>
		<div class="row">

			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.contact" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.contact" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.contact" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="edit.contact" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${contactInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${contactInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="well small form-horizontal" action="edit" id="${contactInstance?.id}" >
						<g:hiddenField name="version" value="${contactInstance?.version}" />
						<fieldset>
                            <g:render template="form"/>
							<div class="form-actions">
								 <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'contact', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >

									<g:message code="default.button.update.label" default="Update" />
								</div>
								<div type="submit" class="btn-flat  btn-primary" name="_action_delete" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
									 <i class="glyphicon glyphicon-trash"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</div>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
