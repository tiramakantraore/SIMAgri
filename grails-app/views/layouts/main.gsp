<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>

    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <asset:javascript src="application.js"/>
    <asset:javascript src="pivot_datatable.js"/>
    <asset:stylesheet src="application"/>


    <g:layoutHead/>

    <style>
body { padding-top: 70px;
    padding-bottom: 5px;
%{--background-image: url("${assetPath(src:"bodybg.jpg")}");--}%
%{--background-repeat: no-repeat;--}%
%{--background-attachment: fixed;--}%
%{--background-size:cover;--}%
}
    </style>
</head>

<body>
<g:render template="/layouts/mainHeader"/>
<g:render template="/layouts/menu"/>
%{--<div id="wrap">--}%
<div id="main" class="container clear-top">

    <div class="row">
        <div id="searchResults"> </div>
            <g:layoutBody/>
    </div>
</div>
%{--</div>--}%

<div class="row">
        <g:render template="/layouts/homeFooter"/>

</div>
</body>
</html>