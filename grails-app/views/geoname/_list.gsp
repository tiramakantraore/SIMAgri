
<a href="#list-geoname" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/home')}"><g:message code="default.home.label"/></a>
        </li>
        <li><g:link class="create" action="create"><g:message code="create.geoname"/></g:link></li>
    </ul>
</div>

<div id="list-geoname" class="content scaffold-list" role="main">
    <h1><g:message code="list.geoname"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <util:remoteSortableColumn update="listContent" action="list" property="name" title="${message(code: 'geoname.name.label', default: 'Name')}"/>

            <util:remoteSortableColumn update="listContent" action="list" property="asciiname"
                              title="${message(code: 'geoname.asciiname.label', default: 'Asciiname')}"/>

            <util:remoteSortableColumn update="listContent" action="list" property="latitude"
                              title="${message(code: 'geoname.latitude.label', default: 'Latitude')}"/>

            <util:remoteSortableColumn update="listContent" action="list" property="longitude"
                              title="${message(code: 'geoname.longitude.label', default: 'Longitude')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${geonameInstanceList}" status="i" var="geonameInstance">
            <tr>

                <td>${fieldValue(bean: geonameInstance, field: "name")}</td>

                <td>${fieldValue(bean: geonameInstance, field: "asciiname")}</td>

                <td>${fieldValue(bean: geonameInstance, field: "latitude")}</td>

                <td>${fieldValue(bean: geonameInstance, field: "longitude")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <util:remotePaginate update="listContent" action="list"  total="${geonameInstanceTotal}"/>
    </div>
</div>
