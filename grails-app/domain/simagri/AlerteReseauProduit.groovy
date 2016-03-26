package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class AlerteReseauProduit implements Serializable{
    Produit produit
    AlerteReseau alerteReseau
    static constraints = {
    }
    static mapping = {
        table 'alerte_reseau_produit'
        version false
        id composite: ['alerteReseau','produit']
    }
    static  AlerteReseauProduit create(Produit produit,AlerteReseau alerteReseau, Boolean flush=false, Boolean insert=true) {
        AlerteReseauProduit alerteReseauProduit=new  AlerteReseauProduit(produit:produit,alerteReseau:alerteReseau)
        alerteReseauProduit.save(flush:flush,insert:insert)
        return alerteReseauProduit
    }

    static Boolean remove(Produit produit,AlerteReseau alerteReseau, Boolean flush=false) {
        AlerteReseauProduit alerteReseauReseau=AlerteReseauProduit.findByProduitAndAlerteReseau(produit,alerteReseau)
        return  alerteReseauReseau ? alerteReseauReseau.delete(flush:flush):false
    }
    static void removeAll(Produit produit) {
        executeUpdate("DELETE FROM AlerteReseauProduit where produit=:produit",[produit:produit])
    }
    static void removeAll(AlerteReseau alerteReseau) {
        executeUpdate("DELETE FROM AlerteReseauProduit where alerteReseau=:alerteReseau",[alerteReseau:alerteReseau])
    }
    static AlerteReseauProduit get (Long produitId, Long alerteReseauId) {
        find 'from AlerteReseauProduit where produit.id=:produitId and alerteReseau.id=:alerteReseauId',[produitId:produitId,alerteReseauId:alerteReseauId]
    }
    boolean equals(other) {
        if (! (other instanceof AlerteReseauProduit)){
            return false
        }
        other.produit?.id==produit?.id &&
                other.alerteReseau?.id==alerteReseau?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (produit)
            builder.append(produit.id)
        if (alerteReseau)
            builder.append(alerteReseau.id)
       return builder.toHashCode()

    }
}
