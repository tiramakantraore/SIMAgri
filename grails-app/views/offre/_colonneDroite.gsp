<%@ page import="simagri.Utilisateur; simagri.Qualite; simagri.Lieux" %>
        <div class="row">
            <div class="col-xs-7">

                <f:field bean="offreInstance" property="modePaiement" required="true" input-class="form-control"/>

            </div>

            <div class="col-xs-5">
            </div>
        </div>

       <div class="row">
          <div class="col-xs-7">

                <f:field bean="offreInstance" property="lieuLivraison" input-class="form-control"/>

           </div>

            <div class="col-xs-5">
            </div>
     </div>


<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="qualite" input-class="form-control"/>

    </div>

    <div class="col-xs-5">
    </div>
</div>

<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="auteur" input-class="form-control"/>

    </div>

    <div class="col-xs-5">
    </div>
</div>

%{--<div class="row">--}%
    %{--<div class="col-xs-9">--}%
        %{--<g:hiddenField name="auteur.id" value="${offreInstance?.auteur?.id}"/>--}%
        %{--<label for="auteurDisplay">--}%
            %{--<g:message code="offre.auteur.text" default="Auteur" />--}%
            %{--<span class="required-indicator">*</span>--}%


        %{--</label>--}%
            %{--<sf:textField  id='auteurDisplay' name='auteurDisplay' class="form-control" placeHolder="${g.message(code: 'offre.auteur.placeHolder.label', default: 'Les 4 prem. caractères, exple:Sawa... ou 7032...')}" />--}%

    %{--</div>--}%

    %{--<div class="col-xs-3">--}%
    %{--</div>--}%
%{--</div>--}%

<div class="row">
    <div class="col-xs-7">
        <f:field bean="offreInstance" property="contact" input-class="form-control"/>

    </div>

    <div class="col-xs-5">
    </div>
</div>

<div class="row">
    <div class="col-xs-10">

        <f:field  bean="offreInstance" property="conditions" input-class="form-control"/>



    </div>

    <div class="col-xs-2">
    </div>
</div>

