

<g:render template="/partials/showHeader" />

    <div class="row">
			<div class="col-sm-12 col-md-12">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="mesureInstance" property="code"/>
                <f:display bean="mesureInstance" property="name"/>
                <f:display bean="mesureInstance" property="isLocal"/>
                <f:display bean="mesureInstance" property="description"/>



					<g:hiddenField name="id" value="${mesureInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>