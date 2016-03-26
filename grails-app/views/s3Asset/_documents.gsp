
<div id="documents">
        <table  class="table ">
            <thead>
            <tr>

                <util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>

                %{--<util:remoteSortableColumn update="listContent" action="list" property="key" title="Key"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="status" title="Status"/>--}%

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

                <util:remoteSortableColumn update="listContent" action="list" property="mimeType" title="Type document"/>

                <util:remoteSortableColumn update="listContent" action="list" property="length" title="${message(code: 's3Asset.length.label', default: 'Taille en ko')}" />

                %{--<util:remoteSortableColumn update="listContent" action="list" property="localPath" title="Local Path"/>--}%

            </tr>
            </thead>
            <tbody>
            <g:each in="${s3AssetList}" status="i" var="s3Asset">
                <tr class='even'>

                    <td><g:link action="showConsulter" id="${s3Asset?.id}">${s3Asset?.title?.encodeAsHTML()}</g:link></td>

                    %{--<td>${s3Asset.key?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.status?.encodeAsHTML()}</td>--}%

                    %{--<td>${s3Asset.title?.encodeAsHTML()}</td>--}%


                    <td>${s3Asset?.mimeType?.encodeAsHTML()}</td>

                    <td>${s3Asset?.length?.toKo()}</td>

                    %{--<td>${s3Asset.localPath?.encodeAsHTML()}</td>--}%

                </tr>
            </g:each>
            </tbody>
        </table>
</div>



