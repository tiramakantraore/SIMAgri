<%@ page import="simagri.Contact" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'contact.label', default: 'Contact')}"/>
    <title><g:message code="list.contact"/></title>
    <filterpane:includes/>

</head>

<body>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.contact"/></li>

                <li>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
                        <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.contact" />
                    </g:remoteLink>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 col-md-12">

        <div class="page-header">
            <h1><g:message code="list.contact"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <filterpane:filterButton text="Rechercher" />
        <table class="table ">
            <thead>
            <tr>
                <th></th>

                <util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'contact.nom.label', default: 'Nom')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="email" title="${message(code: 'contact.email.label', default: 'Email')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="telephone"
                                  title="${message(code: 'contact.telephone.label', default: 'Telephone')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="sujet" title="${message(code: 'contact.sujet.label', default: 'Sujet')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="message"
                                  title="${message(code: 'contact.message.label', default: 'Message')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="publier"
                                  title="${message(code: 'contact.publier.label', default: 'Publier')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${contactInstanceList}" var="contactInstance">
                <tr>
                    <td class="link">
                        <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                      id="${contactInstance.id}" class="btn-flat  btn-small">
                            Modifier&raquo;</g:remoteLink>
                    </td>

                    <td>${fieldValue(bean: contactInstance, field: "nom")}</td>

                    <td>${fieldValue(bean: contactInstance, field: "email")}</td>

                    <td>${fieldValue(bean: contactInstance, field: "telephone")}</td>

                    <td>${fieldValue(bean: contactInstance, field: "sujet")}</td>

                    <td>${fieldValue(bean: contactInstance, field: "message")}</td>

                    <td><g:formatBoolean boolean="${contactInstance.publier}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <util:remotePaginate update="listContent" action="list"  total="${contactInstanceTotal}" params="${filterParams}"/>
        </div>
        <export:formats formats="['csv', 'excel', 'pdf']" />
    </div>

</div>
<filterpane:filterPane dialog="true" domain="simagri.Contact"/>
%{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
</body>
</html>
