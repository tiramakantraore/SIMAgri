<div id="#QCMchildList">
    <g:each var="option" in="${questionChoixMultipleInstance?.options}" status="i">
        
        <!-- Render the phone template (_detail.gsp) here -->
        <g:render template='option' model="['option':option,'i':i,'hidden':false]"/>
        <!-- Render the phone template (_detail.gsp) here -->
        
    </g:each>
</div>
<div type="button" class="btn-flat  btn-medium" onclick="addQCMOption();">
    <i class="icon-plus-sign icon-black"></i> Ajouter reponse
</div>




