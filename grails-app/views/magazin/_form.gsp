<%@ page import="simagri.Lieux; simagri.Produit" %>
<f:with bean="magazinInstance">
    <f:field property="code" input-class="form-control"/>
    <f:field property="nom" input-class="form-control"/>
    <f:field property="nomContact" input-class="form-control"/>
    <f:field property="numTelBureau" input-class="form-control"/>
    <f:field property="adressePhysique" input-class="form-control"/>
    <f:field property="email" input-class="form-control"/>
    <f:field property="actif" input-class="check"/>
    <f:field property="localite" input-noSelection="['': '']" input-class="many-to-one form-control"/>
</f:with>