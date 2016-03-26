<br>
<g:each status="i" var="info" in="${topNInfos}">
    <div class="docContent">
        <p> <span style="font-size:11px;">${info?.date} </span>
            %{--<g:formatDate date="${info?.date}"/>--}%
            <a  href="${g.createLink(controller:"info", action:"showPublic", id:info.id )}"  target="_blank">
                ${info?.titre}
            </a>
        </p>
    </div>
</g:each>