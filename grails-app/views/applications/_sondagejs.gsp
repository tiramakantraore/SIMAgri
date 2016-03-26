<script type="text/javascript">
   var childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
    var latitude=parseInt("${latitude}"), longitude=parseInt("${longitude}"), zoom=parseInt("${zoom}");
   function addChoice(){
       index=childCount+1;
       var clone = $('#detail_clone').clone();
       var htmlId = 'details['+childCount+'].';
       var detailInput = clone.find("input[id$=choix]");

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
       clone.find(".optionLabel").html("  <span class=\"optionLabel\">  Réponse "+index+" </span>");



       detailInput.attr('id',htmlId + 'choix')
               .attr('name',htmlId + 'choix');

       clone.attr('id', 'detail'+childCount);
       $('#childList').append(clone);
       clone.show();
       detailInput.focus();
       childCount++;
   }
    function scriptCreerSondage() {
        childCount = ${sondageInstance?.details?.size()} + 0;
        addChoice();
        addChoice();
        addChoice();
        $('#titre').focus();


        $('#dateFin').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'
        });

        //bind click event on delete buttons using jquery live
        $('body').on('click', '.del-detail', function(){
            //find the parent div
            var prnt = $(this).parents(".detail-div");
            prnt.remove();


        });

        var formParams=$('form[name="createSondage"]');
        var valider=$(".validerSondage");



        valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'sondage', action:'saveByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams))},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function () {
                $.jGrowl("Le sondage a été créé avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("La création du sondage a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });
        });
    }


    $(document).ready(function() {

        scriptCreerSondage();
    });
</script>