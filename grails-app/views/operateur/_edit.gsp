<%@ page import="org.springframework.validation.FieldError; simagri.Operateur" %>

<g:render template="/partials/showHeader"/>
<div class="row">
			<div class="col-sm-12 col-md-12">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${operateurInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${operateurInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit" >
						<g:hiddenField name="version" value="${operateurInstance?.version}" />
                        <g:hiddenField name="id" value="${operateurInstance?.id}" />
                            <g:render template="form"/>
                        <g:render template="/partials/btnEdit"/>
					</g:form>

			</div>

		</div>

