<div class="row myHeaderContainer ">
    <div class="col-sm-1 col-md-1 no-left-padding " style="margin-right: 60px">
            <div style="padding-top:0">
                <g:link controller="home" action="accueil" >
                    %{--<asset:image src="logoSIMAgri.png" class="img-responsive" min-height="100px" />--}%
                    <asset:image src="logoSIMAgri.png" height="100px" />

                </g:link>

            </div>
    </div>
    <div class="col-sm-7 col-md-7 toBanner no-left-padding">
            <g:if test="${pageAccueilInstance?.banniere}">
                %{--<img class="img-responsive" src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.banniere?.id])}"  height="90" alt=""/>--}%
                <img class="img-responsive" src="${createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.banniere?.id])}"   alt=""/>

            </g:if>
            <g:else>
                <p class="slogan"><g:message code="slogan.text" default="VOUS N'ETES PAS CONNECTE" /></p>

            </g:else>

    </div>
    <sec:ifLoggedIn>
      <div class="col-sm-4 col-md-4 no-left-padding" style="margin-right: -60px">
          <div class="row">
                 <div class="col-sm-8 col-md-8 ">
                  <g:if test="${user}">
                      <div class="messageConnexion">
                          <p> <strong><g:message code="connected.text" default="Vous êtes connecté en tant que" />  <span  class="connectedUser">${user?.toString()}</span>  <strong><g:message code="numMobile.text" default="Numéro Mobile :" /> <span  class="connectedUserPhone"> ${user?.mobilePhone}</span></strong></p>
                         <p> <strong><g:message code="profile.text" default="Profile :" />  <span  class="connectedUser">${user?.role}</span></strong></p>

                      </div>
                  </g:if>
                  <g:if test="${flash.password_changed}">
                      <bootstrap:alert class="alert-info">${flash.password_changed}</bootstrap:alert>
                  </g:if>
                  <g:if test="${flash.user_not_registred}">
                      <bootstrap:alert class="alert-info">${flash.user_not_registred}</bootstrap:alert>
                  </g:if>
              </div>
              <div class="col-sm-4 col-md-4 ">
                  <a class="btn-flat btn-primary btn-danger " href="${createLink(uri: '/signOut')}" style="margin-top:5px">
                      <g:message code="logout.text" default="Sortir" /></a>
              </div>
         </div>
            %{--<div class="row">--}%
               %{--<div class="col-sm-10 col-md-10 ">--}%
                      %{--<g:if test="${user}">--}%
                          %{--<div class="messageConnexion">--}%
                              %{--<p> <strong>Vous êtes connecté en tant que <span  class="connectedUser">${user?.toString()}</span></strong> <strong>Numéro Mobile :<span  class="connectedUserPhone"> ${user?.mobilePhone}</span></p>--}%
                          %{--</div>--}%
                      %{--</g:if>--}%
                      %{--<g:if test="${flash.password_changed}">--}%
                          %{--<bootstrap:alert class="alert-info">${flash.password_changed}</bootstrap:alert>--}%
                      %{--</g:if>--}%
                      %{--<g:if test="${flash.user_not_registred}">--}%
                          %{--<bootstrap:alert class="alert-info">${flash.user_not_registred}</bootstrap:alert>--}%
                      %{--</g:if>--}%
               %{--</div>--}%
            %{--</div>--}%
          %{--<div id="spinnertext">--}%
              %{--<div class="row spinnertext" style="display: none;">--}%
                  %{--<div class="col-sm-1 col-md-1" >--}%
                      %{--<asset:image src="spinner.gif" alt="chargement..."/>--}%
                  %{--</div>--}%
                  %{--<div class="col-sm-9 col-md-9 ">--}%
                      %{--<p class="loading"><g:message code="loading.text" default="Veuillez patienter chargement en cours ..." /></p>--}%
                  %{--</div>--}%
              %{--</div>--}%
          %{--</div>--}%

      </div>

    </sec:ifLoggedIn>
        <div class="col-sm-4 col-md-4 no-left-padding" style="margin-right: -60px" >
            <g:render template="/layouts/loginEmbedForm"/>
        </div>
</div>
