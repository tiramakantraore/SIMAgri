

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
		<title><g:message code="show.role"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.role" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.role" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.role" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="show.role"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${roleInstance?.authority}">
						<dt><g:message code="role.authority.label" default="Authority" /></dt>
						
							<dd><g:fieldValue bean="${roleInstance}" field="authority"/></dd>
						
					</g:if>
				
					<g:if test="${roleInstance?.description}">
						<dt><g:message code="role.description.label" default="Description" /></dt>
						
							<dd><g:fieldValue bean="${roleInstance}" field="description"/></dd>
						
					</g:if>
				
				</dl>

				<g:form >
					<g:hiddenField name="id" value="${roleInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${roleInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<div class="btn-flat  btn-primary" type="submit" name="_action_delete">
							 <i class="glyphicon glyphicon-trash"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</div>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
