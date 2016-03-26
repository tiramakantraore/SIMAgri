<%@ page import="simagri.Sondage" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'sondage.label', default: 'Sondage')}"/>
    <title><g:message code="show.sondage"/></title>
</head>

<body>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.sondage"/></li>
                <li>
                    <g:link class="list" action="list">
                         <i class="glyphicon glyphicon-list"></i>
                        <g:message code="list.sondage"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                         <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.sondage"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="show.sondage"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <dl>

            <g:if test="${sondageInstance?.titre}">
                <dt><g:message code="sondage.titre.label" default="Titre"/> :

                <g:fieldValue bean="${sondageInstance}" field="titre"/>
                </dt>

            </g:if>

            <g:if test="${sondageInstance?.description}">
                <dt><g:message code="sondage.description.label" default="Description"/> :

                <g:fieldValue bean="${sondageInstance}" field="description"/>
                </dt>

            </g:if>

            <g:if test="${sondageInstance?.dateDebut}">
                <dt><g:message code="sondage.dateDebut.label" default="Date Debut"/> :

                <g:formatDate date="${sondageInstance?.dateDebut}"/></dt>

            </g:if>

            <g:if test="${sondageInstance?.duree}">
                <dt><g:message code="sondage.duree.label" default="Duree"/> :

                <g:fieldValue bean="${sondageInstance}" field="duree"/>
                </dt>

            </g:if>

            <g:if test="${sondageInstance?.dateFin}">
                <dt><g:message code="sondage.dateFin.label" default="Date Fin"/> :

                <g:formatDate date="${sondageInstance?.dateFin}"/></dt>

            </g:if>

            <g:if test="${sondageInstance?.actif}">
                <dt><g:message code="sondage.actif.label" default="Actif"/> :

                <g:formatBoolean boolean="${sondageInstance?.actif}"/></dt>

            </g:if>

            <g:if test="${sondageInstance?.details}">
                <dt><g:message code="sondage.details.label" default="Details"/> :

                <g:fieldValue bean="${sondageInstance}" field="details"/>
                </dt>

            </g:if>

        </dl>

        <g:form >
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${sondageInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>

            </div>
        </g:form>

    </div>

</div>
</body>
</html>
