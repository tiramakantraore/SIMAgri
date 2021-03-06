<%@ page import="org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader" />

            <div class="row">
			   <div class="col-sm-12 col-md-12">
				<div class="page-header">
					<h1><g:message code="edit.produit" /><span class="ItemModified"> ${produitInstance}</span></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${produitInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${produitInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit">
						<g:hiddenField name="version" value="${produitInstance?.version}" />
                        <g:hiddenField name="id" value="${produitInstance?.id}" />
                            <g:render template="form"/>
                            <g:render template="/partials/btnEdit"/>
					</g:form>
			</div>

		</div>

