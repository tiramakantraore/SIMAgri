<%@ page import="simagri.Utilisateur; simagri.Reseau" %>

<div class="row">
<div class="col-sm-4 col-md-4 offset-8">
    <label for="destinataires">
        <g:message code="alerte.destinataires.label" default="Destinataires" />
    </label>
    <g:select id="destinataires" name="destinataires" from="${Utilisateur.findAllById(0 as Long)}" multiple="multiple" optionKey="id" size="5" value="${smsToReseauxInstance?.destinataires*.id}" class="many-to-many col-sm-6 col-md-6"/>

</div>
</div>

<br>
<div class="row">

<div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error')} required col-sm-12 col-md-12">

    <div id="infodiv"></div>
	<g:textArea name="yourTextMessage" id="yourTextMessage" cols="50" rows="10" maxlength="2000" class="col-sm-12 col-md-12" required="" value="${smsToReseauxInstance?.yourTextMessage}" placeHolder="Votre message ici (maximum 1600 caractères)"/>
</div>
</div>

