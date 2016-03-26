<%@ page import="org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader"/>
		<div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${qualiteInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${qualiteInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit" id="${qualiteInstance?.id}" >
						<g:hiddenField name="version" value="${qualiteInstance?.version}" />
                        <g:hiddenField name="id" value="${qualiteInstance?.id}" />
                            <g:render template="form"/>
                            <g:render template="/partials/btnEdit"/>
					</g:form>

			</div>

		</div>

