<g:form class="form-stacked-vertical myform" controller="mesureCorrespondance" action="saisie">
    <div class="row">
        <div class="col-sm-12 col-md-12 ">
            <h2><g:message code="tableEquivMesure.label"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary valider">
                Valider la table </a>

        </div>

        <div class="col-sm-8 col-md-8">


        </div>


    </div>
    <br>
    <g:hiddenField name="selectedList"  />
    %{--<div class="row">--}%
        %{--<div class="col-sm-12 col-md-12" style="background-color: #0000ff">--}%
            <table id="grid" class=" scroll jqTable table " cellpadding="0" cellspacing="0" ></table>
            %{--<table id="grid" class="scroll jqTable" cellpadding="20" cellspacing="20" ></table>--}%
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        %{--</div>--}%

    %{--</div>--}%
    <br>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary valider">
                Valider la table </a>

        </div>

        <div class="col-sm-8 col-md-8">


        </div>


    </div>
    <br>

</g:form>

    <script type="text/javascript">

            var lastsel;
            var selectedlist=$('#selectedList');
            var valider=$(".valider");
            var jqGridPrice=$('#grid');
//            var quitter=$(".quitter");
            $(document).ready(function() {
           	jqGridPrice.jqGrid({
                    url:"${createLink(controller:'mesureCorrespondance', action:'populate')}",
                    datatype: 'json',
                colNames:['id','Mesure départ','Mesure Destination','Equivalence'],
                colModel :[
                    {name:'id', index:'id', width:0, hidden:true},
                    {name:'mesureDepart', index:'mesureDepart', editable:false, width:50,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                    {name:'mesureDestination', index:'mesureDestination', editable:false, width:50,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"mesure",action:"listOfMesure")}',autocomplete:"off"}},
                    {name:'equivalence', index:'equivalence',align: 'center',  editable:true, width:30,formatter:'currency',editrules:{number:true,required:true}, formatoptions:{decimalSeparator:",", thousandsSeparator: " ",decimalPlaces: 6},autocomplete:"off"}

                ],
                caption: '',
                addCaption:' ',
                sortname: 'mesureDepart',
                sortable: true,
                forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:2000,
                    width:800,
//                autowidth:true,
//                   autowidth:true,
//                  shrinkToFit:true,
                    scrollrows:true,
                    cellsubmit:'clientArray',
                    multiselect:false,
                    afterEditCell: function(rowid, cellname, value, iRow, iCol) {

                    // Get a handle to the actual input field
                    var inputControl = $('#' + (iRow) + '_' + cellname);

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
                            var lastRowInd = jqGridPrice.jqGrid("getGridParam","reccount");
                            if (!( iRow+increment == 0 || iRow+increment == lastRowInd+1))
                                jqGridPrice.jqGrid("editCell",iRow+increment,iCol,true);
                        }
                    }); // End keydown binding
                },  onSelectRow: function(id){
                    if(id && id!==lastsel){

                        jqGridPrice.jqGrid('saveRow',lastsel);
                        jqGridPrice.jqGrid('restoreRow',lastsel);
                        jqGridPrice.jqGrid('editRow',id,true);
                        lastsel=id;
                    }
                }
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);

                valider.click(function() {
                    var gridData =jqGridPrice.getRowData().filter(function (el) {
                        return parseFloat(el.equivalence) > 0  ;
                    });
                    if (gridData.length==0) {
                        alert("vous n'avez pas saisi d'équivalence dans la grille");
                    }else {
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'mesureCorrespondance', action:'saisie')}",
                            data: {selectedList: JSON.stringify(gridData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La mise à jour de la table de correspondance des mesures  a réussi", {
                                sticky: false,
                                header: 'Notification',
                                theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La mise à jour de la table de correspondance, erreur rapportée "+errorThrown, {
                                sticky: true,
                                theme: 'ui-state-error ui-corner-all'
                            });
                            valider.removeAttr("disabled");

                        })
                    }


                });

            	 

            });

    </script>
%{--</body>--}%
%{--</html>--}%
