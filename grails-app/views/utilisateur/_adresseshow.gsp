<%@ page import="simagri.Activite; simagri.Entreprise" %>
<div class="row" style="margin-top: 10px">
    <div class="col-sm-11 col-md-11 col-sm-offset-1 col-md-offset-1">
    <div class="row">
        <div class="col-xs-12">
            <f:display bean="utilisateurInstance" property="country" />
         </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <f:display  bean="utilisateurInstance" property="email"/>


        </div>
    </div>



    <div class="row">
        <div class="col-xs-12">

            <f:display  bean="utilisateurInstance" property="town"/>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-12">

            <f:display  bean="utilisateurInstance" property="physicalAddress"/>



        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">

            <f:display  bean="utilisateurInstance" property="secondPhone"/>


        </div>
    </div>

    <div class="row">

        <div class="col-xs-12">
            <f:display  bean="utilisateurInstance" property="entreprise" input-class="form-control" />

        </div>
    </div>

    <div class="row">

        <div class="col-xs-12">
            <f:display  bean="utilisateurInstance" property="activite" input-class="form-control" />

        </div>
    </div>

    <div class="row">

        <div class="col-xs-12">
            <f:display  bean="utilisateurInstance" property="poste" input-class="form-control" />

        </div>
    </div>
    </div>
</div>