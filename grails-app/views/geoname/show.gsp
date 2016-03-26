
<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <g:set var="entityName" value="${message(code: 'geoname.label', default: 'Geoname')}"/>
    <title><g:message code="show.geoname" args="[entityName]"/></title>
</head>

<body>
<a href="#show-geoname" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/home')}"><g:message code="default.home.label"/></a>
        </li>
        <li><g:link class="list" action="list"><g:message code="list.geoname" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="create.geoname" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-geoname" class="content scaffold-show" role="main">
    <h1><g:message code="show.geoname" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list geoname">

        <g:if test="${geonameInstance?.geonameId}">
            <li class="fieldcontain">
                <span id="geonameId-label" class="property-label"><g:message code="geoname.geonameId.label"
                                                                             default="Geoname Id"/></span>

                <span class="property-value" aria-labelledby="geonameId-label"><g:fieldValue bean="${geonameInstance}"
                                                                                             field="geonameId"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="geoname.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${geonameInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.asciiname}">
            <li class="fieldcontain">
                <span id="asciiname-label" class="property-label"><g:message code="geoname.asciiname.label"
                                                                             default="Asciiname"/></span>

                <span class="property-value" aria-labelledby="asciiname-label"><g:fieldValue bean="${geonameInstance}"
                                                                                             field="asciiname"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.latitude}">
            <li class="fieldcontain">
                <span id="latitude-label" class="property-label"><g:message code="geoname.latitude.label"
                                                                            default="Latitude"/></span>

                <span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${geonameInstance}"
                                                                                            field="latitude"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.longitude}">
            <li class="fieldcontain">
                <span id="longitude-label" class="property-label"><g:message code="geoname.longitude.label"
                                                                             default="Longitude"/></span>

                <span class="property-value" aria-labelledby="longitude-label"><g:fieldValue bean="${geonameInstance}"
                                                                                             field="longitude"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.featureClass}">
            <li class="fieldcontain">
                <span id="featureClass-label" class="property-label"><g:message code="geoname.featureClass.label"
                                                                                default="Feature Class"/></span>

                <span class="property-value" aria-labelledby="featureClass-label"><g:fieldValue
                        bean="${geonameInstance}" field="featureClass"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.featureCode}">
            <li class="fieldcontain">
                <span id="featureCode-label" class="property-label"><g:message code="geoname.featureCode.label"
                                                                               default="Feature Code"/></span>

                <span class="property-value" aria-labelledby="featureCode-label"><g:fieldValue bean="${geonameInstance}"
                                                                                               field="featureCode"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.countryCode}">
            <li class="fieldcontain">
                <span id="countryCode-label" class="property-label"><g:message code="geoname.countryCode.label"
                                                                               default="Country Code"/></span>

                <span class="property-value" aria-labelledby="countryCode-label"><g:fieldValue bean="${geonameInstance}"
                                                                                               field="countryCode"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.cc2}">
            <li class="fieldcontain">
                <span id="cc2-label" class="property-label"><g:message code="geoname.cc2.label" default="Cc2"/></span>

                <span class="property-value" aria-labelledby="cc2-label"><g:fieldValue bean="${geonameInstance}"
                                                                                       field="cc2"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.population}">
            <li class="fieldcontain">
                <span id="population-label" class="property-label"><g:message code="geoname.population.label"
                                                                              default="Population"/></span>

                <span class="property-value" aria-labelledby="population-label"><g:fieldValue bean="${geonameInstance}"
                                                                                              field="population"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.elevation}">
            <li class="fieldcontain">
                <span id="elevation-label" class="property-label"><g:message code="geoname.elevation.label"
                                                                             default="Elevation"/></span>

                <span class="property-value" aria-labelledby="elevation-label"><g:fieldValue bean="${geonameInstance}"
                                                                                             field="elevation"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.dem}">
            <li class="fieldcontain">
                <span id="dem-label" class="property-label"><g:message code="geoname.dem.label" default="Dem"/></span>

                <span class="property-value" aria-labelledby="dem-label"><g:fieldValue bean="${geonameInstance}"
                                                                                       field="dem"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.timeZone}">
            <li class="fieldcontain">
                <span id="timeZone-label" class="property-label"><g:message code="geoname.timeZone.label"
                                                                            default="Time Zone"/></span>

                <span class="property-value" aria-labelledby="timeZone-label"><g:fieldValue bean="${geonameInstance}"
                                                                                            field="timeZone"/></span>

            </li>
        </g:if>

        <g:if test="${geonameInstance?.modificationDate}">
            <li class="fieldcontain">
                <span id="modificationDate-label" class="property-label"><g:message
                        code="geoname.modificationDate.label" default="Modification Date"/></span>

                <span class="property-value" aria-labelledby="modificationDate-label"><g:formatDate
                        date="${geonameInstance?.modificationDate}"/></span>

            </li>
        </g:if>

    </ol>
    <g:form action="profile">
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${geonameInstance?.id}"/>
            <g:link class="edit" action="edit" id="${geonameInstance?.id}"><g:message code="default.button.edit.label"
                                                                                      default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
