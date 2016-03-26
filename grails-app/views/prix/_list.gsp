		<div class="row">
			<div class="col-sm-12 col-md-12 ">
				
				<div class="page-header">
					<h1><g:message code="list.prix" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

                <filterpane:filterButton text="Chercher"/>


				<table id="prixHtml" class="table ">
					<thead>
						<tr>
						<th></th>

							<util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="categorieTarifaire" title="${message(code: 'prix.categorieTarifaire.label', default: 'Type prix')}" />

							<util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="auteur" title="${message(code: 'prix.auteur.label', default: 'Auteur')}" />

                            <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="produit" title="${message(code: 'prix.produit.label', default: 'Produit')}" />
							<util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="mesure" title="${message(code: 'prix.mesure.label', default: 'Mesure')}" />
                            <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="marche" title="${message(code: 'prix.marche.label', default: 'Marché')}" />
						
							<util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="date" title="${message(code: 'prix.date.label', default: 'Date')}" />

                            <util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="isFromSMS" title="${message(code: 'priceHolder.sourcePrix.label', default: 'Source ')}" />
						
							<util:remoteSortableColumn update="listPrixValidesContent" controller="prix" action="list" property="montant" title="${message(code: 'prix.montant.label', default: 'Montant')}" />

                        </tr>
					</thead>
					<tbody>
					<g:each in="${prixInstanceList}" var="prixInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="prix"  action="edit" update="listPrixValidesContent" method="GET"
                                              id="${prixInstance.id}" params="{update:'listPrixValidesContent'}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>

							<td>${fieldValue(bean: prixInstance, field: "categorieTarifaire")}</td>

							<td>${fieldValue(bean: prixInstance, field: "auteur")}</td>

							<td>${fieldValue(bean: prixInstance, field: "produit")}</td>

							<td>${fieldValue(bean: prixInstance, field: "mesure")}</td>
						
							<td>${fieldValue(bean: prixInstance, field: "marche")}</td>
						
							<td><g:formatDate date="${prixInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM" /></td>
							
							<td>${fieldValue(bean: prixInstance, field: "sourcePrix")}</td>
							
						
							<td>${fieldValue(bean: prixInstance, field: "montant")}</td>

						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listPrixValidesContent" controller="prix" action="list"  total="${prixInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats controller="prix" formats="['csv', 'excel', 'pdf']" />
			</div>
		</div>
        <filterpane:filterPane  controller="prix"  associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"  excludeProperties="periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active" dialog="true" update="listPrixValidesContent" domain="simagri.PriceHolder" />

        %{--<form id="filterPaneValide" name="filterPaneValide" method="post">--}%
            %{--<filterpane:filterPane controller="prix" id="filterPaneValide"--}%
                                   %{--associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"--}%
                                   %{--excludeProperties="periodeDebut,periodeFin,commentaire,commentaireAdministrateur" dialog="true" customForm="false" domain="simagri.Prix" />--}%

            %{--<g:actionSubmit value="Appliquer" action="filter" />--}%
        %{--</form>--}%

