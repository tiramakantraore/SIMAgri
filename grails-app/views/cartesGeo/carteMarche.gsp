<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="simagri.Marche" %>
<html>
<head>
    <meta name="layout" content="accueilLayout">

<style>
#gmap img {
    max-width: none;
    vertical-align: baseline;
}
</style>
</head>

<body>
<div class="row">

    <div class="col-sm-12 col-md-12" id="groupMarches" >

        <div class="contentbox" id="IdMarches">
            <div class="row">
                <div class="col-sm-2 col-md-2">
                    <a id="check-allMarkets" href="javascript:void(0);">Tous les marchés</a>
                </div>
                <div class="col-sm-2 col-md-2 offset8">
                    <a id="uncheck-allMarkets" href="javascript:void(0);">Aucun marché </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12" id="listeMarches">
                    <bill:checkBoxList referenceCollection="${mesMarches}" containerClass="${ctnerTemplate}" instanceName="markets" onClickCheck="marcheClick();"/>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <div class="btn-flat  btn-link">Appliquer</div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="row" >
       <div class="col-sm-12 col-md-12 ">
            <div id="gmap"></div>
        </div>
</div>
<script type="text/javascript">
    %{--var submitBtn=$('#submit");--}%

    %{--var marketItems=$("input[type='checkbox'][name^='markets_']");--}%
    %{--var previousSizeOfChecked= 0,--}%
            %{--idResaux=$('#groupsId");--}%

    %{--var markets=$('#markets");--}%
    %{--var dateLimite=new Date();--}%
    %{--var options={format:'dd/mm/yyyy',weekStart:0};--}%

    %{--function marcheClick(){--}%

        %{--var selected = [];--}%
        %{--$('#listeMarches').find('input:checked').each(function() {--}%
            %{--selected.push($(this).attr('value'));--}%
        %{--});--}%
        %{--buildMapDynamically(gmap,selected);--}%
            %{--alert("liste des marchés sélectionnés "+selected);--}%


    %{--}--}%
    %{--function addMarket(gmap,nomMarche,titreContenu,Contenu,latitude,longitude) {--}%
        %{--var contenuMarche=nomMarche+"<br/><p> "+titreContenu+"</p> "+Contenu;--}%
        %{--gmap.addMarker({--}%
            %{--lat: latitude,--}%
            %{--lng: longitude,--}%
            %{--title: nomMarche,--}%
            %{--infoWindow: {--}%
                %{--content: contenuMarche--}%
            %{--}--}%
        %{--});--}%

    %{--}--}%
    %{--function buildMapDynamically(gmap,marketIds){--}%
        %{--gmap.removeMarkers();--}%
        %{--$.getJSON("${createLink(controller:'cartesGeo', action:'getMarketsForMap')}", {marketIds:marketIds},--}%
                %{--function(data){--}%
                    %{--/** Declare options after success callback. */--}%
                    %{--if (!data.isEmpty) {--}%

                        %{--for (var marche in data.marches) {--}%
                            %{--// If there isn't one then add a new series.--}%
                            %{--addMarket(gmap,data.marches[marche].nom,data.marches[marche].titrecontenu,--}%
                                    %{--data.marches[marche].contenu,data.marches[marche].latitude,data.marches[marche].longitude);--}%

                        %{--}--}%

                   %{--}--}%


                %{--}--}%
        %{--);--}%
    %{--}--}%
    function initialize() {
        var gmap=new GMaps({
            div: '#gmap',
            lat:12.238333,
            lng: -1.561593,
            zoom: 7,
            zoomControl : false,
            zoomControlOpt: {
                style : 'SMALL',
                position: 'TOP_LEFT'
            },
            panControl : false
        });

    }
    $(document).ready(function(){
        initialize();
        $(window).bind("load resize", function(){
            var h = $(window).height(),
                    offsetTop = 70;
            $('#gmap').css('height', (h - offsetTop));
        });
        $('#uncheck-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', false);
        });
        $('#check-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', true);
        });
    });

</script>
</body>

</html>