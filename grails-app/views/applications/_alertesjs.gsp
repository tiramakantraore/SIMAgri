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

    var marketItems=$("input[type='checkbox'][name^='markets_alertes_']");
    var produitsItems=$("input[type='checkbox'][name^='products_alertes_']");
    var destinatairesItems;
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
            listeMarchesAlerte=$('#listeMarchesAlerte'),
            queryAlert=$('#queryAlert');
    listContent=$('#listContent');
    var  id=$('#id');
    function regionClick(){
        var regionsSelected = [];
        $('#listeRegions').find('input:checked').each(function () {
            regionsSelected.push($(this).attr('value'));
        });
        fireOnSelectRegion(regionsSelected);
    }
    function fireOnSelectRegion(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateMarchesFromRegion')}",
            data: {regions:JSON.stringify(optionarray),instanceName:"markets_alertes"},
            cache: false,
            success: function(html) {
                listeMarchesAlerte.html(html);
            }
        });
   }
    function categorieClick(){
        var categoriesSelected = [];
        $('#listeCategories').find('input:checked').each(function () {
            categoriesSelected.push($(this).attr('value'));
        });
        fireOnSelectCategorie(categoriesSelected);
    }
    function fireOnSelectCategorie(optionarray)
    {

        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateProduitsFromCategories')}",
            data: {categories:JSON.stringify(optionarray),instanceName:"products_alertes"},
            cache: false,
            success: function(html) {
                listeProduitsAlerte.html(html);
            }
        });

    }
    function fireOnEdit() {
        if (!(id.val()==undefined)) {
            jQuery.ajax({
                url: "${createLink(controller:'autoComplete', action:'updateDestinatairesFromAlerte')}",
                data: {idAlerte:id.val(),instanceName:"destinataires_alertes"},
                cache: false,
                success: function(html) {
                    listeDestinatairesAlertes.html(html);
                    destinatairesItems=$("input[type='checkbox'][name^='destinataires_alertes_']");
                    $.ajax({
                        url: "${createLink(controller:'applications', action:'updateAlerteByJSON')}",
                        accepts: "text/json",
                        data: {id:id.val()},
                        success: function(data, status){
                            if (!data.isEmpty){

                                marketItems.prop('checked', false);
                                produitsItems.prop('checked', false);
                                destinatairesItems.prop('checked', false);

                                marketItems.filter(function( index ) {
                                    return (data.marches.hasObject(this.name))
                                }).prop('checked', true);

                                produitsItems.filter(function( index ) {
                                    return (data.produits.hasObject(this.name))
                                }).prop('checked', true);

                                destinatairesItems.filter(function( index ) {
                                    return (data.destinataires.hasObject(this.name))
                                }).prop('checked', true);
                            }


                        }
                    });
                }
            });



        }
    }


    function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }


    function onChangeDestinatairesAlert(){
        if (queryAlert.val().length>3){
                jQuery.ajax({
                    url: "${createLink(controller:'autoComplete', action:'searchDestinataires')}",
                    data: {query:queryAlert.val(),instanceName:"destinataires_alertes"},
                    cache: false,
                    success: function(html) {
                        listeDestinatairesAlertes.html(html);
                    }
                });
        }
    }
    function onclicktypeAlerte(){
        var filterTypeAlerteOption=$("input[type='radio'][name='filtertypeAlerte']:checked").val();
        typeAlerte.val(filterTypeAlerteOption);
        if (typeAlerte.val()=="Offre") {
            $('#typeOffreDiv').show(200);
        }else {
            $('#typeOffreDiv').hide(200);
        }


    }


    function fireOnSelectReseauAlerte(optionarray)
    {
        jQuery.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateDestinatairesFromReseaux')}",
            data: {selectedList:JSON.stringify(optionarray),instanceName:"destinataires_alertes"},
            cache: false,
            success: function(html) {
                listeDestinatairesAlertes.html(html);
            }
        });


    }

    function creerAlerte(){

        var checkedNodes = [],
                PtreeView = reseauTreeAlerte.data("kendoTreeView"),
                message;

        checkedNodeIds(PtreeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            message = "IDs of checked nodes: " + checkedNodes.join(",");
            idResauxAlerte.val(checkedNodes.join(","));

        } else {
            message = "No nodes checked.";
        }


    }

    function scriptCreateAlertes() {
        var formParams=$('form[name="createAlerte"]');
        var valider=$(".validerAlerte");
        $('#uncheck-allMarketsAlerte').click(function(){
            $("input[type='checkbox'][name^='markets_alertes_']").prop('checked', false);
        });
        $('#check-allMarketsAlerte').click(function(){
            $("input[type='checkbox'][name^='markets_alertes_']").prop('checked', true);
            var selected = [];
            $('#listeMarchesAlerte').find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
        });
        $('#uncheck-allProductsAlerte').click(function(){
            $("input[type='checkbox'][name^='products_alertes_']").prop('checked', false);
        });
        $('#check-allProductsAlerte').click(function(){
            $("input[type='checkbox'][name^='products_alertes_']").prop('checked', true);
            var selected = [];
            listeProduitsAlerte.find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
        });

        $('#uncheck-allDestinatairesAlerte').click(function(){
            $("input[type='checkbox'][name^='destinataires_alertes_']").prop('checked', false);
        });
        $('#check-allDestinatairesAlerte').click(function(){
            $("input[type='checkbox'][name^='destinataires_alertes_']").prop('checked', true);
            var selected = [];
            listeDestinatairesAlertes.find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
        });


        valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'alerteReseau', action:'sendByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams)), idResauxAlerte: idResauxAlerte.val(),isEditing:false},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function () {

                $.jGrowl("L'alerte a été créée avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
                $('a[href="#IdIdentAlerte"]').tab('show');
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("L'alerte a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });
        });

        var modifier=$(".modifierAlerte");

        modifier.click(function(event) {
            event.stopPropagation();
            modifier.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'alerteReseau', action:'sendByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams)), idResauxAlerte: idResauxAlerte.val(),isEditing:true},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function (html) {
                $.jGrowl("L'alerte a été modifiée avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
//                $('#listContent").html(html);
                %{--window.location.replace("${createLink(controller:'dashboard', action:'accueil')}");--}%
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("L'alerte a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                modifier.removeAttr("disabled");

            });
        });

        var supprimer=$(".supprimerAlerte");

        supprimer.click(function(event) {
            event.stopPropagation();
            modifier.attr('disabled', 'disabled');
            var jqxhr = $.ajax({
                type: "POST",
                url: "${createLink(controller:'alerteReseau', action:'deleteByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams))},
                dataType: "text",
                cache: false,
                beforeSend: function (xhr) {
                    xhr.overrideMimeType("text/plain; charset=x-user-defined");
                }
            }).done(function (html) {
                $.jGrowl("L'alerte a été supprimée avec succès", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                listContent.load("${createLink(controller:'listeDesAlertes', action:'show')}");

                %{--window.location.replace("${createLink(controller:'dashboard', action:'accueil')}");--}%
            }).fail(function (jqXHR, textStatus, errorThrown) {
                $.jGrowl("La suppression de l'alerte a échoué, erreur rapportée " + errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                modifier.removeAttr("disabled");

            });
        });


    }

    function scriptTree(){
        $.getJSON("${createLink(controller:'reseau', action:'getAlerteTree')}",function (data) {
            var reseaux= reseauTreeAlerte.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });
            reseauTreeAlerte.data("kendoTreeView").dataSource.bind("change", function (e) {
                var checkedNodes = [],
                        treeView = reseauTreeAlerte.data("kendoTreeView"),
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
                if ((checkedNodes.length!=previousSizeOfChecked))
                    fireOnSelectReseauAlerte(optionarray) ;
                previousSizeOfChecked=checkedNodes.length;

            });
            reseauTreeAlerte.data("kendoTreeView").expand(".k-item");
        });
    }
    function scriptTreeModif(){
        var actionTree;

        $.getJSON("${createLink(controller:'reseau', action:'getAlerteTree')}",{alertId:id.val()}, function (data) {
            var reseaux= reseauTreeAlerte.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });
            reseauTreeAlerte.data("kendoTreeView").dataSource.bind("change", function (e) {
                var checkedNodes = [],
                        treeView = reseauTreeAlerte.data("kendoTreeView"),
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
                if ((checkedNodes.length!=previousSizeOfChecked)
                        &&
                        !(id.val()==undefined)){
                    fireOnSelectReseauAlerte(optionarray) ;
                }

                previousSizeOfChecked=checkedNodes.length;

            });
            reseauTreeAlerte.data("kendoTreeView").expand(".k-item");
        });
    }
    $(document).ready(function() {
        scriptCreateAlertes();
        if ((id.val()==undefined)) {
            scriptTree();
        }else {
            scriptTreeModif();
        }

        fireOnEdit();
        $('a[href="#IdIdentAlerte"]').tab('show');

    });
</script>