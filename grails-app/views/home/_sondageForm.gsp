

%{--<div class="panel panel-default" >--}%
    %{--<div class="panel-heading"> <span class="glyphicon glyphicon-list-alt"></span><b> Sondage </b></div>--}%
    %{--<div class="panel-body">--}%

        <p><b> Sondage </b></p>
        <div id="sondageForm" style= "padding: 10px;background-color: #f9e9c9">
        <div id="Sondage_box"><p style= "font-size: 11px;color:black; padding-left: 10px;"><strong>${titreSondage}</strong></p></div>

        <g:if test="${titreSondage}">
                <g:radioGroup name="sondage" onclick="sondageClick();"
                              labels="${detailsSondage?.choix}"
                              values="${detailsSondage?.choix}" >
                    <p>${it.radio} ${it.label} </p>
                </g:radioGroup>
                <br/>
                <div type="button" class="btn-flat  btn-success" onclick="showSondageResult();">

                    <g:message code="ResultatsSondage.label" default="Resultats" />
                </div>

            </g:if>
            <g:else>

                <p> <strong> Aucun sondage disponible
                </strong>
                </p>

            </g:else>
        </div>
        <div id="sondageGraph" style= "display:true;">
        </div>
        <div id="buttonBack"></div>
    %{--</div>--}%
%{--</div>--}%

