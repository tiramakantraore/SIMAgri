

<div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'toPhoneNumber', 'error')}  required">
    <label for="toPhoneNumber">
        %{--<g:message code="alerte.destinataires.label" default="Destinataires" />--}%
    </label>
    <g:textField name="toPhoneNumber" required="" value="${smsToReseauxInstance?.toPhoneNumber}"  placeHolder="N° Téléphone" class="form-control"/>
</div>

<div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error')} required">
	<label for="yourTextMessage">
		%{--<g:message code="smsToReseaux.yourTextMessage.label" default="Your Text Message" />--}%

	</label>
    <g:textArea name="yourTextMessage" cols="40" rows="5" maxlength="200" class="form-control" required="" value="${smsToReseauxInstance?.yourTextMessage}" placeHolder="Texte SMS" />

</div>

<script type="text/javascript">

</script>

