<%@ page import="simagri.Marche; simagri.Produit; simagri.Utilisateur; org.springframework.validation.FieldError" %>
<h1><g:message code="modif.alerte" /></h1>
<g:form class="form-horizontal" name="createAlerte" >
<div class="form-actions">
    <div type="button" class="btn-flat  btn-primary modifierAlerte" onclick="creerAlerte();">

        <g:message code="default.button.edit.label" default="Edit" />
    </div>
    <div onclick="submitForm($(this).closest('form'),
            '${createLink(controller:'applications', action:'deleteAlerte')}','','La suppression  a réussi','listContent');return false;" class="btn-flat  btn-primary" formnovalidate>
        <i class="glyphicon glyphicon-trash"></i>
        <g:message code="default.button.delete.label" default="Delete" />
    </div>
</div>
<g:hiddenField name="ReseauxIdsAlerte" />
<g:hiddenField name="recevoirOffres" value="${alerteInstance?.recevoirOffres}"/>
<g:hiddenField name="recevoirPrix" value="${alerteInstance?.recevoirPrix}"/>
<g:hiddenField name="recevoirParEmail" value="${alerteInstance?.recevoirParEmail}"/>
<g:hiddenField name="recevoirParSMS" value="${alerteInstance?.recevoirParSMS}"/>
<g:hiddenField name="id" value="${alerteInstance?.id}"/>
<div class="tab-pane" id="idAlertForm">
    <ul class="nav nav-pills" id="IdAlertFormTab">

        <li><a href="#IdIdentAlerte" data-toggle="tab" title="Identification"> 1. Identification </a></li>
        <li><a href="#IdGroupeAlerte" data-toggle="tab" title="Choisir le(s) groupes"> 2. Groupes  </a></li>
        <li><a href="#IdDestinatairesAlerte" data-toggle="tab" title="Choisir le(s) destinataires de l'alerte"> 3. Destinataires </a></li>
        <li><a href="#IdProduitsAlerte" data-toggle="tab" title="Choisir le(s) produits"> 4. Produits </a></li>
        <li><a href="#IdMarchesAlerte" data-toggle="tab" title="Choisir le(s) marchés"> 5. Marchés </a></li>
        <li><a href="#IdCalendrierAlerte" data-toggle="tab" title="Choisir le calendrier"> 6. Calendrier </a></li>
    </ul>
    <div class="tab-content tabContent">

        <div class="tab-pane active" id="IdIdentAlerte">
            <div class="row">
                <div class="col-sm-1 col-md-1">
                </div>
                <div class="col-sm-7 col-md-7">
                    <f:field bean="alerteInstance" property="nom" required="true" input-class="form-control"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-1 col-md-1">
                </div>
                       <div class="col-sm-2 col-md-2">
                           <g:hiddenField id="typeAlerte" name="typeAlerte" value="Prix"/>
                           <bill:radioBoxList referenceCollection="${['Prix','Offre']}" radioName="filtertypeAlerte" defaultValue="Prix"
                                              isArray="true"  containerClass="my1Columns" labelClass="labelClass" onClickRadio="onclicktypeAlerte();"/>

                       </div>
                       <div class="col-sm-2 col-md-2">
                           <div id="typeOffreDiv" style="display:none">
                               <g:hiddenField id="typeOffre" name="typeOffre" value="Achat"/>
                               <bill:radioBoxList referenceCollection="${['Achat','Vente']}" radioName="filtertypeOffre" defaultValue="Achat"
                                                  isArray="true"  containerClass="my1Columns" labelClass="labelClass"/>
                           </div>

                      </div>

                    <div class="col-sm-8 col-md-8">

                    </div>

         </div>
        </div>
    <div class="tab-pane " id="IdGroupeAlerte">
        <h4><strong><g:message code="choisir.reseau"/></strong></h4>
        <div  class="k-content" >
            <div id="treeviewAlerte" class="groupe-section"></div>

        </div>
    </div>
        <div class="tab-pane" id="IdProduitsAlerte">

                    <div class="row">
                        <div class="col-sm-4 col-md-4">
                            <a id="check-allProductsAlerte" href="javascript:void(0);">Tous les produits</a>
                        </div>
                        <div class="col-sm-4 col-md-4">
                            <a id="uncheck-allProductsAlerte" href="javascript:void(0);">Aucun produit </a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12 col-md-12" id="listeProduitsAlerte">
                            <bill:checkBoxList referenceCollection="${Produit.findAllByActif(true)}" containerClass="my4Columns  limitHeight" instanceName="products_alertes" />
                        </div>
                    </div>



        </div>
        <div class="tab-pane" id="IdMarchesAlerte">
            <div class="row">
                <div id="marcheDiv" class="col-sm-12 col-md-12 required" style="display:inline-block">

                        <div class="row">
                            <div class="col-sm-4 col-md-4">
                                <a id="check-allMarketsAlerte" href="javascript:void(0);">Tous les marchés</a>
                            </div>
                            <div class="col-sm-4 col-md-4">
                                <a id="uncheck-allMarketsAlerte" href="javascript:void(0);">Aucun marché </a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12 col-md-12" id="listeMarchesAlerte">
                                <bill:checkBoxList referenceCollection="${Marche.list()}" containerClass="my4Columns  limitHeight" instanceName="markets_alertes"/>
                            </div>
                        </div>

                    </div>

                </div>
     </div>
        <div class="tab-pane" id="IdDestinatairesAlerte">

            <div class="row">
                <div class="col-sm-4 col-md-4">
                    <a id="check-allDestinatairesAlerte" href="javascript:void(0);">Tous les destinataires</a>
                </div>
                <div class="col-sm-4 col-md-4">
                    <a id="uncheck-allDestinatairesAlerte" href="javascript:void(0);">Aucun destinataires </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4 col-md-4 col-sm-offset8 col-md-offset8">
                    <form name="searchDestAlertForm"  class="navbar-form navbar-left form-search form-inline" role="search" >
                        <div class="input-group my-search-group" >
                            <input type="text" name="queryAlert" id="queryAlert" class="form-control"  placeholder="Chercher" onkeydown="onChangeDestinatairesAlert();">
                            <div class="input-group-btn">
                                <div class="btn-success btn-search-flat" onclick="onChangeDestinatairesAlert();">
                                    <i class="glyphicon glyphicon-search"></i>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div id="listeDestinataires_alertes">
                    <bill:checkBoxList referenceCollection="${Utilisateur.findAllById(0)}" containerClass="my4Columns" instanceName="destinataires_alertes" />
                    </div>
                </div>
            </div>

        </div>

        <div class="tab-pane" id="IdCalendrierAlerte">
            <div class="row">
                <div class="col-sm-2 col-md-2">
                </div>
                <div class="col-sm-8 col-md-8">

                    <p><strong>Veuillez choisir les jours d'envoi des sms</strong></p>


                    <div class="row">
                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="lundi"  input-class="check"/>
                        </div>

                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="mardi"  input-class="check"/>
                        </div>
                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="mercredi"  input-class="check"/>
                        </div>

                    </div>

                    <div class="row">
                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="jeudi"  input-class="check"/>
                        </div>


                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="vendredi"  input-class="check"/>

                        </div>

                        <div  class="col-sm-4 col-md-4">
                            <f:field bean="alerteInstance" property="samedi"  input-class="check"/>
                        </div>
                    </div>


                    <div class="row">

                        <div  class="col-sm-6 col-md-6 ">
                            <f:field bean="alerteInstance" property="dimanche"  input-class="check"/>
                        </div>
                    </div>

                </div>
                <div class="col-sm-2 col-md-2">
                </div>

            </div>

        </div>
    </div>
</div>
<div class="form-actions">
    <div type="button" class="btn-flat  btn-primary modifierAlerte" onclick="creerAlerte();">

            <g:message code="default.button.edit.label" default="Edit" />
    </div>

    <div type="button" class="btn-flat  btn-primary supprimerAlerte" >
        <i class="glyphicon glyphicon-trash"></i>
        <g:message code="default.button.delete.label" default="Delete" />
    </div>


</div>

</g:form>
<g:render template="/applications/alertesjs"/>
