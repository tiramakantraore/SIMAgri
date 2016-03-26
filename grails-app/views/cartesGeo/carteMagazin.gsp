<%--
  Created by IntelliJ IDEA.
  User: Tiramakan
  Date: 16/10/12
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="simagri.Magazin" contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
    <meta name="layout" content="accueilLayout">
</head>
<body>
<div class="row">
<div class="col-sm-4 col-md-4">
    <div class="col-sm-12 col-md-12" style="display:inline-block">

        <div class="contentbox" id="groupMagazins">
            <div class="row">
                <div class="col-sm-2 col-md-2">
                    <a id="check-allMarkets" href="javascript:void(0);">Tous les magazins</a>
                </div>
                <div class="col-sm-2 col-md-2 offset8">
                    <a id="uncheck-allMarkets" href="javascript:void(0);">Aucun magazin </a>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12 col-md-12">
                    <bill:checkBoxList referenceCollection="${mesMagazins}" containerClass="${ctnerTemplate}" instanceName="magazins" onClickCheck="marcheClick();"/>
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
<div class="col-sm-8 col-md-8">
    <div id="map"></div>

</div>
</div>


<script type="text/javascript">

    var groupMagazins=$('#groupMagazins');
    var magazins= $('#magazins');
    var magazinsIds= $('#magazinIds');
    function addMagazin(gmap,nom,titreContenu,Contenu,latitude,longitude) {
        var contenuMag=nom+"<br/><p> "+titreContenu+"</p> "+Contenu;
        gmap.addMarker({
            lat: latitude,
            lng: longitude,
            title: nom,
            infoWindow: {
                content: contenuMag
            }
        });

    }
    function buildMapDynamically(gmap){
        gmap.removeMarkers();
        $.getJSON("${createLink(controller:'cartesGeo', action:'getMagazinsForMap')}", {magazinsIds:magazinsIds.val()},
                function(data){
                    /** Declare options after success callback. */
                    if (!data.isEmpty) {

                        for (var magazin in data.magazins) {
                            // If there isn't one then add a new series.
                            addMagazin(gmap,data.magazins[magazin].nom,data.magazins[magazin].titrecontenu,
                                    data.magazins[magazin].contenu,data.magazins[magazin].latitude,data.magazins[magazin].longitude);

                        }

                    }


                }
        );
    }
    $(document).ready(function(){

    });

</script>
</body>
</html>