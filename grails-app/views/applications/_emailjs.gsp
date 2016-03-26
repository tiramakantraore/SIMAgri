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
            yourTextMessage = $('#yourTextMessage'),
            previousSizeOfChecked = 0,
            destinatairesSMS = $('#destinatairesSMS'),
            reseauTreeSMS = $('#treeviewSMS'),
            idResauxSMS = $('#ReseauxIdsSMS'),
            marketsAlerte = $('#marketsAlerte'),
            produitsAlerte = $('#produitsAlerte'),
            categoriesAlerte = $('#categoriesAlerte'),
            destinatairesAlerte = $('#destinatairesAlerte'),
            typeAlerte = $('#typeAlerte'),
            typeOffreDiv = $('#typeOffreDiv'),
            listeDestinatairesAlertes=$('#listeDestinataires_alertes'),
            listeDestinatairesSMS=$('#listeDestinataires_SMS'),
            listeProduitsAlerte=$('#listeProduitsAlerte'),
            marcheDiv = $('#marcheDiv'),
            submitAlerteBtn = $('#submitAlerte'),
            reseauTreeAlerte = $('#treeviewAlerte'),
            idResauxAlerte = $('#ReseauxIdsAlerte'),
            objetEmail=$('#objetEmail'),
            childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
    var latitude=parseInt("${latitude}"), longitude=parseInt("${longitude}"), zoom=parseInt("${zoom}");
   var gmapMarche,gmapMagazin;
    function fireOnSelectReseau(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateDestinatairesFromReseaux')}",
            data: {selectedList:JSON.stringify(optionarray),instanceName:"destinataires_SMS"},
            cache: false,
            success: function(html) {
                listeDestinatairesSMS.html(html);
            }
        });


    }
    function onChangeDestinatairesSMS(){
        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'searchDestinataires')}",
            data: {query:$('#queryDest').val(),instanceName:"destinataires_SMS"},
            cache: false,
            success: function(html) {
                listeDestinatairesSMS.html(html);
            }
        });
    }
    function scriptTree(){
        $.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", function (data) {
            reseauTreeSMS.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });

            reseauTreeSMS.data("kendoTreeView").dataSource.bind("change", function (e) {
                var checkedNodes = [],
                        treeView = reseauTreeSMS.data("kendoTreeView"),
                        message;

                checkedNodeIds(treeView.dataSource.view(), checkedNodes);

                if (checkedNodes.length > 0) {
                    message = "IDs of checked nodes: " + checkedNodes.join(",");
                } else {
                    message = "No nodes checked.";
                }

                var optionarray = []; var i=0; j=0;
                for(i=0; i < checkedNodes.length; i++ ) {
                    optionarray[j] = {
                        'id' :checkedNodes[i]
                    };
                    j++
                }
                if (checkedNodes.length!=previousSizeOfChecked)
                    fireOnSelectReseau(optionarray) ;
                previousSizeOfChecked=checkedNodes.length;

            });
            reseauTreeSMS.data("kendoTreeView").expand(".k-item");


        });
    }

    function scriptSendSMS() {

         $('#uncheck-allDestinataires_SMS').click(function(){
            $("input[type='checkbox'][name^='destinataires_SMS_']").prop('checked', false);
        });
        $('#check-allDestinataires_SMS').click(function(){
            $("input[type='checkbox'][name^='destinataires_SMS_']").prop('checked', true);
            var selected = [];
            listeDestinatairesSMS.find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
        });
        var formParams=$('form[name="sendSMSForm"]');
        var valider=$(".validerEmail");
         valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
             var destinataires_selected =[];
             listeDestinatairesSMS.find('input:checked').each(function() {
                 destinataires_selected.push($(this).attr('value'));
             });
            var jqxhr =$.ajax({
                type: "POST",
                url: "${createLink(controller:'smsToReseaux', action:'sendEmailByService')}",
                data: {destinataires:stringToIntList(destinataires_selected),yourTextMessage:yourTextMessage.val(),idReseaux:idResauxSMS.val(),objetEmail:objetEmail.val()},
                dataType:"text",
                cache: false,
                beforeSend: function( xhr ) {
                    xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                }
            }).done(function() {
                $.jGrowl("Le mail a été envoyé avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
                $('a[href="#IdGroupeSMS"]').tab('show');
            }).fail(function( jqXHR, textStatus, errorThrown ) {
                $.jGrowl("L'envoi du mail a échoué, erreur rapportée "+textStatus, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });

        });

    }


    $(document).ready(function() {

        scriptSendSMS();
        scriptTree();
        $('a[href="#IdGroupeSMS"]').tab('show');
    });
</script>