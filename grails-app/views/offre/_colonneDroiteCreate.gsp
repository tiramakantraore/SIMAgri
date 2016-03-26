<%@ page import="simagri.Qualite; simagri.Lieux" %>
        <div class="row">
            <div class="col-xs-7">

                <f:field bean="offreInstance" property="modePaiement" required="true" >
                    <g:select id="modePaiement" name="modePaiement" from="${offreInstance.constraints.modePaiement.inList}" valueMessagePrefix="offre.modePaiement" class="form-control"/>
                </f:field>
            </div>

            <div class="col-xs-5">
            </div>
        </div>

       <div class="row">
          <div class="col-xs-7">

                <f:field bean="offreInstance" property="lieuLivraison">
                    <g:select id="lieuLivraison" name="lieuLivraison" from="${Lieux.list()}" optionKey="id"  noSelection="['': '']" class="many-to-one form-control"  />
                </f:field>
           </div>

            <div class="col-xs-5">
            </div>
     </div>


<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="qualite">
            <g:select id="qualite" name="qualite" from="${Qualite.list()}" optionKey="id"  noSelection="['': '']" class="many-to-one form-control" />

        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>


<div class="row">
    <div class="col-xs-9">
        <g:hiddenField name="auteur" />
        <label for="auteurDisplay">
            <g:message code="offre.auteur.text" default="Auteur" />
            <span class="required-indicator">*</span>


        </label>
            <input type='text' id='auteurDisplay' class="form-control" placeHolder="${g.message(code: 'offre.auteur.placeHolder.label', default: 'Les 4 prem. caractères, exple:Sawa... ou 7032...')}" />

    </div>

    <div class="col-xs-3">
    </div>
</div>

<div class="row">
    <div class="col-xs-7">
        <f:field bean="offreInstance" property="contact">
            <input type='text' id='contact' name='contact'  class="form-control"  />
        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>

<div class="row">
    <div class="col-xs-10">

        <f:field  bean="offreInstance" property="conditions">
            <g:textArea name="conditions" cols="80" rows="5"  value="${offreInstance?.conditions}"  placeHolder="${g.message(code: 'offre.conditions.placeHolder.label', default: 'Prix négociable selon le volume')}" class="form-control"  />
        </f:field >


    </div>

    <div class="col-xs-2">
    </div>
</div>

