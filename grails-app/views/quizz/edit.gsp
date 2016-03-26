<%@ page import="org.springframework.validation.FieldError; simagri.Quizz" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'quizz.label', default: 'Quizz')}" />
		<title><g:message code="edit.quizz" /></title>
	</head>
	<body>
		<div class="row">
            <div class="col-sm-3 col-md-3">

            </div>

			%{--<div class="col-sm-4 col-md-4">--}%
				%{--<div class="well small">--}%
					%{--<ul class="nav nav-list">--}%
						%{--<li class="nav-header"><g:message code="title.quizz" /></li>--}%
						%{--<li>--}%
							%{--<g:link class="list" action="list">--}%
								%{-- <i class="glyphicon glyphicon-list"></i>--}%
								%{--<g:message code="list.quizz" />--}%
							%{--</g:link>--}%
						%{--</li>--}%
						%{--<li>--}%
							%{--<g:link class="create" action="create">--}%
								%{-- <i class="glyphicon glyphicon-plus"></i>--}%
								%{--<g:message code="create.quizz" />--}%
							%{--</g:link>--}%
						%{--</li>--}%
					%{--</ul>--}%
				%{--</div>--}%
			%{--</div>--}%
			
			<div class="col-sm-6 col-md-6">

				<div class="page-header">
					<h1><g:message code="edit.quizz" /></h1>
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

				<fieldset>
					<g:form class="well small form-horizontal" role="form" action="edit" id="${quizzInstance?.id}" >
						<g:hiddenField name="version" value="${quizzInstance?.version}" />
						<fieldset>
                            <g:render template="form"/>
							<div class="form-actions">
								 <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'quizz', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >

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
            <div class="col-sm-3 col-md-3">

            </div>
		</div>
	</body>
</html>
