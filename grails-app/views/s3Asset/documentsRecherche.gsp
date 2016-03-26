<%@ page import="simagri.S3Asset" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    %{--<meta http-equiv="refresh" content="2">--}%
    <meta name="layout" content="adminLayout">
    <title><g:message code="list.s3Asset" /></title>

</head>
<body>


<div class="row-fluid">
    <div class="span9 offset3">

        <div class="page-header">
            <h1><g:message code="list.s3Asset" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <filterpane:filterButton text="Rechercher" />
        <g:render template="documents"/>
        <export:formats formats="['csv', 'excel', 'pdf']" />
        <div class="pagination">
            <util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}"  action="filter"
                                 update="documents" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>

        </div>
    </div>


</div>
</body>

</html>
