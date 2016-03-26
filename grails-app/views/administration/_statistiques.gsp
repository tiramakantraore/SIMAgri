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
    <li><a href="#IdStatsInscrits" data-toggle="tab">Statistiques Réseaux et Membres</a></li>
    <li><a href="#IdHistoriqueSMS" data-toggle="tab">Etat synthétique SMS</a></li>
</ul>

<div class="tab-content tabContent">
    <div class="tab-pane active" id="IdStatsVisites">


                <div class="row byBodyContainer">
                    <div class="col-sm-3 col-md-3 thecontainer  no-right-padding  ">

                        <div class="bs-example" id="accordionPanel">
                            <div class="panel-group" id="accordion">


                                <div class="panel panel-default" data-render="periode">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a class="accordion-toggle" data-toggle="collapse" data-target="#collapsePeriode" >Période</a>
                                            <i class="indicator icon-minus pull-right">

                                            </i>
                                        </h4>
                                    </div>
                                    <div id="collapsePeriode" class="panel-collapse collapse in">
                                        <div class="panel-body">
                                            <g:hiddenField id="periodeSelected" name="periodeSelected" value="Le mois"/>
                                            <bill:radioBoxList referenceCollection="${['Aujourd\'hui','La semaine','Le mois','Le trimestre','L\'année']}" radioName="filterPeriodeOption" defaultValue="Le mois"
                                                               isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickPeriodeOption();"/>
                                            <br>
                                            <div>
                                                <label><input type="checkbox" name="morePeriodeOpts" value="periode"> Plus d'options </label>
                                            </div>

                                            <div id="periode" class="input-group date pull-left periodebg morePeriodeOpts box">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                                <b class="caret"></b>
                                            </div>

                                            <input type="hidden" id="fromDate" name="fromDate" class="form-control">
                                            <input type="hidden" id="toDate" name="toDate" class="form-control">

                                            <br>
                                        </div>
                                    </div>

                                    <div id="panelVisiteursUniques" class="panel panel-default" data-render="graphique" data-page="visiteurs" style="padding-top: 5px">
                                        <div class="panel-heading">
                                            <h4 class="panel-title">
                                                <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseTypeVisiteurs" >Visiteurs Uniques ?</a>
                                                <i class="indicator icon-minus pull-right">

                                                </i>
                                            </h4>
                                        </div>
                                        <div id="collapseTypeVisiteurs" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <g:hiddenField id="typeVisiteurs" name="typeVisiteurs"/>
                                                <bill:radioBoxList referenceCollection="${['Oui','Non']}" radioName="filterTypeVisiteurs"  defaultValue="Oui"
                                                                   isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickTypeVisiteurs();"/>
                                                <br>

                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                    <div id="myfluidbody" class="col-sm-9 col-md-9 no-right-padding " >
                        %{--<div class="row" >--}%
                                %{--<div id="NbVisiteurs" class="NbVisiteurs">--}%

                                %{--</div>--}%
                        %{--</div>--}%
                        %{--<div class="row" >--}%
                                <div id="statistiquesVisites">

                                </div>
                        %{--</div>--}%
                        %{--<g:counterMonthlyGraph countMonth="12" name="visits" caption="{0} Visites" caption1="1 Visite"/>--}%
                    </div>

                </div>
    </div>

    <div class="tab-pane" id="IdStatsInscrits">
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <g:render template="statistiquesInscrits"/>

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

<g:render template="statistiquesjs"/>
