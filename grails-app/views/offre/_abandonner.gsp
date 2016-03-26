
    <div class="row">
        <div class="col-sm-12 col-md-12 ">
            <h2><g:message code="abondonnerRejets.offre"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary abandoner">
                Abandonner les offres </a>

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
                Abandonner les offres </a>

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
                    url:"${createLink(controller:'offre', action:'populateRejetes')}",
                    datatype: 'json',
                colNames:['id','reseau','Type d\'offre','Auteur','Produit', 'Qte', 'Prix U', 'Total','Date','Commentaires'],
                colModel :[
                    {name:'id', index:'id', width:0, hidden:true},
                    {name:'reseau', index:'reseau', editable:false, width:40, hidden:true},
                    {name:'typeOffre', index:'typeOffre', width:30},
                    {name:'auteur', index:'auteur',align: 'left', editable:false, width:50},
                    {name:'produit', index:'produit',align: 'left', editable:false, width:45},
                    {name:'quantite', index:'quantite',align: 'center',  editable:false, width:15},
                    {name:'prixUnitaire', index:'prixUnitaire',align: 'center',  editable:false, width:30,formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: " FCFA"}},
                    {name:'prixtotal', index:'prixtotal',align: 'center',  editable:false, width:30,formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: " FCFA"}},
                    {name:'date', index:'date',align: 'center',  editable:false, width:30,formatter :'date', formatoptions : {srcformat : 'Y-m-d H:i:s',
                        newformat : 'Y-m-d H:i:s'}},
                    {name:'commentaire',  editable:true, width:40,edittype: "textarea", editoptions:{rows: "2", cols: "40",autocomplete:"off"}}

                ],
                caption: 'Abandon des offres rejetées (sélectionnez les offres à abandonner)',
                addCaption:' ',
                sortname: 'nomProduit',
                grouping:true,
                groupingView : {
                    groupField : ['reseau','date'],
                    groupColumnShow : [false,false],
                    groupText : ['<b>{0}</b>'],
                    groupCollapse : false
                    ,
                    groupOrder: ['asc','desc']
                },

                forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:800,
                    width:900,
//                autowidth:true,
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
                    alert("Vous devez sélectionner au moins une offre ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'offre', action:'abandonner')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("L'abandon des offres a réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'offre', action:'populateRejetes')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("L'abandon des offres a échoué, erreur rapportée "+errorThrown, {
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
