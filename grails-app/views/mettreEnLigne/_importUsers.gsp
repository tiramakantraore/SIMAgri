<style type="text/css">

.entry-content {font-family: 'robotoregular',cursive;}
</style>
<h1><g:message code="importer.utilisateurs" /></h1>
<div class="row">
    <div class="col-sm-6 col-md-6">

        <div class="well small" style="min-height:320px">
            <g:message code="choisir.reseau"/>
            <div  class="k-content" >
                <div id="usertreeview" class="groupe-section"></div>
                <div id="result" class="groupe-section"></div>

                <div class="groupe-section">
                    %{--<h3 class="title">Console log--}%
                    %{--</h3>--}%
                    <div class="console"></div>
                </div>

            </div>
        </div>
    </div>

    <div class="col-sm-6 col-md-6 workarea">
        <g:form action="uploadUsers">
            <div style="display:inline-block;" >
                <label for="ecraserDoublons">
                    <g:message code="ecraserDoublons.label" default="Ecraser doublons (Mobile) ?"/>

                </label>
                <g:checkBox name="ecraserDoublons" value="${ecraserDoublons}" />
            </div>
            <br>

            <p><g:message code="selectionnerFichierExcelUtilisateurs.label" default="Veuillez sélectionner le fichier excel des utilisateurs:" /> </p>

            <g:if test="${flash.message}">
                <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
            </g:if>

                <input type="file" name="myUserFile" />

            <g:hiddenField name="ReseauxIds" />
            <a  href="${createLink(controller: 'home', action: 'downloadUserTemplate')}" >
                <p>  <g:message code="default.texteFormatUser.label" default="" /> </p>
            </a>
            <div class="form-actions">
                <div onclick="submitUserForm($(this).closest('form'),
                        '${createLink(controller:controllerName, action:'uploadUsers')}','myUserFile','La mise en ligne des utilisateurs a réusi','listContent');return false;" class="btn-flat btn-primary">
                    <g:message code="default.button.envoyer.label" default="Click me" />
                </div>
            </div>

        </g:form>
    </div>

</div>
<g:render template="/mettreEnLigne/mettre_en_ligne_users_js"/>