<div class="form-actions">

    <div onclick="submitFormWithCkEditor($(this).closest('form'),
            '${createLink(controller:controllerName, action:actionName)}','photo','La modification a réussi','${update?:'listContent'}');return false;" class="btn-flat  btn-primary">
        <g:message code="default.button.update.label" default="Update" />
    </div>
   %{--<g:if test="${hideDeleteBtn==true}">--}%
            <div onclick="submitForm($(this).closest('form'),
                    '${createLink(controller:controllerName, action:'delete')}','','La suppression  a réussi','${update?:'listContent'}');return false;" class="btn-flat  btn-primary" formnovalidate>
                <i class="glyphicon glyphicon-trash"></i>
                <g:message code="default.button.delete.label" default="Delete" />
            </div>
   %{--</g:if>--}%
</div>