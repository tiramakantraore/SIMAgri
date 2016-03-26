<%@ page import="simagri.Region; simagri.CategorieProduit; simagri.Service; simagri.Magazin; simagri.Produit; simagri.Marche" %>

    <style>

    .groupe-section {
        display: inline-block;
        vertical-align: top;
        width: 100%;
        /*min-height: 250px;*/
        text-align: left;
        margin: 0 0.25em;
    }
    </style>
    %{--<div class="container">--}%
		<div class="row byBodyContainer">
			<div class="col-sm-12 col-md-12">

                <div class="page-header">
                    <h1><g:message code="create.${suffixe}"/></h1>
                </div>
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${utilisateurInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${utilisateurInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

                <g:form class="form-stacked-horizontal" action="create" name="create" enctype="multipart/form-data" >
                    <g:hiddenField name="suffixe" value="${suffixe}" />
                    %{--<g:hiddenField name="role" value="${userType}" />--}%
                    <g:hiddenField name="isChange" value="${isChange}" />
                    <g:hiddenField name="isCreation" value="${isCreation}" />
                    <div class="form-actions">
                        <div  class="btn-flat  btn-primary validerCreate">

                            <g:message code="default.button.create.label" default="Create" />
                        </div>
                    </div>

                            <ul class="nav nav-pills" id="dashboardTab">
                                <li class="active"><a href="#IdIdentification" data-toggle="tab">Identification</a></li>
                                <li class="nav-divider"></li>
                                <li><a href="#IdAdresse" data-toggle="tab">Mon adresse</a></li>
                                <li><a href="#IdGroupes" data-toggle="tab">Mes groupes</a></li>
                                <li><a href="#IdMarches" data-toggle="tab">${g.message(code:"mesMarches.label", default:"Mes marchés")}</a></li>
                                <li><a href="#IdProduits" data-toggle="tab">Mes produits</a></li>
                                <li><a href="#IdMagazins" data-toggle="tab">Mes magazins</a></li>
                                <li><a href="#IdServices" data-toggle="tab">Mes services</a></li>
                            </ul>

                            <div class="tab-content tabContent">
                                <div class="tab-pane active" id="IdIdentification">

                                                <g:render template="identification" model="[isEditing:false]" />

                               </div>
                                <div class="tab-pane" id="IdAdresse">

                                    <div class="row">
                                        <div class="col-sm-8 col-md-8">
                                            <g:render template="adresse" />
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="IdGroupes">
                                    <g:render template="choixgroupes" />
                                    %{--<div class="row" style="margin-top: 10px">--}%
                                        %{--<div class="col-sm-12 col-md-12">--}%
                                            %{--<g:hiddenField name="groupsId" />--}%
                                                    %{--<div  style="min-height:250px">--}%
                                                    %{--<g:message code="choisir.reseau"/>--}%
                                                            %{--<div  class="k-content" >--}%
                                                                        %{--<div id="treeview" class="groupe-section"></div>--}%
                                                                        %{--<div id="result" class="groupe-section"></div>--}%

                                                                        %{--<div class="groupe-section">--}%
                                                                        %{--</div>--}%
                                                            %{--</div>--}%
                                                    %{--</div>--}%
                                        %{--</div>--}%
                                    %{--</div>--}%

                                </div>
                                <div class="tab-pane" id="IdMarches">
                                    <div class="row">

                                        <div class="col-sm-11 col-md-11 col-sm-offset1 col-md-offset1">
                                            <div id="listeRegions" style="max-height: 400px;overflow-y:scroll ">
                                                <bill:checkBoxList referenceCollection="${Region.list()}" containerClass="my4Columns" title="${g.message(code:"regions.text", default:"Régions")}" instanceName="regions" onClickCheck="regionClick();"  />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allMarkets" href="javascript:void(0);">${g.message(code:"tousLesMarches.text", default:"Tous les marchés")}</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allMarkets" href="javascript:void(0);">${g.message(code:"aucunMarche.text", default:"Aucun marché")}</a>
                                        </div>
                                    </div>

                                    <div id="markets">
                                         %{--<bill:checkBoxList referenceCollection="${MarcheList}" containerClass="${ctnerTemplate} limitHeight" instanceName="markets" />--}%
                                    </div>
                                </div>
                                <div class="tab-pane" id="IdProduits">

                                    <div class="row">

                                         <div class="col-sm-11 col-md-11 col-sm-offset1 col-md-offset1">
                                             <div id="listeCategories" style="max-height: 400px;overflow-y:scroll ">
                                                 <bill:checkBoxList referenceCollection="${CategorieProduit.findAllByActif(true)}" containerClass="my4Columns" title="${g.message(code:"categoriesProduits.label", default:"Catégories de produits")}" instanceName="categories" onClickCheck="categorieClick();"  />
                                             </div>
                                         </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allProduits" href="javascript:void(0);">Tous les produits</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allProduits" href="javascript:void(0);">Aucun produit </a>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-11 col-md-11 col-sm-offset1 col-md-offset1">
                                            <div id="produits">
                                            %{--<bill:checkBoxList referenceCollection="${ProduitList}" containerClass="${ctnerTemplateProd} " instanceName="produits" />--}%
                                            </div>
                                        </div>
                                    </div>




                                </div>
                                <div class="tab-pane" id="IdMagazins">
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allMagazins" href="javascript:void(0);">Tous les magazins</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allMagazins" href="javascript:void(0);">Aucun magazin </a>
                                        </div>
                                    </div>
                                    <bill:checkBoxList referenceCollection="${MagazinList}" containerClass="${ctnerTemplateMag} " instanceName="magazins" />

                                </div>

                                <div class="tab-pane" id="IdServices">
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allServices" href="javascript:void(0);">Tous les services</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allServices" href="javascript:void(0);">Aucun service </a>
                                        </div>
                                    </div>
                                    <bill:checkBoxList referenceCollection="${ServicesList}" containerClass="${ctnerTemplateService} " instanceName="services" />

                                </div>
                            </div>
                    <div class="form-actions">
                        <div class="btn-flat  btn-primary validerCreate">

                            <g:message code="default.button.create.label" default="Create" />
                        </div>
                    </div>
                </g:form>
                        </div>
                    </div>




	%{--</div>--}%
    <g:render template="userjs"/>
