<%@ page import="simagri.Event" %>
<%@ page import="org.joda.time.Instant" %>



<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'commune.label', default: 'Commune')}" />
    <title><g:message code="show.event"/></title>
    <r:require module="calendar" />
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
                %{--<li class="nav-header"><g:message code="title.commune" /></li>--}%
                %{--<li>--}%
                    %{--<g:link class="list" action="list">--}%
                        %{-- <i class="glyphicon glyphicon-list"></i>--}%
                        %{--<g:message code="list.commune" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
                %{--<li>--}%
                    %{--<g:link class="create" action="create">--}%
                        %{-- <i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.commune" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    </div>

    <div class="col-sm-5 col-md-5 offset-4">
        <div class="page-header">
            <h1><g:message code="show.event"/></h1>
        </div>
        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <ol class="property-list event">
            <li class="fieldcontain">
                <span id="when-label" class="property-label">Date/Période</span>

                <span class="property-value" aria-labelledby="when-label">
                    <g:formatDate date="${new Instant(occurrenceStart).toDate()}" format="E, MMM d, hh:mma"/>  –
                    <g:formatDate date="${new Instant(occurrenceEnd).toDate()}" format="E, MMM d, hh:mma"/>
                </span>

            </li>

            <g:if test="${eventInstance?.location}">
                <li class="fieldcontain">
                    <span id="location-label" class="property-label"><g:message code="event.location.label"
                                                                                default="Location"/></span>

                    <span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${eventInstance}"
                                                                                                field="location"/></span>

                </li>
            </g:if>

            <g:if test="${eventInstance?.description}">
                <li class="fieldcontain">
                    <span id="description-label" class="property-label"><g:message code="event.description.label"
                                                                                   default="Description"/></span>

                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${eventInstance}"
                                                                                                   field="description"/></span>

                </li>
            </g:if>



        </ol>

        <g:form >
            <g:hiddenField name="id" value="${eventInstance?.id}"/>

            <g:hiddenField name="occurrenceStart" value="${occurrenceStart}" />
            <g:hiddenField name="occurrenceEnd" value="${occurrenceEnd}" />
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${eventInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit" />
                </g:link>
                <div class="btn-flat  btn-primary" type="submit" name="_action_delete">
                     <i class="glyphicon glyphicon-trash"></i>
                    <g:message code="default.button.delete.label" default="Delete" />
                </div>
            </div>
        </g:form>

    </div>
    <g:if test="${eventInstance.isRecurring}">
        <g:render template="deletePopup" model="model" />
    </g:if>
</div>
</body>
</html>


