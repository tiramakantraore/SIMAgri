
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">

						<li>
                            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
                                <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="create.categorieProduit" />
                            </g:remoteLink>
						</li>
					</ul>
				</div>
			</div>
			<div class="page-header">
				<h1><g:message code="list.categorieProduit" /></h1>
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'categorieProduit.nom.label', default: 'Nom')}" />
						
							<th class="header"><g:message code="categorieProduit.mesures.label" default="Mesures" /></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="actif" title="${message(code: 'categorieProduit.actif.label', default: 'Actif')}" />
						
							%{--<util:remoteSortableColumn update="listContent" action="list" property="comment" title="${message(code: 'categorieProduit.comment.label', default: 'Comment')}" />--}%
						%{----}%
							%{--<util:remoteSortableColumn update="listContent" action="list" property="logo" title="${message(code: 'categorieProduit.logo.label', default: 'Logo')}" />--}%
						%{----}%

						</tr>
					</thead>
					<tbody>
					<g:each in="${categorieProduitInstanceList}" var="categorieProduitInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET" id="${categorieProduitInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: categorieProduitInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: categorieProduitInstance, field: "mesures")}</td>
						
							<td><g:formatBoolean boolean="${categorieProduitInstance.actif}" /></td>
						
							%{--<td>${fieldValue(bean: categorieProduitInstance, field: "comment")}</td>--}%

                            %{--<td><img  src="${createLink(controller:'categorieProduit', action:'voirLogo', id:categorieProduitInstance?.id)}" /></td>--}%

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${categorieProduitInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.CategorieProduit" />

