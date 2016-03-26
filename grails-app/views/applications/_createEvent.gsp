<div class="row">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-10 col-xs-offset1">
        <h1><g:message code="create.event" /></h1>
        <g:form class="form-horizontal " name="event" >
                 <g:render template="/event/form" model="model" />
         </g:form>
        <br>
        <div  class="btn-flat  btn-primary validerCalendrier">

            <g:message code="default.button.create.label" default="Create" />
        </div>
    </div>
</div>
<g:render template="/applications/eventsjs"/>
