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
    var datePrix=$('.input-group.date.price');
    var activepage=$('#activepage');
    var magazinId=$('#magazinId');
    var magazin=jQuery('#magazin');
    var datestock=$(".input-group.date.stock");
    var dateExpirationInfo=$(".input-group.date.info");
    var jqStock=$('#gridstock');
    var submitUsersBtn=$('#sendUsers');
    var submitMarketsBtn=$('#sendMarches');
    var submitProductsBtn=$('#sendProduits');
    var id=$("id");

    var ReseauxIds=$('#ReseauxIds');
    var reseauTree=$('#usertreeview');



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
            var updateType="create";
            if (id.val()!=undefined){
                updateType="update";
            }

            var jqxhr =$.ajax({
            type: "POST",
            url: "${createLink(controller:'info', action:'saveByService')}",
            data: {formData: JSON.stringify(getFormObj(formParams)),contenu:CKEDITOR.instances.contenu.getData(),updateType:updateType},
            dataType:"text",
            cache: false,
            beforeSend: function( xhr ) {
            xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
            }
            }).done(function() {
            valider.removeAttr("disabled");
            CKEDITOR.instances.contenu.setData("");
            formParams[0].reset();
                $.jGrowl("La mise en ligne de l'information a réussie", {
                    sticky: false,
                    header: 'Notification',
                    theme: 'iphone'
                });

            }).fail(function( jqXHR, textStatus, errorThrown ) {
            $.jGrowl("La mise en ligne de l'information a échoué, erreur rapportée "+errorThrown, {
            sticky: true,
            theme: 'ui-state-error ui-corner-all'
            });
                valider.removeAttr("disabled");
            });

        });
    } //Fin du script de creation des informations

    $(document).ready(function() {
        createInfos();
    });
</script>