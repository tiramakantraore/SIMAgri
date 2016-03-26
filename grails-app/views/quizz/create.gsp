<%@ page import="org.springframework.validation.FieldError; simagri.Quizz" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'quizz.label', default: 'Quizz')}" />
		<title><g:message code="create.quizz" /></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-3 col-md-3">

			</div>
			
			<div class="col-sm-6 col-md-6">

				<div class="page-header">
					<h1><g:message code="create.quizz"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${quizzInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${quizzInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" role="form" action="create" >
                           <g:render template="form"/>
							<div class="form-actions">
								 <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'quizz', action:'create')}','','La validation de la page  a réussi','listContent');return false;" >

									<g:message code="default.button.create.label" default="Create" />
								</div>
							</div>
					</g:form>
			</div>
            <div class="col-sm-3 col-md-3">

            </div>
		</div>
	</body>
</html>
