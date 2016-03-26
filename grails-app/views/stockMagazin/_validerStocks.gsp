
%{--<!doctype html>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<meta name="layout" content="adminLayout">--}%
    %{--<title><g:message code="stockMagazinValidation.label"/></title>--}%

  %{--</head>--}%
%{--<body>--}%
%{--<title><g:message code="stockMagazinValidation.label"/></title>--}%
<div class="row">
    <div class="col-sm-6 col-md-6 offset2 ">
        <h2><g:message code="stockMagazinValidation.label"/></h2>
    </div>
</div>

<g:form class="form-horizontal" action="validerStock" >
    <g:render template="validatedBtn"/>
    <g:hiddenField name="selectedList"  />
    <div class="row">
        <div class="col-sm-12 col-md-12 ">
            <!-- table tag will hold our grid -->
            <table id="grid" class="scroll jqTable" cellpadding="0" cellspacing="0" ></table>
            <!-- pager will hold our paginator -->
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        </div>

    </div>
    <g:render template="validatedBtn"/>
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
                    url:"${createLink(controller:'stockMagazin', action:'populateValidateJSON')}",
                    datatype: 'json',
                    colNames:['id','Réseau','Date mise en ligne','Auteur','Magazin','Produit' ,'Stock','Mesure','Commentaire'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true} ,
                        {name:'reseau', index:'reseau', editable:false, width:40, hidden:true},
                        {name:'date', index:'date',align: 'center',  editable:false, width:30,formatter :'date'},
                        {name:'auteur', index:'auteur', editable:false, width:50, hidden:true},
                        {name:'magazin', index:'magazin', editable:false, width:90},
                        {name:'nomProduit', index:'nomProduit', editable:false, width:90},
                        {name:'stock', index:'stock', editable:false, width:30,formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: " ", decimalPlaces: 0, suffix: ""}},
                        {name:'mesure', index:'mesure', editable:false, width:30},
                        {name:'commentaire',  editable:true, width:80,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}}

                    ],
                    caption: 'Validation des stocks (sélectionnez les stocks à valider)',
                    addCaption:' ',
                    sortname: 'nomProduit',
                    grouping:true,
                    groupingView : {
                        groupField : ['date','reseau','magazin','auteur'],
                        groupColumnShow : [false,false,false,false],
                        groupText :  ['<b>{0} - {1} Lignes(s)</b>'],
                        groupCollapse : false,
                        groupOrder: ['desc','asc','asc','asc'] },

                    forceFit:true,
                    cellEdit: true,

                    rowNum:1000,
                    rowList:[1000,2000,3000],
                    pager: '#pager1',
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
                    alert("Vous devez sélectionner au moins une ligne ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'stockMagazin', action:'valider')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La validation des stocks a réussie", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'stockMagazin', action:'populateValidateJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La validation des stocks a échoué, erreur rapportée "+errorThrown, {
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
                        alert("Vous devez sélectionner au moins un stock ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'stockMagazin', action:'rejeter')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("Le rejet des stocks a réussi", {
                                sticky: false,
                                header: 'Notification',
                                theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'stockMagazin', action:'populateValidateJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("Le rejet des stocks a échoué, erreur rapportée "+errorThrown, {
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
