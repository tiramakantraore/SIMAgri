<%@ page import="simagri.Reseau" %>
<!doctype html>
<html>
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
    %{--<asset:javascript src="jquery.dataTables"/>--}%
    %{--<asset:javascript src="jquery_pivot.js"/>--}%
    %{--<asset:javascript src="pivot_datatable.js"/>--}%

</head>
<body class="no-left-padding no-right-padding">
    <g:render template="dashboard"/>
</body>
</html>