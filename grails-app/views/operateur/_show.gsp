
<%@ page import="simagri.Operateur" %>

    <g:render template="/partials/btnShow"/>
    <div class="row">

        <div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
			<g:form >
                <f:display bean="operateurInstance" property="nom"/>
                <f:display bean="operateurInstance" property="prefixes"/>
                <f:display bean="operateurInstance" property="emailContact"/>
                <f:display bean="operateurInstance" property="telephoneContact"/>
                <f:display bean="operateurInstance" property="siteWeb"/>



					<g:hiddenField name="id" value="${operateurInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${operateurInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>

