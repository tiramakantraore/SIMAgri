<%@ page import="org.springframework.validation.FieldError; simagri.MyJobConfig" %>
<g:render template="/partials/showHeader"/>
		<div class="row">
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${myJobConfigInstance}">
				<bootstrap:alert class="alert-error">
				<ul>
					<g:eachError bean="${myJobConfigInstance}" var="error">
					<li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

					<g:form class="well small form-horizontal" action="edit">
						<g:hiddenField name="version" value="${myJobConfigInstance?.version}" />
                        <g:hiddenField name="id" value="${myJobConfigInstance?.id}" />
                            <g:render template="form"/>

                                <div class="form-actions">
                                    <button type="submit" class="btn-flat  btn-success" name="_action_start" >

                                        <g:message code="default.button.start.label" default="Démarrer" />
                                    </button>
                                    <button type="submit" class="btn-flat  btn-primary" name="_action_stop" >

                                        <g:message code="default.button.stop.label" default="Arrêter" />
                                    </button>


                                </div>

                                <g:render template="/partials/genericBtn" model="[theactionName:'stop',btnClass:'btn-primary',btnName:'default.button.stop.label']"/>

							%{--</div>--}%
					</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
