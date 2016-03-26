
<p class="titre"><g:message code="accueil.periode.label" default="Choix de la période" /></p>
<div id="periode${periode_suffix?:""}" class="input-group date pull-left periodebg">

    <span class="glyphicon glyphicon-calendar"></span>
    <b class="caret"></b>
</div>
<input type="hidden" id="fromDate${periode_suffix?:""}" name="fromDate${periode_suffix?:""}" class="form-control">
<input type="hidden" id="toDate${periode_suffix?:""}" name="toDate${periode_suffix?:""}" class="form-control">
<br>
