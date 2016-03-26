<%@ page import="org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader" model="[beanName:'offre',isEdit:true,canCreate:false,titre:'Modifier une offre']"/>

		<div class="row workarea">

			
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${offreInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${offreInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="form-stacked-vertical" controller="offre"  action="edit" >
						<g:hiddenField name="update" value="${update}" />
						<g:render template="/partials/btnEdit" model="[update:update]"/>
						<g:hiddenField name="version" value="${offreInstance?.version}" />
                        <g:hiddenField name="id" value="${offreInstance?.id}" />

                            <g:render template="form"/>
                            <g:render template="offrejs"/>
                            %{--<g:render template="btnEditer"/>--}%

						<g:render template="/partials/btnEdit" model="[update:update]"/>
					</g:form>
			</div>

		</div>

