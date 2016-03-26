
<%@ page import="simagri.Quizz" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'quizz.label', default: 'Quizz')}" />
		<title><g:message code="list.quizz"  /></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.quizz" /></li>

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
					<h1><g:message code="list.quizz" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="titre" title="${message(code: 'quizz.titre.label', default: 'Titre')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="description" title="${message(code: 'quizz.description.label', default: 'Description')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${quizzInstanceList}" var="quizzInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${quizzInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: quizzInstance, field: "titre")}</td>
						
							<td>${fieldValue(bean: quizzInstance, field: "description")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${quizzInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Quizz" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
