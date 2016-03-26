<%@ page import="org.springframework.validation.FieldError; simagri.Info" %>

        <ckeditor:resources/>

		<div class="row">
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<h1><g:message code="edit.info" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${infoInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${infoInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" controller="info" action="edit"  >
						<g:hiddenField name="version" value="${infoInstance?.version}" />
                        <g:hiddenField name="id" value="${infoInstance?.id}" />
						<g:hiddenField name="update" value="${update}" />

						<g:render template="/partials/btnEdit" model="[update:update]"/>
                            <g:render template="formEdit"/>
                            <g:render template="/info/infojs"/>
                        <g:render template="/partials/btnEdit" model="[update:update]"/>
					</g:form>

			</div>

		</div>

