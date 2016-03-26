<g:render template="/partials/showHeader" model="[beanName:'province']"/>
<div class="row">
 			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form>
                <f:display bean="provinceInstance" property="region"/>
                <f:display bean="provinceInstance" property="nom"/>
                <f:display bean="provinceInstance" property="communes"/>



					<g:hiddenField name="id" value="${provinceInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>
	%{--</body>--}%
%{--</html>--}%
