<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'smsToReseaux.label', default: 'SmsToReseaux')}"/>
    <title><g:message code="show.smsToReseaux"/></title>
</head>

<body>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.smsToReseaux"/></li>
                <li>
                    <g:link class="list" action="list">
                         <i class="glyphicon glyphicon-list"></i>
                        <g:message code="list.smsToReseaux"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                         <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.smsToReseaux"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="show.smsToReseaux"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <dl>

            <g:if test="${smsToReseauxInstance?.reseaux}">
                <dt><g:message code="smsToReseaux.reseaux.label" default="Reseaux"/> :

                <g:fieldValue bean="${smsToReseauxInstance}" field="reseaux"/>
                </dt>

            </g:if>

            <g:if test="${smsToReseauxInstance?.yourTextMessage}">
                <dt><g:message code="smsToReseaux.yourTextMessage.label" default="Your Text Message"/> :

                <g:fieldValue bean="${smsToReseauxInstance}" field="yourTextMessage"/>
                </dt>

            </g:if>

        </dl>

        <g:form >
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${smsToReseauxInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>

            </div>
        </g:form>

    </div>

</div>
</body>
</html>
