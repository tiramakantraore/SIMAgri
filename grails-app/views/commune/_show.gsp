<g:render template="/partials/showHeader" model="[beanName:'commune']"/>
            <div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="communeInstance" property="nom"/>
                <f:display bean="communeInstance" property="province"/>


					<g:hiddenField name="id" value="${communeInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
