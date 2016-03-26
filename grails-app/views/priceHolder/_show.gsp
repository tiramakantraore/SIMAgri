
    <div class="row">
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<h1><g:message code="show.priceHolder"/></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>




				<g:form >
                    <f:display bean="priceHolderInstance" property="produit"/>
                    <f:display bean="priceHolderInstance" property="marche"/>
                    <f:display bean="priceHolderInstance" property="date"/>
                    <f:display bean="priceHolderInstance" property="prixGros"/>
                    <f:display bean="priceHolderInstance" property="prixDetail"/>
                    <f:display bean="priceHolderInstance" property="mesureGros"/>
                    <f:display bean="priceHolderInstance" property="mesureDetail"/>
                    <f:display bean="priceHolderInstance" property="commentaire"/>
                    <f:display bean="priceHolderInstance" property="isValidated"/>
                    <f:display bean="priceHolderInstance" property="isRejected"/>
                    <f:display bean="priceHolderInstance" property="auteur"/>
					<g:hiddenField name="id" value="${priceHolderInstance?.id}" />
                    <g:hiddenField name="update" value="${update}" />
                    <g:render template="/partials/btnShow"  model="[update:$update,instance:priceHolderInstance]"/>


                </g:form>

			</div>

		</div>