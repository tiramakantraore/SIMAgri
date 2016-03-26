%{--<f:all bean="civiliteInstance"/>--}%
<div class="row ">

    <div class="col-xs-5">

        <f:field bean="civiliteInstance" property="code" required="true" >
            <g:textField name="${property}" value="${it.value}" class="form-control" placeholder="Code exple: Mr"/>
        </f:field>
    </div>

    <div class="col-xs-4">
    </div>
</div>

<div class="row ">

    <div class="col-xs-5">

        <f:field bean="civiliteInstance" property="libelle" required="true" >
            <g:textField name="${property}" value="${it.value}" class="form-control" placeholder="Libellé exple: Monsieur"/>
        </f:field>
    </div>

    <div class="col-xs-4">
    </div>
</div>

