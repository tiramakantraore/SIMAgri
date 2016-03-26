<%@ page import="org.springframework.validation.FieldError; simagri.Info" %>
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${noteMarcheInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${noteMarcheInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>
					<g:form name="info"  class=" form-horizontal">
						%{--<fieldset>--}%
                           <g:render template="/info/form"/>
                                 <g:hiddenField name="id" value="${noteMarcheInstance?.id}" />

							<div class="form-actions">

                              <div class="btn-flat  btn-primary validerInfo">
                                <g:message code="default.button.create.label" default="Create" />

                              </div>

							</div>
						%{--</fieldset>--}%
					</g:form>