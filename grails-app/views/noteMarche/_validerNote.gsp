
%{--<!doctype html>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<meta name="layout" content="adminLayout">--}%
    %{--<title><g:message code="validerInfos.label"/></title>--}%

  %{--</head>--}%
%{--<body>--}%
<div class="row">
    <div class="col-sm-6 col-md-6  ">
        <h2><g:message code="validerNotes.label"/></h2>
    </div>
</div>

<g:form class="form-horizontal" action="validerNote" >
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
            %{--$.fn.fmatter.imageFormatter = function(cellvalue, options, rowObject) {--}%
                %{--alert("cellvalue is "+cellvalue);--}%
                %{--if (cellvalue!="")--}%
                %{--return "${createLink(controller: 'noteMarche', action: 'showImg',params:[id:noteMarcheInstance?.id])}?query=" + encodeURIComponent('id='+cellvalue) ;--}%
            %{--};--}%
            $(document).ready(function() {

                function imageFormatter(cellvalue, options, rowObject) {
                    var root = location.protocol + '//' + location.host;
                    if (rowObject[0]!=null)
                        return "<img src="+root+"${createLink(controller: 'noteMarche', action: 'showImg')}?id="+encodeURIComponent(rowObject[0])+" alt='photo' class='jqImage'/>" ;
                }

            	jqGridPrice.jqGrid({
                    url:"${createLink(controller:'noteMarche', action:'populate')}",
                    datatype: 'json',
                    colNames:['id','Réseau','Auteur','Date','Titre', 'Contenu','Longitude','Latitude','Photo'],
                    colModel :[
                        {name:'id', index:'id', width:0, hidden:true},
                        {name:'reseau', index:'reseau', editable:false, width:250, hidden:true},
                        {name:'auteur', index:'auteur',align: 'left', editable:false, width:120},
                        {name:'date', index:'date',align: 'center',  editable:false, width:80,formatter :'date', formatoptions : {srcformat : 'Y-m-d H:i:s',
                            newformat : 'Y-m-d '}, hidden:true},
                        {name:'titre', index:'titre', width:150,  editable:false},
                        {name:'contenu',  editable:false, width:120,edittype: "textarea", editoptions:{rows: "3", cols: "30",autocomplete:"off"}},
                        {name:'longitude', index:'url',align: 'left', editable:false, width:80},
                        {name:'latitude', index:'url',align: 'left', editable:false, width:80},
                        {
                            name: 'photo',
                            formatter: imageFormatter

                         }
                    ],
                    caption: 'Validation des notes (sélectionnez les notes à valider)',
                    addCaption:' ',
                    rowNum:20,
                    rowList:[20, 40],
                    pager: '#pager1',
                    sortname: 'id',

                    grouping:true, groupingView : { groupField : ['reseau','date','auteur'],
                        groupColumnShow : [false,false,false],
                        groupText : ['<b>{0} - {1} Lignes(s)</b>'], groupCollapse : false, groupOrder: ['asc','desc','asc'] },
                    viewrecords:true,

//                    grouping:true, groupingView : { groupField : ['reseau','auteur'], groupColumnShow : [false,false], groupText : ['<b>{0} - {1} Lignes(s)</b>'], groupCollapse : false, groupOrder: ['asc','asc'] },
//                    viewrecords:true,
                    scrollOffset: 0,
                    height:"100%",
//                    width:"900",
                    autowidth:true,
                    //     shrinkToFit: false,
                    multiselect:true,
                    sortorder:'desc'
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
                            url: "${createLink(controller:'noteMarche', action:'valider')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("La validation des notes a réussie", {
                            sticky: false,
                            header: 'Notification',
                            theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'noteMarche', action:'populate')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("La validation des notes a échoué, erreur rapportée "+errorThrown, {
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
                        alert("Vous devez sélectionner au moins une note ");
                    }else {
                        selectedlist.val(postData);
                        valider.attr('disabled', 'disabled');
                        var jqxhr =$.ajax({
                            type: "POST",
                            url: "${createLink(controller:'noteMarche', action:'rejeter')}",
                            data: {selectedList: JSON.stringify(postData)},
                            dataType:"text",
                            cache: false,
                            beforeSend: function( xhr ) {
                                xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
                            }
                        }).done(function() {
                            $.jGrowl("Le rejet des notes a réussi", {
                                sticky: false,
                                header: 'Notification',
                                theme: 'iphone'
                            });
                            valider.removeAttr("disabled");
                            jqGridPrice.setGridParam({url: "${createLink(controller:'noteMarche', action:'populate')}"}).trigger("reloadGrid");
                        }).fail(function( jqXHR, textStatus, errorThrown ) {
                            $.jGrowl("Le rejet des notes a échoué, erreur rapportée "+errorThrown, {
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
