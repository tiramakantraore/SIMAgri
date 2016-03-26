<div class="infosContent ">
    <g:if test="${noteMarcheInstance?.contenu}">
        ${noteMarcheInstance?.titre}

        <g:if test="${noteMarcheInstance.photo}">
            <bill:imageWithText texte="Photo" imageURL="${createLink(controller: 'noteMarche', action: 'showImg',params:[id:noteMarcheInstance?.id])}"
                                imageHeight="100"/>
        </g:if>
        %{--<h3 class="infoTitle">${infoInstance?.titre}</h3>--}%
        <p style="color:darkgrey"><prettytime:display date="${noteMarcheInstance?.date}" /></p>
        ${noteMarcheInstance?.contenu?.prettify()}
     </g:if>

</div>