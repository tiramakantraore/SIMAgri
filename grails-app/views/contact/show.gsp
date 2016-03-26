<%@ page import="simagri.Contact" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}"/>
    <title><g:message code="show.contact"/></title>
</head>

<body>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.contact"/></li>
                <li>
                    <g:link class="list" action="list">
                         <i class="glyphicon glyphicon-list"></i>
                        <g:message code="list.contact"/>
                    </g:link>
                </li>
                <li>
                    <g:link class="create" action="create">
                         <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.contact"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="show.contact"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <dl>

            <g:if test="${contactInstance?.nom}">
                <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="contact.nom.label" default="Nom"/> :
                    </dt>
                    <dt style="font-weight:bold;display:inline">

                        <g:fieldValue bean="${contactInstance}" field="nom"/>

                    </dt>
                </div>
            </g:if>

            <g:if test="${contactInstance?.email}">
                <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="contact.email.label"
                                                                              default="Email"/> :
                    </dt>
                    <dt style="font-weight:bold;display:inline">

                        <g:fieldValue bean="${contactInstance}" field="email"/>

                    </dt>
                </div>
            </g:if>

            <g:if test="${contactInstance?.telephone}">
                <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="contact.telephone.label"
                                                                              default="Telephone"/> :
                    </dt>
                    <dt style="font-weight:bold;display:inline">

                        <g:fieldValue bean="${contactInstance}" field="telephone"/>

                    </dt>
                </div>
            </g:if>

            <g:if test="${contactInstance?.sujet}">
                <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="contact.sujet.label"
                                                                              default="Sujet"/> :
                    </dt>
                    <dt style="font-weight:bold;display:inline">

                        <g:fieldValue bean="${contactInstance}" field="sujet"/>

                    </dt>
                </div>
            </g:if>

            <g:if test="${contactInstance?.message}">
                <div style="display:inline-block">
                    <dt style="font-weight:normal ;display:inline"><g:message code="contact.message.label"
                                                                              default="Message"/> :
                    </dt>
                    <dt style="font-weight:bold;display:inline">

                        <g:fieldValue bean="${contactInstance}" field="message"/>

                    </dt>
                </div>
            </g:if>

            <g:if test="${contactInstance?.publier}">
                <div style="display:inline-block">
                <dt style="font-weight:normal ;display:inline"><g:message code="contact.publier.label"
                                                                                  default="Publier"/> :
                         </dt>
                <dt style="font-weight:bold;display:inline">

                    <g:formatBoolean boolean="${contactInstance?.publier}"/></dt>

                </dt>
                </div>
            </g:if>

        </dl>

        <g:form >
            <div class="form-actions">
                <g:link class="btn" action="edit" id="${contactInstance?.id}">
                    <i class="icon-pencil"></i>
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>

            </div>
        </g:form>

    </div>

</div>
</body>
</html>
