
<g:render template="/partials/showHeader" />
		<div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="mesureCorrespondanceInstance" property="mesureDepart"/>
                <f:display bean="mesureCorrespondanceInstance" property="mesureDestination"/>
                <f:display bean="mesureCorrespondanceInstance" property="equivalence"/>



					<g:hiddenField name="id" value="${mesureCorrespondanceInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
