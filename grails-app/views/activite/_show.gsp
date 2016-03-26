<g:render template="/partials/showHeader" model="[beanName:'activite']"/>
    <div class="row">

			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="activiteInstance" property="code"/>
                <f:display bean="activiteInstance" property="libelle"/>
                <f:display bean="activiteInstance" property="commentaire"/>



					<g:hiddenField name="id" value="${activiteInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
