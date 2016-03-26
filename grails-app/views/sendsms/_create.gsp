
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'smsToReseaux.label', default: 'SmsToReseaux')}" />
		<title><g:message code="create.smsToReseaux" /></title>
	</head>
	<body>
		<div class="row">

			<div class="col-sm-8 col-md-8">
                <div class="row">
				<div class="page-header ">
					<h1><g:message code="create.smsToReseaux" class="col-sm-6 col-md-6 offset-6"/></h1>
				</div>
                </div>
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${smsToReseauxInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${smsToReseauxInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="create" >
                           <g:render template="form"/>
					       <g:render template="/partials/btnCreer"/>
					</g:form>
				
			</div>


        </div>
	</body>
</html>
