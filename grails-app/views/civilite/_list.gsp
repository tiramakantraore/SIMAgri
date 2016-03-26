<g:render template="/partials/showHeader" model="[beanName:'civilite',canCreate:true,isList:true]"/>

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
                            <util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'civilite.code.label', default: 'Code')}" />
							<util:remoteSortableColumn update="listContent" action="list" property="libelle" title="${message(code: 'civilite.libelle.label', default: 'Libelle')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${civiliteInstanceList}" var="civiliteInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${civiliteInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
                            <td>${fieldValue(bean: civiliteInstance, field: "code")}</td>
							<td>${fieldValue(bean: civiliteInstance, field: "libelle")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${civiliteInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Civilite" />

