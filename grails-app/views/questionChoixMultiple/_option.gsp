<div id="option${i}" class="option-qcm-div" <g:if test="${hidden}">style="display:none;"</g:if>>
    <div class="row">
        <div class="col-xs-8 col-xs-offset4" >
            <label for="options">
                <span class="reponesPossibles">  Réponses possibles </span>
            </label>
            <g:render template="/questionChoixMultiple/optionsQCM" model="['questionChoixMultipleInstance':questionChoixMultipleInstance,'i':i]" />
        </div>
    </div>

    <g:hiddenField name='options[${i}]?.id' value='${option?.id}'/>
    <g:hiddenField name='options[${i}]?.deleted' value='false'/>
	<g:hiddenField name='options[${i}]?.new' value="${option?.id == null?'true':'false'}"/>
    <div class="col-xs-10">
    <label for="options[${i}]?.id">
        Option ${option?.id}
    </label>
    <g:textField name='options[${i}]?.answerOption' maxlength="150"  required="true" value='${option?.answerOption}' autocomplete="off" />

    Bonne réponse ? <g:checkBox name='options[${i}]?.correctOption' maxlength="150"  value='${option?.correctOption}' autocomplete="off" />
    </div>
    <div class="col-xs-2">
        <span class="del-option-qcm">
            <asset:image src="skin/icon_delete.png" style="vertical-align:middle;width:60%;height:60%" alt="Suppr." />
        </span>
    </div>
</div>







