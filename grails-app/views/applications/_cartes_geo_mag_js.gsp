<script type="text/javascript">


    var
            childCount = 0, index = 0, indexQCM= 0,quizzchildCount= 0,QCMchildCount = 0;
    var latitude=parseFloat("${latitude}"), longitude=parseFloat("${longitude}"), zoom=parseInt("${zoom}");
   var gmapMagazin;



    function tabselector(tabname){
        return  'a[href=\"'+'#'+tabname+'\"]';

    }

    function buildMapDynamically(gmap,lookupIds,isForMarket){
        gmap.removeMarkers();

        var markers = [];


        $.getJSON("${createLink(controller:'cartesGeo', action:'getDataForMap')}", {lookupIds:JSON.stringify(stringToIntList(lookupIds)),isForMarket:false},
                function(data){
                    /** Declare options after success callback. */
                    if (!data.isEmpty) {
                        for (var item in data.data) {
                            markers.push({
                                lat: data.data[item].latitude,
                                lng: data.data[item].longitude,
                                title:data.data[item].nom,
                                infoWindow: {
                                    content: data.data[item].contenu,
                                    minWidth: 400,
                                    maxWidth: 500
                                }
                            });
                        }
                        gmap.addMarkers(markers);

                    }


                }
        );
    }


    function magazinClick(){

        var selected = [];
        $('#listeMagazins').find('input:checked').each(function() {
            selected.push($(this).attr('value'));
        });
        buildMapDynamically(gmapMagazin,selected,false);
    }

    function scriptCartesGeo(){

        var valider=$(".maj_carte");

        valider.click(function(event) {
            event.stopPropagation();
        });




        $('#uncheck-allMagazins').click(function(){
            $("input[type='checkbox'][name^='magazins_']").prop('checked', false);
            buildMapDynamically(gmapMagazin,[],false);
        });
        $('#check-allMagazins').click(function(){
            $("input[type='checkbox'][name^='magazins_']").prop('checked', true);
            var selected = [];
            $('#groupMagazins').find('input:checked').each(function() {
                selected.push($(this).attr('value'));
            });
            buildMapDynamically(gmapMagazin,selected,true);
        });
        gmapMagazin=initialize("gmapMagazin");
        $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        mapMagazin=$('#gmapMagazin');

                    mapMagazin.css('left', 0);
                    mapMagazin.css('height', 500);
                    mapMagazin.css('width', $('#mapMagazinsContainer').width());
                    google.maps.event.trigger(gmapMagazin.map, 'resize');
                    var latLongShit = new google.maps.LatLng(latitude, longitude);
                    gmapMagazin.map.setCenter(latLongShit);


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
            scrollwheel: false,
            markerZoomAnimation: false,
            panControl : true
        });
    }

    $(document).ready(function() {

        scriptCartesGeo();


    });
</script>