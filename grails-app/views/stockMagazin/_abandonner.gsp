
    <div class="row">
        <div class="col-sm-6 col-md-6 offset2 ">
            <h2><g:message code="list.stockMagazinHolderRejetes"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les stocks </a>

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
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les stocks </a>

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
                    url:"${createLink(controller:'stockMagazin', action:'populateStocksRejetesJSON')}",
                    datatype: 'json',
                colNames:['id','Réseau','Date mise en ligne','Magazin','Produit' ,'Stock','Mesure'],
                colModel :[
                    {name:'id', index:'id', width:0, hidden:true} ,
                    {name:'reseau', index:'reseau', editable:false, width:40, hidden:true},
                    {name:'date', index:'date',align: 'center',  editable:false, width:30,formatter :'date'
                    },
                    {name:'magazin', index:'magazin', editable:false, width:90},
                    {name:'nomProduit', index:'nomProduit', editable:false, width:90},
                    {name:'stock', index:'stock', editable:false, width:30,formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: ""}},
                    {name:'mesure', index:'mesure', editable:false, width:30}

                ],
                caption: 'Abandon des stocks (sélectionnez les stocks à abandonner)',
                addCaption:' ',
                sortname: 'nomProduit',
                grouping:true,
                groupingView : {
                    groupField : ['reseau','date','magazin'],
                    groupColumnShow : [false,false,false],
                    groupText : ['<b>{0}</b>'],
                    groupCollapse : false
                    ,
                    groupOrder: ['asc','desc','asc']
                },

                forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:800,
                    width:1000,
//                   autowidth:true,
//                  shrinkToFit:true,
                    scrollrows:true,

                    cellsubmit:'clientArray',
                    multiselect:true
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);
            	 
                valider.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                    alert("Vous devez sélectionner au moins un stock ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'stockMagazin', action:'abandonner')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("L'abandon des stocks a réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'stockMagazin', action:'populateStocksRejetesJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("L'abandon des stocks a échoué, erreur rapportée "+errorThrown, {
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
