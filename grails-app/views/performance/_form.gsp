<%@ page import="simagri.Performance" %>
<div class="row">

    <div class="col-sm-8 col-md-8">
        <f:with bean="performanceInstance">
            <f:field property="nom" input-class="form-control"/>
            <f:field property="nbOffre" input-class="form-control"/>
            <f:field property="nbPrix" input-class="form-control"/>
            <f:field property="nbAlerte" input-class="form-control"/>
            <f:field property="nbContact" input-class="form-control"/>
        </f:with>
</div>
</div>



