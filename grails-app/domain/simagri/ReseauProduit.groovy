package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class ReseauProduit implements Serializable{
    Produit produit
    Reseau reseau
    static constraints = {
    }
    static mapping = {
        table 'reseau_produit'
        version false
        id composite: ['reseau','produit']
    }
    static  ReseauProduit create(Produit produit,Reseau reseau, Boolean flush=false) {
        ReseauProduit reseauProduit=new  ReseauProduit(produit:produit,reseau:reseau)
        reseauProduit.save(flush:flush,insert:true)
        return reseauProduit
    }
    static Boolean remove(Produit produit,Reseau reseau, Boolean flush=false) {
        ReseauProduit reseauProduit=findByProduitAndReseau(produit,reseau)
        return  reseauProduit ? reseauProduit.delete(flush:flush):false
    }
    static void removeAll(Produit produit) {
        executeUpdate("DELETE FROM ReseauProduit where produit=:produit",[produit:produit])
    }
    static void removeAll(Reseau reseau) {
        executeUpdate("DELETE FROM ReseauProduit where reseau=:reseau",[reseau:reseau])
    }
    static ReseauProduit get (Long ReseauId, Long produitId) {
        find 'from ReseauProduit where reseau.id=:ReseauId and produit.id=:produitId',[ReseauId:ReseauId,produitId:produitId]
    }
    boolean equals(other) {
        if (! (other instanceof ReseauProduit)){
            return false
        }
        other.produit?.id==produit?.id &&
                other.reseau?.id==reseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (produit)
            builder.append(produit.id)
        if (reseau)
            builder.append(reseau.id)
       return builder.toHashCode()

    }
}
