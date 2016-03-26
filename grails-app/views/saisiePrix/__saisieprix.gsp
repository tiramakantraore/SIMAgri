<g:form class="form-stacked-vertical myform">
<div class="row">
    <div class="col-sm-12 col-md-12 ">
        <g:hiddenField name="priceData"  />
        <g:hiddenField name="marketId" />

        <g:render template="/saisiePrix/enteteFirstPage"/>
        <g:render template="/saisiePrix/grid_position"/>
    </div>

</div>

    <div class="form-actions">
        <div  class="btn-flat  btn-primary validerPrix">

        <g:message code="default.button.create.label" default="Create" />
    </div>
    </div>
</g:form>
