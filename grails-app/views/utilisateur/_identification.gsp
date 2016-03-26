<%@ page import="simagri.Performance; simagri.Civilite; simagri.Produit; simagri.Marche; simagri.Reseau; simagri.CategorieProduit" %>


<div class="row" style="margin-top: 10px">
    <div class="col-sm-5 col-md-5 col-sm-offset-1 col-md-offset-1">
        <div class="row">

            <div class="col-xs-8">

                <f:field bean="utilisateurInstance" property="mobilePhone" required="true" input-class="form-control" placeholder="${message(code: 'login.placeHolder.label', default: 'Login')}" input-autocomplete="off"/>

            </div>

            <div class="col-xs-4">
            </div>
        </div>

        <div class="row">
            <div class="col-xs-9">
                %{--<f:field bean="utilisateurInstance" property="civilite" required="true" >--}%
                    %{--<bill:radioBoxList referenceCollection="${Civilite.list()}"--}%
                                       %{--instanceName="civilite"  containerClass="my2Columns" labelClass="labelClass"  title="" />--}%

                %{--</f:field>--}%
                <f:field bean="utilisateurInstance" property="civilite" input-class=" many-to-one form-control" input-noSelection="['': '']"/>

                %{--<g:select name='${property}' value="${it?.value?.id}" optionKey = "id" from="${Civilite.list()}"  noSelection="['': '']" class=" many-to-one form-control" />--}%
                %{--</f:field>--}%
            </div>

            <div class="col-xs-3">
            </div>
        </div>


                <div class="row">
                    <div class="col-xs-12">

                        <f:field  bean="utilisateurInstance" property="lastName" input-class=" many-to-one form-control" />


                    </div>

                </div>


                <div class="row">
                    <div class="col-xs-12">

                        <f:field  bean="utilisateurInstance" property="firstName" input-class=" many-to-one form-control"/>


                    </div>


                </div>



                <div class="row">
                    <div class="col-xs-9">
                        <f:field  bean="utilisateurInstance" property="gender" input-valueMessagePrefix="utilisateur.gender" input-class="form-control"/>

                    </div>



                    <div class="col-xs-3">
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-9">

                        <f:field bean="utilisateurInstance"  property="dateOfBirth" input-class="form-control">
                            <div class='input-group date' id='dateOfBirth'   data-date-format="dd/mm/yyyy">

                                <input type='text' name='${property}' value="${formatDate(format:'dd/MM/yyyy',date:it.value)}" class="form-control" />
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </f:field>
                    </div>

                    <div class="col-xs-3">
                    </div>
                </div>
                <g:if test="${isEditing}">
                    <div>
                        <label><input type="checkbox" name="changePassword" value="pwd"/> Changer le mot de passe </label>
                    </div>
                    <div class="changepwd box">

                        <div class="row">
                            <div class="col-xs-12">

                                <f:field  bean="utilisateurInstance" property="password">
                                    <input type='password' name='passwordtyped'  class="form-control" autocomplete="off"   />
                                </f:field>

                            </div>
                        </div>


                        <div class="row">
                            <div class="col-xs-12">
                                <f:field  bean="utilisateurInstance" property="password" label="${g.message(code:"confirmPassword.text")}"  input-class="form-control">
                                    <input name="confirmPassword"  type="password" placeholder="" autocomplete="off" class="form-control" />
                                </f:field>
                            </div>
                        </div>
                        <br>
                   </div>

                </g:if>

                %{--<f:field property="dateOfBirth"/>--}%
                <g:if test="${userType=='enqueteur'}">
                    <f:field bean="utilisateurInstance" property="performance" input-class="form-control"/>
                    <f:field bean="utilisateurInstance" property="marcheEnqueteur" input-class="form-control"/>
                </g:if>
                <g:if test="${!isCreation}">
                    <f:field bean="utilisateurInstance" property="reseauPrincipal" >
                        <g:select name="reseauPrincipal" from="${Reseau.findAllByEstReseau(true)}" optionKey="id" value="${utilisateurInstance?.reseauPrincipal?.id}"  class="many-to-one form-control"/>
                    </f:field>
                </g:if>
                <sec:ifSuperViseur>
                    <f:field bean="utilisateurInstance" property="role" input-class="form-control">
                        <g:select name="role" from="${roleList}"  value="${utilisateurInstance?.role}"  class="many-to-one form-control"/>
                    </f:field>
                </sec:ifSuperViseur>
</div>
<div class="col-sm-2 col-md-2">
    </div>
    <div class="col-sm-4 col-md-4 fileinput fileinput-new" style="padding-top:5px;background-color: transparent" data-provides="fileinput">
        <div class="row">
            <div class="fileinput-preview thumbnail" data-trigger="fileinput" style="width: 157px; height: 125px;padding:0 " style="background-color: transparent">
                <g:if test="${photoUrl}">
                    <img class="Photo img-polaroid" src="${photoUrl}" alt="Photo profile" width="157"/>
                </g:if>
                <g:else>
                    <g:img class="Photo" dir="images" file="avatar_homme.jpg" width="157"  alt="Avatar homme" />
                </g:else>
            </div>
            <div style="width: 157px; height: 125px;padding:0 ">
                <span class="btn-file">
                        <span class=" btn-flat  btn-primary  fileinput-new">
                              <g:message code="select.photo.text" default="Select photo" />
                        </span>

                    %{--<span class="fileinput-exists" style="width: 157px;">--}%
                        <a href="#" class="btn-flat btn-primary fileinput-exists" data-dismiss="fileinput" style="width: 157px;">
                            <g:message code="change.image.text" default="change" />
                        </a>
                        %{----}%
                    %{--</span>--}%
                    <input type="file" name="photo">
                </span>
                <a href="#" class="btn-flat  btn-default fileinput-exists" data-dismiss="fileinput" style="width: 157px;">
                    <g:message code="remove.image.text" default="remove" />
                </a>
                <div style="margin-top:10px;">
                    <bootstrap:alert class="alert-info"><p><g:message code="taille.image.text" default="Taille image : 64x64  " /></p></bootstrap:alert>

                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-3 col-md-3" style="padding-top:90px ">
            <asset:image src="cereal_new.png" />
        </div>
        </div>

    </div>
</div>