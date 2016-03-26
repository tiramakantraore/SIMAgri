<script type="text/javascript">
    CKEDITOR.editorConfig = function( config )
    {
        config.toolbar = 'MyToolbar';

        config.toolbar_MyToolbar =
                [
                    { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
                    { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','Scayt' ] },
                    '/',
                    { name: 'styles', items : [ 'Styles','Format' ] },
                    { name: 'basicstyles', items : [ 'Bold','Italic','Strike','-','RemoveFormat' ] },
                    { name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote' ] },
                    { name: 'links', items : [ 'Link','Unlink','Anchor' ] }
                ];
    };
    var tableauDeBord=$('#dashboardTab');
    var jqPrice=$('#gridPrix');
    var marketId=$('#marketId');
    var datePrix=$(".input-group.date.price");
    var activepage=$('#activepage');
    var magazinId=$('#magazinId');
    var magazin=jQuery('#magazin');
    var datestock=$(".input-group.date.stock");
    var dateExpirationInfo=$(".input-group.date.info");
    var jqStock=$('#gridstock');
    var submitUsersBtn=$('#sendUsers');
    var submitMarketsBtn=$('#sendMarches');
    var submitProductsBtn=$('#sendProduits');

    var ReseauxIds=$('#ReseauxIds');
    var reseauTree=$('#usertreeview');
    function createOffre() {
        var typeOffre=$('#typeOffre'),
                origineGrp=$('#origineGrp'),
                lieuStockageGrp=$('#lieuStockageGrp'),
                lieuLivraisonGrp=$('#lieuLivraisonGrp'),
                auteur=$('#auteur'),
                groupPrix=$('#groupPrix'),
                auteurDisplay=$('#auteurDisplay'),
                prixUnitaireGros=$('#prixUnitaireGros'),
                prixTotalGros=$('#prixTotalGros'),
                categorieProduit=$('#categorieProduit'),
                produit=$('#produit'),
                modePaiement=$('#modePaiement'),
                auteurId=$('#auteur\\.id'),
                contact=$('#contact'),
                mesure=$('#mesure'),
                quantite=$('#quantite'),
                prixunitaire=$('#prixunitaire'),
                prixtotal=$('#prixtotal'),
                formParams=$('form[name="offre"]');

        typeOffre.val('Vente');
        modePaiement.val('Especes');
        var valider=$(".valider");
        // Get a handle to the actual input field

        // Listen for enter (and shift-enter)
        quantite.bind("keydown",function(e) {

            if  (!isNumberKey(e)) {
                e.stopImmediatePropagation();
                e.preventDefault();
            }

            if (e.keyCode === 13) {  // Enter key:
                var increment = e.shiftKey ? -1 : 1;

            }
        }); // End keydown binding

        // Listen for enter (and shift-enter)
        prixunitaire.bind("keydown",function(e) {

            if  (!isNumberKey(e)) {
                e.stopImmediatePropagation();
                e.preventDefault();
            }

            if (e.keyCode === 13) {  // Enter key:
                var increment = e.shiftKey ? -1 : 1;

            }
        }); // End keydown binding

        origineGrp.hide();
        lieuStockageGrp.hide();
        lieuLivraisonGrp.hide();
        auteur.tooltip('show');

        auteurDisplay.autocomplete({
            source: function(request, response){
                jQuery.ajax({
                    url:"${createLink(controller:'autoComplete', action:'offerOwnerList')}",
                    data: request,
                    success: function(data){
                        response(data); // set the response

                    },
                    error: function(){ // handle server errors
                        jQuery.jGrowl("Impossible de récupérer les utilisateurs", {
                            theme: 'ui-state-error ui-corner-all'
                        });
                    }
                });
            },
            minLength: 4, // triggered only after minimum 4 characters have been entered.
            select: function(event, ui) { // event handler when user selects a company from the list.
                auteurId.val(ui.item.id); // update the hidden field.
                contact.val(ui.item.mobilePhone);

            }
        });
        origineGrp.show();
        lieuStockageGrp.show();
        lieuLivraisonGrp.hide();
        typeOffre.change(function() {
            var type=typeOffre.val();
            if (type=='Achat') {
                lieuLivraisonGrp.show();
                lieuStockageGrp.hide();
                origineGrp.hide();
                prixunitaire.val(1);
                groupPrix.hide();

            } else {
                origineGrp.show();
                lieuStockageGrp.show();
                lieuLivraisonGrp.hide();
                groupPrix.show();
            }


        });

        quantite.change(function()
        {

            if ((prixUnitaireGros.val()!='') && (quantite.val()!='')){
                prixTotalGros.val(parseFloat(prixUnitaireGros.val())*parseInt(quantite.val()));
            }
            if (prixTotalGros.val()=='NaN'){
                prixTotalGros.val('0');
            }
        });

        prixUnitaireGros.change(function()
        {
            if ((prixUnitaireGros.val()!='') && (quantite.val()!='')){
                prixTotalGros.val(parseFloat(prixUnitaireGros.val())*parseInt(quantite.val()));
            }
            if (prixTotalGros.val()=='NaN'){

                prixTotalGros.val('0');
            }


        });
        categorieProduit.change(function() {

            jQuery.ajax({
                url: "${createLink(controller:'autoComplete', action:'updateProduct')}",
                data: "id=" + this.value,
                cache: false,
                success: function(html) {
                    produit.html(html);
                    produit.change();

                }
            });

        });
        produit.change(function() {

            jQuery.ajax({
                url: "${createLink(controller:'autoComplete', action:'updateMeasure')}",
                data: "id=" + this.value,
                cache: false,
                success: function(html) {
                    mesure.html(html);

                }
            });

        });

        $('#date').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });
        $('#dateExpiration').datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            changeMonth: true, // True if month can be selected directly, false if only prev/next
            changeYear: true, // True if year can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });

        valider.click(function() {
            valider.attr('disabled', 'disabled');
            var jqxhr =$.ajax({
                type: "POST",
                url: "${createLink(controller:'offre', action:'saveByService')}",
                data: {formData: JSON.stringify(getFormObj(formParams))},
                dataType:"text",
                cache: false,
                beforeSend: function( xhr ) {
                    xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                }
            }).done(function() {
                $.jGrowl("La mise en ligne de l'offre a réussie", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });
                valider.removeAttr("disabled");
                formParams[0].reset();
            }).fail(function( jqXHR, textStatus, errorThrown ) {
                $.jGrowl("La mise en ligne de l'offre a échoué, erreur rapportée "+errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });

        });
    }//Fin du script js des Offres
    function reloadGridPrices(market){
        if ((market!="null")&&(market!=""))  {
            jqPrice.setGridParam({url: "${createLink(controller:"saisiePrix", action:"populateIt")}",postData:{marketId:market} }).trigger("reloadGrid");
        }

    }
    function marcheClick(){

        var market=$("input:radio[name='market']:checked").val();
         marketId.val(market);
        reloadGridPrices(market);

    }



    function saisiePrix() {
        var lastsel;
        var validerPrix=$(".validerPrix");
        var dateLimite=new Date();

        validerPrix.click(function() {
            var gridData =jqPrice.getRowData().filter(function (el) {
             //   alert(" element is "+JSON.stringify(el));
                return el.prixGros > 0 ||
                        el.prixDetail > 0 ;
            });
            if (gridData.length==0) {
                alert("vous n'avez pas saisi de prix dans la grille");
            }else {
                validerPrix.attr('disabled', 'disabled');
                var jqxhr =$.ajax({
                    type: "POST",
                    url: "${createLink(controller:'saisiePrix', action:'saisie')}",
                    data: {date:datePrix.val(),marketId:marketId.val(),selectedList: JSON.stringify(gridData)},
                    dataType:"text",
                    cache: false,
                    beforeSend: function( xhr ) {
                        xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                    }
                }).done(function() {
                    $.jGrowl("La saisie des prix a réussie", {
                        sticky: false,
                        header: 'Notification',
                        theme: 'iphone'
                    });
                    validerPrix.removeAttr("disabled");
                    marcheClick();
                }).fail(function( jqXHR, textStatus, errorThrown ) {
                    $.jGrowl("La saisie des prix a échoué, erreur rapportée "+errorThrown, {
                        sticky: true,
                        theme: 'ui-state-error ui-corner-all'
                    });
                    validerPrix.removeAttr("disabled");

                })
            }


        });
        datePrix.datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            viewMode: 0, // True if month can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        }) .on('changeDate', function(ev){
            if (ev.date.valueOf()> dateLimite){
                alert("Vous ne pouvez choisir une date posterieure à la date du jour : "+myformatDate(dateLimite));
                //    date.val(myformatDate(dateLimite));
                datePrix.datepicker('setValue', myformatDate(dateLimite));

            }
        });
        function getSelectedMarket(){
            return $("input:radio[name='market']:checked").val();

        }
        jqPrice.jqGrid({
            url: "${createLink(controller:"saisiePrix", action:"populateIt")}",
            postData:{marketId:getSelectedMarket()},
            datatype: 'json',
            colNames:['id','Nom du produit' ,'Prix de gros', 'mesure', 'Prix de détail', 'mesure','commentaire'],
            colModel :[
                {name:'id', index:'id', width:0, hidden:true} ,
                {name:'nomProduit', index:'nomProduit', editable:false, width:160},
                {name:'prixGros', index:'prixGros', editable:true,width:100,formatter:'currency',editrules:{number:true,required:true}, formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: " FCFA"}, editoptions:{autocomplete:"off"}                    },
                {name:'mesureGros', index:'mesureGros', editable:true, width:50,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                {name:'prixDetail', index:'prixDetail', editable:true, width:100,formatter:'currency',editrules:{number:true}, formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: " FCFA"}, editoptions:{autocomplete:"off"}},
                {name:'mesureDetail', index:'mesureDetail', editable:true,width:50,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                {name:'commentaire',  editable:true, width:160,edittype: "textarea", editoptions:{rows: "2", cols: "40",autocomplete:"off"}}
            ],

            caption: 'Saisie des prix (tapez sur la touche [entrer] pour valider)',
            addCaption:'Ajouter un prix ',
            rowNum:50,
            rowList:[50,100,150],
            // pager: '#pager1Prix',
            viewrecords:true,
            height:300,
            autowidth:true,
            shrinkToFit:true,
            scrollrows:true,
            forceFit:true,
            cellEdit: true,  // Make sure we are using cell edit
            cellsubmit:'clientArray',

            afterEditCell: function(rowid, cellname, value, iRow, iCol) {

                // Get a handle to the actual input field
                var inputControl = jQuery('#' + (iRow) + '_' + cellname);

                // Listen for enter (and shift-enter)
                inputControl.bind("keydown",function(e) {
                    if ((iCol===2) || (iCol===4))
                    {
                        if  (!isNumberKey(e)) {
                            e.stopImmediatePropagation();
                            e.preventDefault();
                        }
                    }
                    if (e.keyCode === 13) {  // Enter key:
                        var increment = e.shiftKey ? -1 : 1;

                        // Do not go out of bounds
                        var lastRowInd = jqPrice.jqGrid("getGridParam","reccount");
                        if (!( iRow+increment === 0 || iRow+increment === lastRowInd+1))
                            jqPrice.jqGrid("editCell",iRow+increment,iCol,true);
                    }
                }); // End keydown binding
            },
            onSelectRow: function(id){
                if(id && id!==lastsel){

                    jqPrice.jqGrid('saveRow',lastsel);
                    jqPrice.jqGrid('restoreRow',lastsel);
                    jqPrice.jqGrid('editRow',id,true);
                    lastsel=id;
                }
            }
        });
        jqPrice.jqGrid('navGrid','#pager1Prix',{edit:false,add:false,del:false,refresh:false,search:true});
        jqPrice.closest('.ui-jqgrid-bdiv').width(jqPrice.closest('.ui-jqgrid-bdiv').width() + 1);

    }//Fin du script js des prix

    function getSelectedMagazin(){
        return $("input:radio[name='magazin']:checked").val();

    }
    function reloadGridMagazin(magazin){
        if (magazin=="null")
            jqStock.hide();
        else {
            jqStock.setGridParam({url: "${createLink(controller:'stockMagazin', action:'populateIt')}",postData:{magazinId:magazin,date:datestock.val()} }).trigger("reloadGrid");
            jqStock.show();
        }
    }
    function magazinClick(){
        var magazin=$("input:radio[name='magazin']:checked").val();

        magazinId.val(magazin);
        reloadGridMagazin(magazin);

    }
    function saisieStocks() {
        var lastsel=-1;
        var validerStock=$(".validerstock");
        var dateLimite=new Date();
        datestock.datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            viewMode: 0, // True if month can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        }) .on('changeDate', function(ev){
            if (ev.date.valueOf()> dateLimite){
                alert("Vous ne pouvez choisir une date posterieure à la date du jour : "+myformatDate(dateLimite));
                //    date.val(myformatDate(dateLimite));
                datestock.datepicker('setValue', myformatDate(dateLimite));

            }
        });
        validerStock.click(function() {
            var gridData = jqStock.getRowData().filter(function (el) {
                //   alert(" element is "+JSON.stringify(el));
                return el.stock > 0;
            });
            if (gridData.length == 0) {
                alert("vous n'avez pas saisi de stocks dans la grille");
            } else {
                validerStock.attr('disabled', 'disabled');
                var jqxhr = $.ajax({
                    type: "POST",
                    url: "${createLink(controller:'stockMagazin', action:'saisie')}",
                    data: {date: datestock.val(), magazinId: magazinId.val(), selectedList: JSON.stringify(gridData)},
                    dataType: "text",
                    cache: false,
                    beforeSend: function (xhr) {
                        xhr.overrideMimeType("text/plain; charset=x-user-defined");
                    }
                }).done(function () {
                    $.jGrowl("La saisie des stocks a réussie", {
                        sticky: false,
                        header: 'Notification',
                        theme: 'iphone'
                    });
                    validerStock.removeAttr("disabled");
                    magazinClick();
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    $.jGrowl("La saisie des stocks a échoué, erreur rapportée " + errorThrown, {
                        sticky: true,
                        theme: 'ui-state-error ui-corner-all'
                    });
                    validerStock.removeAttr("disabled");

                })
            }
        });

        jqStock.jqGrid({
            url: "${createLink(controller:"stockMagazin", action:"populateIt")}",
            postData:{magazinId:getSelectedMagazin(),date:datestock.val()},
            datatype: 'json',
            colNames:['id','Nom du produit' ,'Stock', 'mesure','commentaire'],
            colModel :[
                {name:'id', index:'id', width:0, hidden:true} ,
                {name:'nomProduit', index:'nomProduit', editable:false,fixed:true, width:350},
                {name:'stock', index:'stock', editable:true,editrules:{number:true}, width:90,fixed:true, formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, prefix: ""}, editoptions:{autocomplete:"off"}},
                {name:'mesure', index:'mesure', editable:true, width:80,fixed:true,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                {name:'commentaire',  editable:true,fixed:true, width:250,edittype: "textarea", editoptions:{rows: "2", cols: "40",autocomplete:"off"}}
            ],

            caption: 'Saisie des stocks des magazins (tapez sur la touche [entrer] pour valider)',
            addCaption:'Ajouter un stock ',


            rowNum:50,
            rowList:[50,100,150],
            //          pager: '#pager1Stock',
            viewrecords:true,
            height:500,
            autowidth:true,
            shrinkToFit:false,
            scrollrows:true,
            forceFit:true,
            cellEdit: true,  // Make sure we are using cell edit
            cellsubmit:'clientArray',

            afterEditCell: function(rowid, cellname, value, iRow, iCol) {

                // Get a handle to the actual input field
                var inputControl = jQuery('#' + (iRow) + '_' + cellname);

                // Listen for enter (and shift-enter)
                inputControl.bind("keydown",function(e) {
                    if ((iCol===2) || (iCol===4))
                    {
                        if  (!isNumberKey(e)) {
                            e.stopImmediatePropagation();
                            e.preventDefault();
                        }
                    }
                    if (e.keyCode === 13) {  // Enter key:
                        var increment = e.shiftKey ? -1 : 1;

                        // Do not go out of bounds
                        var lastRowInd = jqStock.jqGrid("getGridParam","reccount");
                        if (!( iRow+increment === 0 || iRow+increment === lastRowInd+1))
                            jqStock.jqGrid("editCell",iRow+increment,iCol,true);
                    }
                }); // End keydown binding
            },
            onSelectRow: function(id){
                if(id && id!==lastsel){

                    jqStock.jqGrid('saveRow',lastsel);
                    jqStock.jqGrid('restoreRow',lastsel);
                    jqStock.jqGrid('editRow',id,true);
                    lastsel=id;
                }
            }
        });
        jqStock.jqGrid('navGrid','#pager1Stock',{edit:false,add:false,del:false,refresh:false,search:false});
        jqStock.closest('.ui-jqgrid-bdiv').width(jqStock.closest('.ui-jqgrid-bdiv').width() + 1);

    }//Fin du script de saisie des stocks
    function CKupdate(){
        for ( instance in CKEDITOR.instances )
            CKEDITOR.instances[instance].updateElement();
        CKEDITOR.instances[instance].setData('');
    }
    function createInfos() {
        var formParams=$('form[name="info"]');
        var valider=$(".validerInfo");

        dateExpirationInfo.datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            viewMode: 0, // True if month can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        });
        valider.click(function(event) {
            event.stopPropagation();
            valider.attr('disabled', 'disabled');
            var jqxhr =$.ajax({
            type: "POST",
            url: "${createLink(controller:'info', action:'saveByService')}",
            data: {formData: JSON.stringify(getFormObj(formParams)),contenu:CKEDITOR.instances.contenu.getData()},
            dataType:"text",
            cache: false,
            beforeSend: function( xhr ) {
            xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
            }
            }).done(function() {
            $.jGrowl("La mise en ligne de l'information a réussie", {
            sticky: false,
            header: 'Notification',
            theme: 'iphone'
            });
            valider.removeAttr("disabled");
            formParams[0].reset();
                CKupdate();
            }).fail(function( jqXHR, textStatus, errorThrown ) {
            $.jGrowl("La mise en ligne de l'information a échoué, erreur rapportée "+errorThrown, {
            sticky: true,
            theme: 'ui-state-error ui-corner-all'
            });
                valider.removeAttr("disabled");
            });

        });
    } //Fin du script de creation des informations
    function loadUsers(){

        var checkedNodes = [],
                PtreeView = $('#usertreeview').data("kendoTreeView"),
                message;

        checkedNodeIds(PtreeView.dataSource.view(), checkedNodes);

        if (checkedNodes.length > 0) {
            message = "IDs of checked nodes: " + checkedNodes.join(",");
            $('#ReseauxIds').val(checkedNodes.join(","));

        } else {
            message = "No nodes checked.";
        }

        $.jGrowl("Veuillez patienter importation en cours", {
            sticky: false,
            theme: 'iphone'
        });
        submitUsersBtn.click();
        $.jGrowl("Le fichier a été bien téléchargé", {
            sticky: false,
            theme: 'iphone'
        });

    }
    function importerUtilisateurs() {


       $('#myUserFile').kendoUpload();


        $.getJSON("${createLink(controller:'reseau', action:'getSimpleTree')}", function (data) {
            var reseaux= reseauTree.kendoTreeView({
                loadOnDemand: true,
                dataSource: data,
                checkboxes: {
                    checkChildren: false
                }

            });
            reseauTree.data("kendoTreeView").dataSource.bind("change", function (e) {
                var checkedNodes = [],
                        treeView = reseauTree.data("kendoTreeView"),
                        message;

                checkedNodeIds(treeView.dataSource.view(), checkedNodes);

                if (checkedNodes.length > 0) {
                    message = "IDs of checked nodes: " + checkedNodes.join(",");
                } else {
                    message = "No nodes checked.";
                }

//                    kendoConsole.log("is changing on " + this.text(e.node));
                var optionarray = []; var i=0; j=0;
                for(i=0; i < checkedNodes.length; i++ ) {
                    optionarray[j] = {
                        'id' :checkedNodes[i]
                    };
                    j++
                }

            });
            reseauTree.data("kendoTreeView").expand(".k-item");
        });



    }//Fin du script d'importation des utilisateurs
    function loadMarches(){
        $.jGrowl("Veuillez patienter importation en cours", {
            sticky: false,
            theme: 'iphone'
        });
        submitMarketsBtn.click();
        $.jGrowl("Le fichier a été bien téléchargé", {
            sticky: false,
            theme: 'iphone'
        });

    }
    function loadProducts(){
        $.jGrowl("Veuillez patienter importation en cours", {
            sticky: false,
            theme: 'iphone'
        });
        submitProductsBtn.click();
        $.jGrowl("Le fichier a été bien téléchargé", {
            sticky: false,
            theme: 'iphone'
        });

    }

    function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }
    jQuery(document).ready(function() {
        createOffre();
        saisiePrix();
        saisieStocks();
        createInfos();
        importerUtilisateurs();
        $(tabselector(activepage.val())).tab('show');
    });
</script>