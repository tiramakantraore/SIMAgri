<!doctype html>
<html>
<head>
    <meta name="layout" content="adminLayout">

</head>
<body>
<div class="row">
    <div class="3">

    </div>
    <div class="col-sm-6 col-md-6">
        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-warn">${flash.message}</bootstrap:alert>
        </g:if>
    </div>
    <div class="3">

    </div>
</div>

</body>

</html>
