

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'priceHolder.label', default: 'PriceHolder')}" />
		<title><g:message code="list.priceHolder"  /></title>


	</head>
	<body>
		<div class="row">
			
			%{--<div class="col-sm-3 col-md-3">--}%
				%{--<div class="well small">--}%
					%{--<ul class="nav nav-list">--}%
						%{--<li class="nav-header"><g:message code="title.priceHolder" /></li>--}%
                        %{--<li>--}%
                            %{--<g:link class="create" controller="priceFlow" action="index">--}%
                                %{-- <i class="glyphicon glyphicon-plus"></i>--}%
                                %{--<g:message code="create.priceHolder" />--}%
                            %{--</g:link>--}%
                        %{--</li>--}%

					%{--</ul>--}%
				%{--</div>--}%
			%{--</div>--}%

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

                            <util:remoteSortableColumn update="listContent" action="list" property="marche" title="${message(code: 'priceHolder.auteur.label', default: 'Auteur')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="marche" title="${message(code: 'priceHolder.marche.label', default: 'Marche')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="produit" title="${message(code: 'priceHolder.produit.label', default: 'Produit')}" />


                            <util:remoteSortableColumn update="listContent" action="list" property="isFromSMS" title="${message(code: 'priceHolder.sourcePrix.label', default: 'Source ')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="active" title="${message(code: 'priceHolder.active.label', default: 'Actif')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="isRejected" title="${message(code: 'priceHolder.isRejected.label', default: 'Est rejeté')}" />


                            <util:remoteSortableColumn update="listContent" action="list" property="date" title="${message(code: 'priceHolder.date.label', default: 'Date')}" />

						
							<util:remoteSortableColumn update="listContent" action="list" property="prixGros" title="${message(code: 'priceHolder.prixGros.label', default: 'Prix Gros')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="prixDetail" title="${message(code: 'priceHolder.prixDetail.label', default: 'Prix Détail')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="commentaire" title="${message(code: 'priceHolder.commentaire.label', default: 'Commentaires')}" />


                        </tr>
					</thead>
					<tbody>
					<g:each in="${priceHolderInstanceList}" var="priceHolderInstance">
						<tr>
                            <td class="link">
                                <g:link action="show" id="${priceHolderInstance.id}" class="btn-flat  btn-small">Modifier&raquo;</g:link>
                            </td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "auteur")}</td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "marche")}</td>
							<td>${fieldValue(bean: priceHolderInstance, field: "produit")}</td>
                            <td>${fieldValue(bean: priceHolderInstance, field: "sourcePrix")}</td>
                             <td><g:if test="${priceHolderInstance?.active}">
                                Oui
                            </g:if>
                            <g:elseif test="${!priceHolderInstance?.active}">
                                Non
                            </g:elseif>
                            </td>
                            
                            <td><g:if test="${priceHolderInstance?.isRejected}">
                                Oui
                            </g:if>
                            <g:elseif test="${!priceHolderInstance?.isRejected}">
                                Non
                            </g:elseif>
                            </td>
                            

							<td><g:formatDate date="${priceHolderInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM"/></td>


                            <td>${fieldValue(bean: priceHolderInstance, field: "prixGros")}</td>

                            <td>${fieldValue(bean: priceHolderInstance, field: "prixDetail")}</td>

                            <td>${fieldValue(bean: priceHolderInstance, field: "commentaire")}</td>

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${priceHolderInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>

    <filterpane:filterPane associatedProperties="marche.nom, produit.nom,mesure.nom,auteur.login,enqueteur.login"  excludeProperties="periodeDebut,periodeFin,commentaire,commentaireAdministrateur,isValidated,isRejected,year,month,globalPrice,transactionDate,active" dialog="true" domain="simagri.PriceHolder" />

    </body>
</html>
