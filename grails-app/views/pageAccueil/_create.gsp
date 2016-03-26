<%@ page import="simagri.PageUtilisateur; simagri.MonImage; simagri.PageAccueil" %>
<g:render template="/partials/showHeader" />
		<div id="create-pageAccueil" class="content scaffold-create" role="main"></div>
			%{--<h1><g:message code="pageAccueil.label" /></h1>--}%
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${pageAccueilInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${pageAccueilInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
     <div class="row">
    <div class="col-sm-12 col-md-12">
    <g:form name="create" class="form-stacked-horizontal"  >
        <g:render template="/partials/btnCreer"/>
        <g:render template="form"/>
        <g:render template="/partials/btnCreer"/>
    </g:form>