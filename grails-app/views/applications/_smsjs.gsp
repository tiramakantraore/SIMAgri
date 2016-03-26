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
            submitSMSBtn = $('#submitSMS'),
            yourTextMessage = $('#yourTextMessage'),
            infodiv = $('#infodiv'),
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
            queryDest=$('#queryDest'),
            childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
    var latitude=parseInt("${latitude}"), longitude=parseInt("${longitude}"), zoom=parseInt("${zoom}");
   var gmapMarche,gmapMagazin;
    function updateReseauxIds(){

        var checkedNodes = [],
                PtreeView = reseauTreeSMS.data("kendoTreeView"),
                message;

        checkedNodeIds(PtreeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            message = "IDs of checked nodes: " + checkedNodes.join(",");
            idResauxSMS.val(checkedNodes.join(","));

        } else {
            message = "No nodes checked.";
        }


    }
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
        if (queryDest.val().length>3){
        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'searchDestinataires')}",
            data: {query:queryDest.val(),instanceName:"destinataires_SMS"},
            cache: false,
            success: function(html) {
                listeDestinatairesSMS.html(html);
            }
        });
        }
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


            var reseaux= reseauTreeAlerte.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });

        });
    }
    function limitChars(limit) {
        var text = yourTextMessage.val();
        var textlength = text.length;
        if (textlength > limit) {
            infodiv.html('Vous ne pouvez pas écrire plus de ' + limit + ' caractères');
            yourTextMessage.val(text.substr(0, limit));
            return false;
        }
        else {
            infodiv.html('Il vous reste ' + (limit - textlength) + ' caractères, vous avez écrit '+ (Math.floor(textlength/160)+1) +' SMS');
            return true;
        }
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
        var valider=$(".validerSMS");
         valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
             var destinataires_selected =[];
             listeDestinatairesSMS.find('input:checked').each(function() {
                 destinataires_selected.push($(this).attr('value'));
             });
             updateReseauxIds();
            var jqxhr =$.ajax({
                type: "POST",
                url: "${createLink(controller:'smsToReseaux', action:'sendByService')}",
                data: {destinataires:stringToIntList(destinataires_selected),yourTextMessage:yourTextMessage.val(),idReseaux:idResauxSMS.val()},
                dataType:"text",
                cache: false,
                beforeSend: function( xhr ) {
                    xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                }
            }).done(function() {
                $.jGrowl("Le SMS a été envoyé avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
                $('a[href="#IdGroupeSMS"]').tab('show');
            }).fail(function( jqXHR, textStatus, errorThrown ) {
                $.jGrowl("L'envoi du SMS a échoué, erreur rapportée "+textStatus, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });

        });





        yourTextMessage.keyup(function() {

            limitChars( 1600);
        })
    }


    $(document).ready(function() {

        scriptSendSMS();
        scriptTree();
        $('a[href="#IdGroupeSMS"]').tab('show');
    });
</script>