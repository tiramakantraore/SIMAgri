<%@ page import="simagri.Reseau; simagri.Marche; simagri.Produit; org.springframework.validation.FieldError" %>

%{--<!doctype html>--}%
%{--<html>--}%
	%{--<head>--}%
		%{--<meta name="layout" content="adminLayout">--}%
		%{--<g:set var="entityName" value="${message(code: 'reseau.label', default: 'Reseau')}" />--}%
		%{--<title><g:message code="show.groupe" /> ${reseauInstance}</title>--}%

	%{--</head>--}%
	%{--<body>--}%

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
                <g:form class="form-horizontal" action="show"  >
                    <g:render template="/partials/btnShow"/>
                        <g:hiddenField name="parentText" value="${reseauInstance?.parent?.nom}" />
                        <g:hiddenField name="parentIdSaved" value="${reseauInstance?.parent?.id}" />
                        <g:hiddenField name="theId" value="${reseauInstance?.id}" />
                        <g:hiddenField name="id" value="${reseauInstance?.id}" />
                        <div class="row">
                            <div class="col-sm-12 col-md-12">

                                <ul class="nav nav-tabs" id="dashboardTab">
                                    <li class="active"><a href="#IdParent" data-toggle="tab">Groupe parent</a></li>
                                    <li><a href="#IdIdentification" data-toggle="tab">Identification</a></li>
                                    <li><a href="#IdMarches" data-toggle="tab">Mes marchés</a></li>
                                    <li><a href="#IdProduits" data-toggle="tab">Mes produits</a></li>
                                    <li><a href="#IdMembres" data-toggle="tab">Mes membres</a></li>
                                </ul>

                                <div class="tab-content">

                                    <div class="tab-pane active" id="IdParent">

                                        <div class="row">
                                            <div class="col-sm-12 col-md-12">
                                                <bill:radioBoxList referenceCollection="${Reseau.findAllByActive(true)}" instanceName="parent" containerClass="my4Columns" labelClass="labelClass" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="tab-pane" id="IdIdentification">

                                        <div class="row">
                                            <div class="col-sm-12 col-md-12">
                                                <g:render template="form" />
                                            </div>
                                        </div>
                                    </div>


                                    <div class="tab-pane" id="IdMarches">

                                        <bill:checkBoxList referenceCollection="${Marche.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="markets"  />


                                    </div>
                                    <div class="tab-pane" id="IdProduits">


                                        <bill:checkBoxList referenceCollection="${Produit.list(sort: 'nom', order: 'asc')}" containerClass="my4Columns  limitHeight" instanceName="produits" />


                                    </div>

                                    <div class="tab-pane" id="IdMembres">
                                        <bill:checkBoxList referenceCollection="${mesMembres}" containerClass="my4Columns  limitHeight" instanceName="membres" />


                                    </div>


                                </div>

                            </div>
                        </div>
                        <g:render template="/partials/btnShow"/>

                </g:form>


			</div>

		</div>
    <g:render template="groupejs"/>
	%{--</body>--}%
%{--</html>--}%
