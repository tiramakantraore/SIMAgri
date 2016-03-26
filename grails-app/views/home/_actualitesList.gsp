<div id="infos">
        <table class="table">
            <thead>
            <tr>

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

            </tr>
            </thead>
            <tbody>
            <g:each in="${lesInfos}" status="i" var="info">
                <tr>

                    <td>
                       <g:render template="/info/infostemplate" model="[infoInstance:info]"/>

                    </td>


                </tr>
            </g:each>
            </tbody>
        </table>

</div>





