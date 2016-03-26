<%@ page import="org.joda.time.Instant; simagri.Marche" %>
<div class="row">
    <div class="col-xs-12">
        <f:field  property="magazin" label="Veuillez sélectionner le magazin ">
            <bill:radioBoxList referenceCollection="${userMagazinList}" instanceName="magazin"  containerClass="${ctnerTemplateMag} limitHeight" labelClass="labelClass50" onClickRadio="magazinClick();" />
        </f:field>


    </div>
</div>


<div class="row">
    <div class="col-sm-3 col-md-3">

        <f:field   property="date">
            <div   data-date-format="dd/mm/yyyy">

                <input type='text' id='datestock' name='date' value="${formatDate(format:'dd/MM/yyyy',date:new Date())}" class="form-control datepicker input-group date stock" />
                %{--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>--}%
                %{--</span>--}%
            </div>
        </f:field>
    </div>
    <div class="col-sm-3 col-md-3">

    </div>
    <div class="col-sm-2 col-md-2">
        <br>
        <div  class="btn-flat  btn-primary validerstock">
            <g:message code="default.button.create.label" default="Create" />
        </div>
    </div>
    <div class="col-sm-4 col-md-4">

    </div>
</div>


<br>
