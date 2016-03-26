<%@ page import="org.springframework.validation.FieldError" %>

				%{--<div class="page-header">--}%
					%{--<h1><g:message code="create.offre"/></h1>--}%
				%{--</div>--}%

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${offreInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${offreInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form name="offre" class="form-stacked-vertical" >

                        <g:render template="/offre/formCreate"/>
                        <g:render template="/offre/btnEnregistrerAjax"/>
                    </g:form>
