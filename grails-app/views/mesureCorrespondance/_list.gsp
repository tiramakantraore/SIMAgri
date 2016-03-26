<%@ page import="simagri.MesureCorrespondance" %>
%{--<div class="row">--}%
    %{--<div class="col-sm-5 col-md-5">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%

                %{--<li>--}%
                    %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                        %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.mesureCorrespondance" />--}%
                    %{--</g:remoteLink>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="col-sm-7 col-md-7">--}%
        %{--<div class="page-header">--}%
            %{--<h1><g:message code="list.mesureCorrespondance" /></h1>--}%
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
						
							<th class="header"><g:message code="mesureCorrespondance.mesureDepart.label" default="Mesure Depart" /></th>
						
							<th class="header"><g:message code="mesureCorrespondance.mesureDestination.label" default="Mesure Destination" /></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="equivalence" title="${message(code: 'mesureCorrespondance.equivalence.label', default: 'Equivalence')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${mesureCorrespondanceInstanceList}" var="mesureCorrespondanceInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${mesureCorrespondanceInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: mesureCorrespondanceInstance, field: "mesureDepart")}</td>
						
							<td>${fieldValue(bean: mesureCorrespondanceInstance, field: "mesureDestination")}</td>
						
							<td>${fieldValue(bean: mesureCorrespondanceInstance, field: "equivalence")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${mesureCorrespondanceInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.MesureCorrespondance" />
