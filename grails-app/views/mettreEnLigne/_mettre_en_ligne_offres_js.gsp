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
    var jqPrice=$('#gridPrix');
    var marketId=$('#marketId');
    var datePrix=$(".input-group.date.price");
    var activepage=$('#activepage');
    var magazinId=$('#magazinId');
    var magazin=jQuery('#magazin');
    var datestock=$(".input-group.date.stock");
    var dateExpirationInfo=$(".input-group.date.info");
    var jqStock=$('#gridstock');

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

        auteurDisplay.autocomplete({messages: {
            noResults: '',
            results: function() {}
        },
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
                auteur.val(ui.item.id); // update the hidden field.
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
//                prixunitaire.val(1);
//                groupPrix.hide();

            } else {
                origineGrp.show();
                lieuStockageGrp.show();
                lieuLivraisonGrp.hide();
//                groupPrix.show();
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

            $.ajax({
                url: "${createLink(controller:'autoComplete', action:'updateProduct')}",
                data: "id=" + this.value,
                cache: false,
                success: function(html) {
                    produit.html(html);
                    produit.change();

                }
            });
            $.ajax({
            url: "${createLink(controller:'autoComplete', action:'updateMesure')}",
            data: "id=" + this.value,
            cache: false,
            success: function(html) {
            mesure.html(html);

            }
            });

        });
        %{--produit.change(function() {--}%

            %{--jQuery.ajax({--}%
                %{--url: "${createLink(controller:'autoComplete', action:'updateMeasure')}",--}%
                %{--data: "id=" + this.value,--}%
                %{--cache: false,--}%
                %{--success: function(html) {--}%
                    %{--mesure.html(html);--}%

                %{--}--}%
            %{--});--}%

        %{--});--}%

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
            if ((typeOffre.val()=='Vente')&& (prixUnitaireGros.val()=='')) {
                alert("Vous devez renseigner le prix de vente");
            }else {
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
                produit.val("");
                typeOffre.change();
            }).fail(function( jqXHR, textStatus, errorThrown ) {
                $.jGrowl("La mise en ligne de l'offre a échoué, erreur rapportée "+errorThrown, {
                    sticky: true,
                    theme: 'ui-state-error ui-corner-all'
                });
                valider.removeAttr("disabled");

            });

            }
        });

    }//Fin du script js des Offres





    $(document).ready(function() {
        createOffre();

    });
</script>