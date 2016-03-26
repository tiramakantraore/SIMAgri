<fieldset>
  <div class="row">
    <div class="col-sm-12 col-md-12">
    <div class="form-actions">
         <div  class="btn-flat  btn-primary" onclick="submitForm($(this).closest('form'), '${createLink(controller:'offre', action:'edit')}','','La validation de la page  a réussi','listContent');return false;" >
            <i class="icon-ok icon-white "></i>
            <g:message code="default.button.create.label" default="Create" />
        </div>
        <div type="submit" class="btn-flat  btn-primary" name="_action_delete" formnovalidate>
             <i class="glyphicon glyphicon-trash"></i>
            <g:message code="default.button.delete.label" default="Delete" />
        </div>
    </div>
    </div>
</div><!--/row-->
</fieldset>
