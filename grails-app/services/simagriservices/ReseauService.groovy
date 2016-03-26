package simagriservices

import grails.transaction.Transactional
import simagri.Marche
import simagri.Produit
import simagri.Reseau
import simagri.ReseauMarche
import simagri.ReseauProduit
import simagri.Utilisateur
import simagri.UtilisateurReseau

class ReseauService {

    def grailsApplication
    def myUtilityService
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
    Boolean save(def params, Reseau reseau, Boolean insert=true) {
        try {

            def listeMarches=Marche.createCriteria().list(){

                    inList("id",toLong(params?.markets)?:[new Long("-1")])
            }



           if (reseau.save(flush:true,failOnError: true))     {
               ReseauMarche.removeAll(reseau)
               ReseauProduit.removeAll(reseau)
            listeMarches.each{marche->
                ReseauMarche.create(marche as Marche,reseau as Reseau,insert)

            }
            if (!params.produits)  {
                reseau?.markets?.each{
                    def prodList=it.produits
                    prodList.each{produit->
                        ReseauProduit.create(produit as Produit,reseau as Reseau,insert)

                    }
               }
            } else {
                def listeProduits=Produit.createCriteria().list(){

                    inList("id",toLong(params?.produits)?:[new Long("-1")])
                }
                listeProduits.each{produit->
                    ReseauProduit.create(produit as Produit,reseau as Reseau,insert)
                 }
            }


            return true
           }
        }
        catch(e) {
            log.debug(" exception in ${this.getClass().simpleName}.Save ${e}")
            return false
        }
    }

    Boolean update(def params, Reseau reseau) {
        try {


            def listeMarches=Marche.createCriteria().list(){

                inList("id",toLong(params?.markets)?:[new Long("-1")])
            }
            def listeMembres=Utilisateur.createCriteria().list(){

                inList("id",toLong(params?.membres)?:[new Long("-1")])
            }
            def user = myUtilityService.getCurrent()
            reseau.properties = params

            if (reseau.save(flush:true,failOnError: true))     {
                ReseauMarche.removeAll(reseau)
                ReseauProduit.removeAll(reseau)
                if (user.isSuperviseur && listeMembres)
                UtilisateurReseau.removeAll(reseau)
                listeMarches.each{marche->
                    ReseauMarche.create(marche as Marche,reseau as Reseau,true)

                }
                if (user.isSuperviseur && listeMembres) {
                    listeMembres.each{membre->

                        UtilisateurReseau.create(membre as Utilisateur,reseau as Reseau,true,true)

                    }
                }


                if (!params.produits)  {
                    reseau?.markets?.each{
                        def prodList=it.produits
                        prodList.each{produit->
                            ReseauProduit.create(produit,reseau,true)

                        }
                    }
                } else {
                    def listeProduits=Produit.createCriteria().list(){

                        inList("id",toLong(params?.produits)?:[new Long("-1")])
                    }
                    listeProduits.each{produit->
                        ReseauProduit.create(produit as Produit,reseau as Reseau,true)
                    }
                }


                return true
            }
        }

        catch(e) {
            log.debug(" exception in ${this.getClass().simpleName}.Save ${e}")
            return false
        }


}
}