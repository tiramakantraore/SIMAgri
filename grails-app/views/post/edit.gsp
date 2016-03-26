<%@ page import="simagri.Post" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title><g:message code="edit.post" /></title>
	</head>
	<body>
		<div class="row">

			<div class="span4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.post" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.post" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.post" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="edit.post" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${postInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${postInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="well small form-horizontal" action="edit" id="${postInstance?.id}" >
						<g:hiddenField name="version" value="${postInstance?.version}" />
                        %{--<g:hiddenField name="version" value="${postInstance?.id}" />--}%

						<fieldset>
                            <f:all bean="${postInstance}"/>
							<div class="form-actions">
								<div type="submit" class="btn btn-primary">

									<g:message code="default.button.update.label" default="Update" />
								</div>
								<div type="submit" class="btn btn-primary" name="_action_delete" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
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
