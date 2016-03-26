<%@ page import="simagri.Region; simagri.CategorieProduit; simagri.Reseau" %>
%{--<asset:javascript src="dashboardjs.js"/>--}%
%{--<asset:javascript src="calendarAccueil.js"/>--}%


<div class="row byBodyContainer">
<div class="col-sm-3 col-md-3 thecontainer no-left-padding no-right-padding  ">

    <div class="bs-example" id="accordionPanel">
        <div class="panel-group" id="accordion">

            <div id="panelCategorieProduits" class="panel panel-default" style="display: none">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#categorie" >Catégories de produits</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="categorie" class="panel-collapse collapse in" >
                    <div class="panel-body">

                            <div id="listeCategories" style="max-height: 400px;overflow-y:scroll ">
                                <bill:checkBoxList referenceCollection="${CategorieProduit.findAllByActif(true)}" containerClass="my1Columns"  instanceName="categories" onClickCheck="categorieClick();"  />
                            </div>

                    </div>
                </div>
            </div>


            <div id="panelProduits" class="panel panel-default" data-render="liste" style="display: block">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseOne" >Choix des produits</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in" >
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-7 col-md-7" style="font-size: 11px;">
                                <a id="check-allProducts" href="javascript:void(0);">Tous les produits</a>
                            </div>
                            <div class="col-sm-5 col-md-5" style="font-size: 11px;">
                                <a id="uncheck-allProducts" href="javascript:void(0);">Aucun produit </a>
                            </div>
                        </div>
                        <div id="listeProduits" style="max-height: 400px;overflow-y:scroll ">
                        <bill:checkBoxList referenceCollection="${userProductsList}" containerClass="my1Columns" instanceName="produits" onClickCheck="produitClick();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>
                         </div>
                    </div>
                </div>
            </div>


            <div id="panelRegions" class="panel panel-default" data-render="liste" style="display: none">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#region" >${g.message(code:"regions.text", default:"Régions")}</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="region" class="panel-collapse collapse in" >
                    <div class="panel-body">

                        <div id="listeRegions" style="max-height: 400px;overflow-y:scroll ">
                            <bill:checkBoxList referenceCollection="${Region.list()}" containerClass="my1Columns" instanceName="regions" onClickCheck="regionClick();"  />
                       </div>

                    </div>
                </div>
            </div>

            <div id="panelMarches" class="panel panel-default" data-render="listeMarche" style="display: block">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMarche" >${g.message(code:"choixMarches.text", default:"Choix des marchés")}</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapseMarche" class="panel-collapse collapse in" >
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-7 col-md-7" style="font-size: 11px;">
                                <a id="check-allMarches" href="javascript:void(0);">${g.message(code:"tousLesMarches.text", default:"Tous les marchés")}</a>
                            </div>
                            <div class="col-sm-5 col-md-5" style="font-size: 11px;">
                                <a id="uncheck-allMarches" href="javascript:void(0);">${g.message(code:"aucunMarche.text", default:"Aucun marché")}</a>
                            </div>
                        </div>
                        <div id="listeMarches" style="max-height: 400px;overflow-y:scroll ">
                            <bill:checkBoxList referenceCollection="${userMarketsList}" containerClass="my1Columns" instanceName="marches" onClickCheck="marcheClick();" emptyText="Il n y a pas de marché, veuillez vérifier votre profil"/>
                        </div>
                    </div>
                </div>
            </div>
            <div id="panelMagazin" class="panel panel-default" data-render="listeMagazin" style="display: block">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseMagazin" >Choix des magazins</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapseMagazin" class="panel-collapse collapse in" >
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-7 col-md-7" style="font-size: 11px;">
                                <a id="check-allMagazins" href="javascript:void(0);">Tous les magazins</a>
                            </div>
                            <div class="col-sm-5 col-md-5" style="font-size: 11px;">
                                <a id="uncheck-allMagazins" href="javascript:void(0);">Aucun magazin </a>
                            </div>
                        </div>
                        <g:hiddenField id="magazinsSelected" name="magazinsSelected" />
                        <div id="listeMagazins" style="max-height: 400px;overflow-y:scroll ">
                            <bill:checkBoxList referenceCollection="${userMagazinList}" containerClass="my1Columns" instanceName="magazins" onClickCheck="magazinClick();" emptyText="Il n y a pas de magazin, veuillez vérifier votre profil"/>
                        </div>
                    </div>
                </div>
            </div>

            %{--<div id="panelChoixProduit"  class="panel panel-default" data-render="graphique" style="display: none">--}%
                %{--<div class="panel-heading">--}%
                    %{--<h4 class="panel-title">--}%
                        %{--<a class="accordion-toggle" data-toggle="collapse" data-target="#collapseZero">Choix du produit</a>--}%
                        %{--<i class="indicator icon-minus pull-right">--}%

                        %{--</i>--}%
                    %{--</h4>--}%
                %{--</div>--}%
                %{--<div id="collapseZero" class="panel-collapse collapse in">--}%
                    %{--<div class="panel-body">--}%
                        %{--<g:hiddenField id="produitSelected" name="produitSelected" value="${userProductsList[0]?.id}"/>--}%
                        %{--<div style="max-height: 400px;overflow-y:scroll ">--}%
                        %{--<bill:radioBoxList referenceCollection="${userProductsList}" instanceName="produit" defaultValue="${userProductsList[0]?.id}" containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickProduitOption();" emptyText="Il n y a pas de produits, veuillez vérifier votre profil"/>--}%
                        %{--</div>--}%
                    %{--</div>--}%
                %{--</div>--}%
            %{--</div>--}%

            <div id="panelTypePrix" class="panel panel-default" data-render-type="price" data-page="prix">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseTypePrix" >Type Prix</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapseTypePrix" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <g:hiddenField id="typePrix" name="typePrix" value="Detail"/>
                        <bill:radioBoxList referenceCollection="${["Detail","Gros",""]}" radioName="filterTypePrix" defaultValue="Detail"
                                           isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickTypePrix();"/>

                        %{--<bill:radioBoxList referenceCollection="${['Detail','Gros']}" radioName="filterTypePrix" defaultValue="Detail"--}%
                                           %{--isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickTypePrix();"/>--}%
                        <br>

                    </div>
                </div>
            </div>


            <div id="panelTypeOffre" class="panel panel-default"  data-render-type="offre" data-page="offre">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseTypeOffre">Type Offre</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapseTypeOffre" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <g:hiddenField id="typeOffre" name="typeOffre" value="Achat"/>
                        <bill:radioBoxList referenceCollection="${['Achat','Vente',""]}" radioName="filterTypeOffre" defaultValue="Achat"
                                           isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickTypeOffre();"/>
                        <br>

                    </div>
                </div>
            </div>

            <div class="panel panel-default" data-render="periode">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapsePeriode" >${g.message(code:"periode.text", default:"Période")}</a>
                        <i class="indicator icon-minus pull-right">

                        </i>
                    </h4>
                </div>
                <div id="collapsePeriode" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <g:hiddenField id="periodeSelected" name="periodeSelected" value="Le mois"/>
                        <bill:radioBoxList referenceCollection="${['Aujourd\'hui','La semaine','Le mois','Le trimestre',"${g.message(code: "annee.text", default:"L''annnée")}"]}" radioName="filterPeriodeOption" defaultValue="Le mois"
                                           isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickPeriodeOption();"/>
                        <br>
                        <div>
                            <label><input type="checkbox" name="morePeriodeOpts" value="periode"> Plus d'options </label>
                        </div>

                            <div id="periodePrix" class="input-group date pull-left periodebg morePeriodeOpts box">
                                <span class="glyphicon glyphicon-calendar"></span>
                                <b class="caret"></b>
                            </div>

                            <input type="hidden" id="fromDatePrix" name="fromDatePrix" class="form-control">
                            <input type="hidden" id="toDatePrix" name="toDatePrix" class="form-control">

                            <br>
                    </div>
                </div>
            </div>

            <div id="ChoixReseau" class="panel panel-default" title="Filtrer par réseau">
                <div id="panelChoixMesure"  class="panel panel-default" data-render="reseau">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseReseau">${g.message(code:"filtrerParReseau.text", default:"Filtrer par réseau")}</a>
                            <i class="indicator icon-minus pull-right">

                            </i>
                        </h4>
                    </div>
                    <div id="collapseReseau" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <g:hiddenField id="reseauSelected" name="reseauSelected" />
                                <bill:radioBoxList referenceCollection="${Reseau.findAllByEstReseauAndActive(true,true)}" instanceName="reseau"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickReseauOption();" emptyText="Il n y a pas de réseaux"/>

                        </div>
                    </div>
                </div>

            </div>

            <div class="panel panel-default" data-render="filtre">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="accordion-toggle" data-toggle="collapse" data-target="#collapseFiltre">${g.message(code:"filtrerParMotCle.text", default:"Filtrer par mot clé")}
                        </a>
                        <i class="indicator icon-minus pull-right">

                        </i>

                    </h4>
                </div>
                <div id="collapseFiltre" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <g:hiddenField id="columnSelected" name="columnSelected" value="Auteur"/>
                        <bill:radioBoxList referenceCollection="${['Auteur','Note',""]}" radioName="filterOption" defaultValue="${g.message(code:"telephone.text", default:"Téléphone")}"
                                           isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclickOption();"/>
                        %{--<br>--}%
                        <div id="searchbox" class="input-group my-search-group">
                            <input id="search" name="search" type="text" class="form-control"  placeholder="Chercher">
                           <div class="input-group-btn">
                                <div  class="btn-success btn-search-flat" onclick="onSearchChange()">
                                    <i class="glyphicon glyphicon-search"></i>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            </div>

    </div>
</div>
    <div id="myfluidbody" class="col-sm-9 col-md-9 no-left-padding no-right-padding" >

        <g:if test="${flash.welcomemessage}">
            <div class="row">
                <bootstrap:alert class="alert-info">${flash.welcomemessage}</bootstrap:alert>
            </div>
        </g:if>
        <p id="error"></p>
        <g:render template="/dashboard/enteteGraphiquesForm"/>
        <br/>
        <div id="accordionHtml" class="row">

        </div>

        <g:hiddenField id="nbMonthLastPrices" name="nbMonthLastPrices" value="${nombreMoisDerniersPrix}" />
        <g:hiddenField id="categorieTarifaire" name="categorieTarifaire"/>

    </div>

</div>
<g:render template="/dashboard/mydashboardjs"/>

