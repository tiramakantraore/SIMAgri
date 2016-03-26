package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurProduit implements Serializable{
    Produit produit
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'utilisateur_produit'
        version false
        id composite: ['utilisateur','produit']
    }
    static  UtilisateurProduit create(Utilisateur utilisateur,Produit produit, Boolean flush=false) {
        UtilisateurProduit utilisateurProduit=new  UtilisateurProduit(produit:produit,utilisateur:utilisateur)
        utilisateurProduit.save(flush:flush,insert:true)
        return utilisateurProduit
    }
    static  UtilisateurProduit edit(Utilisateur utilisateur,Produit produit, Boolean flush=false) {
        def utilisateurProduit=findByProduitAndUtilisateur(produit,utilisateur)
        if (!utilisateurProduit) {
            utilisateurProduit=new  UtilisateurProduit(produit:produit,utilisateur:utilisateur)
            utilisateurProduit.save(flush:flush,insert:true)
        }
        return utilisateurProduit
    }
    static Boolean remove(Produit produit,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurProduit utilisateurProduit=findByProduitAndUtilisateur(produit,utilisateur)
        return  utilisateurProduit ? utilisateurProduit.delete(flush:flush):false
    }
    static void removeAll(Produit produit) {
        executeUpdate("DELETE FROM UtilisateurProduit where produit=:produit",[produit:produit])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurProduit where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurProduit get (Long UtilisateurId, Long produitId) {
        find 'from UtilisateurProduit where utilisateur.id=:UtilisateurId and produit.id=:produitId',[UtilisateurId:UtilisateurId,produitId:produitId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurProduit)){
            return false
        }
        other.produit?.id==produit?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (produit)
            builder.append(produit.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
