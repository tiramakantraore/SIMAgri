



<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="role.authority.label" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${roleInstance?.authority}" autocomplete="off"/>
</div>

<div class="fieldcontain ${hasErrors(bean: roleInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="role.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="255" value="${roleInstance?.description}"autocomplete="off"/>
</div>

