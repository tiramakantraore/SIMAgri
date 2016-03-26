<%@ page import="simagri.Offre" %>

<div id="offres" class="">
    <g:each var="offre" status="i" in="${offreList}">

    </g:each>

    <g:if test="${offreList?.size()==0}">

    <dd><p style="font-size:0.9em;color:black">Il n' y a pas d'offre en cours de validité</p>

    </dd>

    <br>
</g:if><table class="table ">
    <thead>
    <tr>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td><g:link controller="offre" action="showPub" id="${offre?.id}" target="_blank" rel="ligneOffre" data-original-title="${offre.toString()}"><strong>${offre?.titre}</strong></g:link>
        %{--<p style="font-size:1em;color:green">Produit: ${offre.produit}</p>--}%
            %{--<p><prettytime:display date="${offre?.date}" /></p>--}%
        </td>
    </tr>






    </tbody>
</table>

</div>