%{--<fieldset>--}%
<div id="groupEnregistrer">
    <div class="col-sm-1 col-md-10 offset-2">
    <div class="form-actions">
         <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'offre', action:'create')}','','La validation de la page  a réussi','listContent');return false;" >
            <i class="icon-ok icon-white "></i>
            <g:message code="default.button.create.label" default="Create" />
        </div>
    </div>
    </div>
</div>

