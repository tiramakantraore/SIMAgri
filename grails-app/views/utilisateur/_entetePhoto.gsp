<div class="row">
    <div class="col-sm-6 col-md-6 offset2 ">
        <h2><g:message code="changeImage.utilisateur"/></h2>
    </div>
</div>
<div class="row">
<div class="fieldcontain ${hasErrors(bean: utilisateurInstance, field: 'photo', 'error')} ">
    <label for="photo">
        <g:message code="utilisateur.photo.label" default="Photo" />

    </label>
    <input type="file" id="photo" name="photo" />
</div>
</div>
<br>
