<%@ page import="simagri.Offre" %>
<div class="contentbox">
    %{--<div class="title">Les Offres actives </div>--}%
    %{--<br>--}%
    <div class="contentOffres-offre">
          <div id="wrapper">
                    <ul id="vertical-ticker-offre">
                        <g:each var="offre" in="${Offre.list()}">
                            ${offre}
                            %{--<li> Offre : ${offre}  </li>--}%
                            %{--<li><g:link controller="offre" action="showPub" id="${offre?.id}" rel="ligneOffre" data-original-title="${offre.toString()}" target="_blank"><strong>${offre.titre}</strong></g:link>--}%
                                %{--<p style="font-size:0.8em;color:green">Produit: ${offre.produit}</p>--}%
                                %{--<p><prettytime:display date="${offre.date}" /></p>--}%
                            %{--</li>--}%

                            %{--<br>--}%
                        </g:each>
                        <g:if test="${topNOffres?.size()==0}">

                            <li><p style="font-size:0.9em;color:black">Il n' y a pas d'offre en cours de validité</p>

                            </li>

                            <br>
                        </g:if>

                    </ul>
          </div>
    </div>
    <div class="row">
        <div class="col-sm-2 col-md-2">
        </div>
        <div class="col-sm-8 col-md-8">
            <g:link  controller="offreValidee" action="listEnCours"  class="btn-flat  btn-success btn-medium">
                Voir toutes les offres actives
            </g:link>
        </div>
        <div class="col-sm-2 col-md-2">
        </div>
    </div>
</div>



%{--<div class="rightSidebar">--}%
    %{--<div class="navigation">--}%

        %{--<div class="section_3"><h4 id="Navigation-Offres"  >Les dernieres offres</h4>--}%

            %{--<ol>--}%
                %{--<g:each var="offre" in="${topNOffres}">--}%

                    %{--<li><g:link controller="offre" action="showPub" id="${offre?.id}" rel="ligneOffre" data-original-title="${offre.toString()}"><strong>${offre.titre}</strong></g:link>--}%
                        %{--<p><prettytime:display date="${offre.date}" /></p>--}%
                    %{--</li>--}%

                    %{--<br>--}%
                %{--</g:each>--}%
                %{--<g:link  controller="offre" action="listEnCours"  class="btn-flat  btn-success btn-medium">--}%
                    %{--Voir toutes les offres--}%
                %{--</g:link>--}%
            %{--</ol>--}%
        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%