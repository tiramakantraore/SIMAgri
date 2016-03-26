<!doctype html>
<html id="simagriIndexHtml">
<head>
    <meta name="layout" content="accueilLayout"/>

    <title>Bienvenue sur SIMAgri</title>
<style>
.bs-example{
    margin: 20px;
}
.carousel-inner > .item > img,
.carousel-inner > .item > a > img {
    display: block;
    max-width: 100%;
    height: 300px;
}
</style>
</head>
<body class="no-left-padding no-right-padding">
%{--<g:render template="pjaxHeader" />--}%
<div id="simagriIndex">

</div>

<script type="text/javascript">
var menuAccueilLogged=$('#accueilLogged'),menuAccueilNotLogged=$('#accueilNotLogged'),
        updateSelector=$('#simagriIndex'),urlPath;


    $(document).ready(function() {
                if (menuAccueilLogged.exists()){
                    menuAccueilLogged.click();
                }else {
                    if (menuAccueilNotLogged.exists()){
                        menuAccueilNotLogged.click();
                    }
                }


                openMenuOnHover();
            }
    );
</script>

</body>

</html>