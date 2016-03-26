
    <style type="text/css">

    .entry-content {font-family: 'robotoregular',cursive;}


    </style>

    <ckeditor:resources/>
    <h1><g:message code="miseEnLigne.prix" /></h1>
    <g:form class="form-stacked-vertical myform">
        <div class="row">
            <div class="col-sm-12 col-md-12 ">
                <g:hiddenField name="priceData"  />
                <g:hiddenField name="marketId" />

                <g:render template="enteteFirstPage"/>
                <g:render template="grid_position"/>
            </div>

        </div>

        <div class="form-actions">
            <div  class="btn-flat  btn-primary validerPrix">

                <g:message code="default.button.create.label" default="Create" />
            </div>
        </div>
    </g:form>

    <g:render template="mettre_en_ligne_prix_js"/>

