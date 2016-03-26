<%@ page import="simagri.Marche; simagri.Produit; simagri.Reseau; org.springframework.validation.FieldError" %>
<g:render template="/partials/showHeader" />
          <div class="row">
			<div class="col-sm-8 col-md-8">

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
                <g:form >
                <f:display bean="marcheInstance" property="code"/>
                <f:display bean="marcheInstance" property="nom"/>
                <f:display bean="marcheInstance" property="region"/>
                <f:display bean="marcheInstance" property="location"/>
                %{--<g:if test="${marcheInstance?.description}">--}%
                %{--<f:display bean="marcheInstance" property="description"/>--}%
                %{--</g:if>--}%
                %{--<f:display bean="marcheInstance" property="produits"/>--}%
               %{--<g:if test="${marcheInstance?.produits}">--}%
                   %{--${marcheInstance?.produits}--}%
                %{--<f:display bean="marcheInstance" property="produits"/>--}%
               %{--</g:if>--}%
                    %{--<g:if test="${marcheInstance?.reseaux}">--}%
                        %{--${marcheInstance?.reseaux}--}%
                %{--<f:display bean="marcheInstance" property="reseaux"/>--}%
                    %{--</g:if>--}%
                    %{--<g:if test="${marcheInstance?.utilisateurs}">--}%
                        %{--${marcheInstance?.utilisateurs}--}%
                 %{--<f:display bean="marcheInstance" property="utilisateurs"/>--}%
                    %{--</g:if>--}%
                    %{--<g:if test="${marcheInstance?.membresAutorises}">--}%
                        %{--${marcheInstance?.membresAutorises}--}%
                 %{--<f:display bean="marcheInstance" property="membresAutorises"/>--}%
                    %{--</g:if>--}%


					<g:hiddenField name="id" value="${marcheInstance?.id}" />
                    <g:render template="/partials/btnShow"/>

				</g:form>

			</div>
		</div>
