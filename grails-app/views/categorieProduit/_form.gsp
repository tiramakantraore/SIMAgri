<%@ page import="simagri.Mesure" %>

<f:with bean="categorieProduitInstance">
    <f:field property="nom" input-class="form-control"/>
    <bill:checkBoxList referenceCollection="${Mesure.list()}" containerClass="my2Columns" instanceName="mesures" />
    %{--<f:field property="mesure" input-class="form-control"/>--}%
    <f:field property="actif" input-class="check"/>
    <f:field  property="comment" input-class="form-control">
        <g:textArea name="${property}" cols="50" rows="5"  value="${it.value}"  />
    </f:field >

</f:with>

