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
        location.hash ="datePrice";

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
                return parseFloat(el.stock) > 0;
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
                {name:'commentaire',  editable:true, edittype:'textarea', width:300, editoptions:{rows: "3", cols: "30",autocomplete:"off"}}
            ],

            caption: 'Saisie des stocks des magazins (tapez sur la touche [entrer] pour valider)',
            addCaption:'Ajouter un stock ',


            rowNum:500,
            rowList:[500,1000],
            //          pager: '#pager1Stock',
            viewrecords:true,
            height:1000,
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
                    if (iCol==2)
                    {
                        if  (!isNumberKey(e)) {
                            e.stopImmediatePropagation();
                            e.preventDefault();
                        }
                    }
                    if (e.keyCode == 13) {  // Enter key:
                        var increment = e.shiftKey ? -1 : 1;

                        // Do not go out of bounds
                        var lastRowInd = jqStock.jqGrid("getGridParam","reccount");
                        if (!( iRow+increment == 0 || iRow+increment == lastRowInd+1))
                            jqStock.jqGrid("editCell",iRow+increment,iCol,true);
                    }
                }); // End keydown binding
            }
//            ,
//            onSelectRow: function(id){
//                if(id && id!==lastsel){
//
//                    jqStock.jqGrid('saveRow',lastsel);
//                    jqStock.jqGrid('restoreRow',lastsel);
//                    jqStock.jqGrid('editRow',id,true);
//                    lastsel=id;
//                }
//            }
        });
        jqStock.jqGrid('navGrid','#pager1Stock',{edit:false,add:false,del:false,refresh:false,search:false});
//        jqStock.closest('.ui-jqgrid-bdiv').width(jqStock.closest('.ui-jqgrid-bdiv').width() + 1);

    }//Fin du script de saisie des stocks

    $(document).ready(function() {
        saisieStocks();

    });
</script>