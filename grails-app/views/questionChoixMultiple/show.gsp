
<%@ page import="simagri.QuestionChoixMultiple" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple')}" />
		<title><g:message code="show.questionChoixMultiple"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.questionChoixMultiple" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.questionChoixMultiple" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.questionChoixMultiple" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-sm-6 col-md-6 offset-3">

				<div class="page-header">
					<h1><g:message code="show.questionChoixMultiple"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
                <g:if test="${questionChoixMultipleInstance?.quiz}">
                    <div style="display:inline-block">
                        <dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.quiz.label" default="Quiz" /> :
                        </dt>
                        <dt style="font-weight:bold;display:inline">
                            <g:link controller="quizz" action="show" id="${questionChoixMultipleInstance?.quiz?.id}">${questionChoixMultipleInstance?.quiz?.encodeAsHTML()}</g:link> </dt>
                    </div>
                </g:if>

					<g:if test="${questionChoixMultipleInstance?.question}">

                        <div style="display:inline-block">
                            <dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.question.label" default="Question" /> :
                            </dt>
                            <dt style="font-weight:bold;display:inline">
							<g:fieldValue bean="${questionChoixMultipleInstance}" field="question"/>
                        </dt>
                        </div>
					</g:if>
				

				
					<g:if test="${questionChoixMultipleInstance?.actif}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.actif.label" default="Actif" /> :
                        </dt>
                            <dt style="font-weight:bold;display:inline">
							<g:formatBoolean boolean="${questionChoixMultipleInstance?.actif}" />  </dt>
                        </div>
					</g:if>
				
					<g:if test="${questionChoixMultipleInstance?.commentaireBonneReponse}">
                        <div style="display:inline-block">
						<dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.commentaireBonneReponse.label" default="Commentaire bonne réponse" /> :
                        </dt>
                            <dt style="font-weight:bold;display:inline">
							<g:fieldValue bean="${questionChoixMultipleInstance}" field="commentaireBonneReponse"/>
                        </dt>
                        </div>
					</g:if>

                <g:if test="${questionChoixMultipleInstance?.commentaireMauvaiseReponse}">
                    <div style="display:inline-block">
                        <dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.commentaireMauvaiseReponse.label" default="Commentaire mauvaise réponse" /> :
                        </dt>
                        <dt style="font-weight:bold;display:inline">
                            <g:fieldValue bean="${questionChoixMultipleInstance}" field="commentaireMauvaiseReponse"/>
                        </dt>
                    </div>
                </g:if>

                <g:if test="${questionChoixMultipleInstance?.options}">
                    <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="questionChoixMultiple.options.label" default="Options" /></dt>
                    <dt style="font-weight:bold;display:inline">
                    <g:each in="${questionChoixMultipleInstance?.options}" var="p">
                        <dd><g:link controller="optionChoixMultiple" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link> </dd>
                    </g:each>
                    </div>
                </g:if>




                    </dl>

				<g:form >
					<g:hiddenField name="id" value="${questionChoixMultipleInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${questionChoixMultipleInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
