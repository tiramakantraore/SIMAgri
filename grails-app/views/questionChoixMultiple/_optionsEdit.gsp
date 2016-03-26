
<script type="text/javascript">
    var childCount = ${questionChoixMultipleInstance?.options?.size()}+1 ;

    function addChoice(){
      var clone = $('#option-1').clone();
      var htmlId = 'options['+childCount+'].';
      var htmlCheckedId = '_options['+childCount+'].';
      var optionInput = clone.find("input[id$=answerOption]");
      var correctOptionCheck= clone.find("input[id$=correctOption]");
      clone.find("input[id$=id]")
             .attr('id',htmlId + 'id')
             .attr('name',htmlId + 'id');
      clone.find("input[id$=deleted]")
              .attr('id',htmlId + 'deleted')
              .attr('name',htmlId + 'deleted');
      clone.find("input[id$=new]")
              .attr('id',htmlId + 'new')
              .attr('name',htmlId + 'new')
              .attr('value', 'true');
        optionInput.attr('id',htmlId + 'answerOption')
              .attr('name',htmlId + 'answerOption');
        correctOptionCheck.attr('id',htmlId + 'correctOption')
                .attr('name',htmlId + 'correctOption');


      clone.attr('id', 'option'+childCount);
      $('#childList').append(clone);
      clone.show();
        optionInput.focus();
      childCount++;
    }

    //bind click event on delete buttons using jquery live
    $('.del-option').live('click', function() {
        //find the parent div
        var prnt = $(this).parents(".option-div");
        //find the deleted hidden input
        var delInput = prnt.find("input[id$=deleted]");
        //check if this is still not persisted
        var newValue = prnt.find("input[id$=new]").attr('value');
        //if it is new then i can safely remove from dom
        if(newValue == 'true'){
            prnt.remove();
        }else{
            //set the deletedFlag to true
            delInput.attr('value','true');
            //hide the div
            prnt.hide();
        }
    });

</script>

<div id="childList">
    <g:each var="option" in="${questionChoixMultipleInstance?.options}" status="i">
        
        <!-- Render the phone template (_detail.gsp) here -->
        <g:render template='option' model="['option':option,'i':i,'hidden':false]"/>
        <!-- Render the phone template (_detail.gsp) here -->
        
    </g:each>
</div>
%{--<div type="button" class="btn-flat  btn-medium" onclick="addChoice();">--}%
    %{--<i class="icon-plus-sign icon-black"></i> Ajouter--}%
%{--</div>--}%




