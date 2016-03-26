<%@ page import="simagri.Marche; simagri.Reseau" %>


<div class="row">
    <div class="col-sm-4 col-md-4">
        <g:render template="periode" />
        <br>
        <input type="button"  class="btn-flat  btn-primary" onclick="tableToExcel('pvtTable', 'Tableau des prix','tableau_des_prix.xls')" value="exporter en Excel">
    </div>

</div>
<br>
<ul class="nav nav-pills" id="dashboardTab">
    <li><a href="#IdAnalysePrix" data-toggle="tab">Analyse des prix</a></li>
    <li><a href="#IdAnalyseEvolutionPrix" data-toggle="tab">Evolution des prix</a></li>
</ul>

<div class="tab-content tabContent">
    <div class="tab-pane active" id="IdAnalysePrix">
        <div class="col-sm-8 col-md-8" style="padding-top: 5px">
            <div id="parametresTableau">

            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <div id="pricePivot" class="pvtRendererArea pivotContent">
                    <div id="priceContainer"></div>
                </div>

            </div>
        </div>
    </div>

    <div class="tab-pane" id="IdAnalyseEvolutionPrix">
        <div class="row">
            <div class="col-sm-12 col-md-12">

                %{--<div class="row byBodyContainer">--}%
                    %{--<div class="col-sm-4 col-md-4 thecontainer no-left-padding no-right-padding  ">--}%

                        %{--<div class="bs-example" id="accordionPanel">--}%
                            %{--<div class="panel-group" id="accordion">--}%
                                %{--<div id="panelProduits" class="panel panel-default" data-render="liste" style="display: block">--}%
                                    %{--<div class="panel-heading">--}%
                                        %{--<h4 class="panel-title">--}%
                                            %{--<a class="accordion-toggle" data-toggle="collapse" data-target="#collapseOne" >Choix des produits</a>--}%
                                            %{--<i class="indicator icon-minus pull-right">--}%

                                            %{--</i>--}%
                                        %{--</h4>--}%
                                    %{--</div>--}%
                                    %{--<div id="collapseOne" class="panel-collapse collapse in" >--}%
                                        %{--<div class="panel-body">--}%
                                            %{--<div class="row">--}%
                                                %{--<div class="col-sm-7 col-md-7" style="font-size: 11px;">--}%
                                                    %{--<a id="check-allProducts" href="javascript:void(0);">Tous les produits</a>--}%
                                                %{--</div>--}%
                                                %{--<div class="col-sm-5 col-md-5" style="font-size: 11px;">--}%
                                                    %{--<a id="uncheck-allProducts" href="javascript:void(0);">Aucun produit </a>--}%
                                                %{--</div>--}%
                                            %{--</div>--}%
                                            %{--<div id="listeProduits" style="max-height: 400px;overflow-y:scroll ">--}%
                                                %{--<bill:checkBoxList referenceCollection="${userProductsList}" containerClass="my1Columns" instanceName="produits" onClickCheck="produitClick();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>--}%
                                            %{--</div>--}%
                                        %{--</div>--}%
                                    %{--</div>--}%
                                %{--</div>--}%
                            %{--</div>--}%
                        %{--<div class="col-sm-4 col-md-4 thecontainer no-left-padding no-right-padding  ">--}%


                                %{--<div id="panelMarches" class="panel panel-default" data-render="listeMarche" style="display: block">--}%
                                    %{--<div class="panel-heading">--}%
                                        %{--<h4 class="panel-title">--}%
                                            %{--<a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMarche" >Choix des marchés</a>--}%
                                            %{--<i class="indicator icon-minus pull-right">--}%

                                            %{--</i>--}%
                                        %{--</h4>--}%
                                    %{--</div>--}%
                                    %{--<div id="collapseMarche" class="panel-collapse collapse in" >--}%
                                        %{--<div class="panel-body">--}%
                                            %{--<div class="row">--}%
                                                %{--<div class="col-sm-7 col-md-7" style="font-size: 11px;">--}%
                                                    %{--<a id="check-allMarches" href="javascript:void(0);">Tous les marchés</a>--}%
                                                %{--</div>--}%
                                                %{--<div class="col-sm-5 col-md-5" style="font-size: 11px;">--}%
                                                    %{--<a id="uncheck-allMarches" href="javascript:void(0);">Aucun marché </a>--}%
                                                %{--</div>--}%
                                            %{--</div>--}%
                                            %{--<div id="listeMarches" style="max-height: 400px;overflow-y:scroll ">--}%
                                                %{--<bill:checkBoxList referenceCollection="${userMarketsList}" containerClass="my1Columns" instanceName="marches" onClickCheck="marcheClick();" emptyText="Il n y a pas de marché, veuillez vérifier votre profil"/>--}%
                                            %{--</div>--}%
                                        %{--</div>--}%
                                    %{--</div>--}%
                                %{--</div>--}%
                        %{--</div>--}%
                            %{--<div class="col-sm-4 col-md-4 thecontainer no-left-padding no-right-padding  ">--}%
                                %{--<div id="panelTypePrix" class="panel panel-default" data-render="graphique" data-page="prix">--}%
                                    %{--<div class="panel-heading">--}%
                                        %{--<h4 class="panel-title">--}%
                                            %{--<a class="accordion-toggle" data-toggle="collapse" data-target="#collapseTypePrix" >Type Prix</a>--}%
                                            %{--<i class="indicator icon-minus pull-right">--}%

                                            %{--</i>--}%
                                        %{--</h4>--}%
                                    %{--</div>--}%
                                    %{--<div id="collapseTypePrix" class="panel-collapse collapse in">--}%
                                        %{--<div class="panel-body">--}%
                                            %{--<g:hiddenField id="typePrix" name="typePrix" value="Detail"/>--}%
                                            %{--<bill:radioBoxList referenceCollection="${['Detail','Gros']}" radioName="filterTypePrix"  defaultValue="Detail"--}%
                                                               %{--isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickTypePrix();"/>--}%
                                            %{--<br>--}%

                                        %{--</div>--}%
                                    %{--</div>--}%
                                %{--</div>--}%
                            %{--</div>--}%
                       %{--</div>--}%
                        %{--<div class="row byBodyContainer">--}%
                            %{--<div class="col-sm-4 col-md-4 thecontainer no-left-padding no-right-padding  ">--}%
                                %{--<div id="ChoixReseau" class="panel panel-default" title="Filtrer par réseau">--}%
                                    %{--<div id="panelChoixMesure"  class="panel panel-default" data-render="reseau">--}%
                                        %{--<div class="panel-heading">--}%
                                            %{--<h4 class="panel-title">--}%
                                                %{--<a class="accordion-toggle" data-toggle="collapse" data-target="#collapseReseau">Filtrer par réseau</a>--}%
                                                %{--<i class="indicator icon-minus pull-right">--}%

                                                %{--</i>--}%
                                            %{--</h4>--}%
                                        %{--</div>--}%
                                        %{--<div id="collapseReseau" class="panel-collapse collapse in">--}%
                                            %{--<div class="panel-body">--}%
                                                %{--<g:hiddenField id="reseauSelected" name="reseauSelected" />--}%
                                                %{--<bill:radioBoxList referenceCollection="${Reseau.findAllByEstReseauAndActive(true,true)}" instanceName="reseau"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickReseauOption();" emptyText="Il n y a pas de réseaux"/>--}%

                                            %{--</div>--}%
                                        %{--</div>--}%
                                    %{--</div>--}%

                                %{--</div>--}%

                            %{--</div>--}%
                        %{--</div>--}%

                            %{--</div>--}%

                        %{--</div>--}%
                    %{--</div>--}%

            %{--</div>--}%
        <div class="row">
            <div class="col-sm-12 col-md-12">

                        <div id="EvolutionPrix">
                           <p>En cours de développement veuillez patienter</p>
                        </div>

            </div>
        </div>
       </div>
    </div>




<g:render template="dataminingprixjs"/>
