<!doctype html>
<html id="simagriIndexHtml">
<head>

    <meta name="layout" content="indexLayout"/>

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
<div id="simagriIndex">
<g:render template="accueil"/>
</div>
<script type="text/javascript">
    $(document).ready(function() {
                $(".nav li").on("click", function () {
                    $(".nav li").removeClass("active");
                    $(this).addClass("active");
                });
                openMenuOnHover();
            }
    );
</script>

</body>

</html>