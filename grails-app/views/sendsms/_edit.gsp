
		<div class="row">

			<div class="col-sm-4 col-md-4">
				<div class="well small">
					<ul class="nav nav-list">
						<li>
							<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="list" update="listContent" method="GET"  class="list">
								<i class="glyphicon glyphicon-list"></i>
								<g:message code="list.smsToReseaux" />
							</g:remoteLink>
						</li>
						<li>
							<g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="listContent" method="GET"  class="create">
								<i class="glyphicon glyphicon-plus"></i>
								<g:message code="create.smsToReseaux" />
							</g:remoteLink>
						</li>
					</ul>
				</div>
			</div>
				<div class="col-sm-8 col-md-8">

					<div class="page-header">
						<h1><g:message code="edit.smsToReseaux" /></h1>
					</div>
				</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${smsToReseauxInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${smsToReseauxInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit" >
						<g:hiddenField name="version" value="${smsToReseauxInstance?.version}" />
						<g:hiddenField name="id" value="${smsToReseauxInstance?.id}" />
                            <g:render template="form"/>
							<g:render template="/partials/btnEdit"/>
					</g:form>

			</div>

		</div>