<br>
<div class="col-xs-12">

<div class="row">
   <div class="col-xs-8 col-xs-offset4" >
       <label for="question">
           <span class="questionLabel">  Question  </span>
       </label>
       <input type='text' id='question' name='question'  class="form-control" />

    </div>
</div>
<br><br>
<div class="row">
    <div class="col-xs-8 col-xs-offset4" >
        <label>
            <span class="reponesPossibles">  Réponses possibles à la question </span>
        </label>
    </div>
</div>
<g:each var="num" in="${0..nbreponsesQuizz-1}">
<div class="row">
    <div class="col-xs-10 col-xs-offset2">

        <g:textField name="rep_${num}.reponse"  size="60" required="true"  autocomplete="off" />

        <span style="font-weight: bold">Bonne réponse ? </span><g:checkBox name='ok_${num}.bonneReponse' maxlength="150"   autocomplete="off" />
    </div>
</div>
<br>
</g:each>
%{--<div class="row">--}%
    %{--<div class="col-xs-10 col-xs-offset2">--}%

        %{--<g:textField name='reponse2' maxlength="150" size="60"  required="true"  autocomplete="off" />--}%

        %{--<span style="font-weight: bold">Bonne réponse ? </span><g:checkBox name='bonneReponse' maxlength="150"   autocomplete="off" />--}%
    %{--</div>--}%
%{--</div>--}%
%{--<br>--}%
%{--<div class="row">--}%
    %{--<div class="col-xs-10 col-xs-offset2">--}%

        %{--<g:textField name='reponse3' maxlength="150" size="60"  required="true"  autocomplete="off" />--}%

        %{--<span style="font-weight: bold">Bonne réponse ? </span><g:checkBox name='bonneReponse' maxlength="150"   autocomplete="off" />--}%
    %{--</div>--}%
%{--</div>--}%
%{--<br>--}%
<div class="row">
    <div class="col-xs-4 " >

            <span  style="font-weight: bold">  Commentaire bonne réponse </span>

        <g:textArea name="commentaireBonneReponse" rows="3" maxlength="250"
                     class="form-control"/>

    </div>
    <div class="col-xs-4" >
        %{--<label for="commentaireMauvaiseReponse">--}%
            <span  style="font-weight: bold">  Commentaire mauvaise réponse </span>

        <g:textArea name="commentaireMauvaiseReponse" rows="3" maxlength="250"
                     class="form-control"/>

    </div>
</div>
</div>

%{--<dynamic:block itemId="reponseId" min="1" max="4" removeBtnLabel="Supprimer réponse" removeOffset="4"--}%
               %{--limitReachedMsg="Nombre limite de reponses atteinte" template="reponse"/>--}%
