<%@ page import="simagri.MesureCorrespondance" %>


<f:with bean="mesureCorrespondanceInstance">
	<f:field property="mesureDepart" input-class="form-control" input-noSelection="['':'']"/>
	<f:field property="mesureDestination" input-class="form-control"  input-noSelection="['':'']"/>
	<f:field property="equivalence" input-class="form-control"/>
</f:with>

