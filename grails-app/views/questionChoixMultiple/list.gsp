
<%@ page import="simagri.QuestionChoixMultiple" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple')}" />
		<title><g:message code="list.questionChoixMultiple"  /></title>

        
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.questionChoixMultiple" /></li>

						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.questionChoixMultiple" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="col-sm-7 col-md-7 offset-2">
				
				<div class="page-header">
					<h1><g:message code="list.questionChoixMultiple" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="question" title="${message(code: 'questionChoixMultiple.question.label', default: 'Question')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="actif" title="${message(code: 'questionChoixMultiple.actif.label', default: 'Actif')}" />

							<th class="header"><g:message code="questionChoixMultiple.quiz.label" default="Quiz" /></th>
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${questionChoixMultipleInstanceList}" var="questionChoixMultipleInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${questionChoixMultipleInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: questionChoixMultipleInstance, field: "question")}</td>
						
							<td><g:formatBoolean boolean="${questionChoixMultipleInstance.actif}" /></td>

						
							<td>${fieldValue(bean: questionChoixMultipleInstance, field: "quiz")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${questionChoixMultipleInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.QuestionChoixMultiple" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
