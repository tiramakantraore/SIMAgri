<%@ page import="org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader" />
<div class="row">
    <div class="col-sm-12 col-md-12">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${communeInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${communeInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="create" >
                        <f:field bean="communeInstance" property="nom" input-class="form-control"/>
                        <f:field bean="communeInstance" property="province" input-class="form-control"/>
                            <g:render template="/partials/btnCreer"/>
					</g:form>
				
			</div>

		</div>

