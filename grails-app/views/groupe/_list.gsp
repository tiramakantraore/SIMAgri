<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>
%{--<div class="row">--}%

    %{--<div class="col-sm-4 col-md-4">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%

                %{--<li>--}%
                    %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                        %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.groupe" />--}%
                    %{--</g:remoteLink>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="col-sm-8 col-md-8">--}%
        %{--<div class="page-header">--}%
            %{--<h1><g:message code="list.groupe" /></h1>--}%
        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%
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

                            <util:remoteSortableColumn update="listContent" action="list" property="parent" title="${message(code: 'reseau.parent.label', default: 'Parent')}" />
                            <util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'reseau.nom.label', default: 'Nom')}" />

                            %{--<th class="header"><g:message code="reseau.nbProduits.label" default="Nbre produits" /></th>--}%
                            %{--<th class="header"><g:message code="reseau.nbMarches.label" default="Nbre marchés" /></th>--}%
                            <th class="header"><g:message code="reseau.nbTotalMembres.label" default="Nbre membres" /></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${reseauInstanceList}" var="reseauInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${reseauInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>

						
							<td>${fieldValue(bean: reseauInstance, field: "parent")}</td>

                            <td>${fieldValue(bean: reseauInstance, field: "nom")}</td>
                        %{----}%
						%{----}%
							%{--<td>${fieldValue(bean: reseauInstance, field: "nbProduits")}</td>--}%
						%{----}%
							%{--<td>${fieldValue(bean: reseauInstance, field: "nbMarches")}</td>--}%

                            <td>${fieldValue(bean: reseauInstance, field: "nbMembres")}</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${reseauInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>
		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Reseau" excludeProperties="commentaire" />
