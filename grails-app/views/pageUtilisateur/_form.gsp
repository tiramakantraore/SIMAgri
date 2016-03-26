<%@ page import="simagri.TypePartenaire; simagri.DestinationType; simagri.TypeAlignement; simagri.Reseau; simagri.PageUtilisateur" %>
<div class="row ">

    <div class="col-sm-4 col-md-4">

        <f:field bean="pageUtilisateurInstance" property="destinationType" required="true" >
            <bill:radioBoxList referenceCollection="${DestinationType?.values()[0..DestinationType?.values()?.size()-1]}" defaultValue="${pageUtilisateurInstance?.destinationType?.name()}"
                               instanceName="destinationType"  containerClass="my1Columns" labelClass="labelClass" isEnum="true" title="" />

        </f:field>
    </div>

        <div class="col-sm-4 col-md-4">
            <f:field bean="pageUtilisateurInstance" property="alignement" required="true" >
                <bill:radioBoxList referenceCollection="${TypeAlignement?.values()[0..TypeAlignement?.values()?.size()-1]}" defaultValue="${pageUtilisateurInstance?.alignement?.name()}"
                                   instanceName="alignement"  containerClass="my1Columns" labelClass="labelClass" isEnum="true" title="" />
            </f:field>
        </div>

    <div class="col-sm-4 col-md-4">
        <f:field bean="pageUtilisateurInstance" property="typePartenaire" required="true" >
            <bill:radioBoxList referenceCollection="${TypePartenaire?.values()[0..TypePartenaire?.values()?.size()-1]}" defaultValue="${pageUtilisateurInstance?.typePartenaire?.name()}"
                               instanceName="typePartenaire"  containerClass="my1Columns" labelClass="labelClass" isEnum="true" title="" />
        </f:field>
    </div>

</div>
<div class="row">

    <div class="col-sm-9 col-md-9">

        <f:field bean="pageUtilisateurInstance" property="nom" required="true" >
            <g:textField name="${property}"   value="${it.value}" maxlength="250" required=""  class="form-control"/>
        </f:field>
    </div>

</div>



%{--<div class="row ">--}%

    %{--<div class="col-xs-8">--}%

        %{--<f:field bean="pageUtilisateurInstance" property="adresse" required="true" >--}%
            %{--<g:textField name="adresse" value="${pageUtilisateurInstance?.adresse}" class="form-control"/>--}%
        %{--</f:field>--}%
    %{--</div>--}%

    %{--<div class="col-xs-4">--}%
    %{--</div>--}%
%{--</div>--}%
<div class="row ">
    <div class="col-sm-12 col-md-12">
        <f:field bean="pageUtilisateurInstance" property="contenu" >

            <ckeditor:editor name="${property}"  height="300px" width="100%" >
                ${pageUtilisateurInstance?.contenu}
            </ckeditor:editor>

        </f:field>
    </div>

</div>

<div class="row ">
    <div class="col-xs-4">
    <label for="photo">
        <g:message code="monImage.photo.label" default="Photo" />

    </label>
    <input type="file" id="photo" name="photo"/>
    </div>
</div>

<div class="row ">

    <div class="col-sm-8 col-md-8">

        <f:field bean="pageUtilisateurInstance" property="email" required="true" >
            <g:field type="email" name="${property}"   value="${it.value}" class="form-control"  />
       </f:field>
    </div>

</div>

<div class="row ">

    <div class="col-sm-8 col-md-8">

        <f:field bean="pageUtilisateurInstance" property="mobile" required="true" >
            <g:field type="tel" name="${property}"   value="${it.value}" class="form-control"  />
        </f:field>
    </div>

</div>

<div class="row ">

    <div class="col-sm-8 col-md-8">

        <f:field bean="pageUtilisateurInstance" property="telephone" required="true" >
            <g:field type="tel" name="${property}"   value="${it.value}" class="form-control"  />
        </f:field>
    </div>

</div>
<div class="row ">

    <div class="col-sm-12 col-md-12">

        <f:field bean="pageUtilisateurInstance" property="description" required="true" >
            <g:textArea name="${property}"   value="${it.value}" cols="80" rows="5"   class="form-control"  />

        </f:field>
    </div>

</div>
