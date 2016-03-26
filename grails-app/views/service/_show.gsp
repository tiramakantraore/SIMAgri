
<%@ page import="simagri.Service" %>
<g:render template="/partials/showHeader"/>

    <div class="row">
			<div class="col-sm-8 col-md-8">
				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form>
                <f:display bean="serviceInstance" property="nom"/>
                <f:display bean="serviceInstance" property="description"/>



					<g:hiddenField name="id" value="${serviceInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>
	%{--</body>--}%
%{--</html>--}%
