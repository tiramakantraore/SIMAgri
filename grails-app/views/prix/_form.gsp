<%@ page import="simagri.Utilisateur; simagri.Produit; simagri.PriceHolder; simagri.Marche; simagri.Mesure" %>


<f:with bean="prixInstance">
    <f:field property="produit"/>
    <f:field property="marche"/>
    <f:field property="date"/>
    <f:field property="montant"/>
    <f:field property="mesure"/>
    <f:field property="auteur"/>
    <f:field property="commentaireAdministrateur"/>
</f:with>

