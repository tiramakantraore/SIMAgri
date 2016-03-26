
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">


                        <li>
                            <g:link controller="alerteReseau" action="desactiver" onclick="confirm('Confirmez-vous la suspension de toutes les alertes ? ')">
                                <i class="icon-eject"></i>
                                <g:message code="desactiver.alerteReseau" />
                            </g:link>
                        </li>
                        <li>
                            <g:link  controller="alerteReseau" action="activer" onclick="confirm('Confirmez-vous la réactivation de toutes les alertes suspendues ? ')">
                                <i class="icon-lock"></i>
                                <g:message code="activer.alerteReseau" />
                            </g:link>
                        </li>
					</ul>
				</div>
			</div>
            <div class="col-sm-8 col-md-8">

                <div class="page-header">
                    <h1><g:message code="list.alerteReseauDesactive" /></h1>
                </div>
            </div>
     </div>
        <div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
				<table class="table ">
					<thead>
						<tr>
                        <th></th>

                            <util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'alerteReseau.nom.label', default: 'Nom alerte')}" />
                            <util:remoteSortableColumn update="listContent" action="list" property="valide" title="${message(code: 'alerte.suspendue.label', default: 'Validé ?')}" />
                            <util:remoteSortableColumn update="listContent" action="list" property="reseaux" title="${message(code: 'alerte.reseaux.label', default: 'Réseaux')}" />




                        </tr>
					</thead>
					<tbody>
					<g:each in="${alerteInstanceList}" var="alerteInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="applications" action="showAlertesEdit" update="listContent" method="GET" id="${alerteInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;
                                </g:remoteLink>
                            </td>


                            <td>${fieldValue(bean: alerteInstance, field: "nom")}</td>


                            <td><g:if test="${alerteInstance?.valide}">
                                Oui
                            </g:if>
                            <g:elseif test="${!alerteInstance?.valide}">
                                Non
                            </g:elseif>
                            </td>

                            <td>${fieldValue(bean: alerteInstance, field: "reseaux")}</td>

                        </tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate controller="alerteReseau" update="listContent" action="list"  total="${alerteInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats controller="alerteReseau" formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
    <filterpane:filterPane dialog="true" controller="alerteReseau" associatedProperties="operateur.login"  excludeProperties="recevoirOffres,dateCreation,crontabExpression,dateDernierEnvoi,recevoirPrix,recevoirParEmail,recevoirParSMS,validationDate,dateDemarrage,rejectedDate" domain="simagri.AlerteReseau" />
