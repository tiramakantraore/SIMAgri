
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.role" /></li>

						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.role" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="col-sm-8 col-md-8">
				
				<div class="page-header">
					<h1><g:message code="list.role" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="authority" title="${message(code: 'role.authority.label', default: 'Authority')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="description" title="${message(code: 'role.description.label', default: 'Description')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${roleInstanceList}" var="roleInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${roleInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: roleInstance, field: "authority")}</td>
						
							<td>${fieldValue(bean: roleInstance, field: "description")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${roleInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Role" />
