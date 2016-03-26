
%{--<!doctype html>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<meta name="layout" content="adminLayout">--}%
    %{--<title><g:message code="priceValidation.label"/></title>--}%

  %{--</head>--}%
%{--<body>--}%


<g:form class="form-horizontal" action="validerPrix" >
    <div class="row">
        <div class="col-sm-6 col-md-6 offset2 ">
            <h2><g:message code="priceValidation.label"/></h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary valider">
                Valider les prix </a>

        </div>

        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary rejeter">
                Rejeter les prix </a>

        </div>

        <div class="col-sm-4 col-md-4">
            <a class="btn-flat  btn-primary quitter">
                <g:message code="quitter.label" default="Quitter"/> </a>

        </div>

    </div>
    <br>
    <g:hiddenField name="selectedList"  />
    <div class="row">
        <div class="col-sm-12 col-md-12 ">
            <!-- table tag will hold our grid -->
            <table id="grid" class="scroll jqTable" cellpadding="0" cellspacing="0" ></table>
            <!-- pager will hold our paginator -->
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        </div>

    </div>
    <br>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary valider">
                Valider les prix </a>

        </div>

        <div class="col-sm-2 col-md-2">
            <a  class="btn-flat  btn-primary rejeter">
                Rejeter les prix </a>

        </div>

        <div class="col-sm-2 col-md-2 offset-2">
            <a class="btn-flat  btn-primary quitter">
                 <g:message code="quitter.label" default="Quitter"/> </a>

        </div>

    </div>
    <br>

</g:form>

    <script type="text/javascript">

            var lastsel;
            var selectedlist=$('#selectedList');
            var valider=$(".valider");
            var jqGridPrice=$('#grid');
            var quitter=$(".quitter");
            var rejeter=$(".rejeter");
            $(document).ready(function() {


            	jqGridPrice.jqGrid({
                    url:"${createLink(controller:'saisiePrix', action:'populateValidateJSON')}",
                    datatype: 'json',
                    colNames:['id','Réseau','Marché','Auteur','Nom du produit','Source','Prix de gros',  'Prix de détail','Date','Comment. Enqueteur','Comment. Admin.'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true},
                        {name:'reseau', index:'reseau', editable:false, width:35, hidden:true},
                        {name:'nomMarche', index:'nomMarche', editable:false, width:35, hidden:true},
                        {name:'auteur', index:'auteur', editable:false, width:50, hidden:true},
                        {name:'productName', index:'productName', editable:false, width:45},
                        {name:'sourcePrix', index:'sourcePrix', editable:false, width:15},
                        {name:'prixGros', index:'prixGros',align: 'center', editable:false, width:30},
                        {name:'prixDetail', index:'prixDetail',align: 'center',  editable:false, width:30},
                        {name:'date', index:'date',align: 'center',  editable:false, width:20,formatter :'date',
                             formatoptions : {srcformat : 'Y-m-d H:i:s', newformat : 'd-m-Y H:i:s'}},
                        {name:'commentaire',  editable:false, width:40,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}},
                        {name:'commentaireAdministrateur',  editable:true, width:40,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}}


                    ],

                    caption: 'Validation des prix (sélectionnez les prix à valider)',
                    addCaption:' ',
                    pager: '#pager1',
                    grouping:true,
                    groupingView : { groupField : ['date','reseau','nomMarche','auteur'],
                    groupColumnShow : [false,false,false,false],
                    groupText : ['<b>{0} - {1} Lignes(s)</b>'],
                    groupCollapse : false, groupOrder: ['desc','asc','asc','asc'] },

                    forceFit:true,
                    cellEdit: true,
                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    viewrecords:true,
                    height:600,
                    autowidth:true,
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
                            url: "${createLink(controller:'saisiePrix', action:'valider')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La validation des prix a réussie", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'saisiePrix', action:'populateValidateJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La validation des prix a échoué, erreur rapportée "+errorThrown, {
                            sticky: true,
                            theme: 'ui-state-error ui-corner-all'
                            });

                        })

                    }


                });

                quitter.click(function() {
                    window.location.replace("${createLink(controller:'home', action:'accueil')}");
                });
                rejeter.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                        alert("Vous devez sélectionner au moins un prix ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'saisiePrix', action:'rejeter')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("Le rejet des prix a réussi", {
                                sticky: false,
                                header: 'Notification',
                                theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'saisiePrix', action:'populateValidateJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("Le rejet des prix a échoué, erreur rapportée "+errorThrown, {
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
