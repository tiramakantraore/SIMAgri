<%@ page import="simagri.Marche; simagri.Reseau" %>

    <style>

    /*.pvtAggregator { margin-bottom: 5px ;*/
    /*display:inline-block }*/
    /*.pvtAttrDropdown{*/
    /*display:inline-block ;*/
    /*}*/
    #showdata{
      display:none;
    }
    .collapsible{
        display: block;
    }
        p {

        }
    </style>


<ul class="nav nav-pills" id="dashboardTab">
    <li><a href="#IdStatsVisites" data-toggle="tab">Statistiques des visites</a></li>
    <li><a href="#IdHistoriqueSMS" data-toggle="tab">Etat synthétique SMS</a></li>
    <li><a href="#IdDatamining" data-toggle="tab">Datamining</a></li>


</ul>

<div id="showdata"></div>
<div class="tab-content tabContent">
    <div class="tab-pane active" id="IdStatsVisites">
        <div class="row">
            <div class="col-sm-12 col-md-12" style="padding-top: 25px">
                <g:counterMonthlyGraph countMonth="6" name="visits" caption="{0} Visites" caption1="1 Visite"/>
            </div>
        </div>
    </div>
    <div class="tab-pane" id="IdDatamining">
        <ul class="nav nav-pills" id="IdDataminingTab">
        <li><a href="#IdPrix" data-toggle="tab">Prix</a></li>
        <li><a href="#IdOffres" data-toggle="tab">Offres</a></li>
        <li><a href="#IdStocks" data-toggle="tab">Stocks</a></li>
        <li><a href="#IdPerformancesEnqueteurs" data-toggle="tab">Performances des enquêteurs</a></li>
        <li><a href="#IdAnalyseSMS" data-toggle="tab">Analyse des SMS</a></li>
        </ul>
        <div class="tab-content tabContent">
            <div class="tab-pane active" id="IdPrix">
                <div class="row">
                    <div class="col-sm-4 col-md-4">
                        <g:render template="periode" model="[periode_suffix:'Prix']"/>
                    </div>
                </div>
                <br>
                            <div id="pricePivot" class="pvtRendererArea pivotContent">
                                <div id="priceContainer" ></div>

                            </div>
                <br>
                <input type="button"  class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des prix','tableau_des_prix.xls')" value="exporter en Excel">
            </div>
            <div class="tab-pane" id="IdOffres">
                <div class="row">
                    <div class="col-sm-4 col-md-4">
                        <g:render template="periode" model="[periode_suffix:'Offre']"/>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div id="offrePivot" class="pvtRendererArea pivotContent">
                            <div id="offreContainer"></div>
                        </div>
                        <br>
                        <input type="button"  class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des offres','tableau_des_offres.xls')" value="exporter en Excel">
                    </div>
                </div>

            </div>
            <div class="tab-pane" id="IdStocks">
                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div id="stockPivot" class="pvtRendererArea pivotContent">
                            <div id="stockContainer"></div>

                        </div>
                        <br>
                        <input type="button"  class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des stocks','tableau_des_stocks.xls')" value="exporter en Excel">
                    </div>
                </div>
            </div>
            <div class="tab-pane" id="IdPerformancesEnqueteurs">
                <div class="row">
                    <div class="col-sm-4 col-md-4">
                        <g:render template="periode"/>

                    </div>
                    <div class="col-sm-3 col-md-3">
                        <g:render template="groupement"/>
                    </div>
                    <div class="col-sm-2 col-md-2">
                        <g:render template="aggregats"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12 col-md-12"  >
                        <div id="perfPivot" class="classPivotjs pivotContent" style="padding-top: 25px">
                            <div id="perfContainer"></div>
                        </div>
                        <br>
                        <input type="button" class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des performances','tableau_des_performances.xls')" value="exporter en Excel">

                    </div>

                </div>

            </div>
            <div class="tab-pane" id="IdAnalyseSMS">
                <div class="row">
                    <div class="col-sm-4 col-md-4">
                        <g:render template="periode" model="[periode_suffix:'SMS']"/>
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-sm-12 col-md-12">
                        <div id="smsPivot" class="classPivotjs pivotContent">
                            <div id="smsPivotContainer"></div>
                        </div>
                        <br>
                        <input type="button" class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des performances','tableau_des_performances.xls')" value="exporter en Excel">
                    </div>
                </div>

            </div>
        </div>


    </div>





    <div class="tab-pane" id="IdHistoriqueSMS">
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <g:render template="statistiquesSMSOperateurs"/>

            </div>
        </div>

    </div>


</div>

<g:render template="adminjs"/>
