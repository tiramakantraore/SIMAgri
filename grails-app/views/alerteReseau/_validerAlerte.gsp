
<div class="row">
    <div class="col-sm-6 col-md-6 offset2 ">
        <h2><g:message code="alerteValidationReseau.label"/></h2>
    </div>
</div>

<g:form class="form-horizontal" action="validerAlerte" >
    <g:render template="validatedBtn"/>
    <g:hiddenField name="selectedList"  />
    <div class="row">
        <div class="col-sm-12 col-md-12 ">
            <!-- table tag will hold our grid -->
            <table id="grid" class="scroll jqTable" cellpadding="0" cellspacing="0" ></table>
            <!-- pager will hold our paginator -->
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        </div>
        <div class="offset2 "></div>

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
                    url:"${createLink(controller:'alerteReseau', action:'populateValidateJSON')}",
                    datatype: 'json',
                    colNames:['id','Réseau','Libellé','Destinataires', 'Produits'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true},
                        {name:'reseau', index:'reseau', editable:false, width:40, hidden:true},
                        {name:'nom', index:'typeAlerte',align: 'center', editable:false,fixed:true, width:200},
                        {name:'destinataires', index:'destinataires',align: 'center', editable:false, width:300,fixed:true},
                        {name:'produits', index:'produits',align: 'center', editable:false, width:250,fixed:true}


                    ],

                    caption: 'Validation des alertes (sélectionnez les alertes à valider)',
                    addCaption:' ',
                    rowNum:20,
                    rowList:[40, 80],
                    pager: '#pager1',
                    sortname: 'id',
                    grouping:true, groupingView : { groupField : ['reseau'], groupColumnShow : [false], groupText : ['<b>{0} - {1} Lignes(s)</b>'], groupCollapse : false, groupOrder: ['asc'] },
                    viewrecords:true,
                    height:"100%",
                    autowidth:true,
                    multiselect:true,
                    sortorder:'desc'
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);
            	 
                valider.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                    alert("Vous devez sélectionner au moins une alerte ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'alerteReseau', action:'valider')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La validation des alertes a réussie", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'alerteReseau', action:'populateValidateJSON')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La validation des alertes a échoué, erreur rapportée "+errorThrown, {
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
                            url: "${createLink(controller:'alerteReseau', action:'rejeter')}",
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
                            jqGridPrice.setGridParam({url: "${createLink(controller:'alerteReseau', action:'populateValidateJSON')}"}).trigger("reloadGrid");
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

