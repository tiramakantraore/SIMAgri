<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
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

    %{--<meta name="viewport" content="initial-scale = 1.0">--}%
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

%{--<div id="wrap">--}%
    <div id="main" class="container">
            <g:render template="/layouts/mainHeader"/>
            <g:render template="/layouts/menu" model="[activeMenu:'Apps']"/>
                <div class="row byBodyContainer">
                        <div id="searchResults"> </div>
                        %{--<asset:javascript src="calendarAccueil.js"/>--}%
                        <g:layoutBody/>

                 </div>
    <g:render template="/layouts/homeFooter"/>
    </div>



    %{--</div>--}%
%{--</div>--}%

</body>
</html>