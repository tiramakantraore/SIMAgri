
    %{--<div class="row">--}%
			%{----}%
			%{--<div class="col-sm-5 col-md-5">--}%
				%{--<div class="well small">--}%
					%{--<ul class="nav nav-list">--}%

						%{--<li>--}%
							%{--<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"   class="create">--}%
								%{--<i class="glyphicon glyphicon-plus"></i>--}%
								%{--<g:message code="create.pageAccueil" />--}%

							%{--</g:remoteLink>--}%
						%{--</li>--}%
					%{--</ul>--}%
				%{--</div>--}%

			%{--</div>--}%
                %{--<div class="col-sm-7 col-md-7">--}%
                        %{--<h1><g:message code="list.pageAccueil" /></h1>--}%
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
						
							<util:remoteSortableColumn update="listContent" action="list" property="nom" title="${message(code: 'pageAccueil.nom.label', default: 'Nom')}" />

							<util:remoteSortableColumn update="listContent" action="list" property="pagePrix" title="${message(code: 'pageAccueil.pagePrix.label', default: 'Page prix')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="pageOffre" title="${message(code: 'pageAccueil.pageOffre.label', default: 'Page offre')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="pageStock" title="${message(code: 'pageAccueil.pageStock.label', default: 'Page stock')}" />

                            <util:remoteSortableColumn update="listContent" action="list" property="pageReseau" title="${message(code: 'pageAccueil.pageReseau.label', default: 'Page réseau')}" />
						</tr>
					</thead>
					<tbody>
					<g:each in="${pageAccueilInstanceList}" var="pageAccueilInstance">
						<tr>
                            <td class="link">
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner();if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'pageAccueil', action:'edit')}');}" action="edit" update="listContent" method="GET" id="${pageAccueilInstance.id}" class="btn-flat  btn-small">
                                    Modifier&raquo;</g:remoteLink>
                            </td>
						
							<td>${fieldValue(bean: pageAccueilInstance, field: "nom")}</td>

                            <td>${fieldValue(bean: pageAccueilInstance, field: "pagePrix")}</td>

                            <td>${fieldValue(bean: pageAccueilInstance, field: "pageOffre")}</td>

                            <td>${fieldValue(bean: pageAccueilInstance, field: "pageStock")}</td>

                            <td>${fieldValue(bean: pageAccueilInstance, field: "pageReseau")}</td>




                        </tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<util:remotePaginate update="listContent" action="list"  total="${pageAccueilInstanceTotal}" params="${filterParams}" />

				</div>
                
			</div>

		</div>
        <filterpane:filterPane dialog="true" domain="simagri.PageUtilisateur" />
	%{--</div>--}%
	<script type="text/javascript">
		// Not the most elegant code but fit enough for this example. Enjoy the kitten goodness!
	//	var container =$("#main");


	</script>

