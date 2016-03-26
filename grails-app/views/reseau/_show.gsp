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

                <g:form class="form-horizontal" id="${reseauInstance?.id}" >
                    <g:render template="/partials/btnShow"/>
                        <g:hiddenField name="parentText" value="${reseauInstance?.parent?.nom}" />
                        <g:hiddenField name="parentIdSaved" value="${reseauInstance?.parent?.id}" />
                        <g:hiddenField name="theId" value="${reseauInstance?.id}" />
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
                                            <div class="col-sm-12 col-md-12">
                                                <g:render template="formshow" />
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



                                        <bill:checkBoxList referenceCollection="${Marche.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="markets"  />

                                    </div>
                                    <div class="tab-pane" id="IdProduits">


                                        <bill:checkBoxList referenceCollection="${Produit.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="produits" />


                                    </div>



                                </div>

                            </div>
                        </div>
                        <g:render template="/partials/btnShow"/>

                </g:form>



			</div>

		</div>
    <g:render template="groupeshowjs"/>
	%{--</body>--}%
%{--</html>--}%
