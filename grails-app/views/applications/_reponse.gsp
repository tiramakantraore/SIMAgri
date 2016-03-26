
    <div class="row">
        <div class="col-xs-8 col-xs-offset4" >
            <label>
                <span class="reponesPossibles">  Réponses possibles </span>
            </label>
        </div>
    </div>
    <div class="row">
           <div class="col-xs-10 col-xs-offset2">
               <label>
                    Réponse
                </label>
                <g:textField name='options[${i}]?.answerOption' maxlength="150"  required="true" value='${option?.answerOption}' autocomplete="off" />

                Bonne réponse ? <g:checkBox name='options[${i}]?.correctOption' maxlength="150"  value='${option?.correctOption}' autocomplete="off" />
            </div>
    </div>
