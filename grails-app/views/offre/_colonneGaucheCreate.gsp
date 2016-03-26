<%@ page import="simagri.Lieux;simagri.Mesure; simagri.Produit; simagri.CategorieProduit" %>


<div class="row ">

    <div class="col-xs-5">

        <f:field bean="offreInstance" property="typeOffre" required="true" >

            <g:select id="typeOffre" name="${property}" from="${offreInstance.constraints.typeOffre.inList}"  class="form-control"/>
        </f:field>
    </div>

    <div class="col-xs-4">
    </div>
</div>
<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="categorieProduit">
            <g:select id="categorieProduit" name="categorieProduit" from="${CategorieProduit.list()}" optionKey="id"  noSelection="['': '']" class="many-to-one form-control"  />


        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>

<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="produit" required="true">
            <g:select id="produit" name="produit" from="${Produit.list()}" optionKey="id" noSelection="['': '']" class="many-to-one form-control" />


        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>

%{--<div class="row">--}%
    %{--<div class="col-xs-7">--}%

        %{--<f:field bean="offreInstance" property="origine">--}%

            %{--<g:select id="origine" name="origine" from="${Lieux.list()}" optionKey="id" noSelection="['': '']" class="many-to-one form-control" />--}%


        %{--</f:field>--}%
    %{--</div>--}%

    %{--<div class="col-xs-5">--}%
    %{--</div>--}%
%{--</div>--}%

<div class="row">
    <div class="col-xs-7">

        <f:field bean="offreInstance" property="lieuStockage">
            <g:select id="lieuStockage" name="lieuStockage" from="${Lieux.list()}" optionKey="id" noSelection="['': '']" class="many-to-one form-control" />
        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>





<div class="row">
    <div class="col-xs-6">

        <f:field bean="offreInstance"  property="date">
            <div class='input-group date' id='date'   data-date-format="dd/mm/yyyy">
                <input type='text' name='date' value="${formatDate(format:'dd/MM/yyyy',date:offreInstance?.date?:new Date())}" class="form-control" autocomplete="off"/>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </f:field>
    </div>

    <div class="col-xs-6">
        <f:field bean="offreInstance"  property="dateExpiration">
            <div class='input-group date' id='dateExpiration'   data-date-format="dd/mm/yyyy">

                <input type='text' name='dateExpiration' value="${formatDate(format:'dd/MM/yyyy',date:offreInstance?.dateExpiration?:new Date())}" class="form-control" />
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </f:field>
    </div>

</div>

<div class="row">
    <div class="col-xs-6">

        <f:field bean="offreInstance"  property="quantite">
                <input type='number' step="1" id='quantite' name='quantite' class="form-control" />

        </f:field>
    </div>

    <div class="col-xs-6">
        <f:field bean="offreInstance"  property="mesure">
            <g:select id="mesure" name="mesure" from="${Mesure.list()}" optionKey="id"   class="many-to-one form-control" />
        </f:field>

    </div>
</div>

    %{--<div  id="groupPrix" class="row">--}%
        %{--<div class="col-sm-1 col-md-10 offset-2">--}%
            <g:render template="/offre/prixGrosCreate"/>
        %{--</div>--}%
    %{--</div>--}%
