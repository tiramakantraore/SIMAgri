<br>
<div class="row">
    <div class="col-xs-1">
    </div>
    <div class="col-xs-10 col-xs-offset1">
        <h1><g:message code="creationQuizz" /></h1>
<g:form class="form-horizontal" role="form" name="quizzForm" >
    <div class="row">
        <div class="col-xs-10 col-xs-offset2">
            <div class="row">
                <div class="col-xs-8 col-xs-offset4" >
                    <label for="titreQuizz">
                        <span class="titre">  Titre Quizz  </span>
                    </label>
                    <input type='text' id='titreQuizz' name='titreQuizz'  class="form-control" />

                </div>
                <div class="col-xs-8 col-xs-offset4" >
                    %{--<label for="commentaireMauvaiseReponse">--}%
                    <span  style="font-weight: bold">  Description du Quizz </span>

                    <g:textArea name="description" id='description' rows="4" maxlength="250"
                                class="form-control"/>

                </div>
            </div>

            <dynamic:block itemId="questionId" min="1" max="20" addBtnId="addQuestion" removeBtnLabel="Supprimer question" removeOffset="-8"
                           limitReachedMsg="Nombre limite de questions atteinte"  removeBtnId="deleteQuestion" template="question" tabprefix="questions"/>
            <br>
            <input id="addQuestion" type="button" value="Ajouter question"/>
            <asset:deferredScripts/>
        </div>
    </div>
</g:form>
<br>
<div  class="btn-flat  btn-primary validerQuizz">

    <g:message code="default.button.create.label" default="Create" />
</div>
    </div>
</div>
<g:render template="/applications/quizjs"/>