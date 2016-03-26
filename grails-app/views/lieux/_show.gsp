<g:render template="/partials/showHeader" model="[beanName:'lieux']"/>
<div class="row">
			<div class="col-sm-8 col-md-8">


				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
					<f:display bean="lieuxInstance" property="nom" input-class="form-control"/>
					<f:display bean="lieuxInstance" property="commune" input-class="form-control"/>
					<f:display bean="lieuxInstance" property="longitude" input-class="form-control"/>
					<f:display bean="lieuxInstance" property="latitude" input-class="form-control"/>
					<g:hiddenField name="id" value="${lieuxInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
