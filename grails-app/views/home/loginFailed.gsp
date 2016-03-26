<!doctype html>
<html>
<head>
    <meta name="layout" content="indexLayout"/>

    <title><g:message code="springSecurity.denied.title" /></title>

</head>
<body>

    <div class="row">
        <div class="col-sm-3 col-md-3">

        </div>
        <div class="col-sm-6 col-md-6">
            <g:if test="${flash.message}">
                <bootstrap:alert class="alert-warn"><g:message code="springSecurity.denied.message" /></bootstrap:alert>
            </g:if>
        </div>
        <div class="col-sm-3 col-md-3">

        </div>
    </div>

</body>

</html>
