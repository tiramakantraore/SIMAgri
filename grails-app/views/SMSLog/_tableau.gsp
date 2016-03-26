<table class="table ">
    <thead>
    <tr>
        <th></th>

        <util:remoteSortableColumn update="listContent" action="list" property="theOperator" title="${message(code: 'SMSLogger.operateur.label', default: 'Opérateur')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="theUserMobilePhone" title="${message(code: 'SMSLogger.theUserMobilePhone.label', default: 'Phone')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="date" title="${message(code: 'SMSLogger.date.label', default: 'Date')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="direction" title="${message(code: 'SMSLogger.directionShort.label', default: 'Type SMS')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="theUser" title="${message(code: 'SMSLogger.theUser.label', default: 'User')}" />



    </tr>
    </thead>
    <tbody>
    <g:each in="${SMSLoggerInstanceList}" var="SMSLoggerInstance">
        <tr>
            <td class="link">
                <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="show" update="listContent" method="GET"
                              id="${SMSLoggerInstance.id}" class="btn-flat  btn-small">
                    Voir&raquo;</g:remoteLink>
            </td>



            <td>
                %{--${fieldValue(bean: SMSLoggerInstance, field: "operateur")}--}%
                ${SMSLoggerInstance?.theOperator}
            </td>
            <td>
                %{--${fieldValue(bean: SMSLoggerInstance, field: "user")}--}%
                ${SMSLoggerInstance?.theUserMobilePhone}
            </td>

            <td><g:formatDate date="${SMSLoggerInstance.date}" type="datetime" style="MEDIUM" timeStyle="MEDIUM" /></td>

            <td>${fieldValue(bean: SMSLoggerInstance, field: "direction")}</td>


            <td>${fieldValue(bean: SMSLoggerInstance, field: "theUser")}</td>

        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <util:remotePaginate update="listContent" action="listPaginate"  total="${SMSLoggerInstanceTotal}" params="${filterParams}" />

</div>