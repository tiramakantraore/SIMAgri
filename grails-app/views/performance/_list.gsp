<%@ page import="simagri.Performance" %>
%{--<div class="row">--}%
    %{--<div class="col-sm-4 col-md-4">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%

                %{--<li>--}%
                    %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                        %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.performance" />--}%
                    %{--</g:remoteLink>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="col-sm-8 col-md-8">--}%
        %{--<div class="page-header">--}%
            %{--<h2><g:message code="list.performance" /></h2>--}%
        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%
<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'performance.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nbOffre" title="${message(code: 'performance.nbOffre.label', default: 'Nb Offre')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nbPrix" title="${message(code: 'performance.nbPrix.label', default: 'Nb Prix')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nbAlerte" title="${message(code: 'performance.nbAlerte.label', default: 'Nb Alerte')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nbContact" title="${message(code: 'performance.nbContact.label', default: 'Nb Contact')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${performanceInstanceList}" var="performanceInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${performanceInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: performanceInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: performanceInstance, field: "nbOffre")}</td>
						
							<td>${fieldValue(bean: performanceInstance, field: "nbPrix")}</td>
						
							<td>${fieldValue(bean: performanceInstance, field: "nbAlerte")}</td>
						
							<td>${fieldValue(bean: performanceInstance, field: "nbContact")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${performanceInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Performance" />
