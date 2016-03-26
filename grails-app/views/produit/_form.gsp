<%@ page import="simagri.CategorieProduit; simagri.Mesure" %>

<f:with bean="produitInstance">
    <f:field property="categorie" input-class="form-control"/>
    <f:field property="code" input-class="form-control"/>
    <f:field property="nom" input-class="form-control"/>
    <f:field property="nomScientifique" input-class="form-control"/>
    <f:field property="mesure" input-class="form-control"/>
    <f:field property="actif" input-class="check"/>
    <f:field property="commentaire" input-class="form-control"/>
</f:with>

