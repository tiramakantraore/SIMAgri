<g:if test="${title}">
<p><strong>${title}</strong></p>
</g:if>
<g:if test="${limitWidth}">
    <div class="${containerClass}" style="max-width:${limitWidth} ">
</g:if>
<g:else>
    <div class="${containerClass}">
</g:else>


    <g:each in="${referenceCollection}" var="instance"  status="i">
        <g:if test="${isArray}">
            <g:set var="valueName" value="${instance}" />
            <g:set var="radioName" value="${radioName}" />
            <g:set var="radioId" value="${radioName}_${instance}" />
            <g:set var="title" value="${instance}" />
        </g:if>
       <g:else>
           <g:if test="${isEnum}">
               <g:set var="valueName" value="${instance?.name()}" />
               <g:set var="radioName" value="${instanceName}" />
               <g:set var="radioId" value="${instanceName}_${valueName}" />
               <g:set var="title" value="${g.message(code:instance?.name(), default:instance?.name())}" />

           </g:if>
           <g:else>
                    <g:set var="valueName" value="${instance?.id}" />
                   <g:set var="radioName" value="${instanceName}" />
                   <g:set var="radioId" value="${instanceName}_${instance?.id}" />
                   <g:set var="title" value="${instance?.toString()}" />
           </g:else>
        </g:else>
        <g:set var="checked" value="${defaultValue==valueName}" />

        <li class="${liClass}">
        <g:if test="${onClickRadio=="null"}">
            <g:radio id="${radioId}" name="${radioName}" value="${valueName}" checked="${checked}" class="myRadiostyle" />
        </g:if>
        <g:else>
            <g:radio id="${radioId}" name="${radioName}" value="${valueName}" checked="${checked}" class="myRadiostyle"  onclick="${onClickRadio}"/>

        </g:else>
            <label for="${radioId}" class="labelClass" data-toggle="tooltip"  title="${title}">${title}</label>
        </li>
    </g:each>
</div>