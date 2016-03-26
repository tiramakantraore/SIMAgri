<h1><g:message code="importer.produits" /></h1>

<div class="row">
    <div class="col-sm-8 col-md-8 workarea">
        <br>
    <p><g:message code="selectionnerFichierExcelProduit.label" default="Veuillez sélectionner le fichier excel des produits:" /> </p>

<g:if test="${flash.message}">
    <bootstrap:alert class="alert-warning">${flash.message}</bootstrap:alert>
</g:if>
            <g:form>
                <input type="file" id="myProductFile"  name="myProductFile"/>
                <a  href="${createLink(controller: 'home', action: 'downloadProduitTemplate')}" >
                    <p>  <g:message code="default.texteFormatProduit.label" default=""/> </p>
                </a>
                <g:render template="/partials/genericBtn" model="[theactionName:'uploadProduits',btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'myProductFile',successMessage:'La mise en ligne des produits a réussie']"/>

            </g:form>
</div>
</div>
