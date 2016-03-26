
<%@ page import="simagri.MyJobConfig" %>
<g:render template="/partials/showHeader"/>
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="operationId" title="${message(code: 'myJobConfig.operationId.label', default: 'Operation Id')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="cron" title="${message(code: 'myJobConfig.cron.label', default: 'Cron')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="groupJob" title="${message(code: 'myJobConfig.groupJob.label', default: 'Group Job')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="jobClass" title="${message(code: 'myJobConfig.jobClass.label', default: 'Job Class')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="statut" title="${message(code: 'myJobConfig.statut.label', default: 'Statut')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${myJobConfigInstanceList}" var="myJobConfigInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="myJobConfig" action="edit" update="listContent" method="GET"
                                              id="${myJobConfigInstance.id}" params="[update:'listContent']" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                                %{--<g:link action="show" id="${myJobConfigInstance.id}" class="btn-flat  btn-small">Modifier&raquo;</g:link>--}%
                            </td>
						
							<td>${fieldValue(bean: myJobConfigInstance, field: "operationId")}</td>
						
							<td>${fieldValue(bean: myJobConfigInstance, field: "cron")}</td>
						
							<td>${fieldValue(bean: myJobConfigInstance, field: "groupJob")}</td>
						
							<td>${fieldValue(bean: myJobConfigInstance, field: "jobClass")}</td>
						
							<td>${fieldValue(bean: myJobConfigInstance, field: "statut")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${myJobConfigInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.MyJobConfig" />
        %{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  excludeProperties="email,nomSousSection,numeroTelephone,actif"--}%
    %{--</body>--}%
%{--</html>--}%
