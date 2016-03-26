<%@ page import="org.joda.time.Instant; simagri.Marche" %>


<div class="row">
    <div class="col-xs-12">

        <f:field  property="market">
            <g:selectWithOptGroup name = "market"
                                  id = "market"
                                  from = "${userMarketsList}"
                                  optionKey = "id"
                                  optionValue = "nom"
                                  groupBy="regionName"
                                  noSelection="['': 'Aucun marché']"
                                  class="form-control"

            />
        </f:field>

    </div>
</div>


<div class="row">
    <div class="col-xs-8">

        <f:field   property="date">
            <div   data-date-format="dd/mm/yyyy">

                <input type='text' id='date' name='date' value="${formatDate(format:'dd/MM/yyyy',date:new Date())}" class="form-control datepicker input-group date" />
                %{--<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>--}%
                %{--</span>--}%
            </div>
        </f:field>
    </div>
        <div class="col-xs-4">
        </div>
</div>


<br>
