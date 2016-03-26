
<%@ page import="simagri.Operateur" %>
        %{--<div class="row">--}%
            %{--<div class="col-sm-4 col-md-4">--}%
                %{--<div class="well small">--}%
                    %{--<ul class="nav nav-list">--}%

                        %{--<li>--}%
                            %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                                %{--<i class="glyphicon glyphicon-plus"></i>--}%
                                %{--<g:message code="create.operateur" />--}%
                            %{--</g:remoteLink>--}%
                        %{--</li>--}%
                    %{--</ul>--}%
                %{--</div>--}%
            %{--</div>--}%
            %{--<div class="col-sm-8 col-md-8">--}%
                %{--<div class="page-header">--}%
                    %{--<h1><g:message code="list.operateur" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'operateur.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="prefixes" title="${message(code: 'operateur.prefixes.label', default: 'Prefixes')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="emailContact" title="${message(code: 'operateur.emailContact.label', default: 'Email Contact')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="telephoneContact" title="${message(code: 'operateur.telephoneContact.label', default: 'Telephone Contact')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="siteWeb" title="${message(code: 'operateur.siteWeb.label', default: 'Site Web')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${operateurInstanceList}" var="operateurInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${operateurInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: operateurInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: operateurInstance, field: "prefixes")}</td>
						
							<td>${fieldValue(bean: operateurInstance, field: "emailContact")}</td>
						
							<td>${fieldValue(bean: operateurInstance, field: "telephoneContact")}</td>
						
							<td>${fieldValue(bean: operateurInstance, field: "siteWeb")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${operateurInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Operateur" />

