<script type="text/javascript">


    var
            childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
    var latitude=parseFloat("${latitude}"), longitude=parseFloat("${longitude}"), zoom=parseInt("${zoom}");
   var gmapMarche,gmapMagazin;



    function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }

    function buildMapDynamically(gmap,lookupIds,isForMarket){
        gmap.removeMarkers();

        var markers = [];


        $.getJSON("${createLink(controller:'cartesGeo', action:'getDataForMap')}", {lookupIds:JSON.stringify(stringToIntList(lookupIds)),isForMarket:isForMarket},
                function(data){
                    /** Declare options after success callback. */
                    if (!data.isEmpty) {
                        for (var item in data.data) {
                            markers.push({
                                lat: data.data[item].latitude,
                                lng: data.data[item].longitude,
                                title:data.data[item].nom,
                                infoWindow: {
                                    content: data.data[item].contenu
                                }
                            });
                        }
                        gmap.addMarkers(markers);

                    }


                }
        );
    }

    function marcheClick(){

        var selected = [];
        $('#listeMarches').find('input:checked').each(function() {
            selected.push($(this).attr('value'));
        });
        buildMapDynamically(gmapMarche,selected,true);
    }


    function scriptCartesGeo(){

        var marketItems=$("input[type='checkbox'][name^='markets_']");

        var markets=$('#markets');
        var valider=$(".maj_carte");

        valider.click(function(event) {
            event.stopPropagation();
        });



        $('#uncheck-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', false);

            buildMapDynamically(gmapMarche,[],true);
        });
        $('#check-allMarkets').click(function(){
            $("input[type='checkbox'][name^='markets_']").prop('checked', true);
            var selected = [];
            $('#listeMarches').find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
            buildMapDynamically(gmapMarche,selected,true);
        });


        gmapMarche=initialize("gmapMarche");
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        var mapMarche=$('#gmapMarche');
                mapMarche.css('left', 0);
                mapMarche.css('height', 500);
                mapMarche.css('width', $('#groupMarches').width());
                google.maps.event.trigger(gmapMarche.map, 'resize');
                var latLongShit = new google.maps.LatLng(latitude, longitude);
                gmapMarche.map.setCenter(latLongShit);


        });
    }

    function initialize(div) {
        return new GMaps({
            div: div,
            lat:latitude,
            lng: longitude,
            zoom: zoom,                       // set the zoom level manually
            zoomControl: true,
            scaleControl: false,
            scrollwheel: true,
//            zoomControl : true,
//            zoomControlOpt: {
//                style : 'SMALL',
//                position: 'TOP_LEFT'
//            },
            panControl : true
        });
    }

    $(document).ready(function() {

        scriptCartesGeo();


    });
</script>