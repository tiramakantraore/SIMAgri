<fieldset>

    <div class="row">
        <div class="col-sm-12 col-md-12  required">
            <div class="row">
                <div class="col-sm-5 col-md-5">
                    <label for="prixUnitaire">
                        <g:message code="offre.prixUnitaire.label" default="Prix Unitaire" />

                    </label>
                    <g:field type="number" step="any" required="true" id="prixUnitaireDetail" name="prixUnitaire" value="${fieldValue(bean: offreInstance, field: 'prixUnitaire')}" class="col-sm-12 col-md-12"/>

                </div>
                <div class="col-sm-7 col-md-7">
                    <div class="col-sm-12 col-md-12  required">
                        <label for="prixTotal">
                            <g:message code="offre.prixTotal.label" default="Prix Total" />

                        </label>
                        <g:field type="number" step="any"  id="prixTotalDetail" name="prixTotal" value="${fieldValue(bean: offreInstance, field: 'prixTotal')}" class="col-sm-10 col-md-10" readonly="true"/>

                    </div>
                </div>

            </div>
        </div>
    </div>



</fieldset>