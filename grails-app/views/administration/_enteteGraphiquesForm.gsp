<%@ page import="simagri.Mesure; simagri.S3Asset; simagri.Produit; simagri.Marche" %>

            <ul class="nav nav-pills" id="dashboardTab">
                <li><a href="#IdTableauPrix" data-toggle="tab" title="Tableau des prix">Prix</a></li>
                <li><a href="#IdAnalysePrix" data-toggle="tab" title="Analyse des prix">Prix</a></li>
                <li><a href="#IdPrixEvolution" data-toggle="tab" title="Courbe d'évolution des prix">Offres</a></li>

            </ul>

            <div class="tab-content tabContent">
                <div class="tab-pane active" id="IdTableauPrix">
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
                                            <div class="col-sm-4 col-md-4">
                                                <g:hiddenField id="orientationTabPrix" name="orientationTabPrix" value="Simple"/>
                                                <bill:radioBoxList referenceCollection="${['Simple','Croisé']}" radioName="filterorientationTabPrix"  defaultValue="Simple"
                                                                   isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickOrientationTabPrix();"/>
                                                <br>
                                            </div>
                                            <div class="col-sm-6 col-md-6">
                                                <div style="padding-top: 15px;padding-bottom:5px" id="my-pivot-table-prix_length">

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
                                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseProduit" >Sélectionnez le produit</a>
                                        <i class="indicator icon-minus pull-right">

                                        </i>
                                    </h4>
                                </div>
                                <div id="collapseProduit" class="panel-collapse collapse in" >
                                    <div class="panel-body">
                                        <g:hiddenField id="produitSelected" name="produitSelected" />

                                        <div id="produit" style="max-height: 400px;overflow-y:scroll ">
                                            <bill:radioBoxList referenceCollection="${userProductsList}" containerClass="${ctnerTemplateProd} limitHeightProd" instanceName="produit" onClickRadio="produitChoisiClick();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>
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
                <div class="tab-pane" id="IdAnalysePrix">


                    <p>En cours de développement </p>

               </div>
                <div class="tab-pane" id="IdPrixEvolution">


                    <p>En cours de développement </p>

                </div>
 </div>

