
<%@ page import="simagri.Performance" %>

    <g:render template="/partials/showHeader"/>
    <div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				<g:form >
                <f:display bean="performanceInstance" property="nom" input-class="form-control"/>
                <f:display bean="performanceInstance" property="nbOffre" input-class="form-control"/>
                <f:display bean="performanceInstance" property="nbPrix" input-class="form-control"/>
                <f:display bean="performanceInstance" property="nbAlerte" input-class="form-control"/>
                <f:display bean="performanceInstance" property="nbContact" input-class="form-control"/>


					<g:hiddenField name="id" value="${performanceInstance?.id}" />
                    <g:render template="/partials/btnShow"/>
				</g:form>

			</div>

		</div>

