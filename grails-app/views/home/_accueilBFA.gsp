
<div class="row whitenBody" >
    <div class="col-sm-9 col-md-9 no-left-padding" >
      <g:render template="/home/mySlider"/>
    </div>
    <div class="col-sm-3 col-md-3 ">
        <div class="row no-left-padding">
            <div class="myBanner">


                 <p style="font-style: italic;font-weight: bold;font-size: 24px;text-align: center;padding-top: 50px"> Rejoignez-nous !</p>
                <g:link controller="pageUtilisateur" action="showByDestinationType" params="[DestinationType:'Page_Avantages']" target="_blank" title="Les avantages">
                    <p style="color:black;font-weight: bold;font-size: 12px;text-align: center;padding-top: 25px">Quels sont les avantages ?</p>
                </g:link>
                <g:link controller="pageUtilisateur" action="showByDestinationType" params="[DestinationType:'Page_Essaie']" target="_blank" title="Essaie gratuit">
                    <p style="color:black;font-weight: bold;font-size: 12px;text-align: center"> Essayez gratuitement pendant un mois</p>
                </g:link>
            </div>
        </div>
        %{--<g:render template="/home/sondageForm" model="[idSelector:'sondageForm']"/>--}%

        <div class="row no-left-padding" >
        %{--<ul style="list-style-type:none;">--}%
        <g:each in="${videos}" var="videoInstance">

            <li style="list-style-type:none;">
                <h4>${videoInstance.titre}</h4>
                <g:video videoKey="${videoInstance?.url?.split('=')?.size()>1?videoInstance?.url?.split('=')[1]:""}" width="100%" height="250px"/>
                <p><prettytime:display date="${videoInstance?.date}" /></p>
            </li>

        </g:each>
        %{--</ul>--}%
       </div> <!-- end of col-sm-8 col-md-8 tag-->
        <div class="row no-left-padding" style="display: none" >
          <div class="col-sm-12 col-md-12">
           <strong><g:message code="rejoignez-nous-sur" /></strong>

          </div>
        </div>
        <br>

    </div> <!-- row-->


</div> <!-- row-->
<g:render template="/home/myindexjs"/>
