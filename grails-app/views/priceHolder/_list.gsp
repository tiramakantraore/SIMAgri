
		<div class="row">
			<div class="col-sm-12 col-md-12">
				
				<div class="page-header">
					<h1><g:message code="list.priceHolder" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>

                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="marche" title="${message(code: 'priceHolder.auteur.label', default: 'Auteur')}" />

                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="marche" title="${message(code: 'priceHolder.marche.label', default: 'Marche')}" />

                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="produit" title="${message(code: 'priceHolder.produit.label', default: 'Produit')}" />


                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="isFromSMS" title="${message(code: 'priceHolder.sourcePrix.label', default: 'Source ')}" />

                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="date" title="${message(code: 'priceHolder.date.label', default: 'Date')}" />

						
							<util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="prixGros" title="${message(code: 'priceHolder.prixGros.label', default: 'Prix Gros')}" />

                            <util:remoteSortableColumn update="listPrixAValiderContent" controller="priceHolder" action="list" property="prixDetail" title="${message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail')}" />



                        </tr>
					</thead>
					<tbody>
					<g:each in="${priceHolderInstanceList}" var="priceHolderInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="priceHolder" action="edit" update="listPrixAValiderContent" method="GET"
                                              id="${priceHolderInstance.id}" params="{update:'listPrixAValiderContent'}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "auteur")}</td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "marche")}</td>
							<td>${fieldValue(bean: priceHolderInstance, field: "produit")}</td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "sourcePrix")}</td>

                            

							<td><g:formatDate date="${priceHolderInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM"/></td>


                            <td>${fieldValue(bean: priceHolderInstance, field: "prixGros")}</td>

                            <td>${fieldValue(bean: priceHolderInstance, field: "prixDetail")}</td>


						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listPrixAValiderContent" controller="priceHolder" action="list"  total="${priceHolderInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats controller="priceHolder" formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>

    <filterpane:filterPane controller="priceHolder"  associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"  excludeProperties="periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active" dialog="true"  update="listPrixAValiderContent"  domain="simagri.PriceHolder" />

    %{--</body>--}%
%{--</html>--}%
