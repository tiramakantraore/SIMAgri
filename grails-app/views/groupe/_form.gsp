
<div class="row ">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-4">

        <f:field bean="reseauInstance" property="nom" required="true" >
            <input type='text' name='nom' value="${reseauInstance?.nom}" class="form-control" autocomplete="off"/>
        </f:field>
    </div>

    <div class="col-xs-7">
    </div>
</div>

<div class="row ">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-5">

        <f:field bean="reseauInstance" property="email" required="true" >
            <input type='email' name='email' value="${reseauInstance?.email}" class="form-control" autocomplete="off"/>
        </f:field>
    </div>

    <div class="col-xs-6">
    </div>
</div>
<div class="row ">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-6">

        <f:field bean="reseauInstance" property="siteWeb" required="true" >
            <input type='url' name='siteWeb' value="${reseauInstance?.siteWeb}" class="form-control" autocomplete="off"/>
        </f:field>
    </div>

    <div class="col-xs-5">
    </div>
</div>
<div class="row ">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-6">
        <f:field bean="reseauInstance" property="active"  input-class="check" input-autocomplete="off"/>
    </div>

    <div class="col-xs-5">
    </div>
</div>
<div class="row ">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-6">
        <f:field bean="reseauInstance" property="estReseau"  input-class="check" input-autocomplete="off"/>
    </div>

    <div class="col-xs-5">
    </div>
</div>


