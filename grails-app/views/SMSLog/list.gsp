
<%@ page import="simagri.SMSLogger" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'SMSLogger.label', default: 'SMSLogger')}" />
		<title><g:message code="list.SMSLogger"  /></title>

	</head>
	<body>
		<div class="row">
            <div class="col-sm-1 col-md-1">
            </div>

			<div class="col-sm-1 col-md-11">
				
				<div class="page-header">
					<h1><g:message code="list.SMSLogger" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>

                            <util:remoteSortableColumn update="listContent" action="list" property="operateur" title="${message(code: 'SMSLogger.operateur.label', default: 'Opérteur')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="message" title="${message(code: 'SMSLogger.message.label', default: 'Message')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="date" title="${message(code: 'SMSLogger.date.label', default: 'Date')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="direction" title="${message(code: 'SMSLogger.direction.label', default: 'Type SMS')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="User" title="${message(code: 'SMSLogger.user.label', default: 'User')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="fromPhone" title="${message(code: 'SMSLogger.fromPhone.label', default: 'N° Source')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="toPhone" title="${message(code: 'SMSLogger.toPhone.label', default: 'N° Destination')}" />
                            
                            <util:remoteSortableColumn update="listContent" action="list" property="dureeEnS" title="${message(code: 'SMSLogger.dureeEnS.label', default: 'Durée (ms)')}" />
                            

						</tr>
					</thead>
					<tbody>
					<g:each in="${SMSLoggerInstanceList}" var="SMSLoggerInstance">
						<tr>
                            <td class="link">
                                <g:link action="show" id="${SMSLoggerInstance.id}" class="btn-flat  btn-small">Modifier&raquo;</g:link>
                            </td>

                            <td>${fieldValue(bean: SMSLoggerInstance, field: "operateur")}</td>
						
							<td>${fieldValue(bean: SMSLoggerInstance, field: "message")}</td>
						
							<td><g:formatDate date="${SMSLoggerInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM" /></td>

							<td>${fieldValue(bean: SMSLoggerInstance, field: "direction")}</td>
<%--                            <td><g:if test="${SMSLoggerInstance.isIn}">--%>
<%--                                Oui--%>
<%--                            </g:if>--%>
<%--                            <g:elseif test="${!SMSLoggerInstance.isIn}">--%>
<%--                                Non--%>
<%--                            </g:elseif>--%>
<%--                            </td>--%>
						
							<td>${fieldValue(bean: SMSLoggerInstance, field: "user")}</td>

                            <td>${fieldValue(bean: SMSLoggerInstance, field: "fromPhone")}</td>

                            <td>${fieldValue(bean: SMSLoggerInstance, field: "toPhone")}</td>
                            
                            <td>${fieldValue(bean: SMSLoggerInstance, field: "dureeEnS")}</td>
                            
 						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${SMSLoggerInstanceTotal}" params="${filterParams}" />

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.SMSLogger" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    </body>
</html>
