<g:render template="/partials/showHeader" model="[beanName:'qualite']"/>

       <div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="qualiteInstance" property="code"/>
                <f:display bean="qualiteInstance" property="nom"/>
                <f:display bean="qualiteInstance" property="description"/>


					<g:hiddenField name="id" value="${qualiteInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
