 <g:render template="/partials/showHeader" />
    <div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

                <bill:imageWithText texte="${monImageInstance?.nom}" imageURL="${createLink(controller: 'monImage', action: 'showImg',params:[id:monImageInstance?.id])}"
                                    imageHeight="50"/>
				<g:form >
                <f:display bean="monImageInstance" property="description"/>
                <f:display bean="monImageInstance" property="proprietaire"/>



					<g:hiddenField name="id" value="${monImageInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
