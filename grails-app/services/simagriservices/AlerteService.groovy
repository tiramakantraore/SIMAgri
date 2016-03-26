package simagriservices

import grails.converters.JSON
import simagri.AlerteReseau
import simagri.AlerteReseauDestinataire
import simagri.AlerteReseauMarche
import simagri.AlerteReseauProduit
import simagri.AlerteReseauReseau
import simagri.Marche
import simagri.Produit
import simagri.Reseau
import simagri.Utilisateur

class AlerteService {
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
    Boolean save( params, AlerteReseau alerteReseau) {
        try {

            def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
            def idReseaux=toList(listeParam?:[new Long("0")])

            def idMarches=(params?.markets?.size()>0)?myUtilityService.toLong(params?.markets?:"0"):[new Long("0")]
            def idDestinataires=(params?.destinataires?.size()>0)?myUtilityService.toLong(params?.destinataires?:"0"):[new Long("0")]
            def listeDestinataires=Utilisateur.createCriteria().list(){

                inList("id",idDestinataires)
            }
            def listeMarches=Marche.createCriteria().list(){
                inList("id",idMarches)
            }
            def idProduits=(params?.produits?.size()>0)?myUtilityService.toLong(params?.produits?:"0"):[new Long("0")]

            def listeProduits=Produit.createCriteria().list(){
                inList("id",idProduits)
            }
            def listeReseaux=Reseau.createCriteria().list(){
                inList("id",idReseaux)
            }
            if (alerteReseau?.save(flush:true,failOnError:true)) {

               if (params?.ReseauxIds)
                AlerteReseauReseau.removeAll(alerteReseau)
                if (params?.destinataires?.size()>0)
                AlerteReseauDestinataire.removeAll(alerteReseau)
                if (params?.markets?.size()>0)
                AlerteReseauMarche.removeAll(alerteReseau)
                if (params?.produits?.size()>0)
                AlerteReseauProduit.removeAll(alerteReseau)
                if (params?.ReseauxIds) {
                    listeReseaux.each{reseau->
                        AlerteReseauReseau.create(reseau as Reseau,alerteReseau,true)

                    }
                }
                if (params?.markets?.size()>0){
                    listeMarches.each { marche ->
                        AlerteReseauMarche.create(marche as Marche ,alerteReseau, true)
                    }
                }

                if (params?.produits?.size()>0) {
                    listeProduits.each { produit ->
                        AlerteReseauProduit.create( produit as Produit,alerteReseau, true)
                    }
                }


               if (params?.destinataires?.size()>0){

                        listeDestinataires.each{destinataire->
                            AlerteReseauDestinataire.create(destinataire as Utilisateur,alerteReseau,true)

                        }
               }

            }
            else
                return false
            return true
        }
        catch(e) {

            log.debug( " exception in ${this.getClass().simpleName}.Save ${e}")
            return false
        }
    }
    Boolean update(def params, AlerteReseau alerteReseau) {
        try {
            alerteReseau.properties = params
            return save(params,alerteReseau)
        }
        catch(e) {

            println " exception in ${this.getClass().simpleName}.update ${e}"
            log.debug(e)
            return false
        }
    }
    def valider(params) {
     try {
        if (params.selectedList != "null") {
            def transactionDate = new Date()
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {
                AlerteReseau alerte = AlerteReseau.get(it.toLong())
                alerte.valide = true
                alerte.validationDate = transactionDate
                alerte.save(flush:true,failOnError: true)


            }

        }
        return true
    }
    catch(e) {

        println " exception in ${this.getClass().simpleName}.update ${e}"
        log.debug(e)
        return false
    }
    }
    def rejeter(params) {
        if (params.selectedList != "null") {
            def transactionDate = new Date()
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {
                AlerteReseau alerte = AlerteReseau.get(it.toLong())
                alerte.isRejected = true
                alerte.valide = false
                alerte.rejectedDate = transactionDate
                alerte.save(flush:true,failOnError: true)

            }
            return true
        }
    }
    def abandonner(params){
        try {
        def deleteList=[]
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {
            deleteList<<it.toLong()

        }
        AlerteReseau.findAllByIsRejected(true).each {
            deleteList<<it.id
        }
        deleteList.each{
            AlerteReseauDestinataire.executeUpdate("DELETE AlerteReseauDestinataire c where c.alerteReseau.id = :alerteId", [alerteId: it])
            AlerteReseauMarche.executeUpdate("DELETE AlerteReseauMarche c where c.alerteReseau.id = :alerteId", [alerteId: it])
            AlerteReseauProduit.executeUpdate("DELETE AlerteReseauProduit c where c.alerteReseau.id = :alerteId", [alerteId: it])
            AlerteReseauReseau.executeUpdate("DELETE AlerteReseauReseau c where c.alerteReseau.id = :alerteId", [alerteId: it])
            AlerteReseau.executeUpdate("DELETE AlerteReseau c where c.id = :alerteId", [alerteId: it])
        }
            return true
        }catch (Exception e) {
            log.error  " exception ${e}"
            println "log error ${e}"
            return  false
        }
    }
    def execute(params) {
        try {
            Map formData = JSON.parse(params.formData?.toString()) as Map
            Boolean isEditing=new Boolean(params.isEditing?.toString())
            def user=myUtilityService.getCurrent()
            def alerteInstance
            if (isEditing) {
                alerteInstance = AlerteReseau.get(formData?.id?.toLong())
            }else {
                alerteInstance = new AlerteReseau()

            }
            alerteInstance?.properties=formData
            if (alerteInstance?.isAlertePrix){
                alerteInstance.recevoirOffres=false
                alerteInstance.recevoirPrix=true
            }else {
                alerteInstance.recevoirOffres=true
                alerteInstance.recevoirPrix=false
            }
            alerteInstance.operateur=user
            alerteInstance.suspendre=false
            alerteInstance.recevoirParEmail=true
            alerteInstance.valide=false
            alerteInstance.dateCreation=new Date()
            alerteInstance.recevoirParSMS=true


            def marketIds = []
            def produitsIds = []
            def destinataires = []

            formData.each {
                if (it.key.startsWith("markets_alertes_"))
                    marketIds << (it.key - "markets_alertes_")

            }
            formData.each {
                if (it.key.startsWith("products_alertes_"))
                    produitsIds << (it.key - "products_alertes_")

            }
            formData.each {
                if (it.key.startsWith("destinataires_alertes_"))
                    destinataires << (it.key - "destinataires_alertes_")

            }
                def  newparams = formData + ['ReseauxIds': params?.idResauxAlerte]+ ['markets': marketIds] + ['produits': produitsIds]+ ['destinataires': destinataires]

                if (isEditing) {
                    return update(newparams,alerteInstance)
                }else {
                    return save(newparams,alerteInstance)
                }
        }catch (Exception e) {
            log.error  " exception ${e}"
            return  false
        }
    }
    def delete(params) {
        try {
            Map formData = JSON.parse(params.formData?.toString()) as Map
            def alerteInstance = AlerteReseau.get(formData?.id?.toLong())
            AlerteReseauMarche.removeAll(alerteInstance)
            AlerteReseauProduit.removeAll(alerteInstance)
            AlerteReseauDestinataire.removeAll(alerteInstance)
            AlerteReseauReseau.removeAll(alerteInstance)
            AlerteReseau.executeUpdate("DELETE AlerteReseau c where id = :alerteId", [alerteId: formData?.id?.toLong()])

            return true
        }catch (Exception e) {
            log.error  " exception ${e}"
            return  false
        }
    }

}
