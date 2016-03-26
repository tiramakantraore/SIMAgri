<%@ page import="simagri.Operateur" %>


<f:with bean="operateurInstance">
    <f:field property="nom" input-class="form-control"/>
    <f:field property="prefixes" input-class="form-control"/>
    <f:field property="emailContact" input-class="form-control"/>
    <f:field property="telephoneContact" input-class="form-control"/>
    <f:field property="siteWeb" input-class="form-control"/>
</f:with>
