
		<div class="row">

			<div class="col-sm-12 col-md-12 workarea">
				
				<div class="page-header">
					<h1><g:message code="list.offreAValider" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="date" title="${message(code: 'offre.date.label', default: 'Date de mise en ligne')}" />

							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="typeOffre" title="${message(code: 'offre.typeOffre.label', default: 'Type Offre')}" />


							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="auteur" title="${message(code: 'offre.auteur.label', default: 'Statut')}" />

                            <util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="produit" title="${message(code: 'offre.produit.label', default: 'Produit')}" />

                            <util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="isFromSMS" title="${message(code: 'offre.sourceOffre.label', default: 'Source ')}" />
							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="quantite" title="${message(code: 'offre.quantite.label', default: 'Source ')}" />
							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="mesure" title="${message(code: 'offre.mesure.label', default: 'Source ')}" />
							<util:remoteSortableColumn update="listOffresAValiderContent" action="list" property="prixUnitaire" title="${message(code: 'offre.prixUnitaire.label', default: 'Source ')}" />

								<th class="header"><g:message code="offre.montantGlobalGrosDetail.label" default="Montant total" /></th>
                        </tr>
					</thead>
					<tbody>
					<g:each in="${offreInstanceList}" var="offreInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="offre" action="edit" update="listOffresAValiderContent" method="GET"
                                              id="${offreInstance.id}" params="[update:'listOffresAValiderContent']" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
							<td>${fieldValue(bean: offreInstance, field: "date")}</td>
							<td>${fieldValue(bean: offreInstance, field: "typeOffre")}</td>

                            
                            <td>${fieldValue(bean: offreInstance, field: "auteur")}</td>
						
							<td>${fieldValue(bean: offreInstance, field: "produit")}</td>

                            <td>${fieldValue(bean: offreInstance, field: "sourceOffre")}</td>

							<td>${fieldValue(bean: offreInstance, field: "quantite")}</td>
							<td>${fieldValue(bean: offreInstance, field: "mesure")}</td>
							<td>${fieldValue(bean: offreInstance, field: "prixUnitaire")}</td>
                             %{--<td><g:formatDate date="${offreInstance.dateValidation}" type="datetime" style="MEDIUM" timeStyle="MEDIUM"/></td>--}%

                            %{--<td><g:formatDate date="${offreInstance.dateExpiration}" type="datetime" style="MEDIUM" timeStyle="MEDIUM"/></td>--}%

							%{--<td>${fieldValue(bean: offreInstance, field: "prixUnitGrosDetail")}</td>--}%

                            <td>${fieldValue(bean: offreInstance, field: "montantGlobalGrosDetail")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listOffresAValiderContent" controller="offre" action="list"  total="${offreInstanceTotal}" params="${filterParams}"/>
				</div>

                <export:formats controller="offre" formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>

        <filterpane:filterPane controller="offre"  associatedProperties="produit.nom, lieuStockage.nom,mesure.nom,qualite.nom,origine.nom,lieuLivraison.nom,auteur.login"
        excludeProperties="email,delaiEnJours,quantite,dateValidation, dateExpiration,delaiLivraison,isValidated,isRejected,coutTransport,commentaire,conditions,prixUnitaireGros,prixTotalGros,contact,IsForSell" dialog="true" domain="simagri.Offre" />
