<%@ page import="simagri.Sondage" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'sondage.label', default: 'Sondage')}"/>
    <title><g:message code="list.sondage"/></title>


</head>

<body>
<div class="row">

    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.sondage"/></li>
                <li>
                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent"  class="create">
                    <i class="glyphicon glyphicon-plus"></i>
                    <g:message code="create.sondage" />
                </g:remoteLink>
                </li>
            </ul>
        </div>
    </div>

    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="list.sondage"/></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <filterpane:filterButton text="Rechercher"/>
        <table class="table ">
            <thead>
            <tr>
                <th></th>

                <util:remoteSortableColumn update="listContent" action="list" property="titre" title="${message(code: 'sondage.titre.label', default: 'Titre')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="description"
                                  title="${message(code: 'sondage.description.label', default: 'Description')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="dateDebut"
                                  title="${message(code: 'sondage.dateDebut.label', default: 'Date Debut')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="duree" title="${message(code: 'sondage.duree.label', default: 'Duree')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="dateFin"
                                  title="${message(code: 'sondage.dateFin.label', default: 'Date Fin')}"/>

                <util:remoteSortableColumn update="listContent" action="list" property="actif" title="${message(code: 'sondage.actif.label', default: 'Actif')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${sondageInstanceList}" var="sondageInstance">
                <tr>
                    <td class="link">
                        <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                      id="${sondageInstance.id}" class="btn-flat  btn-small">
                            Modifier&raquo;</g:remoteLink>
                    </td>

                    <td>${fieldValue(bean: sondageInstance, field: "titre")}</td>

                    <td>${fieldValue(bean: sondageInstance, field: "description")}</td>

                    <td><g:formatDate date="${sondageInstance.dateDebut}"/></td>

                    <td>${fieldValue(bean: sondageInstance, field: "duree")}</td>

                    <td><g:formatDate date="${sondageInstance.dateFin}"/></td>

                    <td><g:formatBoolean boolean="${sondageInstance.actif}"/></td>

                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <util:remotePaginate update="listContent" action="list"  total="${sondageInstanceTotal}" params="${filterParams}"/>

        </div>
        <export:formats formats="['csv', 'excel', 'pdf']" />
    </div>

</div>
<filterpane:filterPane dialog="true" domain="simagri.Sondage"/>
%{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
</body>
</html>
