<sec:ifNotLoggedIn>

        <g:form controller="login" action="login" role="form" name='auth'  method="post" accept-charset="UTF-8" autocomplete='off'>
            <div class="row" style="font-size:13px; ">
                <div class="col-sm-6 col-md-6" style=" border-right-style:solid;border-color: black;border-right-width:1px;  ">

                    %{--<p><g:message code="vous_avez_deja_un_compte.text" default="Vous avez dejà un compte ?" /></p>--}%
                    <div class="my-form-group">

                        <input type='text'  name="j_username" id='username'  placeholder="${session.codePlaceHolderLogin}" class="form-control" />

                    </div>
                    <div class="my-form-group">
                        <input id="j_password" name="j_password" value="" type="password" class="form-control" />
                    </div>
                    <g:if test="${flash.messageEchecLogin}">
                        <br>
                        <bootstrap:alert>${flash.messageEchecLogin}</bootstrap:alert>
                        <a href="${createLink(uri: '/resetPassword')}"><g:message code="passwordForgot.text" default="Forgot password?" /></a>
                    </g:if>
                        %{--<div class="my-form-group">--}%

                    %{--</div>--}%
                    <div class="my-form-group">
                            <button class="btn-flat btn-primary btn-sm"  name="commit"><i class="icon-signin"></i> <g:message code="springSecurity.login.button" default="Entrer" /></button>
                     </div>

                </div>

                <div class="col-sm-6 col-md-6">
                            %{--<p><g:message code="vous_navez_pas_de_compte.text" default="Vous n'avez pas de compte ?" /></p>--}%

                            <a class="btn-flat  btn-warning btn-sm" href="${createLink(uri: '/signUp')}"><i class="icon-edit"></i>
                                <g:message code="creer_un_compte.text" default="S'Inscrire" /></a>

                    <br>

                    %{--<div class="row">--}%
                                %{--<div class="col-sm-12 col-md-12">--}%
                            %{--<a href="${createLink(uri: '/resetPassword')}"><g:message code="reinitialisationpwd.text" default="Forgot password?" /></a>--}%
                                %{--</div>--}%
                     %{--</div>--}%
                    <p id="remember_me_holder">
                        <label for='remember_me'><g:message code="springSecurity.login.remember.me.label"/></label>
                        <input type='checkbox' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>

                    </p>

                </div>
            </div>

        </g:form>

</sec:ifNotLoggedIn>