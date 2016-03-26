%{--<div class="row" >--}%
    %{--<div class="col-sm-4 col-md-4">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%

                %{--<li>--}%
                    %{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">--}%
                        %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.mesure" />--}%
                    %{--</g:remoteLink>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
    %{--<div class="col-sm-8 col-md-8">--}%
        %{--<div class="page-header">--}%
            %{--<h1><g:message code="list.mesure" /></h1>--}%
        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%
<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>

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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'mesure.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="name" title="${message(code: 'mesure.name.label', default: 'Name')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="isLocal" title="${message(code: 'mesure.isLocal.label', default: 'Is Local')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="isConvertible" title="${message(code: 'mesure.isConvertible.label', default: 'Is Local')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="description" title="${message(code: 'mesure.description.label', default: 'Description')}" />
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${mesureInstanceList}" var="mesureInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${mesureInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: mesureInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: mesureInstance, field: "name")}</td>
						
							<td><g:formatBoolean boolean="${mesureInstance.isLocal}" /></td>

                            <td><g:formatBoolean boolean="${mesureInstance.isConvertible}" /></td>
						
							<td>${fieldValue(bean: mesureInstance, field: "description")}</td>
						
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${mesureInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>


        <filterpane:filterPane dialog="true" domain="simagri.Mesure" />
        </div>
