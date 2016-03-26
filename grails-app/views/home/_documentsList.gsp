<%@ page import="simagri.S3Asset" %>
<div id="documents">
        <table class="table ">
            <thead>
            <tr>

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

            </tr>
            </thead>
            <tbody>
            <g:each in="${documentList}" status="i" var="s3Asset">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link controller="s3Asset" action="showConsulter" id="${s3Asset?.id}"><strong>${s3Asset?.title?.encodeAsHTML()}</strong></g:link>


                    </td>


                </tr>
            </g:each>
            </tbody>
        </table>

</div>





