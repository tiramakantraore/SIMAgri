
<g:render template="/partials/showHeader" model="[beanName:'entreprise']"/>

    <div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="entrepriseInstance" property="nom"/>
                <f:display bean="entrepriseInstance" property="site_web"/>
                <f:display bean="entrepriseInstance" property="email"/>




					<g:hiddenField name="id" value="${entrepriseInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
