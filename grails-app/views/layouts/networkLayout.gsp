<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="fr">
<head>

    <title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
    <meta name="viewport" content="initial-scale = 1.0">
    <meta charset="utf-8">
    <meta name="description" content="Système d'Informations sur les Marchés de produits agricoles ">
    <meta name="author" content="BAMIG">

    <meta name="keywords" content="SIM, Système d'informations  de marché, Marchés, Agricole" />

    <meta name="robots" content="index,follow" />


    <meta name="document-classification" content="SIM,Agriculture, elevage">

    <meta name="rating" content="General">

    <meta name="MSSmartTagsPreventParsing" content="TRUE">

    <meta name="Copyright" content="SIM Agri">

    <meta name="developer" content="http://www.bamig.com">
    <!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
    <!--[if lt IE 9]>
			<script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
    <link href='http://fonts.googleapis.com/css?family=Simonetta:400,900' rel='stylesheet' type='text/css'>


    %{--<asset:stylesheet src="bootstrap.css"/>--}%
    %{--<asset:stylesheet src="bootstrap-theme.css"/>--}%
    %{--<asset:javascript src="bootstrap.js"/>--}%
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application"/>
    <asset:stylesheet src="custom"/>
<style>
html {
    font-family: 'robotoregular',sans-serif;
}
</style>
</head>

<body>
<g:render template="/layouts/mainHeader"/>
<g:render template="/layouts/menu"/>
<div id="wrap">
    <div id="main" class="container-fluid clear-top">
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <g:layoutBody/>
            </div>
        </div>
    </div>
</div>
<g:render template="/layouts/homeFooter"/>
</body>
</html>