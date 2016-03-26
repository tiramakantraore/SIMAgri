

<!doctype html>
<html>
	<head>
		<meta name="layout" content="adminLayout">
		<g:set var="entityName" value="${message(code: 'prix.label', default: 'Prix')}" />
		<title><g:message code="show.prix"/></title>
	</head>
	<body>
		<div class="row">
			
			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li class="nav-header"><g:message code="title.prix" /></li>
						<li>
							<g:link class="list" action="list">
								 <i class="glyphicon glyphicon-list"></i>
								<g:message code="list.prix" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								 <i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.prix" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
        </div>
            <div class="row">
			<div class="col-sm-4 col-md-4">

				<div class="page-header">
					<h1><g:message code="show.prix"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="prixInstance" property="produit"/>
                <f:display bean="prixInstance" property="marche"/>
                <f:display bean="prixInstance" property="date"/>
                <f:display bean="prixInstance" property="montant"/>
                <f:display bean="prixInstance" property="mesure"/>
                <f:display bean="prixInstance" property="categorieTarifaire"/>
                <f:display bean="prixInstance" property="statut"/>
                <f:display bean="prixInstance" property="mesure"/>
                <f:display bean="prixInstance" property="commentaire"/>
                <f:display bean="prixInstance" property="commentaireAdministrateur"/>


					<g:hiddenField name="id" value="${prixInstance?.id}" />

					<g:hiddenField name="update" value="${update}" />
					<g:render template="/partials/btnShow"  model="[update:$update,instance:prixInstance]"/>

				</g:form>

			</div>

		</div>
	</body>
</html>
