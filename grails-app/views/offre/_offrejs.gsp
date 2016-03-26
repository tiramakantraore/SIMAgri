<script type="text/javascript">
    var typeOffre=$('#typeOffre'),
        origineGrp=$('#origineGrp'),
        lieuStockageGrp=$('#lieuStockageGrp'),
        lieuLivraisonGrp=$('#lieuLivraisonGrp'),
        auteur=$('#auteur'),
        groupPrix=$('#groupPrix'),
        auteurDisplay=$('#auteurDisplay'),
        groupEnregistrer=$('#groupEnregistrer'),
        prixUnitaireGros=$('#prixUnitaireGros'),
        prixTotalGros=$('#prixTotalGros'),
        categorieProduit=$('#categorieProduit'),
        produit=$('#produit'),
        modePaiement=$('#modePaiement'),
        auteurId=$('#auteur\\.id'),
        contact=$('#contact'),
        mesure=$('#mesure'),
        quantite=jQuery('#quantite'),
        prixunitaire=jQuery('#prixunitaire'),
        prixtotal=jQuery('#prixtotal')
            ;
    function isNumberKey(evt) {
        var charCode = (evt.which) ? evt.which : event.keyCode;

        if (event.shiftKey == true) {
            event.preventDefault();
        }
        // Allow Only: keyboard 0-9, numpad 0-9, backspace, tab, left arrow, right arrow, delete
        return (event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46 || event.keyCode == 110;

    }
    jQuery(document).ready(function() {
        typeOffre.val('Vente');
     //   $('.myform').trigger("reset"); Permet de reinitialiser la fiche
        modePaiement.val('Especes');
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
 //                  prixunitaire.hide();
//                  prixtotal.hide();
                groupPrix.hide();

              } else {
                  origineGrp.show();
                  lieuStockageGrp.show();
                  lieuLivraisonGrp.hide();
                groupPrix.show();
//                  prixtotal.show();
//                  prixunitaire.show();
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
            jQuery.ajax({
                url: "${createLink(controller:'autoComplete', action:'updateMesure')}",
                data: "id=" + this.value,
                cache: false,
                success: function(html) {
                    mesure.html(html);

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



    });
</script>





















