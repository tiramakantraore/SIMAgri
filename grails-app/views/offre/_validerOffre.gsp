<div class="row">
    <div class="col-sm-6 col-md-6 offset2 ">
        <h2><g:message code="offerValidation.label"/></h2>
    </div>
</div>
<g:form action="validerOffre" method="post">
    <g:render template="thirdPageNav"/>
    <g:render template="grid_position"/>
    <g:render template="thirdPageNav"/>

    <script type="text/javascript">

            var lastsel;
            var gridOffre=$('#grid');
            var valider=jQuery(".valider");
            var quitter=jQuery(".quitter");
            jQuery(document).ready(function() {


                valider.click(function() {
                    var ids =gridOffre.jqGrid('getGridParam','selarrrow');
                    if (ids==""){
                        alert("Vous devez sélectionner au moins une offre ");
                    }else {
                    valider.attr('disabled', 'disabled');
                    jQuery.ajax({
                        type: "POST",
                        url: "${createLink(controller:'offre', action:'valider')}",
                        data: {selectedList: JSON.stringify(ids)},
                        dataType: "json",
                        beforeSend: function(x) {
                            if (x && x.overrideMimeType) {
                                x.overrideMimeType("application/json;charset=UTF-8");
                            }
                        }
                        ,
                        error: function(){ // handle server errors
                            jQuery.jGrowl("La validation des offres a échoué", {
                                theme: 'ui-state-error ui-corner-all'
                            });
                        },
                        complete:function(x){
                            $.jGrowl("La validation des offres a réussie", {

                                theme: 'iphone'
                            });
                            gridOffre.setGridParam({url: "${createLink(controller:'offre', action:'populate')}"}).trigger("reloadGrid");
                            valider.removeAttr("disabled");
                            %{--window.location.replace("${createLink(controller:'offreValidee', action:'list')}");--}%
                        }

                    });
                    }
                });
                jQuery(".rejeter").click(function() {
                    var ids =gridOffre.jqGrid('getGridParam','selarrrow');
                    if (ids==""){
                        alert("Vous devez sélectionner au moins une offre ");
                    }else {
                    jQuery.ajax({
                        type: "POST",
                        url: "${createLink(controller:'offre', action:'rejeter')}",
                        data: {selectedList: JSON.stringify(ids)},
                        dataType: "json",
                        beforeSend: function(x) {
                            if (x && x.overrideMimeType) {
                                x.overrideMimeType("application/json;charset=UTF-8");
                            }
                        }
                        ,
                        error: function(){ // handle server errors
                            jQuery.jGrowl("Le rejet des offres a échoué", {
                                theme: 'ui-state-error ui-corner-all'
                            });
                        },
                        complete:function(x){
                            $.jGrowl("Le rejet des offres a réussie", {

                                theme: 'iphone'
                            });
                            gridOffre.setGridParam({url: "${createLink(controller:'offre', action:'populate')}"}).trigger("reloadGrid");

                        }

                    });
                    }

                });
                quitter.click(function() {
                    window.location.replace("${createLink(controller:'home', action:'accueil')}");
                });
                gridOffre.jqGrid({
                    url:"${createLink(controller:'offre', action:'populate')}",
                    datatype: 'json',
                    colNames:['id','reseau','Type d\'offre','Auteur','Produit', 'Quantite', 'Prix unitaire', 'Montant total','Date mise en ligne','Commentaires'],
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
                            newformat : 'd-m-Y H:i:s'}},
                        {name:'commentaire',  editable:true, width:40,edittype: "textarea", editoptions:{rows: "2", cols: "40",autocomplete:"off"}}

                    ],
                    caption: 'Validation des offres (sélectionnez les offres à valider)',
                    addCaption:' ',
                    pager: '#pager1',
                    sortname: 'id',
                    grouping:true, groupingView : { groupField : ['date','reseau','typeOffre'],
                        groupColumnShow : [false,false,false],
                        groupText : ['<b>{0} - {1} Lignes(s)</b>'], groupCollapse : false, groupOrder: ['desc','asc','desc'] },

                    scrollOffset: 0,
                    forceFit:true,
                    cellEdit: true,
                    rowNum:100,
                    rowList:[100,200,300],
                    // pager: '#pager1',
                    viewrecords:true,
                    height:600,
                    autowidth:true,
                    shrinkToFit:true,
                    scrollrows:true,

                    multiselect:true,
                    sortorder:'desc'
                });
                gridOffre.jqGrid('navGrid','#pager1',{edit:true,add:false,del:false,search:false});
              
            });

    </script>

</g:form>