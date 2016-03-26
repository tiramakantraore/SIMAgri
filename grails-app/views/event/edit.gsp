<%@ page import="org.springframework.validation.FieldError; simagri.Event" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'event.label', default: 'Evenement')}" />
    <title><g:message code="edit.event" /></title>
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
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%
                %{--<li class="nav-header"><g:message code="title.marche" /></li>--}%
                %{--<li>--}%
                    %{--<g:link class="list" action="list">--}%
                        %{-- <i class="glyphicon glyphicon-list"></i>--}%
                        %{--<g:message code="list.marche" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
                %{--<li>--}%
                    %{--<g:link class="create" action="create">--}%
                        %{-- <i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.marche" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="edit.event" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <g:hasErrors bean="${marcheInstance}">
            <bootstrap:alert class="alert-error">
                <ul>
                    <g:eachError bean="${eventInstance}" var="error">
                        <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </bootstrap:alert>
        </g:hasErrors>
        %{--<g:form method="post" class="main">--}%
            %{--<g:hiddenField name="id" value="${eventInstance?.id}"/>--}%
            %{--<g:hiddenField name="version" value="${eventInstance?.version}"/>--}%
            %{--<g:hiddenField name="editType" value="" />--}%

            %{--<fieldset class="form">--}%
                %{--<g:render template="form"/>--}%
            %{--</fieldset>--}%
            %{--<fieldset class="buttons">--}%

                %{--<g:actionSubmit class="save ${eventInstance.isRecurring ? 'recurring' : ''}" action="update"--}%
                                %{--value="${message(code: 'default.button.update.label', default: 'Update')}"/>--}%
                %{--<g:actionSubmit class="delete ${eventInstance.isRecurring ? 'recurring' : ''}" action="delete"--}%
                                %{--value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" />--}%
            %{--</fieldset>--}%
        %{--</g:form>--}%
        <g:form class="well small form-horizontal main " method="post" >
            <g:hiddenField name="id" value="${eventInstance?.id}"/>
            <g:hiddenField name="version" value="${eventInstance?.version}"/>
            <g:hiddenField name="editType" value="" />

        <fieldset class="form">
            <g:render template="form"/>

        </fieldset>
            <div class="row">
                <div class="form-actions">


                    <g:actionSubmit class="btn-flat  btn-primary ${eventInstance.isRecurring ? 'recurring' : ''}" action="edit"
                                    value="${message(code: 'default.button.update.label', default: 'Update')}"/>
                    <g:actionSubmit class="btn-flat  btn-primary ${eventInstance.isRecurring ? 'recurring' : ''}" action="delete"
                                    value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" />
                </div>
            </div>
        </g:form>
        <g:if test="${eventInstance.isRecurring}">
            <g:render template="deletePopup" model="model" />
            <g:render template="editPopup" model="model" />
        </g:if>
    </div>

</div>
</body>
</html>
