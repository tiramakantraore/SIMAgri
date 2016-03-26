<%@ page import="simagri.S3Asset" %>
<div class="row">


    <div class="col-sm-8 col-md-8">
        <div class="page-header">
            <h1><g:message code="list.s3Asset" /></h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-12 col-md-12">

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>
        <filterpane:filterButton text="Rechercher" />
        <table class="table ">
            <thead>
            <tr>
                <th></th>
                <util:remoteSortableColumn update="listContent" action="list" property="title" title="Titre"/>

                <util:remoteSortableColumn update="listContent" action="list" property="mimeType" title="Type document"/>

                <util:remoteSortableColumn update="listContent" action="list" property="length" title="${message(code: 's3Asset.length.label', default: 'Taille en ko')}" />

                %{--<util:remoteSortableColumn update="listContent" action="list" property="localPath" title="Local Path"/>--}%

            </tr>
            </thead>
            <tbody>
            <g:each in="${s3AssetList}" status="i" var="s3AssetInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td class="link">
                        <g:remoteLink onLoading="showSpinner();" onComplete="hideSpinner()" action="edit" update="listContent" method="GET"
                                      id="${s3AssetInstance.id}" class="btn-flat  btn-small">
                            Modifier&raquo;
                        </g:remoteLink>
                    </td>
                    <td>${fieldValue(bean: s3AssetInstance, field: "title")}</td>

                    <td>${s3AssetInstance.mimeType?.encodeAsHTML()}</td>

                    <td>${s3AssetInstance.length.toKo()}</td>

                    %{--<td>${s3Asset.localPath?.encodeAsHTML()}</td>--}%

                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <util:remotePaginate update="listContent" action="list"  params="${filterParams}" total="${S3Asset.count()}" />
        </div>

        <export:formats formats="['csv', 'excel', 'pdf']" />
    </div>

</div>
<filterpane:filterPane dialog="true" domain="S3Asset" excludeProperties="bucket,mimeType,localPath,localUrl,key,protocol,length,lastModified,percentTransferred,bytesPerSecond,bytesTransfered,options,hostName" />
