<%@ page import="simagri.Performance; simagri.Civilite; simagri.Produit; simagri.Marche; simagri.Reseau; simagri.CategorieProduit" %>
<div class="row" style="margin-top: 10px">
    <div class="col-sm-5 col-md-5 col-sm-offset-1 col-md-offset-1">

        <div class="row">
            <div class="col-xs-12">

                <f:display bean="utilisateurInstance" property="civilite"/>

            </div>
        </div>

                <div class="row">
                    <div class="col-xs-12">

                        <f:display  bean="utilisateurInstance" property="lastName"/>


                    </div>

                </div>


                <div class="row">
                    <div class="col-xs-12">

                        <f:display  bean="utilisateurInstance" property="firstName"/>


                    </div>


                </div>
        <div class="row">

            <div class="col-xs-12">

                <f:display bean="utilisateurInstance" property="mobilePhone" required="true" />
            </div>
        </div>


                <div class="row">
                    <div class="col-xs-12">

                        <f:display bean="utilisateurInstance"  input-valueMessagePrefix="utilisateur.gender" property="gender"/>

                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-12">

                        <f:display bean="utilisateurInstance"  property="dateOfBirth"/>

                    </div>

                </div>
        <div class="row">
            <div class="col-xs-12">

                <f:display bean="utilisateurInstance"  property="role"/>

            </div>

        </div>
                %{--<f:field property="dateOfBirth"/>--}%
                <g:if test="${userType=='enqueteur'}">
                    <div class="row">
                            <div class="col-xs-12">

                                <f:display bean="utilisateurInstance" property="performance" input-class="form-control"/>
                          </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <f:display bean="utilisateurInstance" property="marcheEnqueteur" input-class="form-control"/>
                        </div>
                    </div>

                </g:if>
                <g:if test="${!isCreation}">
                    <div class="row">
                        <div class="col-xs-12">
                            <f:display bean="utilisateurInstance" property="reseauPrincipal"/>
                        </div>
                    </div>


                </g:if>
</div>
<div class="col-sm-2 col-md-2">
    </div>
    <div class="col-sm-4 col-md-4 fileinput fileinput-new" style="padding-top:5px;background-color: transparent;" data-provides="fileinput">
        <div class="row">
                 <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 157px; height: 125px;padding:0 ">
                    <g:if test="${photoUrl}">
                        <img class="Photo img-polaroid" src="${photoUrl}" alt="Photo profile" style="width: 157px; height: 100% "/>
                    </g:if>
                    <g:else>
                            <g:img class="Photo" dir="images" file="avatar_homme.jpg" width="157"  alt="Avatar homme" />
                    </g:else>
                </div>

       </div>

        %{--<div class="row">--}%
            %{--<div style="width: 157px; height: 125px;padding:0 ">--}%

                %{--<a class="btn-flat  btn-link " href="${createLink(uri: '/resetPassword')}" style="margin-top:50px">--}%
                    %{--changer le mot de passe--}%
                %{--</a>--}%
            %{--</div>--}%

            %{--<div class="col-xs-7">--}%
            %{--</div>--}%
        %{--</div>--}%
        <div class="row">
            <div class="col-sm-3 col-md-3" style="padding-top:90px ">
            <asset:image src="cereal_new.png" />
        </div>
        </div>

    </div>
</div>