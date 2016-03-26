

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'magazin.label', default: 'Magazin')}" />
		<title><g:message code="list.magazin"  /></title>


	</head>
	<body>
	<g:render template="/partials/showHeader" model="[canCreate:true,isList:true]"/>
    %{--<div class="row">--}%
    %{--<div class="col-sm-4 col-md-4">--}%
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%
                %{--<li class="nav-header"><g:message code="title.magazin" /></li>--}%

                %{--<li>--}%
                    %{--<g:link class="create" action="create">--}%
                         %{--<i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.magazin" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    %{--</div>--}%
        %{--<div class="col-sm-8 col-md-8">--}%

            %{--<div class="page-header">--}%
                %{--<h1><g:message code="list.magazin" /></h1>--}%
            %{--</div>--}%
        %{--</div>--}%
    %{--</div>--}%

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
						
							<util:remoteSortableColumn update="listContent" action="list" property="code" title="${message(code: 'magazin.code.label', default: 'Code')}" />
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'magazin.nom.label', default: 'Nom')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="localite" title="${message(code: 'magazin.localite.label', default: 'Lieux')}" />



							<util:remoteSortableColumn update="listContent" action="list" property="produits" title="${message(code: 'magazin.produits.label', default: 'Produits')}" />
						

						</tr>
					</thead>
					<tbody>
					<g:each in="${magazinInstanceList}" var="magazinInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                              id="${magazinInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: magazinInstance, field: "code")}</td>
						
							<td>${fieldValue(bean: magazinInstance, field: "nom")}</td>

                            <td>${fieldValue(bean: magazinInstance, field: "localite")}</td>
							<td>${fieldValue(bean: magazinInstance, field: "produits")}</td>
						

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${magazinInstanceTotal}" params="${filterParams}"/>

				</div>
                <export:formats formats="['csv', 'excel', 'pdf']" />
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.Magazin" />
    </body>
</html>
