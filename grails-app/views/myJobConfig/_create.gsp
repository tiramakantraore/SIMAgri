<%@ page import="org.springframework.validation.FieldError; simagri.MyJobConfig" %>
<g:render template="/partials/showHeader"/>
<div class="row">
        <div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${myJobConfigInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${myJobConfigInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="create" >
                           <g:render template="form"/>
							<div class="form-actions">
                            <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'myJobConfig', action:'create')}','','La validation de la page  a réussi','listContent');return false;" >

                                <g:message code="default.button.create.label" default="Create" />
								</div>
							</div>
					</g:form>

			</div>
	%{--</body>--}%
%{--</html>--}%
