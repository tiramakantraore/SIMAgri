<%@ page import="simagri.CategorieProduit; simagri.Mesure; simagri.Utilisateur; simagri.Produit; simagri.Magazin" %>


<f:with bean="stockMagazinInstance">
    <f:field property="magazin"/>
    <f:field property="produit"/>
    <f:field property="date"/>
    <f:field property="stock"/>
    <f:field property="mesure"/>
    <f:field property="auteur"/>
</f:with>
