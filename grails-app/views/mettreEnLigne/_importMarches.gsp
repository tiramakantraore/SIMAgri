<h1><g:message code="importer.marches" /></h1>

<div class="row">
    <div class="col-sm-8 col-md-8 workarea">
        <br />
        <p><g:message code="selectionnerFichierExcelMarche.label" default="Veuillez sélectionner le fichier excel des marchés:" /> </p>

<g:if test="${flash.message}">
    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
</g:if>
<g:form action="uploadMarches">
    <input type="file" name="myMarketFile"  />
    <a  href="${createLink(controller: 'home', action: 'downloadMarcheTemplate')}" >
        <p>  <g:message code="default.texteFormatMarche.label" default="" /> </p>
    </a>
    <div class="form-actions">
        <g:render template="/partials/genericBtn" model="[theactionName:'uploadMarches',
        btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'myMarketFile',successMessage:'La mise en ligne des marchés a réusi']"/>
   </div>


</g:form>
    </div>
</div>
