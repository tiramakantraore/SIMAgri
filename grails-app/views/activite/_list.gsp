    <g:render template="/partials/showHeader" model="[canCreate:true,isList:true]" />
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'activite.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="libelle" title="${message(code: 'activite.libelle.label', default: 'Libelle')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="commentaire" title="${message(code: 'activite.commentaire.label', default: 'Commentaire')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${activiteInstanceList}" var="activiteInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET" id="${activiteInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;
                                </g:remoteLink>

                            </td>
						
							<td>${fieldValue(bean: activiteInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: activiteInstance, field: "libelle")}</td>
						
							<td>${fieldValue(bean: activiteInstance, field: "commentaire")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${activiteInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true"  domain="simagri.Activite" />
