<%@ page import="org.springframework.validation.FieldError; simagri.QuestionChoixMultiple" %>

		<div class="row">

			
			<div class="col-sm-12 col-md-12">



                <div class="row">
                    <div class="col-xs-8 col-xs-offset4" >
                        <label for="question">
                            <span class="questionLabel">  Question </span>
                        </label>
                        %{--<f:field bean="questionChoixMultipleInstance"  property="question">--}%
                            <input type='text' id='question' name='question' value="${questionChoixMultipleInstance?.question}" class="form-control" />

                        %{--</f:field>--}%
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-6 " >
                        <label for="commentaireBonneReponse">
                            <span class="commentaireBonneRep">  Commentaire bonne réponse </span>
                        </label>
                        %{--<f:field bean="questionChoixMultipleInstance"  property="commentaireBonneReponse">--}%
                            <g:textArea name="commentaireBonneReponse" rows="3" maxlength="250"
                                        value="${questionChoixMultipleInstance?.commentaireBonneReponse}" class="form-control"/>
                        %{--</f:field>--}%
                    </div>
                    <div class="col-xs-6" >
                        <label for="commentaireMauvaiseReponse">
                            <span class="commentaireMauvaiseRep">  Commentaire mauvaise réponse </span>
                        </label>
                            <g:textArea name="commentaireMauvaiseReponse" rows="3" maxlength="250"
                                        value="${questionChoixMultipleInstance?.commentaireMauvaiseReponse}" class="form-control"/>

                    </div>
                </div>

				

            <g:render template='/questionChoixMultiple/option' model="['option':null,'i':'-1','hidden':true]"/>
            </div>
		</div>
