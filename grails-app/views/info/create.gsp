<%@ page import="org.springframework.validation.FieldError; simagri.Info" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'info.label', default: 'Info')}" />
		<title><g:message code="create.info" /></title>
        <ckeditor:resources/>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.info" /> </li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.info" />
							</g:link>
						</li>
						%{--<li class="active">--}%
							%{--<g:link class="create" action="create">--}%
								%{--<i class="icon-plus icon-white"></i>--}%
								%{--<g:message code="create.info"/>--}%
							%{--</g:link>--}%
						%{--</li>--}%
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="create.info"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${infoInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${infoInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="well small form-horizontal" action="create" >
						<fieldset>
                           <g:render template="form"/>
                           <g:render template="/info/infojs"/>
							<div class="form-actions">
								 <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'info', action:'create')}','','La validation de la page  a réussi','listContent');return false;" >

									<g:message code="default.button.create.label" default="Create" />
								</div>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
				
			</div>

		</div>
	</body>
</html>
