<g:if test="${lesNotes}">
        <table class="table">
            <thead>
            <tr>

                %{--<util:remoteSortableColumn update="listContent" action="list" property="title" title="Title"/>--}%

            </tr>
            </thead>
            <tbody>

            <g:each in="${lesNotes}" status="i" var="note">
                <tr>

                    <td>
                       <g:render template="/noteMarche/notetemplate" model="[noteMarcheInstance:note]"/>

                    </td>


                </tr>
            </g:each>
            </tbody>
        </table>

</g:if>




