<%@ page import="simagri.MonImage" %>

<g:render template="/partials/showHeader"/>
        <div class="row">
			<div class="col-sm-12 col-md-12">



				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${monImageInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${monImageInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="well small form-horizontal" action="edit" enctype="multipart/form-data">
						<g:hiddenField name="version" value="${monImageInstance?.version}" />
                        <g:hiddenField name="id" value="${monImageInstance?.id}" />

						<fieldset>
                            <g:render template="form"/>
							<div class="form-actions">
							<g:render template="/partials/btnEdit"/>

							</div>
						</fieldset>
					</g:form>
				</fieldset>

			</div>

		</div>

