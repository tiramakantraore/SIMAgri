<br>
<div class="row">
    <div class="col-xs-2">
    </div>
    <div class="col-xs-10">
            <g:form id="sondage" class="form-horizontal" role="form" name="createSondage">


                    <div class="row">
                        <div class="col-xs-8">

                            <f:field bean="sondageInstance"  property="titre">
                                <input type='text' id='titre' name='titre' value="${sondageInstance?.titre}" class="form-control" />

                            </f:field>
                        </div>

                        <div class="col-xs-4">

                        </div>
                    </div>

                    <br>
                    <g:hiddenField name='details[${i}]?.id' value='${detail?.id}'/>
                    <g:hiddenField name='details[${i}]?.deleted' value='false'/>
                    <g:hiddenField name='details[${i}]?.new' value="${detail?.id == null?'true':'false'}"/>


                    <g:render template="/sondage/details" model="['sondageInstance':sondageInstance]" />

                    <div class="row">

                        <div class="col-xs-4 offset8">
                            <f:field bean="sondageInstance" property="dateFin">
                                <div class='input-group date' id='dateFin' data-date-format="dd/mm/yyyy">
                                    <input type='text'   name='dateFin' value="${sondageInstance.dateFin?.format('dd/MM/yyyy')}" class="form-control" />
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </f:field>
                        </div>
                    </div>

                <br>

            </g:form>
            <div class="btn-flat  btn-primary center-block validerSondage">

                <g:message code="default.button.create.label" default="Create"/>
            </div>
    <g:render template='/sondage/detail' model="['detail':null,'i':'_clone','hidden':true]"/>
    </div>
</div>
