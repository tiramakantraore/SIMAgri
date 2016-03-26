
	<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>
        <div class="row">
			<div class="col-sm-12 col-md-12">
				


				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                %{--<filterpane:filterButton text="Rechercher" />--}%
				<table class="table" >
					<thead>
						<tr>
                        <th></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'pageUtilisateur.nom.label', default: 'Nom')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="destinationType" title="${message(code: 'pageUtilisateur.destinationType.label', default: 'Destination Type')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="photo" title="${message(code: 'monImage.photo.label', default: 'Photo')}" />


                        </tr>
					</thead>
					<tbody>
					<g:each in="${pageUtilisateurInstanceList}" var="pageUtilisateurInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET" id="${pageUtilisateurInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: pageUtilisateurInstance, field: "nom")}</td>
						
							<td>${message(code: pageUtilisateurInstance?.destinationType, default: pageUtilisateurInstance?.destinationType)} </td>

                            <td><img src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id])}"  width="100" alt=""/>
                            </td>


                        </tr>
					</g:each>
					</tbody>
				</table>
                <div class="pagination">
                    <util:remotePaginate update="listContent" action="list"  total="${pageUtilisateurInstanceTotal}" params="${filterParams}"/>

                </div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.PageUtilisateur" />

