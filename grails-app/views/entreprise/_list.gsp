<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>


%{--<div class="row">--}%
			%{----}%
			%{--<div class="col-sm-4 col-md-4">--}%
				%{--<div class="well small">--}%
					%{--<ul class="nav nav-list">--}%

						%{--<li>--}%
                            %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                                %{--<i class="glyphicon glyphicon-plus"></i>--}%
                                %{--<g:message code="create.entreprise" />--}%
                            %{--</g:remoteLink>--}%
						%{--</li>--}%
					%{--</ul>--}%
				%{--</div>--}%
			%{--</div>--}%
                    %{--<div class="col-sm-8 col-md-8">--}%

                        %{--<div class="page-header">--}%
                            %{--<h1><g:message code="list.entreprise" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'entreprise.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="site_web" title="${message(code: 'entreprise.site_web.label', default: 'Siteweb')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="email" title="${message(code: 'entreprise.email.label', default: 'Email')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${entrepriseInstanceList}" var="entrepriseInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${entrepriseInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: entrepriseInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: entrepriseInstance, field: "site_web")}</td>
						
							<td>${fieldValue(bean: entrepriseInstance, field: "email")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${entrepriseInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Entreprise" />
