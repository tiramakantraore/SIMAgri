
		<div class="row">

			
			<div class="col-sm-12 col-md-12">

				<div class="page-header">
					<g:if test="${update=='listStocksAValiderContent'}">
						<h1><g:message code="edit.stockMagazinAValider" /></h1>
					</g:if>
					<g:else>
						<h1><g:message code="edit.stockMagazinValide" /></h1>
					</g:else>


				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${stockMagazinInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${stockMagazinInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" controller="stockMagazin" action="edit">
						<g:hiddenField name="version" value="${stockMagazinInstance?.version}" />
                        <g:hiddenField name="id" value="${stockMagazinInstance?.id}" />
						<g:hiddenField name="update" value="${update}" />
						<g:render template="/partials/btnEdit" model="[update:update]"/>
                            <g:render template="form"/>
						<g:render template="/partials/btnEdit"  model="[update:update]"/>
					</g:form>

			</div>

		</div>

