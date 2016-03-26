package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.context.MessageSourceResolvable
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.dao.OptimisticLockingFailureException
import simagri.*
@Transactional
class MarcheService {
    def grailsApplication
    def messageSource
    def myUtilityService
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
    Boolean save(def params, Marche marche, Boolean insert=true) {
        try {

                  if (marche.save()){


                          if (params.produits)  {
                              MarcheProduit.removeAll(marche)
                            def listeProduits=Produit.createCriteria().list(){

                                inList("id",toLong(params?.produits)?:[new Long("-1")])
                            }
                            listeProduits.each{produit->
                                MarcheProduit.create(produit as Produit,marche as Marche,insert)
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
    Boolean createAndSave (def params, Marche marche) {
        try {



            return  save (params,marche)


        }
        catch(e) {
            log.debug(e)
            return false

        }

    }
    Boolean update(def params, Marche marche) {
        try {
            marche.properties = params
        return save(params,marche,true)
        }
        catch(e) {
            log.error  " exception ${e}"
            return false
        }
    }
    def autoriser(params) {
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {
            def marche = Marche.findByNom(params.marcheId)
            def utilisateur = Utilisateur.get(it.id.toLong())
            try {
                utilisateur.affecterMarcheEnqueteur(marche)
                utilisateur.save(failOnError: true)
            }
            catch (OptimisticLockingFailureException e) {
                ApplicationRole.merge(utilisateur)
                log.error " exception ${e}"
                return false
            }

        }
        return true
    }
    def refuser(params) {
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List

        selectedList.each {

            def utilisateur = Utilisateur.get(it.id.toLong())
            try {

                utilisateur.retirerMarcheEnqueteur()
                utilisateur.save( failOnError: true)
            }
            catch (OptimisticLockingFailureException e) {
                ApplicationRole.merge(utilisateur)
                log.error " exception ${e}"
            }

        }
        return true
    }
    Boolean canWriteToMarket(Utilisateur user,Long idMarche){
        try {
            def resultat=user.isAdmin?:UtilisateurMarcheEcriture.get(user.id,idMarche)
            return resultat
        }catch(Exception e){
            log.error  " exception ${e}"
            return false
        }
    }
    def valider(params) {
        try {
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {

                def utilisateur = Utilisateur.get(it.id.toLong())
                try {
                    def marche = Marche.findByNom(it.marcheEnqueteur)
                    if (marche)
                        utilisateur.marcheEnqueteur = marche
                    utilisateur.save(failOnError: true)
                }
                catch (OptimisticLockingFailureException e) {
                    Utilisateur.merge(utilisateur)
                    log.error " exception ${e}"
                }

            }
            return true
        }
        catch(e) {
            log.error  " exception ${e}"
            return false
        }
    }

}
