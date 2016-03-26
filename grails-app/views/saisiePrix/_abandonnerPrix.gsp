
    <div class="row">
        <div class="col-sm-6 col-md-6 offset2 ">
            <h2><g:message code="prixRejetes.label"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les prix </a>

        </div>

        <div class="col-sm-8 col-md-8">


        </div>


    </div>
    <br>
    <g:hiddenField name="selectedList"  />
    %{--<div class="row">--}%
        %{--<div class="col-sm-12 col-md-12" style="background-color: #0000ff">--}%
            <table id="grid" class=" scroll jqTable table " cellpadding="0" cellspacing="0" style="width:100%"></table>
            %{--<table id="grid" class="scroll jqTable" cellpadding="20" cellspacing="20" ></table>--}%
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        %{--</div>--}%

    %{--</div>--}%
    <br>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les prix </a>

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
                    url:"${createLink(controller:'saisiePrix', action:'populateRejeteJSON')}",
                    datatype: 'json',
                    colNames:['id','Réseau','Marché','Auteur','Nom du produit','Source','Prix de gros',  'Prix de détail','Date'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true},
                        {name:'reseau', index:'reseau', editable:false, width:40, hidden:true},
                        {name:'nomMarche', index:'nomMarche', editable:false, width:40, hidden:true},
                        {name:'auteur', index:'auteur', editable:false, width:50, hidden:true},
                        {name:'productName', index:'productName', editable:false, width:40},
                        {name:'sourcePrix', index:'sourcePrix', editable:false, width:10},
                        {name:'prixGros', index:'prixGros',align: 'center', editable:false, width:25},
                        {name:'prixDetail', index:'prixDetail',align: 'center',  editable:false, width:25},
                        {name:'date', index:'date',align: 'center',  editable:false, width:20,formatter :'date',
                             formatoptions : {srcformat : 'Y-m-d H:i:s', newformat : 'Y-m-d H:i:s'}}


                    ],

                    caption: 'Abandon des prix (sélectionnez les prix à abandonner)',
                    addCaption:' ',

                    pager: '#pager1',
                    grouping:true,
                    groupingView : { groupField : ['reseau','nomMarche','auteur'],
                    groupColumnShow : [false,false,false],
                    groupText : ['<b>{0} - {1} Lignes(s)</b>'],
                    groupCollapse : false, groupOrder: ['asc','asc','asc'] },

                    forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:800,
                    width:800,
              //     autowidth:true,
                  shrinkToFit:true,
                    scrollrows:true,

                    cellsubmit:'clientArray',
                    multiselect:true
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);
            	 
                valider.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                    alert("Vous devez sélectionner au moins un prix ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'saisiePrix', action:'abandonner')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("L'abandon des prix a réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'saisiePrix', action:'populateRejeteJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("L'abandon des prix a échoué, erreur rapportée "+errorThrown, {
                            sticky: true,
                            theme: 'ui-state-error ui-corner-all'
                            });

                        })

                    }


                });

                %{--quitter.click(function() {--}%
                    %{--window.location.replace("${createLink(controller:'home', action:'accueil')}");--}%
                %{--});--}%


            });

    </script>
%{--</body>--}%
%{--</html>--}%
