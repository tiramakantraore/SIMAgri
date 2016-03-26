<script>
    var lastsel;
    var valider=$(".btn-primary");
    var magazinId=$('#magazinId");
    function magazinClick(){
        var magazin=$("input:radio[name='magazin']:checked").val();
        magazinId.val(magazin);
        reloadGrid(magazin);

    }
    function getSelectedMagazin(){
        return $("input:radio[name='magazin']:checked").val();

    }
    function reloadGrid(magazin){
        if (magazin=="null")
            jqStock.hide();
        else {
            jqStock.setGridParam({url: "${createLink(controller:'stockMagazin', action:'populateIt')}",postData:{magazinId:magazin,date:date.val()} }).trigger("reloadGrid");
            jqStock.show();
        }
    }
    function myformatDate(date){
        if(!date.getDate()){return '';}
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();
        month++; // adjust javascript month
        return jQuery.datepicker.formatDate( 'dd/mm/yy', date );
    }
    function isNumberKey(evt) {
        var event = evt || window.event; //For IE
        var charCode = (event.which) ? event.which : event.keyCode;

        if (event.shiftKey === true) {
            event.preventDefault();
        }
        // Allow Only: keyboard 0-9, numpad 0-9, backspace, tab, left arrow, right arrow, delete
        return (event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode === 8 || event.keyCode === 9 || event.keyCode === 37 || event.keyCode === 39 || event.keyCode === 46 || event.keyCode === 110;

    }

    var magazin=jQuery('#magazin');

    var currentRowId;
    var currentCellName;
    var canValideRow;
    var date=$(".input-group.date");
    var jqStock=$('#gridStock");
    var dateLimite=new Date();

    jQuery(document).ready(function() {

        date.datepicker({
            gotoCurrent: true, // True if today link goes back to current selection instead
            viewMode: 0, // True if month can be selected directly, false if only prev/next
            autoclose:true,
            language: 'fr'

        }) .on('changeDate', function(ev){
            if (ev.date.valueOf()> dateLimite){
                alert("Vous ne pouvez choisir une date posterieure à la date du jour : "+myformatDate(dateLimite));
                //    date.val(myformatDate(dateLimite));
                date.datepicker('setValue', myformatDate(dateLimite));

            }
        });

        jqStock.jqGrid({
            url: "${createLink(controller:"stockMagazin", action:"populateIt")}",
            postData:{magazinId:getSelectedMagazin(),date:date.val()},
            datatype: 'json',
            colNames:['id','Nom du produit' ,'Stock', 'mesure','commentaire'],
            colModel :[
                {name:'id', index:'id', width:0, hidden:true} ,
                {name:'nomProduit', index:'nomProduit', editable:false,fixed:true, width:350},
                {name:'stock', index:'stock', editable:true,editrules:{number:true}, width:90,fixed:true, formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, prefix: ""}, editoptions:{autocomplete:"off"}},
                {name:'mesure', index:'mesure', editable:true, width:80,fixed:true,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                {name:'commentaire',  editable:true, width:80,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}}
            ],

            caption: 'Saisie des stocks des magazins (tapez sur la touche [entrer] pour valider)',
            addCaption:'Ajouter un stock ',


            rowNum:50,
            rowList:[50,100,150],
            //          pager: '#pager1Stock',
            viewrecords:true,
            height:500,
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




    });
</script>
