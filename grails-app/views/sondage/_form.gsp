<div class="form-group form-group">

    <div class="row">
        <div class="col-xs-8">

            <f:field bean="sondageInstance"  property="titre">
                <input type='text' id='titre' name='titre' value="${sondageInstance?.titre}" class="form-control" />

            </f:field>
        </div>

        <div class="col-xs-4">

        </div>
    </div>

    <div class="row">
        <div class="col-xs-3">
            <f:field bean="sondageInstance" property="dateDebut">
                <div class='input-group date' id='dateDebut'   data-date-format="dd/mm/yyyy">

                    <input type='text' name='dateDebut' value="${formatDate(format:'dd/MM/yyyy',date:sondageInstance.dateDebut)}" class="form-control" />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </f:field>
        </div>
        <div class="col-xs-2">
            <f:field bean="sondageInstance" property="duree" input-class="form-control" />
        </div>
        <div class="col-xs-3 offset4">
            <f:field bean="sondageInstance" property="dateFin">
                <div class='input-group date' id='dateFin' data-date-format="dd/mm/yyyy">
                    <input type='text'   name='dateFin' value="${sondageInstance.dateFin?.format('dd/MM/yyyy')}" class="form-control" />
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </f:field>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-4">
            <div style="display:inline-block;" >
                <label for="actif">
                    <g:message code="sondage.actif.label" default="Actif ?"/>
                </label>
                    <f:input bean="sondageInstance" property="actif" id="actif"/>
                    %{--<g:checkBox name="actif" bean="sondageInstance" value="${sondageInstance.actif}" />--}%

            </div>

        </div>

        <div class="col-xs-8">

        </div>
    </div>
   <br>
    <g:hiddenField name='details[${i}]?.id' value='${detail?.id}'/>
    <g:hiddenField name='details[${i}]?.deleted' value='false'/>
    <g:hiddenField name='details[${i}]?.new' value="${detail?.id == null?'true':'false'}"/>


    %{--<f:field bean="sondageInstance" property="details">--}%
            <g:render template="details" model="['sondageInstance':sondageInstance]" />

    %{--</f:field>--}%


</div>