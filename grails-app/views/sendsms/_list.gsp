<g:render template="/partials/showHeader"/>
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="yourTextMessage" title="${message(code: 'smsToReseaux.yourTextMessage.label', default: 'Your Text Message')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${smsToReseauxInstanceList}" var="smsToReseauxInstance">
						<tr>
                            <td class="link">
								<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
											  id="${smsToReseauxInstance.id}" class="btn-flat  btn-small">
									Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: smsToReseauxInstance, field: "yourTextMessage")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${smsToReseauxInstanceTotal}" />

				</div>
                %{--<export:formats formats="['excel', 'pdf']" />--}%
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.SmsToReseaux" />
