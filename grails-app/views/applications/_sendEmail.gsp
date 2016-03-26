<%@ page import="simagri.Utilisateur" %>

<h1><g:message code="envoyerMail" /></h1>
<g:form name="sendSMSForm" class="form-horizontal" >


    <g:hiddenField name="ReseauxIdsSMS" />
    <div class="form-actions">
        <div type="button" class="btn-flat  btn-primary validerEmail" onclick="updateReseauxIds();">

            <g:message code="sendEmail.button.label" default="Envoyer" />
        </div>
    </div>
<div class="tab-pane active" id="idSMSForm">
<ul class="nav nav-pills" id="IdSMSFormTab">

    <li><a href="#IdGroupeSMS" data-toggle="tab" title="Choisir le(s) groupes"> 1. Groupes </a></li>
    <li><a href="#IdDestinatairesSMS" data-toggle="tab" title="Choisir le(s) destinataires du sms"> 2. Destinataires e-mail </a></li>
    <li><a href="#idMessageSMS" data-toggle="tab" title="Ecrire le message"> 3. Message e-mail </a></li>
</ul>
<div class="tab-content">
<div class="tab-pane" id="IdGroupeSMS">
    <h4><strong><g:message code="choisir.reseau"/></strong></h4>
    <div  class="k-content" >
        <div id="treeviewSMS" class="groupe-section"></div>

    </div>
</div>

<div class="tab-pane" id="IdDestinatairesSMS">

    <div class="row">
        <div class="col-sm-3 col-md-3">
            <a id="check-allDestinataires_SMS" href="javascript:void(0);">Tous les destinataires</a>
        </div>
        <div class="col-sm-3 col-md-3 offset6">
            <a id="uncheck-allDestinataires_SMS" href="javascript:void(0);">Aucun destinataires </a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4 col-sm-offset8 col-md-offset8">
            <form name="searchDestForm"  class="navbar-form navbar-left form-search form-inline" role="search" >
                      <div class="input-group my-search-group">
                        <input type="text" name="queryDest" id="queryDest" class="form-control"  placeholder="Chercher" >
                        <div class="input-group-btn">
                        <div type="submit"  class="btn-success btn-search-flat" onclick="onChangeDestinatairesSMS();">
                        <i class="glyphicon glyphicon-search"></i>
                        </div>
                        </div>
                    </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-12">
            <div id="listeDestinataires_SMS">
                <bill:checkBoxList referenceCollection="${Utilisateur.findAllById(0)}" containerClass="${ctnerTemplate}" instanceName="destinataires_SMS" />
            </div>
        </div>
    </div>

</div>
    <div class="tab-pane" id="idMessageSMS">
        <div class="row">

            %{--<div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error')} required col-sm-12 col-md-12">--}%

            %{--<div id="infodiv"></div>--}%
            <div class="col-sm-7 col-md-7">
                <label for="objetEmail">Objet : </label>

                <input type="text" name="objetEmail" id="objetEmail" class="form-control"  placeholder="Chercher" >
            </div>
            %{--</div>--}%
        </div>
        <br>
        <div class="row">

            %{--<div class="fieldcontain ${hasErrors(bean: smsToReseauxInstance, field: 'yourTextMessage', 'error')} required col-sm-12 col-md-12">--}%

                %{--<div id="infodiv"></div>--}%
            <div class="col-sm-7 col-md-7">
            <label for="yourTextMessage">Message : </label>
                <g:textArea name="yourTextMessage" id="yourTextMessage" cols="50" rows="10" maxlength="3000" class="col-sm-12 col-md-12" required=""  placeHolder="Votre message ici (maximum 3000 caractères)"/>
            </div>
            %{--</div>--}%
        </div>

    </div>



</div>
</div>

    <div class="form-actions">
                        <div type="button" class="btn-flat  btn-primary validerEmail" onclick="updateReseauxIds();">
                        <g:message code="sendEmail.button.label" default="Envoyer" />
                       </div>
    </div>

</g:form>
<g:render template="/applications/emailjs"/>