<%@ page import="simagri.Utilisateur; simagri.Info" %>

<f:with bean="noteMarcheInstance">
    <f:field property="titre" input-class="form-control"/>
    <f:field property="contenu" input-class="form-control"/>
    <f:field property="longitude" input-class="form-control"/>
    <f:field property="latitude" input-class="form-control"/>
    <f:field property="dateExpiration" input-class="form-control"/>
    <f:field property="auteur" input-class="form-control"/>
</f:with>


