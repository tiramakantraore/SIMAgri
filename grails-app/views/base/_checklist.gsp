<g:if test="${title}">
    <p><strong>${title}</strong></p>
</g:if>
        <div class="${containerClass}">
        <g:if test="${referenceCollection?.size()>0}">
            <g:each in="${referenceCollection}" var="instance"  status="i">
                <li class="${liClass}">

                    <g:if test="${onClickCheck=="null"}">
                        <g:checkBox id="${instanceName}_${instance?.id}" name="${instanceName}_${instance?.id}" value="${instance?.id}" checked="${checked}" class="myCheckBoxstyle icon-check" />
                    </g:if>
                    <g:else>
                        <g:checkBox id="${instanceName}_${instance?.id}" name="${instanceName}_${instance?.id}" value="${instance?.id}" checked="${checked}" class="myCheckBoxstyle icon-check" onclick="${onClickCheck}" />
                      </g:else>

                    <span class="lbl padding-8"><label for="${instanceName}_${instance?.id}" class="labelClass" data-toggle="tooltip"  title="${instance?.toString()}">${instance?.toString()}</label>
                    </span>
                </li>
            </g:each>
        </g:if>
        </div>
%{--isCreating?false:--}%