

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

    <div class="span4">
        %{--<div class="well small">--}%
            %{--<ul class="nav nav-list">--}%
                %{--<li class="nav-header"><g:message code="title.s3Asset" /></li>--}%

                %{--<li>--}%
                    %{--<g:link class="create" action="create">--}%
                        %{-- <i class="glyphicon glyphicon-plus"></i>--}%
                        %{--<g:message code="create.s3Asset" />--}%
                    %{--</g:link>--}%
                %{--</li>--}%
            %{--</ul>--}%
        %{--</div>--}%
    </div>

    <div class="span6 offset-3">

        <div class="page-header">
            <h1><g:message code="list.s3Asset" /></h1>
        </div>

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <filterpane:filterButton text="Rechercher" />
        <table class="table ">
            <thead>
            <tr>

                <util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>

                %{--<util:remoteSortableColumn update="listContent" action="list" property="key" title="Key"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="status" title="Status"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

                <util:remoteSortableColumn update="listContent" action="list" property="description" title="Description"/>

                <util:remoteSortableColumn update="listContent" action="list" property="mimeType" title="Type document"/>

                <util:remoteSortableColumn update="listContent" action="list" property="length" title="${message(code: 's3Asset.length.label', default: 'Taille en ko')}" />

                %{--<util:remoteSortableColumn update="listContent" action="list" property="localPath" title="Local Path"/>--}%

            </tr>
            </thead>
            <tbody>
            <g:each in="${s3AssetList}" status="i" var="s3Asset">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="showConsulter" id="${s3AssetInstance.id}">${s3AssetInstance.title?.encodeAsHTML()}</g:link></td>

                    %{--<td>${s3Asset.key?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.status?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.title?.encodeAsHTML()}</td>--}%

                    <td>${s3AssetInstance.description?.encodeAsHTML()}</td>

                    <td>${s3AssetInstance.mimeType?.encodeAsHTML()}</td>

                    <td>${s3AssetInstance.length/1024}</td>


                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <util:remotePaginate update="listContent" action="list"  total="${S3Asset.count()}" params="${filterParams}" />

        </div>
        <export:formats formats="['csv', 'excel', 'pdf']" />
    </div>

</div>
<filterpane:filterPane dialog="true" domain="org.grails.s3.S3Asset" excludeProperties="bucket,mimeType,protocol,length,lastModified,percentTransferred,bytesPerSecond,bytesTransfered,options,hostName" />
%{--associatedProperties="section.nom, sousSection.nom,groupement.nom"  --}%
</body>

</html>
