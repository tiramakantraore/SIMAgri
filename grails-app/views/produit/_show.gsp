
<g:render template="/partials/showHeader"/>

		<div class="row">
			
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="produitInstance" property="code"/>
                <f:display bean="produitInstance" property="nom"/>
                <f:display bean="produitInstance" property="nomScientifique"/>
                <f:display bean="produitInstance" property="categorie"/>
                <f:display bean="produitInstance" property="mesure"/>
                <f:display bean="produitInstance" property="actif"/>
                <f:display bean="produitInstance" property="commentaire"/>


					<g:hiddenField name="id" value="${produitInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
