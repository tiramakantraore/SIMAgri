package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class UtilisateurCategorieProduit implements Serializable{
    CategorieProduit categorieProduit
    Utilisateur utilisateur
    static constraints = {
    }
    static mapping = {
        table 'user_categorie_produit'
        version false
        id composite: ['utilisateur','categorieProduit']
    }
    static  UtilisateurCategorieProduit create(Utilisateur utilisateur,CategorieProduit categorieProduit, Boolean flush=false) {
        UtilisateurCategorieProduit utilisateurCategorieProduit=new  UtilisateurCategorieProduit(categorieProduit:categorieProduit,utilisateur:utilisateur)
        utilisateurCategorieProduit.save(flush:flush,insert:true)
        return utilisateurCategorieProduit
    }

    static  UtilisateurCategorieProduit edit(Utilisateur utilisateur,CategorieProduit categorieProduit, Boolean flush=false) {
        def utilisateurCategorieProduit=findByCategorieProduitAndUtilisateur(categorieProduit,utilisateur)
        if (!utilisateurCategorieProduit){
            utilisateurCategorieProduit=new  UtilisateurCategorieProduit(categorieProduit:categorieProduit,utilisateur:utilisateur)
            utilisateurCategorieProduit.save(flush:flush,insert:true)
        }
        return utilisateurCategorieProduit
    }
    static Boolean remove(CategorieProduit categorieProduit,Utilisateur utilisateur, Boolean flush=false) {
        UtilisateurCategorieProduit utilisateurCategorieProduit=findByCategorieProduitAndUtilisateur(categorieProduit,utilisateur)
        return  utilisateurCategorieProduit ? utilisateurCategorieProduit.delete(flush:flush):false
    }
    static void removeAll(CategorieProduit categorieProduit) {
        executeUpdate("DELETE FROM UtilisateurCategorieProduit where categorieProduit=:categorieProduit",[categorieProduit:categorieProduit])
    }
    static void removeAll(Utilisateur utilisateur) {
        executeUpdate("DELETE FROM UtilisateurCategorieProduit where utilisateur=:utilisateur",[utilisateur:utilisateur])
    }
    static UtilisateurProduit get (Long UtilisateurId, Long categorieProduitId) {
        find 'from UtilisateurCategorieProduit where utilisateur.id=:UtilisateurId and categorieProduit.id=:categorieProduitId',[UtilisateurId:UtilisateurId,categorieProduitId:categorieProduitId]
    }
    boolean equals(other) {
        if (! (other instanceof UtilisateurCategorieProduit)){
            return false
        }
        other.categorieProduit?.id==categorieProduit?.id &&
                other.utilisateur?.id==utilisateur?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (categorieProduit)
            builder.append(categorieProduit.id)
        if (utilisateur)
            builder.append(utilisateur.id)
       return builder.toHashCode()

    }
}
