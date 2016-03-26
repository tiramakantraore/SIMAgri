
    <div class="row">
			<div class="col-sm-12 col-md-12">
                <div class="page-header">
                    <h1 class="info-title"><g:message code="list.alerteAValider" /></h1>
                </div>
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <filterpane:filterButton text="Rechercher" />
                <table class="table ">
                    <thead>
                    <tr>
                        <th></th>

                        <util:remoteSortableColumn update="listAlerteContent" action="list" property="nom" title="${message(code: 'alerteReseau.nom.label', default: 'Nom alerte')}" />

                        <util:remoteSortableColumn update="listAlerteContent" action="list" property="valide" title="${message(code: 'alerte.suspendue.label', default: 'Validé ?')}" />

                        <util:remoteSortableColumn update="listAlerteContent" action="list" property="reseaux" title="${message(code: 'alerte.reseaux.label', default: 'Réseaux')}" />



                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${alerteInstanceList}" var="alerteInstance">
                        <tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" controller="applications" action="showAlertesEdit" update="listAlerteContent"
                                              params="{update:'listAlerteContent'}"
                                              method="GET" id="${alerteInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
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
					<util:remotePaginate controller="alerteReseau" update="listAlerteContent" action="list"  total="${alerteInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats controller="alerteReseau" formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane controller="alerteReseau"  dialog="true" associatedProperties="operateur.login"  excludeProperties="recevoirOffres,dateCreation,crontabExpression,dateDernierEnvoi,recevoirPrix,recevoirParEmail,recevoirParSMS,validationDate,dateDemarrage,rejectedDate" domain="simagri.AlerteReseau" />
