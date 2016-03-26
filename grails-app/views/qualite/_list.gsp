    %{--<div class="row">--}%

        %{--<div class="col-sm-4 col-md-4">--}%
            %{--<div class="well small">--}%
                %{--<ul class="nav nav-list">--}%

                    %{--<li>--}%
                        %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                            %{--<i class="glyphicon glyphicon-plus"></i>--}%
                            %{--<g:message code="create.qualite" />--}%
                        %{--</g:remoteLink>--}%
                    %{--</li>--}%
                %{--</ul>--}%
            %{--</div>--}%
        %{--</div>--}%
        %{--<div class="col-sm-8 col-md-8">--}%
            %{--<div class="page-header">--}%
                %{--<h1><g:message code="list.qualite" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'qualite.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'qualite.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="description" title="${message(code: 'qualite.description.label', default: 'Description')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${qualiteInstanceList}" var="qualiteInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${qualiteInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: qualiteInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: qualiteInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: qualiteInstance, field: "description")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${qualiteInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Qualite" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
