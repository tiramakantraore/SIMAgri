<div class="row">
    <div class="col-sm-4 col-md-4">
        <div class="well small">
            <ul class="nav nav-list">
            <g:if test="${!isList}">
                <li>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="list" update="${update?:'listContent'}" method="GET"  class="list">
                        <i class="glyphicon glyphicon-list"></i>
                        <g:message code="list.${controllerName}" />
                    </g:remoteLink>

                </li>
            </g:if>
                <g:if test="${canCreate || (canCreate==null)}">
                <li>
                    <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="create" update="${update?:'listContent'}" method="GET"  class="create">
                        <i class="glyphicon glyphicon-plus"></i>
                        <g:message code="create.${controllerName}" />
                    </g:remoteLink>
                </li>
                </g:if>
            </ul>
        </div>
    </div>
    <div class="col-sm-8 col-md-8">
        <div class="page-header">
               <g:if test="${titre}">
                     <h2>${titre}</h2>
               </g:if>
               <g:else>
                       <g:if test="${actionName!="filter"}">
                       <h2><g:message code="${actionName}.${controllerName}" /></h2>
                   </g:if>
                   <g:else>
                       <h2><g:message code="list.${controllerName}" /></h2>
                   </g:else>
               </g:else>


        </div>
    </div>
</div>