<%@ page import="simagri.EventRecurType;  simagri.Event" %>

<%@ page import="org.joda.time.Instant" %>



<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />


<div class="row">
    <div class="col-xs-8 col-xs-offset4">

        <f:field bean="eventInstance" property="title">
            <g:textField name="title" value="${eventInstance?.title}" class="form-control"/>
        </f:field>
    </div>

</div>

<div class="row">
<div class="col-xs-6 col-xs-offset6">
    <f:field bean="eventInstance"  property="startTime">
        <div class='input-group date'  data-date-format="dd/mm/yyyy">

            <input type='text' id='startTime' name='startTime' value="${formatDate(date: occurrenceStart ? new Instant(occurrenceStart).toDate() : eventInstance?.startTime, format: 'dd/MM/yyyy hh:mm a')}" class="form-control" />
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
    </f:field>
</div>
</div>
<div class="row">
    <div class="col-xs-6 col-xs-offset6">
    <f:field bean="eventInstance"  property="endTime">
        <div class='input-group date'    data-date-format="dd/mm/yyyy">

            <input type='text' id='endTime' name='endTime' value="${formatDate(date: occurrenceEnd ? new Instant(occurrenceEnd).toDate() : eventInstance?.endTime, format: 'dd/MM/yyyy hh:mm a')}" class="form-control" />
            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
    </f:field>
</div>
</div>
<div class="row">
<div class="col-xs-7 col-xs-offset5" >
    <f:field bean="eventInstance"  property="location">
        <g:textField name="location" value="${eventInstance?.location}" autocomplete="off" class="form-control"/>

    </f:field>
</div>
</div>
<div class="row">
<div class="col-xs-12" >
    <f:field bean="eventInstance"  property="description">
        <g:textArea name="description" rows="5" value="${eventInstance?.description}" autocomplete="off"  class="form-control"/>

    </f:field>
</div>
</div>

<div id="recurPopup"></div>
<div id="recurOptions" style="display:none" >
    <div>
        <label>Type recurrence:</label>
        <g:select name="recurType" from="${EventRecurType.values()}" optionValue="name" value="${eventInstance?.recurType}"/>
    </div>
     <div>
        <label>Repéter chaque:</label>
        <g:select name="recurInterval" from="${1..30}" value="${eventInstance?.recurInterval}" />
        <span id="repeatLabel"></span>
    </div>

    <div id="weeklyOptions" ${eventInstance.recurType != EventRecurType.WEEKLY ? 'style="display:none"' : ''}>
        <label>Repéter le: </label>
        <div class="options">
            <calendar:daysOfWeek name="recurDaysOfWeek" selectedDays="${eventInstance?.recurDaysOfWeek}" />
        </div>
    </div>

    <div><label>Se termine:</label>
        <div class="input">
            <input id="recurEndOption1" name="recurEndOption" type="radio" group="recurEndOption" ${(!eventInstance.recurCount && !eventInstance.recurUntil) ? 'checked="checked"' : ''} value="never" />
            <label for="recurEndOption1">Jamais</label><br />

            <input id="recurEndOption2" name="recurEndOption" type="radio" group="recurEndOption" ${(eventInstance.recurCount) ? 'checked="checked"' : ''} value="occurrences" />
            <label for="recurEndOption2">Après <g:textField name="recurCount" size="3" value="${eventInstance?.recurCount}" /> occurrences</label><br/>

            <input id="recurEndOption3" name="recurEndOption" type="radio" group="recurEndOption" ${(!eventInstance.recurCount && eventInstance.recurUntil) ? 'checked="checked"' : ''} value="endDate" />
            <label for="recurEndOption3">Le <g:textField name="recurUntil" size="8" value="${formatDate(date: (eventInstance?.recurCount ? null : eventInstance?.recurUntil), format: 'MM/dd/yyyy hh:mm a')}" /></label>


        </div>
    </div>

    <div>
            <strong><span id="recurSummary"></span></strong>

    </div>

</div>




