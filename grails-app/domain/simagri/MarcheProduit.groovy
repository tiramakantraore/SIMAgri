package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class MarcheProduit implements Serializable{
    Produit produit
    Marche marche
    static constraints = {
    }
    static mapping = {
        table 'marche_produit'
        version false
        id composite: ['marche','produit']
    }
    static  MarcheProduit create(Produit produit,Marche marche, Boolean flush=false) {
        MarcheProduit marcheProduit=new  MarcheProduit(produit:produit,marche:marche)
        marcheProduit.save(flush:flush,insert:true)
        return marcheProduit
    }
    static Boolean remove(Produit produit,Marche marche, Boolean flush=false) {
        MarcheProduit marcheProduit=findByProduitAndMarche(produit,marche)
        return  marcheProduit ? marcheProduit.delete(flush:flush):false
    }
    static void removeAll(Produit produit) {
        executeUpdate("DELETE FROM MarcheProduit where produit=:produit",[produit:produit])
    }
    static void removeAll(Marche marche) {
        executeUpdate("DELETE FROM MarcheProduit where marche=:marche",[marche:marche])
    }
    static MarcheProduit get (Long MarcheId, Long produitId) {
        find 'from MarcheProduit where marche.id=:MarcheId and produit.id=:produitId',[MarcheId:MarcheId,produitId:produitId]
    }
    boolean equals(other) {
        if (! (other instanceof MarcheProduit)){
            return false
        }
        other.produit?.id==produit?.id &&
                other.marche?.id==marche?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (produit)
            builder.append(produit.id)
        if (marche)
            builder.append(marche.id)
       return builder.toHashCode()

    }
}
