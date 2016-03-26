<div class="form-group form-group">
    <f:field bean="quizzInstance" property="titre" input-class="form-control"/>


    <f:field property="actif" label="Sondage actif ?">
        <g:checkBox name="actif" value="${quizzInstance.actif}" class="form-control" />

    </f:field>

    <f:field  property="description" >
        <g:textArea name="description" value="${quizzInstance.description}" class="form-control" />

    </f:field>

    <br>


</div>