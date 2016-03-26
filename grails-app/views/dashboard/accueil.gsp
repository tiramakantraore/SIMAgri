<%@ page import="simagri.Reseau" %>
<!doctype html>
<html id="simagriIndexHtml">
<head>
    <meta name="layout" content="accueilLayout"/>

    <title>Bienvenue sur SIMAgri</title>


    <style>
    .carousel-inner > .item > img,
    .carousel-inner > .item > a > img {
        display: block;
        max-width: 100%;
        height: 300px;
    }

    .bs-example{
        margin: 20px;
    }
    .box{
        padding: 10px;
        display: none;
        margin-top: 10px;
        background-color: #f9e9c9;
        /*border: 1px solid #eddba7;*/
    }
    .alignRight { text-align: center; }
    .alignLeft { text-align: left; }
    .alignCenter { text-align: center; }
    .morePeriodeOpts{
        background: #ffecb4;
    }
    .panel-heading {
        padding-top: 5px;
        padding-right: 15px;
        padding-left: 15px;
        padding-bottom: 5px;
        border-bottom: 1px solid transparent;
        border-top-right-radius: 0;
        border-top-left-radius: 0;
    }
    .panel-heading {
        cursor: pointer;
    }


    .thecontainer{
        /*display: none;*/
        /*padding : 5px;*/
        /*height:100%;*/
        /*width:100%;*/
    }


    </style>
    <asset:javascript src="pivot_datatable.js"/>
</head>
<body class="no-left-padding no-right-padding">
<g:render template="dashboard"/>
%{--<div id="simagriIndex">--}%

%{--</div>--}%

%{--<script type="text/javascript">--}%
    %{--$(document).ready(function() {--}%
               %{--if ($('[data-render-menu="tableauBord"]').hasClass('active')){--}%
                   %{--$('#tableauDeBord').click();--}%
               %{--}--}%


                %{--$(".nav li").on("click", function () {--}%
                    %{--$(".nav li").removeClass("active");--}%
                    %{--$(this).addClass("active");--}%
                %{--});--}%
            %{--}--}%
    %{--);--}%
%{--</script>--}%


</body>
</html>