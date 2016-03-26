
    %{--<div class="row">--}%

        %{--<div class="col-sm-4 col-md-4">--}%
            %{--<div class="well small">--}%
                %{--<ul class="nav nav-list">--}%

                    %{--<li>--}%
                        %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                            %{--<i class="glyphicon glyphicon-plus"></i>--}%
                            %{--<g:message code="create.produit" />--}%
                        %{--</g:remoteLink>--}%
                    %{--</li>--}%
                    %{--<li>--}%
                        %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="mettreAJour" update="listContent"  class="create">--}%
                            %{--<i class="glyphicon glyphicon-plus"></i>--}%
                            %{--<g:message code="miseAJour.produit" />--}%
                        %{--</g:remoteLink>--}%
                    %{--</li>--}%
                %{--</ul>--}%
            %{--</div>--}%
        %{--</div>--}%
        %{--<div class="col-sm-8 col-md-8">--}%
            %{--<div class="page-header">--}%
                %{--<h1><g:message code="list.produit" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'produit.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'produit.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nomScientifique" title="${message(code: 'produit.nomScientifique.label', default: 'Nom Scientifique')}" />
						
							<th class="header"><g:message code="produit.categorie.label" default="Categorie" /></th>
						
							%{--<util:remoteSortableColumn update="listContent" action="list" property="variete" title="${message(code: 'produit.variete.label', default: 'Variete')}" />--}%
						
							<th class="header"><g:message code="produit.mesure.label" default="Mesure" /></th>
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${produitInstanceList}" var="produitInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${produitInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: produitInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: produitInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: produitInstance, field: "nomScientifique")}</td>
						
							<td>${fieldValue(bean: produitInstance, field: "categorie")}</td>
						
							%{--<td>${fieldValue(bean: produitInstance, field: "variete")}</td>--}%
						
							<td>${fieldValue(bean: produitInstance, field: "mesure")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${produitInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Produit" excludeProperties="commentaire"/>
