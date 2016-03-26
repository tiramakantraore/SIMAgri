<!doctype html>
<html id="simagriIndexHtml">
<head>
    <meta name="layout" content="accueilLayout"/>

    <title>Recherche</title>
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
<div class="row" >

    <div class="col-sm-10 col-md-10 ">
        <g:if test="$topNDocuments">
            <h1> Documents</h1>
            <g:render template="documents" model="['topNDocuments':topNDocuments]"/>
        </g:if>
        <g:if test="$topNInfos">
            <h1> Informations</h1>
            <g:render template="infos" model="['topNInfos':topNInfos]"/>
        </g:if>
    </div>
</div>
<script type="text/javascript">
var menuAccueilLogged=$('#accueilLogged'),menuAccueilNotLogged=$('#accueilNotLogged'),
        updateSelector=$('#simagriIndex'),urlPath;


    $(document).ready(function() {

//                if (menuAccueilLogged.exists()){
//                    menuAccueilLogged.click();
//                }else {
//                    if (menuAccueilNotLogged.exists()){
//                        menuAccueilNotLogged.click();
//                    }
//                }


                openMenuOnHover();
            }
    );
</script>

</body>

</html>