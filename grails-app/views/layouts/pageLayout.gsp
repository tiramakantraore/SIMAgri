<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>

    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="viewport" content="initial-scale = 1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="description" content="Système d'Informations sur les Marchés de produits agricoles ">
    <meta name="author" content="BAMIG">

    <meta name="keywords" content="SIM, Système d'informations  de marché, Marchés, Agricole" />

    <meta name="robots" content="index,follow" />


    <meta name="document-classification" content="SIM,Agriculture, elevage">

    <meta name="rating" content="General">

    <meta name="MSSmartTagsPreventParsing" content="TRUE">

    <meta name="Copyright" content="SIM Agri">

    <meta name="developer" content="http://www.bamig.com">
    <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

    %{--<asset:stylesheet src="bootstrap.css"/>--}%
    %{--<asset:stylesheet src="bootstrap-theme.css"/>--}%
    %{--<asset:javascript src="bootstrap.js"/>--}%

    %{--<asset:stylesheet src="Image-gallery/bootstrap-image-gallery.css"/>--}%
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application"/>
    <asset:stylesheet src="custom"/>
    <ckeditor:resources/>
    %{--<asset:javascript src="Image-gallery/js/bootstrap-image-gallery.js"/>--}%
    %{--<asset:javascript src="Image-gallery/js/demo.js"/>--}%
    <g:layoutHead/>
    %{--<ga:trackPageview />--}%
    <style>
html {
    font-family: 'robotoregular',sans-serif;
}
/*.partnersLogo{*/
    /*display: none;*/
/*}*/
body {
    /*padding-top: 150px;*/
    /*padding-bottom: 5px;*/
    /*min-height:1300px ;*/
    %{--background-image: url("${assetPath(src:"bodybg.jpg")}");--}%
    %{--background-repeat: no-repeat;--}%
    %{--background-attachment: fixed;--}%
    %{--background-size:cover;--}%
}
/*.dropdown-menu { min-width: 550px }*/
    </style>
</head>

<body>

<div id="main" class="container">
    <g:render template="/layouts/mainHeader"/>
    <g:render template="/layouts/menu"  model="[activeMenu:'Accueil']"/>

    <div id="wrap">
        <div class="row no-top-padding">
            <div class="col-sm-3 col-md-3 no-left-padding">
            </div>
            <div class="col-sm-5 col-md-5">
            </div>
            <div class="col-sm-4 col-md-4">
            </div>
        </div>
        <g:layoutBody/>

    </div>
    <g:render template="/layouts/homeFooter"/>
</div>


%{--<div id="main" class="container">--}%
        %{--<g:render template="/layouts/mainHeader"/>--}%
        %{--<g:render template="/layouts/menu"/>--}%
        %{--<div id="wrap">--}%
        %{--<div class="row">--}%
        %{--<div class="col-sm-4 col-md-4 no-left-padding">--}%
        %{--</div>--}%
        %{--<div class="col-sm-4 col-md-4">--}%
            %{--<div id="searchResults"> </div>--}%
        %{--</div>--}%
        %{--<div class="col-sm-4 col-md-4">--}%
        %{--</div>--}%
       %{--</div>--}%
         %{--<g:layoutBody/>--}%

       %{--</div>--}%
        %{--<g:render template="/layouts/homeFooter"/>--}%
%{--</div>--}%

%{--<div class="row">--}%
    %{--<div class="col-sm-2 col-md-2"> </div>--}%
    %{--<div class="col-sm-9 col-md-9">--}%
        %{--<g:render template="/layouts/homeFooter"/>--}%

    %{--</div>--}%
    %{--<div class="col-sm-1 col-md-1"> </div>--}%
%{--</div>--}%

</body>
</html>