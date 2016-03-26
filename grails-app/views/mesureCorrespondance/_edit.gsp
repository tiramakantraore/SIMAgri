<%@ page import="simagri.MesureCorrespondance" %>
<g:render template="/partials/showHeader" />
		<div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${mesureCorrespondanceInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${mesureCorrespondanceInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="form-horizontal" action="edit">
						<g:hiddenField name="version" value="${mesureCorrespondanceInstance?.version}" />
                        <g:hiddenField name="id" value="${mesureCorrespondanceInstance?.id}" />
						<g:render template="form"/>
                        <g:render template="/partials/btnEdit"/>
					</g:form>

			</div>

		</div>
