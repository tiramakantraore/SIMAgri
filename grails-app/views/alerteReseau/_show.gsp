

<!doctype html>
<html xmlns="http://www.w3.org/1999/html">
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'alerte.label', default: 'Alerte')}" />
		<title><g:message code="show.alerte"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
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
			</div>
        </div>
            <div class="row">
			<div class="col-sm-6 col-md-6">

				<div class="page-header">
					<h1><g:message code="show.alerte"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <g:form>
                <f:display bean="alerteInstance" property="typeAlerte"/>
                <f:display bean="alerteInstance" property="typeOffre"/>
                <f:display bean="alerteInstance" property="valide"/>
                <f:display bean="alerteInstance" property="suspendre"/>
                <f:display bean="alerteInstance" property="reseaux"/>
                <f:display bean="alerteInstance" property="destinataires"/>
                <f:display bean="alerteInstance" property="produits"/>
                <f:display bean="alerteInstance" property="markets"/>
                <f:display bean="alerteInstance" property="lundi"/>
                <f:display bean="alerteInstance" property="mardi"/>
                <f:display bean="alerteInstance" property="mercredi"/>
                <f:display bean="alerteInstance" property="jeudi"/>
                <f:display bean="alerteInstance" property="vendredi"/>
                <f:display bean="alerteInstance" property="samedi"/>
                <f:display bean="alerteInstance" property="dimanche"/>
                    <g:hiddenField name="update" value="${update}" />


					<g:hiddenField name="id" value="${alerteInstance?.id}" />
                    <g:render template="/partials/btnShow" model="[update:$update,instance:alerteInstance]"/>
					%{--<div class="form-actions">--}%
						%{--<g:link class="btn" action="edit" id="${alerteInstance?.id}">--}%
							%{--<i class="icon-pencil"></i>--}%
							%{--<g:message code="default.button.edit.label" default="Edit" />--}%
						%{--</g:link>--}%

					%{--</div>--}%
				</g:form>

			</div>

		</div>
	</body>
</html>
