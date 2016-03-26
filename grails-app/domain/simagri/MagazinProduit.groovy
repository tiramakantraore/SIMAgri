package simagri

import org.apache.commons.lang.builder.HashCodeBuilder

class MagazinProduit implements Serializable{
    Produit produit
    Magazin magazin
    static constraints = {
    }
    static mapping = {
        table 'magazin_produit'
        version false
        id composite: ['magazin','produit']
    }
    static  MagazinProduit create(Produit produit,Magazin magazin, Boolean flush=false) {
        MagazinProduit magazinProduit=new  MagazinProduit(produit:produit,magazin:magazin)
        magazinProduit.save(flush:flush,insert:true)
        return magazinProduit
    }
    static Boolean remove(Produit produit,Magazin magazin, Boolean flush=false) {
        MagazinProduit magazinProduit=findByProduitAndMagazin(produit,magazin)
        return  magazinProduit ? magazinProduit.delete(flush:flush):false
    }
    static void removeAll(Produit produit) {
        executeUpdate("DELETE FROM MagazinProduit where produit=:produit",[produit:produit])
    }
    static void removeAll(Magazin magazin) {
        executeUpdate("DELETE FROM MagazinProduit where magazin=:magazin",[magazin:magazin])
    }
    static MagazinProduit get (Long MagazinId, Long produitId) {
        find 'from MagazinProduit where magazin.id=:MagazinId and produit.id=:produitId',[MagazinId:MagazinId,produitId:produitId]
    }
    boolean equals(other) {
        if (! (other instanceof MagazinProduit)){
            return false
        }
        other.produit?.id==produit?.id &&
                other.magazin?.id==magazin?.id

    }
    int hashCode() {
         def builder=new HashCodeBuilder()
        if (produit)
            builder.append(produit.id)
        if (magazin)
            builder.append(magazin.id)
       return builder.toHashCode()

    }
}
