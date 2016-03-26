<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>

    <meta name="description" content="Système d'Informations sur les Marchés de produits agricoles ">
    <meta name="author" content="BAMIG">
    <meta charset="utf-8">
    <meta name="keywords" content="SIM, Système d'informations  de marché, Marchés, Agricole" />

    <meta name="robots" content="index,follow" />


    <meta name="document-classification" content="SIM,Agriculture, elevage">

    <meta name="rating" content="General">

    <meta name="MSSmartTagsPreventParsing" content="TRUE">

    <meta name="Copyright" content="SIMAgri">
    <meta name="developer" content="TRAORE JOEL HYACINTHE">
    <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">

    %{--<meta name="viewport" content="initial-scale = 1.0">--}%
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <asset:javascript src="application.js"/>
    %{--<asset:javascript src="mediaelements/mediaelement-and-player.js"/>--}%
    %{--<asset:javascript src="mediaelement-and-player.js"/>--}%

    <asset:javascript src="fp.js"/>
    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="fp.css"/>
    <asset:stylesheet src="custom.css"/>
    <asset:stylesheet src="mediaelementplayer2.css"/>
    <ckeditor:resources/>
    <g:layoutHead/>

    <style>
html {
    font-family: 'robotoregular',sans-serif;
}
    body {
        /*padding-top: 70px;*/
        padding-bottom: 5px;
        /*min-height:2000px ;*/

    }

</style>
%{--<ga:trackPageview />--}%
</head>

<body>

%{--<div id="wrap">--}%
    <div id="main" class="container">
            <g:render template="/layouts/mainHeader"/>
            %{--<g:render template="/layouts/topMenu"/>--}%
           <div id="body">
           <g:render template="/layouts/menu"/>
             <div id="bodyProfile">
                 <g:render template="/layouts/spinnerPage"/>

                 <g:layoutBody/>
            </div>
            </div>

        <g:render template="/layouts/homeFooter"/>


    </div>

</body>
</html>