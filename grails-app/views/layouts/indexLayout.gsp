<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>

    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale = 1.0">
    <meta name="description" content="Système d'Informations sur les Marchés de produits agricoles ">
    <meta name="author" content="BAMIG">

    <meta name="keywords" content="SIM, Système d'informations  de marché, Marchés, Agricole" />

    <meta name="robots" content="index,follow" />


    <meta name="document-classification" content="SIM,Marchés,Prix, Offres,Agriculture, élevage">

    <meta name="rating" content="General">

    <meta name="MSSmartTagsPreventParsing" content="TRUE">

    <meta name="Copyright" content="SIMAgri">

    <meta name="developer" content="http://www.bamig.com">
    %{--<meta name="viewport" content="initial-scale = 1.0">--}%
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <asset:javascript src="application.js"/>
    %{--<asset:javascript src="mediaelements/mediaelement-and-player.js"/>--}%
    %{--<asset:javascript src="fp.js"/>--}%
    <asset:stylesheet src="application.css"/>
    %{--<asset:stylesheet src="fp.css"/>--}%
    <asset:stylesheet src="custom.css"/>
    <asset:stylesheet src="mediaelementplayer2.css"/>

    <ckeditor:resources/>
    <g:layoutHead/>
    %{--<ga:trackPageview />--}%
    <style>
    html {
        font-family: 'robotoregular', sans-serif;
    }
    body {
        /*padding-top: 70px;*/
        padding-bottom: 5px;
        /*min-height:2000px ;*/

        /*background-image: -webkit-linear-gradient(top, #ffe592 0%, #f5ebe1 100%);*/
        /*background-image:      -o-linear-gradient(top, #ffe592 0%, #f5ebe1 100%);*/
        /*background-image: -webkit-gradient(linear, left top, left bottom, from(#ffe592), to(#f5ebe1));*/
        /*background-image:         linear-gradient(to bottom, #ffe592 0%, #f5ebe1 100%);*/
        /*filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffe592', endColorstr='#f5ebe1', GradientType=0);*/
        /*background-repeat: repeat-x;*/
        /*border-color: #ffe592;*/
        /*-webkit-box-shadow: inset 0 1px 3px rgba(232, 208, 131, 0.05), 0 1px 0 rgba(255, 229, 146, 0.10);*/
        /*box-shadow: inset 0 1px 3px rgba(232, 208, 131, 0.05), 0 1px 0 rgba(255, 255, 255, 0.10);*/
    }

    </style>
</head>

<body>
    <div id="main" class="container">
        <g:render template="/layouts/mainHeader"/>
        <g:render template="/layouts/menu"  model="[activeMenu:'Accueil']"/>

    %{--<div id="wrap">--}%
                %{--<div class="row no-top-padding">--}%
                %{--<div class="col-sm-3 col-md-3 no-left-padding">--}%
                %{--</div>--}%
                %{--<div class="col-sm-5 col-md-5">--}%
                    %{--<div id="searchResults"> </div>--}%
                %{--</div>--}%
                %{--<div class="col-sm-4 col-md-4">--}%
                %{--</div>--}%
               %{--</div>--}%
        %{--<div class="byBodyContainer">--}%
        %{--<asset:javascript src="calendarAccueil.js"/>--}%
        <div id="body">
        <g:render template="/layouts/spinnerPage"/>
         <g:layoutBody/>

         </div>
        %{--</div>--}%

        %{--</div>--}%
        <g:render template="/layouts/homeFooter"/>
    </div>


%{--<div class="row">--}%
    %{--<div class="col-sm-2 col-md-2"> </div>--}%
    %{--<div class="col-sm-9 col-md-9">--}%


    %{--</div>--}%
    %{--<div class="col-sm-1 col-md-1"> </div>--}%
%{--</div>--}%

</body>
</html>