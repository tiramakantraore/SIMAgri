%{--<div class="row">--}%
    %{--<div class="col-sm-4 col-md-4">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%

                %{--<li>--}%
                    %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                        %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.marche" />--}%
                    %{--</g:remoteLink>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="col-sm-8 col-md-8">--}%
        %{--<div class="page-header">--}%
            %{--<h1><g:message code="list.marche" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'marche.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'marche.nom.label', default: 'Nom')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="region" title="${message(code: 'marche.region.label', default: 'Region')}" />

						
							<th class="header"><g:message code="marche.location.label" default="Location" /></th>

                            <th class="header"><g:message code="marche.produits.label" default="Produits" /></th>


						</tr>
					</thead>
					<tbody>
					<g:each in="${marcheInstanceList}" var="marcheInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${marcheInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: marcheInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: marcheInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: marcheInstance, field: "region")}</td>
						
							<td>${fieldValue(bean: marcheInstance, field: "location")}</td>

                            <td>${fieldValue(bean: marcheInstance, field: "produits")}</td>

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${marcheInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" associatedProperties="region.nom" domain="simagri.Marche" />
