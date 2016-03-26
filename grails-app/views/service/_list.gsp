
<%@ page import="simagri.Service" %>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">

                <li>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
                        <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.service" />
                    </g:remoteLink>
                </li>
            </ul>
        </div>
    </div>
    <div class="col-sm-8 col-md-8">
        <div class="page-header">
            <h1><g:message code="list.service" /></h1>
        </div>
    </div>
</div>
		<div class="row">

			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>

							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'service.nom.label', default: 'Nom')}" />

							<util:remoteSortableColumn update="listContent" action="list" property="description" title="${message(code: 'service.description.label', default: 'Description')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${serviceInstanceList}" var="serviceInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${serviceInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: serviceInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: serviceInstance, field: "description")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${serviceInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Service" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%

