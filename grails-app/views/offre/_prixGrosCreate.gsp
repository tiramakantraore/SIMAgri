%{--<fieldset class="well small ">--}%

    <div id="groupPrix" class="row ">

        <div class="col-xs-6">

            <f:field bean="offreInstance"  property="prixUnitaireGros" required="true">
                    <input type='number'  step="any" id='prixUnitaireGros' name='prixUnitaireGros' value="${offreInstance?.prixUnitaireGros}" class="form-control" />
            </f:field>
        </div>
        <div class="col-xs-6">

            <f:field bean="offreInstance"  property="prixTotalGros">
                <input type='number' step="any"  id='prixTotalGros' name='prixTotalGros' value="${offreInstance?.prixTotalGros}" class="form-control" />
            </f:field>
        </div>

    </div>


%{--</fieldset>--}%