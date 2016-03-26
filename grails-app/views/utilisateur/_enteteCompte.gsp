<div class="page-header">
    <h1><g:message code="changePassword.utilisateur" /></h1>
    <h4><span class="ItemModified">${nomUtilisateur}</span> </h4>
</div>


<g:if test="${flash.message}">
    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
</g:if>


<g:hasErrors bean="${utilisateurInstance}">
    <bootstrap:alert class="alert-error">
        <ul>
            <g:eachError bean="${utilisateurInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </bootstrap:alert>
</g:hasErrors>

<div class="row">
    <div class="col-sm-4 col-md-4">
        <label for="oldPassword">
            <g:message code="utilisateur.oldPassword.label" default="Ancien mot de passe"/>

        </label>
        <g:field type="password" name="oldPassword" maxlength="64" value="${oldPassword}" autocomplete="off" class="col-sm-12 col-md-12"/>
    </div>
    <div class="col-sm-8 col-md-8">
    </div>
</div>

<div class="row">
    <div class="col-sm-4 col-md-4">
        <label for="newPassword">
            <g:message code="utilisateur.newPassword.label" default="Password"/>

        </label>
        <g:field type="password" name="newPassword" maxlength="64" value="${newPassword}" autocomplete="off" class="col-sm-12 col-md-12"/>
    </div>
    <div class="col-sm-8 col-md-8">
    </div>
</div>

<div class="row">
    <div class="col-sm-4 col-md-4">
        <label for="confirmPassword">
            <g:message code="utilisateur.confirmPassword.label" default="Password"/>

        </label>
        <g:field type="password" name="confirmPassword" maxlength="64" value="${confirmPassword}" autocomplete="off" class="col-sm-12 col-md-12"/>
    </div>
    <div class="col-sm-8 col-md-8">
    </div>
</div>


















