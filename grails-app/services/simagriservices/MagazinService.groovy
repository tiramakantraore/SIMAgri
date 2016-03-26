package simagriservices

import grails.transaction.Transactional
import simagri.*
@Transactional
class MagazinService {
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
    Boolean save(def params, Magazin magazin, Boolean insert=true) {
        try {

                  if (magazin.save(flush:true,failOnError: true)){
                      MagazinProduit.removeAll(magazin)
                          if (params.produits)  {

                            def listeProduits=Produit.createCriteria().list(){

                                inList("id",toLong(params?.produits)?:[new Long("-1")])
                            }
                            listeProduits.each{produit->
                                MagazinProduit.create(produit as Produit,magazin,insert)
                            }
                        }

                  }else
                      params.messageRetour="Echec de l'enregistrement"
                    return true
          }
            catch(e) {

                log.debug(e)
                return false
            }
    }
    Boolean createAndSave (def params, Magazin magazin) {
        try {



            return  save (params,magazin)


        }
        catch(e) {
            log.debug(e)
            return false

        }

    }
    Boolean canWriteToMagazin(Utilisateur user,Long idMagazin){
        try {
            def resultat=user.isAdmin?:UtilisateurMagazin.get(user.id,idMagazin)
            return resultat
        }catch(Exception e){
            log.error  " exception ${e}"
            return false
        }
    }
    Boolean update(def params, Magazin magazin) {
        try {
            magazin.properties = params
        return save(params,magazin,true)
        }
        catch(e) {
            log.error  " exception ${e}"
            return false
        }
    }

}
