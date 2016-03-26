

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'smsToReseaux.label', default: 'SmsToReseaux')}" />
		<title><g:message code="list.smsToReseaux"  /></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.smsToReseaux" /></li>

                        <li>
                            <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent"  class="create">
                                <i class="glyphicon glyphicon-plus"></i>
                                <g:message code="create.smsToReseaux" />
                            </g:remoteLink>
                        </li>
					</ul>
				</div>
			</div>

			<div class="col-sm-8 col-md-8">
				
				<div class="page-header">
					<h1><g:message code="list.smsToReseaux" /></h1>
				</div>
            </div>
      </div>
    <div class="row">
        <div class="col-sm-12 col-md-12">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="yourTextMessage" title="${message(code: 'smsToReseaux.yourTextMessage.label', default: 'Your Text Message')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${smsToReseauxInstanceList}" var="smsToReseauxInstance">
						<tr>
                            <td class="link">
                                <g:link action="show" id="${smsToReseauxInstance.id}" class="btn-flat  btn-small">Modifier&raquo;</g:link>
                            </td>
						
							<td>${fieldValue(bean: smsToReseauxInstance, field: "yourTextMessage")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${smsToReseauxInstanceTotal}" />

				</div>
                %{--<export:formats formats="['excel', 'pdf']" />--}%
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.SmsToReseaux" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
