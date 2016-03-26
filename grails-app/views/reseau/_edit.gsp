<%@ page import="simagri.PageAccueil; simagri.Produit; simagri.Marche; org.springframework.validation.FieldError" %>


<g:render template="/partials/showHeader"/>

		<div class="row">
			
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${reseauInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${reseauInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

                <g:form class="form-horizontal" action="edit" name="edit" >
                    <div class="form-actions">
                        <div class="btn-flat  btn-primary validerEdit">
                            <g:message code="default.button.update.label" default="Update" />
                        </div>
                        <div type="submit" class="btn-flat  btn-primary" name="_action_delete" formnovalidate>
                             <i class="glyphicon glyphicon-trash"></i>
                            <g:message code="default.button.delete.label" default="Delete" />
                        </div>
                    </div>
                    <br>
                        <g:hiddenField name="parentText" value="${reseauInstance?.parent?.nom}" />
                        <g:hiddenField name="parentIdSaved" value="${reseauInstance?.parent?.id}" />
                        <g:hiddenField name="id" value="${reseauInstance?.id}" />
                        <div class="row">
                            <div class="col-sm-12 col-md-12">

                                <ul class="nav nav-tabs" id="dashboardTab">
                                    <li class="active"><a href="#IdIdentification" data-toggle="tab">Identification</a></li>

                                    <li><a href="#IdMarches" data-toggle="tab">Mes marchés</a></li>
                                    <li><a href="#IdProduits" data-toggle="tab">Mes produits</a></li>
                                    <li><a href="#IdPageAccueil" data-toggle="tab">Page d'accueil personnalisée</a></li>
                                </ul>

                                <div class="tab-content">


                                    <div class="tab-pane active" id="IdIdentification">

                                        <div class="row">
                                            <div class="col-sm-10 col-md-10">
                                                <g:render template="form" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="IdPageAccueil">

                                        <div class="row">
                                            <div class="col-sm-12 col-md-12">
                                                <bill:radioBoxList referenceCollection="${PageAccueil.findAllByEstPrincipal(false)}" instanceName="pageAccueil"  containerClass="my1Columns" labelClass="labelClass" title="Page d'accueil personnalisée"/>
                                            </div>
                                        </div>
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


                                        <bill:checkBoxList referenceCollection="${Marche.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="markets"  />

                                    </div>
                                    <div class="tab-pane" id="IdProduits">
                                        <div class="row">
                                            <div class="col-sm-3 col-md-3">
                                                <a id="check-allProduits" href="javascript:void(0);">Tous les produits</a>
                                            </div>
                                            <div class="col-sm-3 col-md-3">
                                                <a id="uncheck-allProduits" href="javascript:void(0);">Aucun produit </a>
                                            </div>
                                        </div>

                                        <bill:checkBoxList referenceCollection="${Produit.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="produits" />


                                    </div>



                                </div>

                            </div>
                        </div>
                    <g:render template="/partials/btnEdit"/>

                </g:form>



			</div>

		</div>
    <g:render template="groupejs"/>
