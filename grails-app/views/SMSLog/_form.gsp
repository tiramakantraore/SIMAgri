<%@ page import="simagri.SMSLogger" %>



<div class="fieldcontain ${hasErrors(bean: SMSLoggerInstance, field: 'message', 'error')} required">
	<label for="message">
		<g:message code="SMSLogger.message.label" default="Message" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="message" maxlength="200" required="" value="${SMSLoggerInstance?.message}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: SMSLoggerInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="SMSLogger.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${SMSLoggerInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: SMSLoggerInstance, field: 'isIn', 'error')} ">
	<label for="isIn">
		<g:message code="SMSLogger.isIn.label" default="Is In" />
		
	</label>
	<g:checkBox name="isIn" value="${SMSLoggerInstance?.isIn}" />
</div>

<div class="fieldcontain ${hasErrors(bean: SMSLoggerInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="SMSLogger.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${simagri.Utilisateur.list()}" optionKey="id" required="" value="${SMSLoggerInstance?.user?.id}" class="many-to-one"/>
</div>

