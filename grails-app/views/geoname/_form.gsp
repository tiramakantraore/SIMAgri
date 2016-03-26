

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'geonameId', 'error')} required">
    <label for="geonameId">
        <g:message code="geoname.geonameId.label" default="Geoname Id"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field type="number" name="geonameId" required=""
             value="${fieldValue(bean: geonameInstance, field: 'geonameId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="geoname.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${geonameInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'asciiname', 'error')} ">
    <label for="asciiname">
        <g:message code="geoname.asciiname.label" default="Asciiname"/>

    </label>
    <g:textField name="asciiname" value="${geonameInstance?.asciiname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'latitude', 'error')} ">
    <label for="latitude">
        <g:message code="geoname.latitude.label" default="Latitude"/>

    </label>
    <g:field type="number" name="latitude" value="${fieldValue(bean: geonameInstance, field: 'latitude')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'longitude', 'error')} ">
    <label for="longitude">
        <g:message code="geoname.longitude.label" default="Longitude"/>

    </label>
    <g:field type="number" name="longitude" value="${fieldValue(bean: geonameInstance, field: 'longitude')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'featureClass', 'error')} ">
    <label for="featureClass">
        <g:message code="geoname.featureClass.label" default="Feature Class"/>

    </label>
    <g:textField name="featureClass" value="${geonameInstance?.featureClass}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'featureCode', 'error')} ">
    <label for="featureCode">
        <g:message code="geoname.featureCode.label" default="Feature Code"/>

    </label>
    <g:textField name="featureCode" value="${geonameInstance?.featureCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'countryCode', 'error')} ">
    <label for="countryCode">
        <g:message code="geoname.countryCode.label" default="Country Code"/>

    </label>
    <g:textField name="countryCode" value="${geonameInstance?.countryCode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'cc2', 'error')} ">
    <label for="cc2">
        <g:message code="geoname.cc2.label" default="Cc2"/>

    </label>
    <g:textField name="cc2" value="${geonameInstance?.cc2}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'population', 'error')} ">
    <label for="population">
        <g:message code="geoname.population.label" default="Population"/>

    </label>
    <g:field type="number" name="population" value="${fieldValue(bean: geonameInstance, field: 'population')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'elevation', 'error')} ">
    <label for="elevation">
        <g:message code="geoname.elevation.label" default="Elevation"/>

    </label>
    <g:field type="number" name="elevation" value="${fieldValue(bean: geonameInstance, field: 'elevation')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'dem', 'error')} ">
    <label for="dem">
        <g:message code="geoname.dem.label" default="Dem"/>

    </label>
    <g:field type="number" name="dem" value="${fieldValue(bean: geonameInstance, field: 'dem')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'timeZone', 'error')} ">
    <label for="timeZone">
        <g:message code="geoname.timeZone.label" default="Time Zone"/>

    </label>
    <g:textField name="timeZone" value="${geonameInstance?.timeZone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: geonameInstance, field: 'modificationDate', 'error')} ">
    <label for="modificationDate">
        <g:message code="geoname.modificationDate.label" default="Modification Date"/>

    </label>
    <g:datePicker name="modificationDate" precision="day" value="${geonameInstance?.modificationDate}" default="none"
                  />
</div>

