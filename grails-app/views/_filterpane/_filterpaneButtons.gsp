<div class="form-actions">
    <span class="button">
        <input type="button"
               class="btn"
               value="${cancelText}" 
               onclick="return grailsFilterPane.hideElement('${containerId}');" />
    </span>
    <span class="button">
        <input type="button"
               class="btn"
               value="${clearText}" 
               onclick="return grailsFilterPane.clearFilterPane('${formName}');" />
    </span>


    <span class="button">
        <g:submitToRemote onLoading="showSpinner();" onComplete="hideSpinner()" url='[action:"${action}"]'  value="${applyText}" update="listContent" method="GET"  class="btn"/>

    </span>
</div>