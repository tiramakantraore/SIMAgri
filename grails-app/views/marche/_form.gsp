<%@ page import="simagri.CategorieProduit; simagri.Region; simagri.Produit; simagri.Lieux" %>
<f:with bean="marcheInstance">
<f:field property="code" required="true" input-class="many-to-one form-control" input-autocomplete="off"/>
<f:field property="nom"  required="true" input-class="many-to-one form-control" input-autocomplete="off"/>
<f:field property="region" input-noSelection="['': '']" input-class="many-to-one form-control" />
<f:field property="location" input-noSelection="['': '']" input-class="many-to-one form-control"/>
<f:field property="actif" input-class="check"/>
</f:with>

