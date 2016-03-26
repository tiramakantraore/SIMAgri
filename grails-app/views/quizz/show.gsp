
<%@ page import="simagri.Quizz" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'quizz.label', default: 'Quizz')}" />
		<title><g:message code="show.quizz"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.quizz" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.quizz" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.quizz" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-8 col-md-8">

				<div class="page-header">
					<h1><g:message code="show.quizz"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${quizzInstance?.titre}">
						<dt><g:message code="quizz.titre.label" default="Titre" /> :
						
							<g:fieldValue bean="${quizzInstance}" field="titre"/>
                        </dt>
                        
					</g:if>
				
					<g:if test="${quizzInstance?.description}">
						<dt><g:message code="quizz.description.label" default="Description" /> :
						
							<g:fieldValue bean="${quizzInstance}" field="description"/>
                        </dt>
                        
					</g:if>
                <g:if test="${quizzInstance?.questions}">
                    <dt><g:message code="quizz.questions.label" default="Questions" /></dt>

                    <g:each in="${quizzInstance?.questions}" var="p">
                        <dd><g:link controller="questionChoixMultiple" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></dd>
                    </g:each>

                </g:if>

				</dl>

				<g:form >
					<g:hiddenField name="id" value="${quizzInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${quizzInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
