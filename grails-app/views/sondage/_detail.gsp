<div id="detail${i}" class="row required detail-div" <g:if test="${hidden}">style="display:none;"</g:if>>

     <div class="col-xs-6">
    <label for="details[${i}]?.id">
       <span class="optionLabel">  Option ${i} </span>
    </label>
    <g:textField name='details[${i}]?.choix' maxlength="150"  required="true" value='${detail?.choix}' autocomplete="off" class="form-control " />

   </div>
    <div class="col-xs-1">
        <br><br>
        <span class="del-detail">
            <asset:image src="skin/icon_delete.png" style="vertical-align:middle;width:60%;height:60%" alt="Suppr." />
        </span>
    </div>
    <div class="col-xs-1">
        <br>  <br>
        <span class="glyphicon glyphicon-plus" style="vertical-align:middle;width:60%;height:60%" onclick="addChoice();" >

        </span>
    </div>

    <div class="col-xs-4">
    </div>

    


</div>

