<%@ page import="simagri.Service; simagri.UtilisateurMagazin; simagri.UtilisateurProduit; simagri.UtilisateurMarche; simagri.Magazin; simagri.Produit; simagri.Marche" %>

%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="profilLayout">--}%
		%{--<g:set var="entityName" value="${message(code: 'utilisateur.label', default: 'Utilisateur')}" />--}%
		%{--<title><g:message code="show.${suffixe}" /></title>--}%
        <style>
        /*#example {*/
            /*text-align: center;*/
        /*}*/

        .groupe-section {
            display: inline-block;
            vertical-align: top;
            width: 100%;
            /*background-color:black;*/
            /*min-height: 250px;*/
            text-align: left;
            margin: 0 0.25em;
        }
        .limitHeight{
            max-height: 350px;
            overflow-y:scroll ;
        }
        .box{
            padding: 10px;
            display: none;
            margin-top: 10px;
            /*border: 1px solid #eddba7;*/
        }
        .changepwd{
            /*background: #ffecb4;*/
        }

        </style>
	%{--</head>--}%
	%{--<body>--}%
		<div class="row byBodyContainer">
            <g:if test="${flash.messageErreur}">
                <bootstrap:alert class="alert-info">${flash.messageErreur}</bootstrap:alert>
            </g:if>

			<div class="col-sm-12 col-md-12">


            <div class="page-header">
                <h3><g:message code="show.${suffixe}"/><strong> ${utilisateurInstance}</strong></h3>
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

                <g:form class="form-stacked-vertical"  name="show" >
                    <div class="form-actions">
                        <div class="btn-link modifier">
                            <g:message code="default.button.edit.label" default="Edit" />
                        </div>
                        %{--<g:remoteLink controller="utilisateur" action="edit" id="${utilisateurInstance?.id}"  params="{userType:$userType,isCreation:false,suffixe:$suffixe}" class="btn btn-link" update="simagriIndex" onLoading="showSpinner();"--}%
                                      %{--onComplete="hideSpinner()"> <g:message code="default.button.edit.label" default="Edit"/>--}%
                        %{--</g:remoteLink>--}%

                    </div>
                    <g:hiddenField name="version" value="${utilisateurInstance?.version}" />
                    <g:hiddenField name="ReseauxIds"/>
                    <g:hiddenField name="password" value="${utilisateurInstance?.password}"/>
                    <g:hiddenField name="theId" value="${utilisateurInstance?.id}" />
                    <g:hiddenField name="userType" value="${userType}" />
                    <g:hiddenField name="suffixe" value="${suffixe}" />
                    <g:hiddenField name="isChange" value="${isChange}" />


                            <ul class="nav nav-pills" id="dashboardTab">
                                <li class="active"><a href="#IdIdentification" data-toggle="tab">Identification</a></li>
                                <li><a href="#IdAdresse" data-toggle="tab">Mon adresse</a></li>
                                <li><a href="#IdGroupes" data-toggle="tab">Mes groupes</a></li>
                                <li><a href="#IdMarches" data-toggle="tab">Mes marchés</a></li>
                                <li><a href="#IdProduits" data-toggle="tab">Mes produits</a></li>
                                <li><a href="#IdMagazins" data-toggle="tab">Mes magazins</a></li>
                                <li><a href="#IdServices" data-toggle="tab">Mes services</a></li>
                            </ul>

                            <div class="tab-content tabContent">
                                <div class="tab-pane active" id="IdIdentification">

                                    <div class="row">
                                        <div class="col-sm-12 col-md-12">
                                            <g:render template="identificationshow" model="[isShowing:true]"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="IdAdresse">

                                    <div class="row">
                                        <div class="col-sm-8 col-md-8">
                                            <g:render template="adresseshow" />
                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="IdGroupes">
                                    <g:render template="choixgroupes" />

                                </div>
                                <div class="tab-pane" id="IdMarches">
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allMarkets" href="javascript:void(0);">Tous les marchés</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allMarkets" href="javascript:void(0);">Aucun marché </a>
                                        </div>
                                    </div>

                                    <bill:checkBoxList referenceCollection="${MarcheList}" containerClass="${ctnerTemplate} limitHeight" instanceName="markets"/>

                                </div>
                                <sec:ifSuperViseur>
                                    <div class="tab-pane" id="IdMarchesMiseEnLigne">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-3">
                                                <a id="check-allMarketsMiseEnLigne" href="javascript:void(0);">Tous les marchés</a>
                                            </div>
                                            <div class="col-sm-3 col-md-3">
                                                <a id="uncheck-allMarketsMiseEnLigne" href="javascript:void(0);">Aucun marché </a>
                                            </div>
                                        </div>

                                        <bill:checkBoxList referenceCollection="${MarcheList}" containerClass="${ctnerTemplate} limitHeight" instanceName="marchesEcriture"/>
                                    </div>
                                </sec:ifSuperViseur>
                                <div class="tab-pane" id="IdProduits">
                                    <div class="row">
                                        <div class="col-sm-3 col-md-3">
                                            <a id="check-allProduits" href="javascript:void(0);">Tous les produits</a>
                                        </div>
                                        <div class="col-sm-3 col-md-3">
                                            <a id="uncheck-allProduits" href="javascript:void(0);">Aucun produit </a>
                                        </div>
                                    </div>

                                    <bill:checkBoxList referenceCollection="${ProduitList}" containerClass="${ctnerTemplateProd} limitHeight" instanceName="produits"  />


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
                                    <bill:checkBoxList referenceCollection="${MagazinList}" containerClass="${ctnerTemplateMag} limitHeight" instanceName="magazins" />

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
                        <div class="btn-link modifier">
                            <g:message code="default.button.edit.label" default="Edit" />
                        </div>
                    </div>
                </g:form>
                        </div>
                    </div>



    <g:render template="usershowjs"/>

