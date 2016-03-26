<g:render template="/partials/showHeader" model="[beanName:'offre',isEdit:true,canCreate:false,titre:'Voir une offre']"/>

<div class="row">
<div class="col-sm-12 col-md-12">



<g:if test="${flash.message}">
    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
</g:if>

<g:form >
    <f:display bean="offreInstance" property="auteur"/>
    <f:display bean="offreInstance" property="typeOffre"/>
    <f:display bean="offreInstance" property="modePaiement"/>
    <f:display bean="offreInstance" property="date"/>
    <f:display bean="offreInstance" property="delaiEnJours"/>
    <f:display bean="offreInstance" property="dateExpiration"/>
    <f:display bean="offreInstance" property="origine"/>
    <f:display bean="offreInstance" property="lieuStockage"/>
    <f:display bean="offreInstance" property="lieuLivraison"/>
    <f:display bean="offreInstance" property="produit"/>
    <f:display bean="offreInstance" property="qualite"/>
    <f:display bean="offreInstance" property="prixUnitaire"/>
    <f:display bean="offreInstance" property="quantite"/>
    <f:display bean="offreInstance" property="prixTotalGros"/>

    <f:display bean="offreInstance" property="delaiLivraison"/>
    <f:display bean="offreInstance" property="nbJoursExpiration"/>
    <f:display bean="offreInstance" property="operateur"/>
    <f:display bean="offreInstance" property="conditions"/>
    <f:display bean="offreInstance" property="commentaire"/>
    <g:hiddenField name="update" value="${update}" />


    <g:render template="/partials/btnShow"  model="[update:update,instance:offreInstance]"/>
</g:form>

</div>

</div>