<%@ page import="simagri.Marche; simagri.Produit; org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader"/>
		<div class="row">
		<div class="col-sm-12 col-md-12 ">

				<div class="page-header">
					<h1><g:message code="create.marche"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${marcheInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${marcheInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="create" >
                        <g:render template="/partials/btnCreer"/>
                            <div class="row">
                                <div class="col-sm-12 col-md-12">

                                    <ul class="nav nav-tabs" id="dashboardTab">
                                        <li class="active"><a href="#IdIdentification" data-toggle="tab">Identification</a></li>
                                        <li><a href="#IdProduits" data-toggle="tab">Mes produits</a></li>
                                    </ul>

                                    <div class="tab-content">

                                        <div class="tab-pane active" id="IdIdentification">

                                            <div class="row">
                                                <div class="col-sm-12 col-md-12">
                                                    <g:render template="form" />
                                                </div>
                                            </div>
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
                            <g:render template="marchejs" />
                            <g:render template="/partials/btnCreer"/>
					</g:form>
				
			</div>

		</div>
