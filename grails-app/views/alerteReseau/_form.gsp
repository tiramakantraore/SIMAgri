<%@ page import="simagri.Utilisateur; simagri.Produit; simagri.Marche; simagri.CategorieProduit; simagri.Reseau" %>

<g:hiddenField name="recevoirOffres" value="${alerteInstance?.recevoirOffres}"/>

<g:hiddenField name="recevoirPrix" value="${alerteInstance?.recevoirPrix}"/>

<g:hiddenField name="recevoirParEmail" value="${alerteInstance?.recevoirParEmail}"/>

<g:hiddenField name="recevoirParSMS" value="${alerteInstance?.recevoirParSMS}"/>
<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="nom" required="true" input-class="form-control"/>
    </div>
</div>
<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="typeAlerte"  input-class="form-control"/>
    </div>
</div>
<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="categories"  input-class="form-control"/>
    </div>
</div>

<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="produits"  input-class="form-control"/>
    </div>
</div>

<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="produits"  input-class="form-control"/>
    </div>
</div>

<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="markets"  input-class="form-control"/>
    </div>
</div>


<div class="row">
    <div class="col-sm-8 col-md-8">
        <f:field bean="alerteInstance" property="markets"  input-class="form-control"/>
    </div>
</div>
%{--<div class="row">--}%
    %{--<div class="col-sm-8 col-md-8">--}%
        %{--<f:field bean="alerteInstance" property="destinataires"  input-class="form-control"/>--}%
    %{--</div>--}%
%{--</div>--}%
<div class="row">
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="suspendre"  input-class="check"/>
    </div>
</div>


  <p><strong>Veuillez choisir les jours d'envoi des sms</strong></p>
<div class="row">
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="lundi"  input-class="check"/>
    </div>
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="mardi"  input-class="check"/>
    </div>
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="mercredi"  input-class="check"/>
    </div>
</div>

<div class="row">
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="jeudi"  input-class="check"/>
    </div>
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="vendredi"  input-class="check"/>
    </div>
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="samedi"  input-class="check"/>
    </div>
</div>
<div class="row">
    <div class="col-sm-4 col-md-4">
        <f:field bean="alerteInstance" property="dimanche"  input-class="check"/>
    </div>
</div>




