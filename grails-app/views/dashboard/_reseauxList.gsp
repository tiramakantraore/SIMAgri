
%{--<div id="reseauxList">--}%
        %{--<table class="table ">--}%
            %{--<thead>--}%
             %{--<tr>--}%

             %{--</tr>--}%
            %{--</thead>--}%
            %{--<tbody>--}%
            %{--<g:each in="${reseauxList}" status="i" var="reseau">--}%
                %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">--}%

                    %{--<td><g:link controller="networkPage" action="accueil" id="${reseau?.id}"  target="_blank" >--}%
                        %{--<strong>${reseau?.nom?.encodeAsHTML()}</strong></g:link>--}%
                    %{--</td>--}%


                %{--</tr>--}%
            %{--</g:each>--}%
            %{--</tbody>--}%
        %{--</table>--}%

%{--</div>--}%





