

<g:render template="/partials/showHeader" model="[beanName:'categorieProduit']"/>

		<div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:form >
					<g:hiddenField name="id" value="${categorieProduitInstance?.id}" />
					<f:display bean="categorieProduitInstance" property="nom"/>
					<f:display bean="categorieProduitInstance" property="actif"/>
					<f:display bean="categorieProduitInstance" property="produits"/>
					<f:display bean="categorieProduitInstance" property="mesures"/>
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
