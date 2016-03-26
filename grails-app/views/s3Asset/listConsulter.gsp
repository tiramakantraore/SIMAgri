<%@ page import="simagri.S3Asset" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    %{--<meta http-equiv="refresh" content="2">--}%
    <meta name="layout" content="adminLayout">
    <title><g:message code="list.s3Asset" /></title>

</head>
<body>
%{--<div class="nav">--}%
    %{--<span class="menuButton"><a class="home" href="${resource(dir: '')}">Home</a></span>--}%
    %{--<span class="menuButton"><g:link class="create" action="create">Upload New Asset</g:link></span>--}%
%{--</div>--}%

<div class="row">
    <div class="col-sm-3 col-md-3">
    </div>
    <div class="col-sm-6 col-md-6">

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
            <util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}"  action="remoteFilter"
                                 update="documents" max="5" alwaysShowPageSizes="true" pageSizes="[5, 10,15,20,100]"/>

        </div>
    </div>
    <div class="col-sm-3 col-md-3">
    </div>
    %{--<div id="documents" class="span9 offset3">--}%

        %{--<div class="page-header">--}%
            %{--<h1><g:message code="list.s3Asset" /></h1>--}%
        %{--</div>--}%

        %{--<g:if test="${flash.message}">--}%
            %{--<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>--}%
        %{--</g:if>--}%
        %{--<filterpane:filterButton text="Rechercher" />--}%
        %{--<table class="table ">--}%
            %{--<thead>--}%
            %{--<tr>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="key" title="Key"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="status" title="Status"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="description" title="Description"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="mimeType" title="Type document"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="length" title="${message(code: 's3Asset.length.label', default: 'Taille en ko')}" />--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="localPath" title="Local Path"/>--}%

            %{--</tr>--}%
            %{--</thead>--}%
            %{--<tbody>--}%
            %{--<g:each in="${s3AssetList}" status="i" var="s3Asset">--}%
                %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">--}%

                    %{--<td><g:link action="showConsulter" id="${s3Asset?.id}">${s3Asset?.title?.encodeAsHTML()}</g:link></td>--}%

                    %{--<td>${s3Asset.key?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.status?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.title?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.description?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.mimeType?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.length/1024}</td>--}%

                    %{--<td>${s3Asset.localPath?.encodeAsHTML()}</td>--}%

                %{--</tr>--}%
            %{--</g:each>--}%
            %{--</tbody>--}%
        %{--</table>--}%
        %{--<div class="pagination">--}%
            %{--<util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}"--}%
                                 %{--update="documents" max="5"  pageSizes="[5, 10,15,20,100]"/>--}%
            %{--<util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}" params="${filterParams}" />--}%

        %{--</div>--}%
        %{--<export:formats formats="['csv', 'excel', 'pdf']" />--}%
    %{--</div>--}%

</div>
<filterpane:filterPane dialog="true" domain="simagri.S3Asset" excludeProperties="bucket,mimeType,protocol,length,lastModified,percentTransferred,bytesPerSecond,bytesTransfered,options,hostName" />
%{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  --}%
</body>

</html>
