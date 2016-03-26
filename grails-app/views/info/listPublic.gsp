
<%@ page import="simagri.Info" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'info.label', default: 'Info')}" />
		<title><g:message code="list.info"  /></title>


	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				%{--<div class="well small">--}%
					%{--<ul class="nav nav-list">--}%
                        %{--<g:each in="${infoInstanceList}" var="infoInstance">--}%

                              %{--<g:if test="${infoInstance.contenu}">--}%
                              %{--<li>  <a href="#${infoInstance.id}">${infoInstance.titre}</a></li>--}%
                              %{--</g:if>--}%


                        %{--</g:each>--}%

					%{----}%
					%{--</ul>--}%
				%{--</div>--}%
			</div>
            <filterpane:filterButton text="Chercher" />
			<div class="col-sm-5 col-md-5 well">

				%{--<div class="page-header">--}%
					%{--<h1 class="info-title"><g:message code="list.info" /></h1>--}%
				%{--</div>--}%

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

                <div class="info-title">

                </div>
					<g:each in="${infoInstanceList}" var="infoInstance">
                        <div class="infosContent">
                                <g:if test="${infoInstance.contenu}">
                                    <a name="${infoInstance.id}"></a>
                                    %{--<span class="infos-date">${infoInstance.date}</span>--}%
                                    <h4><g:link action="showPublic" id="${infoInstance.id}">${infoInstance.titre}</g:link></h4>
                                    ${infoInstance.contenu.decodeHTML()}
                                    %{--<p>${infoInstance.contenu}</p>--}%
                                    <a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">
                                        découvrir le lien
                                    </a>
                                    <p><prettytime:display date="${infoInstance?.date}" /></p>
                                </g:if>
                        </div>

					</g:each>
					%{--</tbody>--}%
				%{--</table>--}%
                <div class="pagination">
                <util:remotePaginate update="listContent" action="list"  total="${infoInstanceTotal}" params="${filterParams}" />

                </div>
                <export:formats formats="['csv', 'excel', 'pdf']" />

			</div>
            <div class="col-sm-4 col-md-4">
                %{--<div class="well small">--}%
                %{--<ul class="nav nav-list">--}%
                %{--<li class="nav-header"><g:message code="title.info" /></li>--}%

                %{--<li>--}%
                %{--<g:link class="create" action="create">--}%
                %{-- <i class="glyphicon glyphicon-plus"></i>--}%
                %{--<g:message code="create.info" />--}%
                %{--</g:link>--}%
                %{--</li>--}%
                %{--</ul>--}%
                %{--</div>--}%
            </div>
		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Info" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
