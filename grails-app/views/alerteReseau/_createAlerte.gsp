<%@ page import="simagri.Marche; simagri.Produit; simagri.CategorieProduit; simagri.Utilisateur; org.springframework.validation.FieldError" %>

		<div class="row">
            <div class="col-sm-5 col-md-5">
                <div class="well small">
                    <ul class="nav nav-list">
                        <li class="nav-header"><g:message code="title.alerteReseau" /></li>

                        <li>
                            <g:link class="list" action="list">
                                 <i class="glyphicon glyphicon-list"></i>
                                <g:message code="list.alerte" />
                            </g:link>
                        </li>
                    </ul>
                </div>

                <div class="well small" style="min-height:320px">
                    <g:message code="choisir.reseau"/>
                    <div  class="k-content" >
                        <div id="treeviewAlerte" class="groupe-section"></div>
                        <div id="result" class="groupe-section"></div>

                        <div class="groupe-section">
                            %{--<h3 class="title">Console log--}%
                            %{--</h3>--}%
                            <div class="console"></div>
                        </div>

                    </div>
               </div>
            </div>

            <div class="col-sm-7 col-md-7">

				<div class="page-header">
					<h1><g:message code="create.alerte"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${alerteInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${alerteInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

			<g:form class="well small form-horizontal" name="createAlerte" action="create" >

                    <g:hiddenField name="ReseauxIdsAlerte" />

                    <g:hiddenField name="recevoirOffres" value="${alerteInstance?.recevoirOffres}"/>

                    <g:hiddenField name="recevoirPrix" value="${alerteInstance?.recevoirPrix}"/>

                    <g:hiddenField name="recevoirParEmail" value="${alerteInstance?.recevoirParEmail}"/>

                    <g:hiddenField name="recevoirParSMS" value="${alerteInstance?.recevoirParSMS}"/>
                    <div class="row">
                        %{--<div class="col-sm-12 col-md-12  required">--}%
                        <div class="col-sm-12 col-md-12 required" style="display:inline-block">
                            <div style="display:block">
                                <label for="typeAlerte">
                                    <g:message code="alerte.typeAlerte.label" default="Type Alerte " />
                                    <span class="required-indicator">*</span>

                                </label>
                            </div>
                            <div style="display:block">
                                <g:select id="typeAlerte" name="typeAlerte" from="${alerteInstance.constraints.typeAlerte.inList}" required="" value="${alerteInstance?.typeAlerte}" valueMessagePrefix="alerte.typeAlerte"  />
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div id="typeOffreDiv" class="col-sm-12 col-md-12 required" style="display:none">
                            <div style="display:block">
                                <label for="typeOffre">
                                    <g:message code="alerte.typeOffre.label" default="Type Offre " />
                                    <span class="required-indicator">*</span>

                                </label>
                            </div>
                            <div style="display:block">
                                <g:select name="typeOffre" from="${alerteInstance.constraints.typeOffre.inList}" required="" value="${alerteInstance?.typeOffre}" valueMessagePrefix="alerte.typeOffre" />
                            </div>
                        </div>
                    </div>



                    <div class="row">
                        <div class="col-sm-12 col-md-12 required" style="display:inline-block">
                            <div style="display:block">
                                <label for="categories">
                                    <g:message code="alerte.categories.label" default="Categories " />
                                    <span class="required-indicator">*</span>

                                </label>
                            </div>
                            <div style="display:block">
                                <g:if test="${isCreation}">
                                    <g:select name="categories" id="categoriesAlerte" from="${CategorieProduit.list()}" multiple="multiple" optionKey="id" size="5"  class="many-to-many col-sm-1 col-md-1"/>
                                </g:if>
                                <g:else>
                                    <g:select name="categories" id="categoriesAlerte" from="${CategorieProduit.list()}" multiple="multiple" optionKey="id" size="5" value="${categoriesId}" class="many-to-many col-sm-1 col-md-1"/>
                                </g:else>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-12 col-md-12 required" style="display:inline-block">
                            <div style="display:block">
                                <label for="produits">
                                    <g:message code="alerte.produits.label" default="Produits " />
                                    <span class="required-indicator">*</span>

                                </label>
                            </div>
                            <div style="display:block">
                                <g:if test="${isCreation}">
                                    <g:select name="produits" id="produitsAlerte" from="${Produit.list()}" multiple="multiple" optionKey="id" size="5"  class="many-to-many col-sm-1 col-md-1"/>
                                 </g:if>
                                <g:else>
                                    <g:select name="produits" id="produitsAlerte" from="${Produit.list()}" value="${produitsId}" multiple="multiple" optionKey="id" size="5"  class="many-to-many col-sm-1 col-md-1"/>
                               </g:else>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div id="marcheDiv" class="col-sm-12 col-md-12 required" style="display:inline-block">
                            <div style="display:block">
                                <label for="markets">
                                    <g:message code="alerte.markets.label" default="Markets" />
                                    <span class="required-indicator">*</span>
                                </label>
                            </div>
                            <div style="display:block">
                                <g:if test="${isCreation}">
                                    <g:select name="markets" id="marketsAlerte" from="${Marche.list()}" multiple="multiple" noSelection="['': 'Aucun marché']" optionKey="id" size="5"  class="many-to-many col-sm-1 col-md-1"/>

                                </g:if>
                                <g:else>
                                    <g:select name="markets" id="marketsAlerte" from="${Marche.list()}"  value="${marchesId}" multiple="multiple" noSelection="['': 'Aucun marché']" optionKey="id" size="5"  class="many-to-many col-sm-1 col-md-1"/>
                                </g:else>

                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div  class="col-sm-12 col-md-12 required" style="display:inline-block">
                            <div style="display:block">
                                <label for="destinataires">
                                    <g:message code="alerte.destinataires.label" default="Destinataires" />
                                    <span class="required-indicator">*</span>
                                </label>

                            </div>
                            <div style="display:block">
                                <g:if test="${isCreation}">
                                    <g:select id="destinatairesAlerte" name="destinataires" from="${Utilisateur.list()?.sort{it.nomComplet}}" multiple="multiple" optionKey="id" size="5" class="many-to-many col-sm-1 col-md-1"/>
                                </g:if>
                                <g:else>
                                    <g:select id="destinatairesAlerte" name="destinataires" from="${Utilisateur.list()?.sort{it.nomComplet}}" multiple="multiple" optionKey="id" size="5" value="${alerteInstance?.destinataires*.id}" class="many-to-many col-sm-1 col-md-1"/>
                                </g:else>


                            </div>
                        </div>
                    </div>
                     %{--</div>--}%
                    <div class="row">
                        %{--<div  class="col-sm-12 col-md-12 required" style="display:inline-block">--}%

                        <div class="col-sm-4 col-md-4">
                            <label for="suspendre">
                                <g:message code="alerte.suspendre.label" default="Suspendre" />

                            </label>
                        </div>
                        <div class="col-sm-1 col-md-1 offset-8">
                            <g:checkBox name="suspendre" value="${alerteInstance?.suspendre}"  />
                        </div>

                    </div>

                    <br>
                    <div class="hrPrix">
                        <hr>
                    </div>
                    <p><strong>Veuillez choisir les jours d'envoi des sms</strong></p>


                    <div class="row">
                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class="col-sm-5 col-md-5">
                                    <label for="samedi">
                                        <g:message code="alerte.lundi.label" default="lundi" />

                                    </label>
                                </div>
                                <div class="col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="lundi" value="${alerteInstance?.lundi}" />
                                </div>

                            </div>
                        </div>

                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class="col-sm-5 col-md-5">
                                    <label for="samedi">
                                        <g:message code="alerte.mardi.label" default="mardi" />

                                    </label>
                                </div>
                                <div class="col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="mardi" value="${alerteInstance?.mardi}" />
                                </div>

                            </div>
                        </div>
                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class="col-sm-5 col-md-5">
                                    <label for="samedi">
                                        <g:message code="alerte.mercredi.label" default="mercredi" />

                                    </label>
                                </div>
                                <div class=" col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="mercredi" value="${alerteInstance?.mercredi}" />
                                </div>

                            </div>
                        </div>

                    </div>

                    <div class="row">

                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class=" col-sm-5 col-md-5">
                                    <label for="jeudi">
                                        <g:message code="alerte.jeudi.label" default="jeudi" />

                                    </label>
                                </div>
                                <div class=" col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="jeudi" value="${alerteInstance?.jeudi}" />
                                </div>

                            </div>
                        </div>

                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class=" col-sm-5 col-md-5">
                                    <label for="vendredi">
                                        <g:message code="alerte.vendredi.label" default="vendredi" />

                                    </label>
                                </div>
                                <div class=" col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="vendredi" value="${alerteInstance?.vendredi}" />
                                </div>

                            </div>
                        </div>

                        <div  class="col-sm-4 col-md-4">
                            <div class="row">
                                <div class=" col-sm-5 col-md-5">
                                    <label for="samedi">
                                        <g:message code="alerte.samedi.label" default="Samedi" />

                                    </label>
                                </div>
                                <div class=" col-sm-3 col-md-3 offset4">
                                    <g:checkBox name="samedi" value="${alerteInstance?.samedi}" />
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="row">

                        <div  class="col-sm-6 col-md-6 offset6">
                            <div class="row">
                                <div class=" col-sm-4 col-md-4">
                                    <label for="dimanche">
                                        <g:message code="alerte.dimanche.label" default="dimanche" />

                                    </label>
                                </div>
                                <div class=" col-sm-3 col-md-3 offset5">
                                    <g:checkBox name="dimanche" value="${alerteInstance?.dimanche}" />
                                </div>

                            </div>
                        </div>
                    </div>
                    </g:form>
                            <div type="button" class="btn-flat  btn-primary validerAlerte" onclick="creerAlerte();">

                                <g:message code="default.button.create.label" default="Create" />
                            </div>
	 </div>
	</div>

