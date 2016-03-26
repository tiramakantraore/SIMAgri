<div class="form-actions">
    <div onclick="submitFormAsync($(this).closest('form'),
            '${createLink(controller:controllerName, action:theactionName)}','${inputField?:''}','${successMessage?:'La mise à jour a réussie'}','listContent');return false;" class="btn-flat ${btnClass}">
        <g:message code="${btnName}" default="Click me" />
    </div>
</div>