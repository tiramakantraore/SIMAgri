<script type="text/javascript">

    var jqPrice=$('#gridPrix');
    var marketId=$('#marketId');
    var datePrix=$(".input-group.date.price");
    var ReseauxIds=$('#ReseauxIds');
    var reseauTree=$('#usertreeview');


    function reloadGridPrices(market){
        if ((market!="null")&&(market!=""))  {
            jqPrice.setGridParam({url: "${createLink(controller:"mettreEnLigne", action:"populatePrix")}",postData:{marketId:market} }).trigger("reloadGrid");
        }

    }
    function marcheClick(){

        var market=$("input:radio[name='market']:checked").val();
        marketId.val(market);
        jQuery.ajax({
            url: "${createLink(controller:'mettreEnLigne', action:'canWriteToMarket')}",
            type: "GET",
            data: {marketId:market},
            cache: false,
            success: function(html) {
                if (html.contains('false')) {
                    alert("Vous n'êtes pas autorisé à mettre en ligne sur ce marché, veuillez contacter un superviseur "+html);
                }else {
                    reloadGridPrices(market);

                }



            }
        });
        reloadGridPrices(market);
        location.hash ="ancreDate";

    }



    function saisiePrix() {
        var lastsel;
        var validerPrix=$(".validerPrix");
        var dateLimite=new Date();

        validerPrix.click(function() {
            var gridData =jqPrice.getRowData().filter(function (el) {
                if (parseFloat(el.prixGros)>parseFloat(el.prixDetail)) {
                    if  (parseFloat(el.prixDetail)>0) {
                       alert(" Le prix de gros :"+el.prixGros+" du produit : "+el.nomProduit+" est supérieur à son prix de détail : "+el.prixDetail);
                    return false;
                    }
                    else
                    return true
                }else {
                    return (parseFloat(el.prixGros) > 0 ||
                    parseFloat(el.prixDetail) > 0) ;
                }

            });
            var postData = JSON.stringify(gridData);
            if (gridData.length==0) {
                alert("vous n'avez pas saisi de prix dans la grille");
            }else {
                validerPrix.attr('disabled', 'disabled');
                var jqxhr=$.ajax({
                    type: "POST",
                    url: "${createLink(controller:'mettreEnLigne', action:'savePrix')}",
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

                });
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
            url: "${createLink(controller:"mettreEnLigne", action:"populatePrix")}",
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
                {name:'commentaire',  editable:true, width:160,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}}
            ],

            caption: 'Saisie des prix (tapez sur la touche [entrer] pour valider)',
            addCaption:'Ajouter un prix ',
            rowNum:500,
            rowList:[500,1000],
            // pager: '#pager1Prix',
            viewrecords:true,
            height:1000,
            autowidth:true,
            shrinkToFit:true,
            scrollrows:true,
            forceFit:true,
            cellEdit: true,  // Make sure we are using cell edit
            cellsubmit:'clientArray',

            afterEditCell: function(rowid, cellname, value, iRow, iCol) {

                // Get a handle to the actual input field
                var inputControl = $('#' + (iRow) + '_' + cellname);

                // Listen for enter (and shift-enter)
                inputControl.bind("keydown",function(e) {
                    if ((iCol==2) || (iCol==4))
                    {
                        if  (!isNumberKey(e)) {
                            e.stopImmediatePropagation();
                            e.preventDefault();
                        }
                    }
//                    if (e.keyCode == 13) {  // Enter key:
                   if (e.keyCode == 13) {  // Enter key:
                        var increment = e.shiftKey ? -1 : 1;

                        // Do not go out of bounds
                        var lastRowInd = jqPrice.jqGrid("getGridParam","reccount");
                        if (!( iRow+increment == 0 || iRow+increment == lastRowInd+1))
                            jqPrice.jqGrid("editCell",iRow+increment,iCol,true);
                    }
                }); // End keydown binding
            }
//            ,
//            onSelectRow: function(id){
//                if(id && id!==lastsel){
//
//                    jqPrice.jqGrid('saveRow',lastsel);
//                    jqPrice.jqGrid('restoreRow',lastsel);
//                    jqPrice.jqGrid('editRow',id,true);
//                    lastsel=id;
//                }
//            }
        });
        jqPrice.jqGrid('navGrid','#pager1Prix',{edit:false,add:false,del:false,refresh:false,search:true});
        jqPrice.closest('.ui-jqgrid-bdiv').width(jqPrice.closest('.ui-jqgrid-bdiv').width() + 1);

    }//Fin du script js des prix

    $(document).ready(function() {
        saisiePrix();

    });
</script>