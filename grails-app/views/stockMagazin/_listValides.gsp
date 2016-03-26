


		<div class="row">

			<div class="col-sm-12 col-md-12">
				
				<div class="page-header">
					<h1><g:message code="list.stockMagazinHolderValide" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                            <th></th>
							<util:remoteSortableColumn update="listStocksValidesContent" action="list" property="date" title="${message(code: 'stockMagazin.date.label', default: 'Date')}" />

							<th class="header"><g:message code="stockMagazin.magazin.label" default="Magazin" /></th>
						
							<th class="header"><g:message code="stockMagazin.produit.label" default="Produit" /></th>
						

                            <util:remoteSortableColumn update="listStocksValidesContent" action="list" property="stock" title="${message(code: 'stockMagazin.stock.label', default: 'Stock')}" />

                            <th class="header"><g:message code="stockMagazin.mesure.label" default="Mesure" /></th>
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${stockMagazinInstanceValideList}" var="stockMagazinInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="stockMagazin" action="edit" update="listStocksValidesContent" method="GET"
                                              id="${stockMagazinInstance.id}" params="[update:'listStocksValidesContent']" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
							<td><g:formatDate date="${stockMagazinInstance.date}" /></td>

							<td>${fieldValue(bean: stockMagazinInstance, field: "magazin")}</td>
						
							<td>${fieldValue(bean: stockMagazinInstance, field: "produit")}</td>
						


                            <td>${fieldValue(bean: stockMagazinInstance, field: "stock")}</td>
						
							<td>${fieldValue(bean: stockMagazinInstance, field: "mesure")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listStocksValidesContent" controller="stockMagazin" action="list"  total="${stockMagazinInstanceValideTotal}" params="${filterParams}"/>

				</div>
                <export:formats controller="stockMagazin" formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" controller="stockMagazin" domain="simagri.StockMagazin" />
