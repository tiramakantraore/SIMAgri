<g:render template="/partials/showHeader" model="[beanName:'magazin']"/>
		<div class="row">
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="magazinInstance" property="code"/>
                <f:display bean="magazinInstance" property="nom"/>
                <f:display bean="magazinInstance" property="localite"/>
                <f:display bean="magazinInstance" property="description"/>
                <g:if test="${magazinInstance?.produits}">

                    <g:each in="${magazinInstance.produits}" var="p">
                        <p>${p?.encodeAsHTML()}</p>
                    </g:each>

                </g:if>


					<g:hiddenField name="id" value="${magazinInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
