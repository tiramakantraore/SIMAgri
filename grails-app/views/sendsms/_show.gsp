<g:render template="/partials/showHeader"/>

<div class="row">
    <div class="col-sm-12 col-md-12">

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <f:display bean="smsToReseauxInstance" property="toPhoneNumber"/>
        <f:display bean="smsToReseauxInstance" property="yourTextMessage"/>


        <g:form >
            <g:render template="/partials/btnShow"/>
        </g:form>

    </div>

</div>
