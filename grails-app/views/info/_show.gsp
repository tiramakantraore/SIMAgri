
<%@ page import="simagri.Info" %>
			<div class="row">
			<div class="col-sm-12 col-md-12">

                <g:if test="${flash.message}">
                    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                </g:if>
                <g:form >
				<div class="page-header">
                    <f:display bean="infoInstance" property="titre"/>
                  </div>



                    <f:display bean="infoInstance" property="contenu"/>
                    <f:display bean="infoInstance" property="auteur"/>
                    <f:display bean="infoInstance" property="date"/>
                    <f:display bean="infoInstance" property="dateExpiration"/>
                    <f:display bean="infoInstance" property="actif"/>
                    <f:display bean="infoInstance" property="url"/>

                    <g:hiddenField name="id" value="${infoInstance?.id}" />
                        <g:render template="/partials/btnShow"/>

				</g:form>

			</div>
            </div>