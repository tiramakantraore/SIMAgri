<%@ page import="org.springframework.validation.FieldError; simagri.Event" %>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />
    <title><g:message code="createevent.label" /></title>
</head>
<body>
<div class="nav" role="navigation">
    <ul>
<li><a href="${createLink(uri: '/')}" class="home">Retour</a></li>
<li><g:link action="index" class="calendar">Calendrier</g:link></li>
<li><g:link action="create" class="create">Nouvel événement</g:link></li>
    </ul>
</div>
<div class="row">

    <div class="col-sm-4 col-md-4">

    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="createevent.label"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${eventInstance}">
            <bootstrap:alert class="alert-error">
                <ul>
                    <g:eachError bean="${eventInstance}" var="error">
                        <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </bootstrap:alert>
        </g:hasErrors>


        <g:form class="well small form-horizontal main" action="save" method="post" >

                <fieldset class="form">
                    <g:render template="form" model="model" />
                </fieldset>

                <div class="form-actions">
                     <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'event', action:'create')}','','La validation de la page  a réussi','listContent');return false;" >

                        <g:message code="default.button.create.label" default="Create" />
                    </div>
                </div>

        </g:form>

    </div>

</div>
</body>
</html>
