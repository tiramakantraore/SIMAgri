
    <div class="row">
        <div class="col-sm-12 col-md-12  ">
            <h2><g:message code="abondonnerRejets.Notes"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les notes </a>

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
        <div class="col-sm-12 col-md-12  ">
            <h2><g:message code="abondonnerRejets.Notes"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les notes </a>

        </div>

        <div class="col-sm-8 col-md-8">


        </div>


    </div>
    <br>

%{--</g:form>--}%

    <script type="text/javascript">

            var lastsel;
            var selectedlist=$('#selectedList');
            var valider=$(".abandoner");
            var jqGridPrice=$('#grid');
//            var quitter=$(".quitter");
            $(document).ready(function() {
           	jqGridPrice.jqGrid({
                    url:"${createLink(controller:'noteMarche', action:'populateRejetes')}",
                    datatype: 'json',
                colNames:['id','Réseau','Auteur','Date','Titre', 'Contenu','Longitude','Latitude'],
                colModel :[
                    {name:'id', index:'id', width:0, hidden:true},
                    {name:'reseau', index:'reseau', editable:false, width:250, hidden:true},
                    {name:'auteur', index:'auteur',align: 'left', editable:false, width:120},
                    {name:'date', index:'date',align: 'center',  editable:false, width:80,formatter :'date', formatoptions : {srcformat : 'Y-m-d H:i:s',
                        newformat : 'Y-m-d '}, hidden:true},
                    {name:'titre', index:'titre', width:200},
                    {name:'contenu',  editable:false, width:250,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}},
                    {name:'longitude', index:'url',align: 'left', editable:false, width:100},
                    {name:'latitude', index:'url',align: 'left', editable:false, width:100}
                ],
                caption: 'Abandon des notes (sélectionnez les notes à abandonner)',
                addCaption:' ',
                sortname: 'nomProduit',
                grouping:true, groupingView : { groupField : ['reseau','date','auteur'],
                    groupColumnShow : [false,false,false],
                    groupText : ['<b>{0} - {1} Lignes(s)</b>'], groupCollapse : false, groupOrder: ['asc','desc','asc'] },
                forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:800,
                    width:800,
                    scrollrows:true,
                    cellsubmit:'clientArray',
                    multiselect:true
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);
            	 
                valider.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                    alert("Vous devez sélectionner au moins une note ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'noteMarche', action:'abandonner')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("L'abandon des notes a réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'noteMarche', action:'populateRejetes')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("L'abandon des notes a échoué, erreur rapportée "+errorThrown, {
                            sticky: true,
                            theme: 'ui-state-error ui-corner-all'
                            });

                        })

                    }


                });




            });

    </script>
%{--</body>--}%
%{--</html>--}%
