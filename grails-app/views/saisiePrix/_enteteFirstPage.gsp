<%@ page import="org.joda.time.Instant; simagri.Marche" %>


<div class="row">
    <div class="col-sm-12 col-md-12">
        <f:field  property="market" label="Veuillez sélectionner le marché ">
            <bill:radioBoxList referenceCollection="${userMarketsList}" instanceName="market" containerClass="${ctnerTemplate} limitHeight" labelClass="labelClass50" onClickRadio="marcheClick();" />
        </f:field>


    </div>
</div>


<div class="row">
    <div class="col-sm-3 col-md-3 ">

        <f:field property="date">
            <div data-date-format="dd/mm/yyyy">

                <input type='text' id='datePrice' name='date' value="${formatDate(format:'dd/MM/yyyy',date:new Date())}" class="form-control datepicker input-group date price" />
                %{--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>--}%
                %{--</span>--}%
            </div>
        </f:field>
    </div>
    <div class="col-sm-7 col-md-7">

    </div>
        <div class="col-sm-2 col-md-2">
            <br>
            <div  class="btn-flat  btn-primary validerPrix">

                <g:message code="default.button.create.label" default="Create" />
            </div>
        </div>
</div>


<br>
