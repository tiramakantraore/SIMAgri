
<%@ page import="simagri.NoteMarche" %>
<div class="row">
<div class="col-sm-12 col-md-12">

                <g:if test="${flash.message}">
                    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
                </g:if>
                <g:form >
				<div class="page-header">
                    <f:display bean="noteMarcheInstance" property="titre"/>
                  </div>
                    <div class="row">
                        <div class="col-sm-4 col-md-4">
                            <g:if test="${noteMarcheInstance.photo}">
                            <bill:imageWithText texte="Photo" imageURL="${createLink(controller: 'noteMarche', action: 'showImg',params:[id:noteMarcheInstance?.id])}"
                            imageHeight="100"/>
                            </g:if>
                        </div>
                        <div class="col-sm-8 col-md-8">
                            <f:display bean="noteMarcheInstance" property="contenu"/>
                            <f:display bean="noteMarcheInstance" property="auteur"/>
                            <f:display bean="noteMarcheInstance" property="date"/>
                            <f:display bean="noteMarcheInstance" property="dateExpiration"/>
                            <f:display bean="noteMarcheInstance" property="actif"/>
                            <f:display bean="noteMarcheInstance" property="longitude"/>
                            <f:display bean="noteMarcheInstance" property="latitude"/>
                        </div>
                    </div>

                    <g:hiddenField name="id" value="${noteMarcheInstance?.id}" />
                        <g:render template="/partials/btnShow"/>

				</g:form>
         </div>
        </div>