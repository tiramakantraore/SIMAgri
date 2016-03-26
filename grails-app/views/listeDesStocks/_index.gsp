
    <div class="tab-pane" id="idPrixForm">

        <ul class="nav nav-pills" >

            <li><a href="#IdStocksAValider" data-toggle="tab" title="${g.message(code:"stocksAValider.text", default:"Stocks à valider")}"> ${g.message(code:"stocksAValider.text", default:"Stocks à valider")} </a></li>
            <li><a href="#IdStocksValides" data-toggle="tab" title="${g.message(code:"stocksValides.text", default:" Stocks validés")}">${g.message(code:"stocksValides.text", default:" Stocks validés")}  </a></li>
            <li><a href="#IdStocksRejetes" data-toggle="tab" title="${g.message(code:"stocksRejetes.text", default:" Stocks rejétés")}">${g.message(code:"stocksRejetes.text", default:" Stocks rejétés")} </a></li>
        </ul>
        <div class="tab-content">

            <div class="tab-pane active" id="IdStocksAValider">
                <div id="listStocksAValiderContent">
                    <div class="row">



                        <div class="col-sm-12 col-md-12">

                            <div class="page-header">
                                <h1><g:message code="list.stockMagazin" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>
                            %{--<filterpane:filterButton text="Rechercher" />--}%
                            <table class="table ">
                                <thead>
                                <tr>
                                    <th></th>
                                    <util:remoteSortableColumn update="listStocksAValiderContent" action="list" property="date" title="${message(code: 'stockMagazin.date.label', default: 'Date')}" />

                                    <th class="header"><g:message code="stockMagazin.magazin.label" default="Magazin" /></th>

                                    <th class="header"><g:message code="stockMagazin.produit.label" default="Produit" /></th>


                                    <util:remoteSortableColumn update="listStocksAValiderContent" action="list" property="stock" title="${message(code: 'stockMagazin.stock.label', default: 'Stock')}" />

                                    <th class="header"><g:message code="stockMagazin.mesure.label" default="Mesure" /></th>


                                </tr>
                                </thead>
                                <tbody>
                                <g:each in="${stockMagazinInstanceList}" var="stockMagazinInstance">
                                    <tr>
                                        <td class="link">
                                            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="stockMagazin" action="edit" update="listStocksAValiderContent" method="GET"
                                                          id="${stockMagazinInstance.id}" params="[update:'listStocksAValiderContent']" class="btn-flat  btn-small">
                                                Modifier&raquo;</g:remoteLink>
                                        </td>
                                        <td><g:formatDate date="${stockMagazinInstance.date}"/></td>

                                        <td>${fieldValue(bean: stockMagazinInstance, field: "magazin")}</td>

                                        <td>${fieldValue(bean: stockMagazinInstance, field: "produit")}</td>



                                        <td>${fieldValue(bean: stockMagazinInstance, field: "stock")}</td>

                                        <td>${fieldValue(bean: stockMagazinInstance, field: "mesure")}</td>


                                    </tr>
                                </g:each>
                                </tbody>
                            </table>
                            <div class="pagination">
                                <util:remotePaginate update="listStocksAValiderContent" controller="stockMagazin" action="list"  total="${stockMagazinInstanceTotal}" params="${filterParams}"/>

                            </div>
                            <export:formats controller="stockMagazin" action="list" formats="['csv', 'excel', 'pdf']" />
                        </div>

                    </div>
                    %{--<filterpane:filterPane controller="stockMagazin" dialog="true" domain="simagri.StockMagazin" />--}%

                </div>

             </div>
            <div class="tab-pane " id="IdStocksValides">
                <div id="listStocksValidesContent">
                    <div class="row">

                        <div class="col-sm-12 col-md-12">

                            <div class="page-header">
                                <h1><g:message code="list.stockMagazinHolderValide" /></h1>
                            </div>

                            <g:if test="${flash.message}">
                                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                            </g:if>
                            %{--<filterpane:filterButton text="Rechercher" />--}%
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
                                <util:remotePaginate update="listStocksValidesContent" controller="stockMagazin" action="listValides"  total="${stockMagazinInstanceValideTotal}" params="${filterParams}"/>

                            </div>
                            <export:formats controller="stockMagazin" action="listValides" formats="['csv', 'excel', 'pdf']" />
                        </div>

                    </div>
                    %{--<filterpane:filterPane dialog="true" controller="stockMagazin" domain="simagri.StockMagazin" />--}%

                </div>
            </div>
            <div class="tab-pane" id="IdStocksRejetes" >
            <g:render template="/stockMagazin/abandonner"/>
                   
             </div>
        </div>

    </div>
<script type="text/javascript">

    $(document).ready(function() {


       $('a[href="#IdStocksAValider"]').tab('show');
    });

</script>
