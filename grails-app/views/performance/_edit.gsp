<%@ page import="simagri.Performance" %>
<g:render template="/partials/showHeader" />
<div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${performanceInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${performanceInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="form-horizontal" action="edit" id="${performanceInstance?.id}" >
						<g:hiddenField name="version" value="${performanceInstance?.version}" />
                        <g:hiddenField name="id" value="${performanceInstance?.id}" />
                            <g:render template="form"/>
							<div class="form-actions">
								 <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'performance', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >

									<g:message code="default.button.update.label" default="Update" />
								</div>
                                <div onclick="submitForm($(this).closest('form'),
                                        '${createLink(controller:'performance', action:'delete')}','','La suppression de l\'objectif a réussi','listContent');return false;" class="btn-flat  btn-primary" formnovalidate>
                                    <i class="glyphicon glyphicon-trash"></i>
                                    <g:message code="default.button.delete.label" default="Delete" />
                                </div>
							</div>
					</g:form>

			</div>

		</div>
