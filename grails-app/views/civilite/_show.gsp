<g:render template="/partials/showHeader" model="[beanName:'civilite']"/>
		<div class="row">
		<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form class="well ">
                <f:display bean="civiliteInstance" property="code"/>
                <f:display bean="civiliteInstance" property="libelle"/>


					<g:hiddenField name="id" value="${civiliteInstance?.id}" />

                    <g:render template="/partials/btnShow"/>

				</g:form>

			</div>

		</div>
	
