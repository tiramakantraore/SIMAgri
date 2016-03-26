<script type="text/javascript">
    var URL =$(location).attr('href')+"#",
            menuSMS= URL+"IdSMS",
            menuAlerte=URL+"IdAlerte",
            menuSondage =URL+"IdSondage",
            menuQuiz=URL+"IdQuiz",
            menuGoogleMapMarche= URL+"IdCarteGeoMarches",
            menuGoogleMapMagazin= URL+"IdCarteGeoMagazins",
            menuGroupeSMS=URL+"IdGroupeSMS",
            menuDestinatairesSMS=URL+"IdDestinatairesSMS",
            menuMessageSMS=URL+"idMessageSMS",
            menuIdentAlerte=URL+"IdIdentAlerte",
            menuGroupeAlerte=URL+"IdGroupeAlerte",
            menuProduitsAlerte=URL+"IdProduitsAlerte",
            menuMarchesAlerte=URL+"IdMarchesAlerte",
            menuDestinatairesAlerte=URL+"IdDestinatairesAlerte",
            menuCalendrierAlerte=URL+"IdCalendrierAlerte",
            activeMenu=menuSMS;


    var tableauDeBord = $('#dashboardTab'),
            activepage = $('#activepage'),

            previousSizeOfChecked = 0,

            childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
   function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }




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
    function addQCM(){
        indexQCM=quizzchildCount+1;
        var clone = $('#quizz_detail_clone').clone();
        var htmlId = 'quizz_details['+quizzchildCount+'].';
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
        clone.find(".questionLabel").html("  <span class=\"optionLabel\">  Question "+indexQCM+" </span>");
        clone.find(".commentaireBonneRep").html("  <span class=\"optionLabel\">  Commentaire bonne réponse à la question "+indexQCM+" </span>");
        clone.find(".commentaireMauvaiseRep").html("  <span class=\"optionLabel\">  Commentaire mauvaise réponse à la question "+indexQCM+" </span>");
        clone.find(".reponesPossibles").html("  <span class=\"optionLabel\">  Réponses possibles à la question "+indexQCM+" </span>");



        detailInput.attr('id',htmlId + 'choix')
                .attr('name',htmlId + 'choix');

        clone.attr('id', 'detail'+quizzchildCount);
        $('#quizz_childList').append(clone);
        clone.show();
        detailInput.focus();
        quizzchildCount++;
    }

    function scriptCreerQuizz() {
        var formParams=$('form[name="quizzForm"]');
        var valider=$(".validerQuizz");


        valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'quizz', action:'saveByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams))},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function () {
                $.jGrowl("Le quizz a été créé avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("La création du quizz a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });
        });

    }





    $(document).ready(function() {

        scriptCreerQuizz();
    });
</script>