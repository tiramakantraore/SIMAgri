
<%@ page import="simagri.MyJobConfig" %>
<g:render template="/partials/showHeader"/>
<div class="row">
			<div class="col-sm-12 col-md-12">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
              			<g:if test="${myJobConfigInstance?.cron}">
						<dt><g:message code="myJobConfig.cron.label" default="Cron" /> :
						
							<g:fieldValue bean="${myJobConfigInstance}" field="cron"/>
                        </dt>
                        
					</g:if>
				

					<g:if test="${myJobConfigInstance?.statut}">
						<dt><g:message code="myJobConfig.statut.label" default="Statut" /> :
						
							<g:fieldValue bean="${myJobConfigInstance}" field="statut"/>
                        </dt>
                        
					</g:if>
                Heure : "${new Date()}"
				</dl>

				<g:form class="well small" action="cron">
					<g:hiddenField name="id" value="${myJobConfigInstance?.id}" />

                <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
