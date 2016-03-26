<%@ page import="simagri.Performance" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">
    <title><g:message code="habil.contrat.performance.label"/></title>

  </head>
<body>
<div class="row">
    <div class="col-sm-6 col-md-6 offset2 ">
        <h2><g:message code="habil.contrat.performance.label"/></h2>
    </div>
</div>

<g:form class="form-horizontal" action="validerPerformance" >
    <g:hiddenField name="selectedList"  />
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <g:message code="select.performance.label" default="Sélectionnez la performance" />

            <g:select id="performance" name="performance" from="${Performance.list()}"  noSelection="['': 'Choisissez une performance']"
                      class="form-control"/>

        </div>
        <div class="col-sm-2 col-md-2">
            <a class="btn-flat  btn-success autoriser">
                Affecter tous </a>

        </div>
        <div class="col-sm-2 col-md-2">
            <a class="btn-flat  btn-primary refuser">
                retirer tous </a>
        </div>
        <div class="col-sm-4 col-md-4">
        </div>
    </div>
    <br>

    <br>

    <div class="row">
        <div class="col-sm-1 col-md-10 ">
            <!-- table tag will hold our grid -->
            <table id="grid" class="scroll jqTable" cellpadding="0" cellspacing="0" ></table>
            <!-- pager will hold our paginator -->
            <div id="pager1" class="scroll" style="text-align:center;"></div>
        </div>
        <div class="offset2 "></div>

    </div>
    <br>
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <a  class="btn-flat  btn-primary valider">
                 <g:message code="valider.label" default="Valider"/> </a>

        </div>

        <div class="col-sm-2 col-md-2">

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
            var affecter=$(".autoriser");
            var performance=jQuery('#performance');
            $(document).ready(function() {
            	jqGridPrice.jqGrid({
                    url: "${createLink(controller:'performance', action:'populate')}",
                    postData:{performanceId:performance.val(),nom:"joel"},
                    datatype: 'json',
                    colNames:['id','Nom Enqueteur' ,'Contrat performance'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true} ,
                        {name:'nomUtilisateur', index:'nomUtilisateur', editable:false,fixed:true, width:300},
                        {name:'contratPerformance', index:'contratPerformance', editable:true, width:150,edittype:'select', editoptions:{dataUrl:'${createLink(controller:"performance",action:"listOfPerformance")}',autocomplete:"off"}}

                    ],
                    caption: 'Habilitations des objectifs de performance',
                    addCaption:' ',
                    rowNum:100,
//                rowList:[20, 40],
                    pager: '#pager1',
                    sortname: 'id',
                    viewrecords:true,
                    height:"100%",
                    autowidth:true,
                    shrinkToFit:true,
                    sortorder:'desc',
                    cellEdit: true,  // Make sure we are using cell edit
                    multiselect:true,
                    cellsubmit:'clientArray'
                });
            	jqGridPrice.jqGrid('navGrid','#pager1',{edit:false,add:false,del:false,search:false});
                jqGridPrice.closest('.ui-jqgrid-bdiv').width(jqGridPrice.closest('.ui-jqgrid-bdiv').width() + 1);
            	 
                valider.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                    alert("Vous devez sélectionner au moins un enquêteur ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'performance', action:'valider')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La validation des performances a réussie", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'performance', action:'populate')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La validation des performances a échoué, erreur rapportée "+errorThrown, {
                            sticky: true,
                            theme: 'ui-state-error ui-corner-all'
                            });

                        })

                    }


                });



                affecter.click(function() {
                    var gridData = jqGridPrice.getRowData();
                    var postData = JSON.stringify(gridData);

                    jQuery.ajax({
                        type: "POST",
                        url: "${createLink(controller:"performance", action:"autoriser")}",
                        data: {selectedList:postData,performanceId:performance.val()},
                        dataType: "text",
                        beforeSend: function( xhr ) {
                            xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                        }
                    }).done(function() {
                        $.jGrowl("Les affectations des performances ont réussi", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                        });
                        valider.removeAttr("disabled");
                        jqGridPrice.setGridParam({url: "${createLink(controller:'performance', action:'populate')}"}).trigger("reloadGrid");
                    }).fail(function( jqXHR, textStatus, errorThrown ) {
                        $.jGrowl("Les affectations des performances ont échoué, erreur rapportée "+errorThrown, {
                            sticky: true,
                            theme: 'ui-state-error ui-corner-all'
                        });

                    })

                });


                quitter.click(function() {
                    window.location.replace("${createLink(controller:'home', action:'accueil')}");
                });
                rejeter.click(function() {
                    var postData =jqGridPrice.jqGrid('getGridParam','selarrrow');
                    if (postData===""){
                        alert("Vous devez sélectionner au moins une performance ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'performance', action:'refuser')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("Le retrait des performance a réussi", {
                                sticky: false,
                                header: 'Notification',
                                theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'performance', action:'populate')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("Le retrait des performances a échoué, erreur rapportée "+errorThrown, {
                                sticky: true,
                                theme: 'ui-state-error ui-corner-all'
                            });

                        })

                    }


                });

            });

    </script>
</body>
</html>
