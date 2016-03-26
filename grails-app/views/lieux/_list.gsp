    <div class="row">
    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
                <li class="nav-header"><g:message code="title.lieux" /></li>

                <li>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
                        <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.lieux" />
                    </g:remoteLink>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="geoCodeAll" update="listContent"  class="create">
                        <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="geoCodeAll.location" />
                    </g:remoteLink>


                </li>
            </ul>
        </div>
    </div>
    <div class="col-sm-8 col-md-8">

        <div class="page-header">
            <h1><g:message code="list.lieux" /></h1>
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'lieux.nom.label', default: 'Nom')}" />
						
							<th class="header"><g:message code="lieux.commune.label" default="Commune" /></th>
						
							<util:remoteSortableColumn update="listContent" action="list" property="longitude" title="${message(code: 'lieux.longitude.label', default: 'Longitude')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="latitude" title="${message(code: 'lieux.latitude.label', default: 'Latitude')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${lieuxInstanceList}" var="lieuxInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${lieuxInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: lieuxInstance, field: "nom")}</td>
						
							<td>${fieldValue(bean: lieuxInstance, field: "commune")}</td>
						
							<td>${fieldValue(bean: lieuxInstance, field: "longitude")}</td>
						
							<td>${fieldValue(bean: lieuxInstance, field: "latitude")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${lieuxInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Lieux" />

