<%@ page import="simagri.Activite; simagri.Entreprise" %>
<div class="row" style="margin-top: 10px">
    <div class="col-sm-11 col-md-11 col-sm-offset-1 col-md-offset-1">
    <div class="row">
        <div class="col-xs-8">
            <f:field bean="utilisateurInstance" property="country" >
                <g:countrySelect name="country" value="${utilisateurInstance?.country}" default="grails.defaultCountry" from="['bfa','ben','civ', 'gha','gnb', 'mli','ner','sen','tgo']" class="form-control" noSelection="['': 'Choisissez un pays']"  />
            </f:field>
        </div>

        <div class="col-xs-4">
        </div>
    </div>

    <div class="row">
        <div class="col-xs-8">

            <f:field  bean="utilisateurInstance" property="email">
                <input type='text' name='email' value="${utilisateurInstance?.email}" class="form-control"  />
            </f:field>

        </div>

        <div class="col-xs-4">
        </div>
    </div>



    <div class="row">
        <div class="col-xs-8">

            <f:field  bean="utilisateurInstance" property="town">
                <input type='text' name='town' value="${utilisateurInstance?.town}" class="form-control"  />
            </f:field>

        </div>
        <div class="col-xs-4">
        </div>
    </div>

    <div class="row">
        <div class="col-xs-8">

            <f:field  bean="utilisateurInstance" property="physicalAddress">
                <g:textArea name="physicalAddress" cols="80" rows="5" maxlength="40" value="${utilisateurInstance?.physicalAddress}" placeholder="Saisir ici l'adresse physique" class="form-control" />
            </f:field >


        </div>

        <div class="col-xs-4">
        </div>
    </div>
    <div class="row">
        <div class="col-xs-8">

            <f:field  bean="utilisateurInstance" property="secondPhone">
                <input type='text' name='secondPhone' value="${utilisateurInstance?.secondPhone}" class="form-control"  />
            </f:field>

        </div>

        <div class="col-xs-4">
        </div>
    </div>
        <div class="row">

            <div class="col-xs-8">
                <f:field  bean="utilisateurInstance" property="activite" input-class="form-control" />

            </div>
            <div class="col-xs-4">

            </div>
        </div>
    <div class="row">

        <div class="col-xs-8">
            <f:field  bean="utilisateurInstance" property="entreprise" input-class="form-control" />

        </div>
        <div class="col-xs-4">

        </div>
    </div>



    <div class="row">

        <div class="col-xs-8">
            <f:field  bean="utilisateurInstance" property="poste" input-class="form-control" />

        </div>
        <div class="col-xs-4">

        </div>
    </div>
    </div>

</div>