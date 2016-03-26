<!DOCTYPE html>
<html>
<head>
   <script type="text/javascript"
  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDLdkKo7gZLZVa7FJwpWBEOucW-wSpasoc&libraries=geometry,places,weather&sensor=false">  </script>
    <script type="text/javascript">


        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(12.238333,-1.561593),
                zoom: 7,
                backgroundColor: '#ddd',
                mapTypeId: google.maps.MapTypeId.ROADMAP
                ,
                mapTypeControl: true,
                mapTypeControlOptions: {
                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
                    position: google.maps.ControlPosition.BOTTOM_RIGHT,
                    mapTypeIds: [google.maps.MapTypeId.ROADMAP,
                        google.maps.MapTypeId.HYBRID,
                        google.maps.MapTypeId.SATELLITE,
                        google.maps.MapTypeId.TERRAIN]
                }
                ,
                navigationControl: true

            };
            var map = new google.maps.Map(document.getElementById("map"),
                    mapOptions);

            var markersArray = [];
            <g:each in="${magazinList}" status="i" var="magazin">

            var point${magazin.id} = new google.maps.LatLng(${magazin.localite?.latitude?:0}, ${magazin.localite?.longitude?:0});
            var titremagazin="${magazin.nom}";

            var marker${magazin.id}= new google.maps.Marker({
                // Coordonnées du cinéma
                position: point${magazin.id},
                map: map,
                animation: google.maps.Animation.DROP,
                title: "Magazin : "+titremagazin
            });

            marker${magazin.id}.infowindow = new google.maps.InfoWindow({
                content: "${magazin.nom}<br/><p> ${magazin.titrecontenu}</p> ${magazin.contenu}"
            });

            google.maps.event.addListener(marker${magazin.id}, 'click', function() {
                marker${magazin.id}.infowindow.open(map, marker${magazin.id});
                setTimeout(function () { marker${magazin.id}.infowindow.close(); }, 50000);

            });
            </g:each>



        }
        function addMarker(location) {
            marker = new google.maps.Marker({
                position: location,
                map: map
            });
            markersArray.push(marker);
        }

        function detectBrowser() {
            var useragent = navigator.userAgent;
            var mapdiv = document.getElementById("map");
            if (useragent.indexOf('iPhone') != -1 || useragent.indexOf('Android') != -1)
            {
                mapdiv.style.width = '100%';
                myOptions = {
                    navigationControlOptions : {
                        style : google.maps.NavigationControlStyle.ANDROID
                    },
                    mapTypeControlOptions : {
                        style : google.maps.MapTypeControlStyle.DROPDOWN_MENU
                    }
                };
                map.setOptions(myOptions);
            } else {
                mapdiv.style.width = '100%';
            }
        }
    </script>

    

</head>
<body onload="initialize()">
  <div id="map" style="width:100%; height:100%">

  </div>
</body>
</html>