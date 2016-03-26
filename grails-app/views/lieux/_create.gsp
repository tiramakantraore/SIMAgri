<%@ page import="org.springframework.validation.FieldError" %>

<g:render template="/partials/showHeader" />
<div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${lieuxInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${lieuxInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="create" >
                        <f:field bean="lieuxInstance" property="nom" input-class="form-control"/>
                        <f:field bean="lieuxInstance" property="commune" input-class="form-control"/>
                        <f:field bean="lieuxInstance" property="longitude" input-class="form-control"/>
                        <f:field bean="lieuxInstance" property="latitude" input-class="form-control"/>
                            <g:render template="/partials/btnCreer"/>

					</g:form>
			</div>

		</div>
