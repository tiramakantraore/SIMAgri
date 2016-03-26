<div class="form-actions">
<g:if test="${instance}">
    <g:remoteLink onLoading="showSpinner();" params="[update:update?:'listContent',id:instance?.id]"  onComplete="hideSpinner()" action="edit" update="${update?:'listContent'}"
                  method="GET"  class="btn-flat  btn-primary">
        <i class="glyphicon glyphicon-pencil"></i>
        <g:message code="edit.$controllerName" />
    </g:remoteLink>
</g:if>
<g:else>
    <g:remoteLink onLoading="showSpinner();" params="{id:\$('#id').val()}"  onComplete="hideSpinner()" action="edit" update="${update?:'listContent'}"
                  method="GET"  class="btn-flat  btn-primary">
        <i class="glyphicon glyphicon-pencil"></i>
        <g:message code="edit.$controllerName" />
    </g:remoteLink>
</g:else>
    <div onclick="submitForm($(this).closest('form'),
            '${createLink(controller:controllerName, action:'delete')}','','La suppression  a réussi','${update?:'listContent'}');return false;" class="btn-flat  btn-primary" formnovalidate>
        <i class="glyphicon glyphicon-trash"></i>
        <g:message code="default.button.delete.label" default="Delete" />
    </div>

</div>