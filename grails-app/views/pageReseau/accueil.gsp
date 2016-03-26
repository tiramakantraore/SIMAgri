<%@ page import="simagri.Reseau" %>
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
<div id="simagriIndex">

</div>

<script type="text/javascript">
    $(document).ready(function() {
               if ($('[data-render-menu="tableauBord"]').hasClass('active')){
                   $('#tableauDeBord').click();
               }


                $(".nav li").on("click", function () {
                    $(".nav li").removeClass("active");
                    $(this).addClass("active");
                });
            }
    );
</script>


</body>
</html>