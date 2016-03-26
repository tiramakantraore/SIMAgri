<g:if test="${imageUrl}">
        <g:if test="${imagePosition=="Haut"}">
            %{--<g:if test="${imageWidth}">--}%
                %{--<img src="${imageUrl}"  width="${imageWidth}"   alt="image du texte" class="${wrappingClass}"/>--}%
            %{--</g:if>--}%
            %{--<g:elseif test="${imageHeight}">--}%
                %{--<img src="${imageUrl}"  height="${imageHeight}"   alt="image du texte" class="${wrappingClass}"/>--}%
            %{--</g:elseif>--}%
            <img src="${imageUrl}" alt="image du texte" class="${wrappingClass}"/>
            <p>${texte}</p>
        </g:if>
        <g:else>
            <g:if test="${imagePosition=="Bas"}">
                <p>${texte}</p>
                <img src="${imageUrl}" alt="image du texte" class="${wrappingClass}"/>
                %{--<g:if test="${imageWidth}">--}%
                    %{--<img src="${imageUrl}"  width="${imageWidth}"   alt="image du texte" class="${wrappingClass}"/>--}%
                %{--</g:if>--}%
                %{--<g:elseif test="${imageHeight}">--}%
                    %{--<img src="${imageUrl}"  height="${imageHeight}"   alt="image du texte" class="${wrappingClass}"/>--}%
                %{--</g:elseif>--}%

            </g:if>
            <g:else>
                <img src="${imageUrl}" alt="image du texte" class="${wrappingClass}"/>
                %{--<g:if test="${imageWidth}">--}%
                    %{--<img src="${imageUrl}"  width="${imageWidth}"   alt="image du texte" class="${wrappingClass}"/>--}%
                %{--</g:if>--}%
                %{--<g:elseif test="${imageHeight}">--}%
                    %{--<img src="${imageUrl}"  height="${imageHeight}"   alt="image du texte" class="${wrappingClass}"/>--}%
                %{--</g:elseif>--}%
                <p>${texte}</p>
            </g:else>
        </g:else>
</g:if>
<g:else>
    <p>${texte}</p>
</g:else>
