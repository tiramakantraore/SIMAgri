<%@ page import="org.springframework.validation.FieldError" %>

		<div class="row">
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<h1><g:message code="edit.priceHolder" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${priceHolderInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${priceHolderInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit" >
						<g:render template="/partials/btnEdit" model="[update:$update]"/>
						<g:hiddenField name="version" value="${priceHolderInstance?.version}" />
                        <g:hiddenField name="id" value="${priceHolderInstance?.id}" />
						<g:hiddenField name="update" value="${update}" />
                            <g:render template="form"/>
						<g:render template="/partials/btnEdit" model="[update:$update]"/>
					</g:form>

			</div>
		</div>