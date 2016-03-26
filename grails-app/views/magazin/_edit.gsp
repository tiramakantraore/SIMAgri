<%@ page import="simagri.Produit; org.springframework.validation.FieldError" %>

<g:render template="/partials/showHeader"/>
		<div class="row">
			<div class="col-sm-12 col-md-12 ">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${magazinInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${magazinInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit" >
						<g:hiddenField name="version" value="${magazinInstance?.version}" />
                        <g:hiddenField name="id" value="${magazinInstance?.id}" />
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

                                            <bill:checkBoxList referenceCollection="${Produit.list(sort: 'nom', order: 'asc')}" instanceName="produits"  containerClass="my4Columns  limitHeight"/>


                                        </div>



                                    </div>

                                </div>
                            </div>
                            <g:render template="magazinjs" />
                            <g:render template="/partials/btnEdit"/>
					</g:form>

			</div>

		</div>
