<table class="table ">
    <thead>
    <tr>
        <th></th>



        <util:remoteSortableColumn update="listContent" action="list" property="nomComplet" title="${message(code: 'utilisateur.nomComplet.label', default: 'Nom complet')}" />


        <util:remoteSortableColumn update="listContent" action="list" property="mobilePhone" title="${message(code: 'utilisateur.mobilePhone.label', default: 'Téléphone')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="role" title="${message(code: 'utilisateur.role.label', default: 'Rôle')}" />

        <util:remoteSortableColumn update="listContent" action="list" property="reseauPrincipal" title="${message(code: 'utilisateur.reseauPrincipal.label', default: 'Réseau Principal')}" />


        <util:remoteSortableColumn update="listContent" action="list" property="reseaux" title="${message(code: 'utilisateur.reseaux.label', default: 'Réseau Principal')}" />


    </tr>
    </thead>
    <tbody>
    <g:each in="${utilisateurInstanceList}" var="utilisateurInstance">
        <tr>
            <td class="link">
                <g:remoteLink controller="utilisateur" action="edit" params="{isChange:'false'}" method="GET" update="listContent" id="${utilisateurInstance?.id}" onLoading="showSpinner();"
                              onComplete="hideSpinner()">Modifier&raquo;
                </g:remoteLink>
            %{----}%
            %{--<g:remoteLink onLoading="showSpinner();" params="{userType:$userType}" onComplete="hideSpinner()" action="edit" id="${utilisateurInstance?.id}" update="listContent" method="GET"  class="edit">--}%

            %{--Modifier&raquo;--}%
            %{--</g:remoteLink>--}%

            %{--<div class="btn-flat  btn-small modifier">--}%
            %{--Modifier&raquo;--}%
            %{--</div>--}%
            </td>



            <td>${fieldValue(bean: utilisateurInstance, field: "nomComplet")}</td>

            <td>${fieldValue(bean: utilisateurInstance, field: "mobilePhone")}</td>

            <td>${fieldValue(bean: utilisateurInstance, field: "role")}</td>

            <td>${fieldValue(bean: utilisateurInstance, field: "reseauPrincipal")}</td>

            <td>${fieldValue(bean: utilisateurInstance, field: "reseaux")}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="pagination">
    <util:remotePaginate update="listContent" action="listPaginate"  params="[userType:userType]" total="${utilisateurInstanceTotal}" />
</div>