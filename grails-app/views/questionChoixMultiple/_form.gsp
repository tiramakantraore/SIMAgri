<%@ page import="simagri.QuestionChoixMultiple" %>

<div class="row">
<div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'quiz', 'error')} required col-sm-12 col-md-12">
    <label for="quiz">
        <g:message code="questionChoixMultiple.quiz.label" default="Quiz"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="quiz" name="quiz.id" from="${simagri.Quizz.list()}" optionKey="id" required=""
              value="${questionChoixMultipleInstance?.quiz?.id}" class="many-to-one"/>
</div>
</div>

<div class="row">
<div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'question', 'error')} required col-sm-12 col-md-12">
    <label for="question">
        <g:message code="questionChoixMultiple.question.label" default="Question"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="question" maxlength="200" required="" value="${questionChoixMultipleInstance?.question}" class="col-sm-7 col-md-7 offset-5" autocomplete="off"/>
</div>
</div>


<div class="row">
<div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'actif', 'error')} ">
    <label for="actif">
        <g:message code="questionChoixMultiple.actif.label" default="Actif"/>

    </label>
    <g:checkBox name="actif" value="${questionChoixMultipleInstance?.actif}"/>
</div>
</div>

<div class="row">
<div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'commentaireBonneReponse', 'error')} ">
    <label for="commentaireBonneReponse">
        <g:message code="questionChoixMultiple.commentaireBonneReponse.label" default="Commentaire Bonne Reponse"/>

    </label>
    <g:textArea name="commentaireBonneReponse" cols="40" rows="5" maxlength="250"
                value="${questionChoixMultipleInstance?.commentaireBonneReponse}"/>
</div>
</div>

<div class="row">
<div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'commentaireMauvaiseReponse', 'error')} ">
    <label for="commentaireMauvaiseReponse">
        <g:message code="questionChoixMultiple.commentaireMauvaiseReponse.label"
                   default="Commentaire Mauvaise Reponse"/>

    </label>
    <g:textArea name="commentaireMauvaiseReponse" cols="40" rows="5" maxlength="250"
                value="${questionChoixMultipleInstance?.commentaireMauvaiseReponse}"/>
</div>
</div>
<div class="row">
    <div class="fieldcontain ${hasErrors(bean: questionChoixMultipleInstance, field: 'options', 'error')} col-sm-12 col-md-12">
        %{--<label for="options">--}%
          <p> <strong> <g:message code="questionChoixMultipleInstance.options.label" default="Options"/> </strong> </p>
        %{--</label>--}%
        <g:render template="options" model="['questionChoixMultipleInstance':questionChoixMultipleInstance]" />
    </div>
</div>