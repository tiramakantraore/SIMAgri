
<g:render template="/partials/showHeader" model="[beanName:'region']"/>
    <div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="regionInstance" property="nom"/>
                <f:display bean="regionInstance" property="provinces"/>




					<g:hiddenField name="id" value="${regionInstance?.id}" />
                    <g:render template="/partials/btnShow"/>

				</g:form>

			</div>

		</div>
