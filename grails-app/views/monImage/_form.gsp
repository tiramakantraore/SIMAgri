<%@ page import="simagri.TypeAlignement; simagri.MonImage" %>



<div class="fieldcontain ${hasErrors(bean: monImageInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="monImage.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" maxlength="25" required="" value="${monImageInstance?.nom}" class="form-control"/>
</div>


<div class="fieldcontain ${hasErrors(bean: monImageInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="monImage.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="1000" value="${monImageInstance?.description}" class="form-control"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monImageInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="monImage.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />
</div>

