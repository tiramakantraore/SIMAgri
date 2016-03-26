<%@ page import="simagri.Utilisateur; simagri.Info" %>

<f:with bean="infoInstance">
    <f:field property="titre" input-class="form-control"/>
    <f:field property="contenu" input-class="form-control"/>
    <f:field property="url" input-class="form-control"/>
    <f:field property="dateExpiration" input-class="form-control"/>
    <f:field property="auteur" input-class="form-control"/>
</f:with>


