

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'offre.label', default: 'Offre')}" />
		<title><g:message code="show.offre"/></title>
	</head>
	<body>
		<div class="row">
			<div class="col-sm-4 col-md-4 col-sm-offset8 col-md-offset8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="offreInstance" property="auteur"/>
                <f:display bean="offreInstance" property="typeOffre"/>
                <f:display bean="offreInstance" property="modePaiement"/>
                <f:display bean="offreInstance" property="date"/>
                <f:display bean="offreInstance" property="delaiEnJours"/>
                <f:display bean="offreInstance" property="dateExpiration"/>
                <f:display bean="offreInstance" property="origine"/>
                <f:display bean="offreInstance" property="lieuStockage"/>
                <f:display bean="offreInstance" property="lieuLivraison"/>
                <f:display bean="offreInstance" property="produit"/>
                <f:display bean="offreInstance" property="qualite"/>
                <f:display bean="offreInstance" property="prixUnitaire"/>
                <f:display bean="offreInstance" property="quantite"/>
                <f:display bean="offreInstance" property="prixTotalGros"/>
                <f:display bean="offreInstance" property="conditions"/>
                <f:display bean="offreInstance" property="delaiLivraison"/>
                <f:display bean="offreInstance" property="nbJoursExpiration"/>
                <f:display bean="offreInstance" property="operateur"/>



					<g:hiddenField name="id" value="${offreInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" controller="home" action="accueil" >
							<i class="icon-home"></i>
							<g:message code="default.button.close.label" default="Retour" />
						</g:link>

					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
