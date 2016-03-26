<%@ page import="simagri.PageUtilisateur" %>
<style>
.entry-content {font-family: 'robotoregular',cursive;}
</style>

<g:render template="/partials/showHeader" />
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${pageUtilisateurInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${pageUtilisateurInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

                        <g:form name="edit" method="POST" class="form-horizontal"  >
                            <g:hiddenField name="version" value="${pageUtilisateurInstance?.version}" />
                            <g:hiddenField name="id" value="${pageUtilisateurInstance?.id}" />

                            <g:render template="/partials/btnEditWithCkEditor"/>

                            <g:render template="form"/>
                            <g:render template="/partials/btnEditWithCkEditor"/>

					</g:form>

			</div>
		</div>
