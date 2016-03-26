
%{--<div class="row">--}%
    %{--<div class="col-sm-12 col-md-12 ">--}%
        %{--<div id="reseauAdministreGroup" >--}%

                %{--<label for="groupId"><g:message code="groupe.text" default="Groupe" /> * </label>--}%
                %{--<input id="groupId" name="ReseauxIds" style="width:250px;" />--}%
                %{--<div id="treeview" >   </div>--}%
                %{--<g:if test="${invalid}"><span class="help-inline">${errors.join('<br>')}</span></g:if>--}%

        %{--</div>--}%
    %{--</div>--}%
%{--</div>--}%
<div class="row">
    <div class="col-sm-12 col-md-12">
        %{--well small well-sm--}%
                <div class="row">

                    <div class="col-xs-7">

                        <f:field bean="utilisateurInstance" property="mobilePhone" required="true" >
                            <input type='tel' id='mobilePhone' name='mobilePhone' value="${utilisateurInstance?.mobilePhone}" class="form-control" placeholder="${message(code: 'login.placeHolder.label', default: 'Login')}" autocomplete="off"/>
                        </f:field>
                </div>

                <div class="col-xs-5">
                </div>
               </div>


            <div class="row">
                <div class="col-xs-8">

                    <f:field  bean="utilisateurInstance" property="lastName">
                        <input type='text' name='lastName' value="${utilisateurInstance?.lastName}" class="form-control"  />
                    </f:field>

                </div>
                <div class="col-xs-4">
                </div>
            </div>


            <div class="row">
                <div class="col-xs-8">

                    <f:field  bean="utilisateurInstance" property="firstName">
                        <input type='text' name='firstName' value="${utilisateurInstance?.firstName}" class="form-control" />
                    </f:field>
                    <div class="col-xs-4">
                    </div>
                </div>


            </div>
        <div class="row">
            <div class="col-xs-5">

                <f:field bean="utilisateurInstance"  property="gender">
                    <g:select name="gender" from="${utilisateurInstance.constraints.gender.inList}" value="${utilisateurInstance?.gender}"  valueMessagePrefix="utilisateur.gender" class="form-control"/>
                </f:field>
            </div>



            <div class="col-xs-7">
            </div>
        </div>


                <div class="row">
                    <div class="col-xs-8">
                        <f:field bean="utilisateurInstance" property="country" >
                            <g:countrySelect name="country" value="${utilisateurInstance?.country}" default="grails.defaultCountry" from="['bfa','ben','civ', 'gha','gnb', 'mli','ner','sen','tgo']" class="form-control" noSelection="['': 'Choisissez un pays']"  />
                        </f:field>
                    </div>

                    <div class="col-xs-4">
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-9">

                        <f:field  bean="utilisateurInstance" property="email">
                            <input type='email' name='email' value="${utilisateurInstance?.email}" class="form-control" autocomplete="off" />
                        </f:field>

                    </div>

                    <div class="col-xs-3">
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-7">

                        <f:field  bean="utilisateurInstance" property="password">
                            <input type='password' name='passwordtyped'  class="form-control" autocomplete="off"   />
                        </f:field>

                    </div>

                    <div class="col-xs-5">
                    </div>
                </div>


                <div class="row">
                    <div class="col-xs-7">
                        <f:field  bean="utilisateurInstance" property="password" label="${g.message(code:"confirmPassword.text")}">
                        <input name="confirmPassword"  type="password" placeholder="" autocomplete="off" class="form-control" />
                        </f:field>
                    </div>
                    <div class="col-xs-5">
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-xs-9">

                        <p><g:message code="haveAlreadyAnAccount.text" default="Already have an account? " />  <a href="${createLink(uri: '/signIn')}"><g:message code="cliquezici.text" default="Sign In" /> </a> </p>

                    </div> <!-- end of col-sm-12 col-md-12 tag-->
                    <div class="col-xs-3">
                    </div>
                </div> <!-- end of row tag-->

</div>
</div>