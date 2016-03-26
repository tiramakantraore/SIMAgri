

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'stockMagazin.label', default: 'StockMagazin')}" />
		<title><g:message code="show.stockMagazin"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<h1><g:message code="show.stockMagazin"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form class="well small form-horizontal" controller="stockMagazin" action="show">
                <f:display bean="stockMagazinInstance" property="magazin"/>
                <f:display bean="stockMagazinInstance" property="produit"/>
                <f:display bean="stockMagazinInstance" property="date"/>
                <f:display bean="stockMagazinInstance" property="stock"/>
                <f:display bean="stockMagazinInstance" property="mesure"/>
                <f:display bean="stockMagazinInstance" property="auteur"/>

					<g:hiddenField name="id" value="${stockMagazinInstance?.id}" />
					<g:hiddenField name="update" value="${update}" />

					<g:render template="/partials/btnShow"  model="['update':update,'instance':stockMagazinInstance]"/>
				</g:form>

			</div>

		</div>
	</body>
</html>
