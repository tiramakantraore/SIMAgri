

<div id="quizz_childList">
    <g:each var="detail" in="${quizzInstance?.questions}" status="i">
        
        <!-- Render the phone template (_detail.gsp) here -->
        <g:render template='detail' model="['quizz_detail':detail,'i':i,'hidden':false]"/>
        <!-- Render the phone template (_detail.gsp) here -->
        
    </g:each>
</div>





