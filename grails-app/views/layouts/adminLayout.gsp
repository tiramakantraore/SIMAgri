<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>

    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="viewport" content="initial-scale = 1.0">
    <meta name="description" content="Système d'Informations sur les Marchés de produits agricoles ">
    <meta name="author" content="BAMIG">

    <meta name="keywords" content="SIM, Système d'informations  de marché, Marchés, Agricole" />

    <meta name="robots" content="index,follow" />


    <meta name="document-classification" content="SIM,Agriculture, elevage">

    <meta name="rating" content="General">

    <meta name="MSSmartTagsPreventParsing" content="TRUE">

    <meta name="Copyright" content="SIM Agri">
    <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

    <meta name="developer" content="http://www.bamig.com">
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application"/>
    <asset:stylesheet src="custom"/>
    <g:layoutHead/>
    %{--<ga:trackPageview />--}%
    <style>
html {
    font-family: 'robotoregular',sans-serif;
}
    body {
        /*padding-top: 70px; */
    }
    .partnersLogo{
        display: none;
    }
    .even{
        background-color:#f5f5f5 ;
    }
    </style>
</head>

<body>

<div id="wrap">
    <div id="main" class="container">
        <g:render template="/layouts/mainHeader"/>
        <g:render template="/layouts/menu" model="[activeMenu:'Admin']"/>
        <div class="row collapsible ">
            <div  class="col-sm-3 col-md-3 byBodyContainer">
                <span class="myheader">Reduire(-)</span>
            </div>
        </div>
        <br>

        %{--<div class="row byBodyContainer">--}%

            %{--<div class="col-sm-12 col-md-12" id="myfluidbody">--}%
            %{--<div id="searchResults"> </div>--}%
             %{--<g:layoutBody/>--}%
            %{--</div>--}%
        %{--</div>--}%
        <div class="row">
            <div class="col-sm-12 col-md-12 byBodyContainer" id="myfluidbody">
                <div id="searchResults"> </div>
                <g:layoutBody/>
            </div>
        </div>

        <g:render template="/layouts/homeFooter"/>
    </div>
</div>

</body>
</html>