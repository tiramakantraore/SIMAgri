<!doctype html>
<html>
<head>
	<meta name="layout" content="accueilLayout">
	<title><g:message code="list.listes" /></title>
	<style type="text/css">

	.entry-content {font-family: 'robotoregular',cursive;}


	</style>
	<ckeditor:resources/>
</head>
<body>
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
                                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET" id="${pageAccueilInstance.id}" class="btn-flat  btn-small">
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
%{--<script type="text/javascript">--}%
	%{--function scrollTo() {--}%
		%{--location.hash = "#listContent";--}%
	%{--}--}%
	%{--var pageAccueil=$('#pageAccueil');--}%
	%{--$(document).ready(function() {--}%
		%{--$("[data-type='item']").on("click", function () {--}%
			%{--$("[data-type='item']").removeClass("active");--}%
			%{--$(this).addClass("active");--}%
		%{--});--}%

		%{--$('[data-toggle="collapse"]').click(function (e) {--}%
			%{--var chevState =$(this).children("i.indicator").toggleClass('icon-plus icon-minus');--}%
			%{--$(this).children("i.indicator").not(chevState).removeClass("icon-plus").addClass("icon-minus");--}%


		%{--});--}%

		%{--openMenuOnHover();--}%
	%{--});--}%

%{--</script>--}%
</body>
</html>