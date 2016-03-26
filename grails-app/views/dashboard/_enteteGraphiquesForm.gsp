<%@ page import="simagri.Mesure; simagri.S3Asset; simagri.Produit; simagri.Marche" %>

            <ul class="nav nav-pills" id="dashboardTab">
                <li><a href="#IdPrix" data-toggle="tab" title="Informations relatives aux prix">Prix</a></li>
                <li><a href="#IdOffres" data-toggle="tab" title="Informations relatives aux offres">Offres</a></li>
                <li><a href="#IdStocks" data-toggle="tab" title="Informations relatives aux stocks">Stocks</a></li>
                <li><a href="#IdNotes" data-toggle="tab" title="Dernières Notes">${g.message(code:"notes.text", default:"Notes")}
                    <g:if test="${isNewNote}">
                        <i class="glyphicon glyphicon-flag" ></i>
                    </g:if>
                </a></li>
                <li><a href="#IdActualite" data-toggle="tab" title="Dernières actualités">${g.message(code:"actualites.text", default:"Actualités")}
                    <g:if test="${isNewInfo}">
                    <i class="glyphicon glyphicon-flag" ></i>
                    </g:if>
                </a></li>
            </ul>

            <div class="tab-content tabContent">
                <div class="tab-pane active" id="IdPrix">
                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom:5px">
                        <div id="Affichage" data-render="affichage" title="Mode Affichage tableau" class="col-sm-6 col-md-6">

                            <div id="panelOrientationTabPrix" class="panel panel-default" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseOrientationPrix" >Mode Affichage Tableau</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseOrientationPrix" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-sm-6 col-md-6">
                                                <g:hiddenField id="orientationTabPrix" name="orientationTabPrix" value="Simple"/>
                                                <bill:radioBoxList referenceCollection="${['Simple',"${g.message(code:"croise.text", default:"Croisé")}"]}" radioName="filterorientationTabPrix"  defaultValue="Simple"
                                                                   isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickOrientationTabPrix();"/>
                                                <br>
                                            </div>
                                            <div class="col-sm-6 col-md-6" >
                                                %{--<div style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-prix_length">--}%

                                                %{--</div>--}%
                                            </div>
                                        </div>
                                        <div class="row">
                                            %{--<div class="col-sm-1 col-md-1">--}%

                                            %{--</div>--}%
                                            <div class="col-sm-12 col-md-12">
                                                <div  id="my-pivot-table-prix_length">
                                                    %{--<div style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-prix_length">--}%

                                                    %{--</div>--}%
                                                </div>
                                            </div>

                                        </div>
                                        %{--<div class="col-sm-4 col-md-4" style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-prix_length">--}%

                                        %{--</div>--}%
                                    </div>
                                </div>


                            </div>

                        </div>


                    </div>

                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom:5px">
                        <div id="AffichageChoixProduit" data-render="AffichageChoixProduit" title="Choix du produit" class="col-sm-12 col-md-12">
                            <div id="panelProduit" class="panel panel-default" data-render="listeProduit" style="display: none">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseProduit" >${g.message(code:"selectionnerProd.text", default:"Sélectionnez le produit")}</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseProduit" class="panel-collapse collapse in" >
                                    <div class="panel-body">
                                        <g:hiddenField id="produitSelected" name="produitSelected" />

                                        <div id="produit" style="max-height: 550px;overflow-y:scroll ">
                                            <bill:radioBoxList referenceCollection="${userProductsList}" containerClass="${ctnerTemplateProd}" instanceName="produit" onClickRadio="produitChoisiClick();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                            <div class="row no-left-padding">
                                <div id="btnPrixListe" title="afficher le tableau des prix" class="col-sm-1 col-md-1 liste active" onclick="onClickListe();" style="margin-left:15px; display:none">

                                </div>
                                <div id="btnPrixHisto"  title="afficher l'histogramme des prix" class="col-sm-1 col-md-1  histo" onclick="onClickHisto();">

                                </div>
                                <div id="btnPrixCamembert" title="afficher le camembert des prix" class="col-sm-1 col-md-1  camembert" onclick="onClickCamembert();">

                                </div>
                            </div>
                                    <g:render template="prix" model="model" />

                </div>
                <div class="tab-pane" id="IdOffres">


                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom: 5px">
                        <div id="ChoixMesure" title="Filtrer par mesure" class="col-sm-8 col-md-8 ">
                            <div id="panelChoixMesure"  class="panel panel-default" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMesure">Filtrer par mesure</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseMesure" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <g:hiddenField id="mesureSelected" name="mesureSelected" />
                                        <div style="max-height: 400px;overflow-y:scroll ">
                                            <bill:radioBoxList referenceCollection="${Mesure.findAllByIsConvertible(true)}" instanceName="mesure"  containerClass="my4Columns" labelClass="labelClass" onClickRadio="onclickMesureOption();" emptyText="Il n y a pas de mesures, veuillez vérifier votre profil"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div id="AffichageOffres"  data-render="affichage" title="Mode Affichage tableau" class="col-sm-4 col-md-4">

                            <div id="panelOrientationTabOffres" class="panel panel-default" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseOrientationOffres" >Mode Affichage Tableau</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseOrientationOffres" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                                <div  style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-offre_length">

                                                </div>
                                    </div>
                                </div>


                            </div>

                        </div>
                   </div>
                    <div class="row no-left-padding">
                        <div id="btnOffreListe"  title="afficher le tableau des offres" class="col-sm-1 col-md-1 liste active" onclick="onClickOffreListe();" style="margin-left:15px; display:none">

                        </div>
                        <div id="btnOffreHisto"  title="afficher l'histogramme des offres" class="col-sm-1 col-md-1  histo" onclick="onClickOffreHisto();">

                        </div>
                        <div id="btnOffreCamembert"  title="afficher le camembert des offres" class="col-sm-1 col-md-1  camembert" onclick="onClickOffreCamembert();">

                        </div>
                    </div>
                    <g:render template="offres" model="model" />
                </div>
                <div class="tab-pane" id="IdStocks">
                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom: 5px">
                        <div id="ChoixMesureStock" title="Filtrer par mesure" class="col-sm-8 col-md-8 ">
                            <div id="panelChoixMesureStock"  class="panel panel-default" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMesureStock">Filtrer par mesure</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseMesureStock" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <g:hiddenField id="mesureSelectedStock" name="mesureSelectedStock" />
                                        <div style="max-height: 400px;overflow-y:scroll ">
                                            <bill:radioBoxList referenceCollection="${Mesure.findAllByIsConvertible(true)}" instanceName="mesureStock"  containerClass="my4Columns" labelClass="labelClass" onClickRadio="onclickMesureStockOption();" emptyText="Il n y a pas de mesures, veuillez vérifier votre profil"/>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div id="AffichageStocks"  data-render="affichage" title="Mode Affichage tableau" class="col-sm-4 col-md-4">

                            <div id="panelOrientationTabStocks" class="panel panel-default" >
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseOrientationStocks" >Mode Affichage Tableau</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseOrientationStocks" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div  style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-stock_length">

                                        </div>
                                    </div>
                                </div>


                            </div>

                        </div>
                    </div>
                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom:5px">
                        <div id="AffichageChoixProduitStock" data-render="AffichageChoixProduit" title="Choix du produit" class="col-sm-12 col-md-12">
                            <div id="panelProduitStock" class="panel panel-default" data-render="listeProduit" style="display: none">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseProduitStock" >${g.message(code:"selectionnerProd.text", default:"Sélectionnez le produit")}</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseProduitStock" class="panel-collapse collapse in" >
                                    <div class="panel-body">
                                        <g:hiddenField id="produitStockSelected" name="produitStockSelected" />

                                        <div id="produitStock" style="max-height: 400px;overflow-y:scroll ">
                                            <bill:radioBoxList referenceCollection="${userProductsList}" containerClass="${ctnerTemplateProd}" instanceName="produitStock" onClickRadio="produitStockChoisiClick();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row no-left-padding" style="padding-top: 15px;padding-bottom: 5px;">
                                <div id="btnStockListe" title="afficher le tableau des stocks" class="col-sm-1 col-md-1 liste active" onclick="onClickStockListe();" style="margin-left:15px; display:none">

                                </div>
                                <div id="btnStockHisto" title="afficher l'histogramme des stocks" class="col-sm-1 col-md-1  histo" onclick="onClickStockHisto();">

                                </div>
                                <div id="btnStockCamembert" title="afficher le camembert des stocks" class="col-sm-1 col-md-1  camembert" onclick="onClickStockCamembert();">

                                </div>

                    </div>
                    <g:render template="stocks" model="model" />


                </div>

                <div class="tab-pane" id="IdNotes">
                    <div class="row">
                        <div class="col-sm-9 col-md-9 ">
                            <div id="listeNotes">
                                <g:render template="notesList" model="['lesNotes':topNotes]"/>
                            </div>
                        </div>
                    </div>
                    <div class="pagination">
                        <util:remotePaginate total="${topNotes?.size()?:0}" action="filterNotes"
                                             update="actualitesUpdate" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>

                    </div>
                </div>

                <div class="tab-pane" id="IdActualite">
                        <div class="row">
                          <div class="col-sm-9 col-md-9 ">
                              <div id="listeActualites">
                               <g:render template="actualitesList" model="['lesInfos':topNInfos]"/>
                              </div>
                          </div>
                        </div>
                    <div class="pagination">
                    <util:remotePaginate total="${topNInfos?.size()?:0}" action="filterInfos"
                    update="actualitesUpdate" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>

                    </div>
                </div>

            </div>
