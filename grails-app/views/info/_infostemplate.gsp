<div class="infosContent ">
    <g:if test="${infoInstance?.contenu}">
    %{--<g:if test="${infoInstance?.url}">--}%
        <g:set var="infoURL" value="${infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id )}" />
    %{--<g:formatDate date="${info?.date}"/>--}%
        <a  href="${infoURL}"  target="_blank">
            ${infoInstance?.titre}
        </a>

        %{--<h3 class="infoTitle">${infoInstance?.titre}</h3>--}%
        <p style="color:darkgrey"><prettytime:display date="${infoInstance?.date}" /></p>
        ${infoInstance?.contenu?.prettify()}
    %{--<p>${infoInstance.contenu}</p>--}%
        <g:if test="${infoInstance?.url?.contains('youtube')}">
        %{--<span class="infos-date">${infoInstance.date}</span>--}%

            <g:video videoKey="${infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""}" height="350px"/>

        </g:if>
        %{--<g:if test="${infoInstance?.url}">--}%
            <g:set var="infoURL" value="${infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id )}" />
        %{--<g:formatDate date="${info?.date}"/>--}%
            <a  href="${infoURL}"  target="_blank">
                découvrir le lien
            </a>

            %{--<a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">--}%
                %{--découvrir le lien--}%
            %{--</a>--}%
        %{--</g:if>--}%

    </g:if>
    <g:elseif test="${infoInstance?.url} && ${infoInstance?.url?.contains('youtube')}">
        %{--<h4>${infoInstance?.titre}</h4>--}%
    %{--<g:if test="${infoInstance?.url}">--}%
        <g:set var="infoURL" value="${infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id )}" />
    %{--<g:formatDate date="${info?.date}"/>--}%
        <a  href="${infoURL}"  target="_blank">
            ${infoInstance?.titre}
        </a>

        <g:if test="${infoInstance?.url?.contains('youtube')}">
        %{--<span class="infos-date">${infoInstance.date}</span>--}%

            <g:video videoKey="${infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""}" height="350px"/>

        </g:if>


    %{--<a class="infos-link" href="<g:fieldValue bean="${infoInstance}" field="url"/>">--}%
    %{--découvrir le lien--}%
    %{--</a>--}%
    %{--</g:if>--}%

    </g:elseif>
</div>