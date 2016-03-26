package simagriservices

import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException
import simagri.AlerteReseauProduit
import simagri.Magazin
import simagri.MagazinProduit
import simagri.MarcheProduit
import simagri.Offre
import simagri.PriceHolder
import simagri.Prix
import simagri.Produit
import simagri.ReseauProduit
import simagri.StockMagazin
import simagri.UtilisateurProduit

@Transactional
class ProduitService {
    def grailsApplication
    def messageSource
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def toLong(def value){
        def valueLong=[]
        value.each{

            valueLong<<it.toLong()
        }
        return valueLong
    }
    def delete(params){
        def produitInstance = Produit.get(params.id)

        if (produitInstance) {
            try {
                AlerteReseauProduit.removeAll(produitInstance)
                ReseauProduit.removeAll(produitInstance)
                UtilisateurProduit.removeAll(produitInstance)
                MarcheProduit.removeAll(produitInstance)
                MagazinProduit.removeAll(produitInstance)
                StockMagazin.executeUpdate("DELETE StockMagazin c where produit.id = :produitId", [produitId: produitInstance.id])
                Offre.executeUpdate("DELETE Offre c where produit.id = :produitId", [produitId: produitInstance.id])
                Prix.executeUpdate("DELETE Prix c where produit.id = :produitId", [produitId: produitInstance.id])
                PriceHolder.executeUpdate("DELETE PriceHolder c where produit.id = :produitId", [produitId: produitInstance.id])
                Produit.executeUpdate("DELETE Produit c where c.id = :produitId", [produitId: produitInstance.id])
                return true
            }
            catch (DataIntegrityViolationException e) {
                println "message d'erreur $e"
                return false
            }
        }else {
            return false
        }
    }

}
