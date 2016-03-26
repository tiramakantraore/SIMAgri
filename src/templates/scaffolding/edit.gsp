<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="edit.${domainClass.propertyName}" /></title>
	</head>
	<body>
		<div class="row">

			<div class="col-sm-4 col-md-4">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.${domainClass.propertyName}" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.${domainClass.propertyName}" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.${domainClass.propertyName}" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
        </div>
    <div class="row">
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<h1><g:message code="edit.${domainClass.propertyName}" /></h1>
				</div>

				<g:if test="\${flash.message}">
				<bootstrap:alert class="alert-info">\${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="\${${propertyName}}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="\${${propertyName}}" var="error">
					<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="edit" id="\${${propertyName}?.id}" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
						<g:hiddenField name="version" value="\${${propertyName}?.version}" />
                        %{--<g:hiddenField name="version" value="\${${propertyName}?.id}" />--}%

						<fieldset>
                            <f:all bean="\${${propertyName}}" input-class=" many-to-one form-control"/>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">

									<g:message code="default.button.update.label" default="Update" />
								</button>
								<button type="submit" class="btn btn-danger" name="_action_delete" formnovalidate="" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
									 <i class="glyphicon glyphicon-trash"></i>
									<g:message code="default.button.delete.label" default="Delete" />
								</button>
							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>
	</body>
</html>
