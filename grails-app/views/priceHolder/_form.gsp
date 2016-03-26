<%@ page import="simagri.Produit; simagri.Marche; simagri.Mesure" %>

<f:with bean="priceHolderInstance">
    <f:field property="produit"/>
    <f:field property="marche"/>
    <f:field property="date"/>
    <f:field property="prixGros"/>
    <f:field property="prixDetail"/>
    <f:field property="mesureGros"/>
    <f:field property="mesureDetail"/>
    <f:field property="auteur"/>
    <f:field property="isRejected"/>
    <f:field property="commentaire"/>
    <f:field property="commentaireAdministrateur"/>
</f:with>

