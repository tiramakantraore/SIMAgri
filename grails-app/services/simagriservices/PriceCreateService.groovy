package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import simagri.Marche
import simagri.Mesure
import simagri.PriceHolder
import simagri.Prix
import simagri.Produit
@Transactional
class PriceCreateService {
    def  myUtilityService
    String messageEchec

    def validerPrix(params) {
        try {
            if (params.selectedList != "null") {
                def transactionDate = new Date()
                List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
                //students in request params is parsed to json objects and stored in the List
                def currentUser = myUtilityService.getCurrent()
                selectedList.each {
                    PriceHolder priceHolder = PriceHolder.get(it.toLong())
                    priceHolder.isValidated = true
                    priceHolder.transactionDate = transactionDate
                    priceHolder.reseau=currentUser.reseauPrincipal
                    priceHolder.save(flush:true)
                    if (((priceHolder.prixGros ?: 0.0 + priceHolder.prixDetail ?: 0.0) > 0.0) && (priceHolder.marche)) {

                        if (priceHolder.hasWholePrices()){
                            def prixInstance=new Prix(priceHolder: priceHolder, produit: priceHolder.produit, date: priceHolder.date, month: priceHolder.month, year: priceHolder.year, isFromSMS: priceHolder.isFromSMS, marche: priceHolder.marche, mesure: priceHolder.mesureDetail, montant: priceHolder.prixGros, periodeDebut: priceHolder.periodeDebut, periodeFin: priceHolder.periodeFin, commentaireAdministrateur: priceHolder.commentaireAdministrateur, commentaire: priceHolder.commentaire,
                                    categorieTarifaire: "Gros", statut: "Approuve", auteur: currentUser,
                                    enqueteur: priceHolder.auteur,reseau:priceHolder.reseau).save(flush:true)
                                prixInstance.montant=priceHolder.prixGros
                                prixInstance.save(flush:true)
                        }
                        if (priceHolder.hasDetailPrices()){
                            def prixInstance=new Prix(priceHolder: priceHolder, produit: priceHolder.produit, date: priceHolder.date, month: priceHolder.month, year: priceHolder.year, isFromSMS: priceHolder.isFromSMS, marche: priceHolder.marche, mesure: priceHolder.mesureDetail, montant: priceHolder.prixDetail, periodeDebut: priceHolder.periodeDebut, periodeFin: priceHolder.periodeFin, commentaireAdministrateur: priceHolder.commentaireAdministrateur, commentaire: priceHolder.commentaire, categorieTarifaire: "Detail", statut: "Approuve", auteur: currentUser, enqueteur: priceHolder.auteur,reseau:priceHolder.reseau).save(flush: true)
                            prixInstance.montant=priceHolder.prixDetail
                            prixInstance.save(flush:true)
                        }

                    }
                }
                return selectedList.size() > 0
            }
        }
        catch (Exception e) {
            log.error " exception ${e}"
            return false
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

        deleteList.each{
            def priceHolder=PriceHolder.get(it)
            Prix.executeUpdate("DELETE Prix c where c.priceHolder = :priceHolder", [priceHolder: priceHolder])
            priceHolder.delete(flush: true)
        }
            return true
        } catch (Exception e) {
            log.error " exception ${e}"
            return false
        }
    }
    def rejeterPrix(params) {
        try {
            if (params.selectedList != "null") {
                def transactionDate = new Date()
                List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
                //students in request params is parsed to json objects and stored in the List
                selectedList.each {
                    PriceHolder priceHolder = PriceHolder.get(it.toLong())
                    priceHolder.isValidated = false
                    priceHolder.isRejected = true
                    priceHolder.transactionDate = transactionDate

                    priceHolder.save(failOnError: true)

                }
                return true
            } else return false
        }
        catch (Exception e) {
            log.error " exception ${e}"
            return false
        }
    }

    def mettreAJourPrix(def params) {
        try {
            def user = myUtilityService.getCurrent()
            user?.attach()
            def marche = Marche.get(params.marketId?.toLong())
            def listefromDate = params.date.tokenize('/')
            Integer fromDay = listefromDate[0].toInteger() ?: 1
            Integer fromMonth = listefromDate[1].toInteger() ?: 1
            Integer fromYear = listefromDate[2].toInteger() ?: 1890
            Date fromDate = new Date(fromYear - 1900, fromMonth - 1, fromDay)
            BigDecimal prixGros=0.0
            BigDecimal prixDetail=0.0
            def priceHolder

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {
                prixGros = ((it.prixGros > "") && (!it.prixGros.contains("input"))) ? new BigDecimal(it.prixGros?.toString()) : 0.0
                prixDetail = (it.prixDetail > "" && (!it.prixDetail.contains("input"))) ? new BigDecimal(it.prixDetail?.toString()) : 0.0

                if ((prixGros > 0.0) || (prixDetail > 0.0)) {
                    if (it.id.toLong() > 0)
                        priceHolder = PriceHolder.get(it.id.toLong())
                    else
                        priceHolder = new PriceHolder()

                    try {
                        priceHolder.mesureGros = Mesure.findByCode(it.mesureGros)
                        priceHolder.mesureDetail = Mesure.findByCode(it.mesureDetail)
                        priceHolder.nomProduit = it.nomProduit
                        priceHolder.produit = Produit.findByNom(it.nomProduit)
                        priceHolder.prixGros = prixGros
                        priceHolder.prixDetail = prixDetail
                        priceHolder.marche = marche
                        if (!it.commentaire?.contains("textarea")) {
                            priceHolder.commentaire = it.commentaire ?: ""
                        } else
                            priceHolder.commentaire = ""
                        priceHolder.isValidated = false
                        priceHolder.isRejected = false
                        priceHolder.active = true
                        priceHolder.date = fromDate
                        priceHolder.transactionDate = fromDate
                        priceHolder.auteur = user
                        priceHolder.save(failOnError: true)
                    }
                    catch (OptimisticLockingFailureException e) {
                        PriceHolder.merge(priceHolder)
                        println "error ${e}"
                        log.error " exception ${e}"
                        return false
                    }
                }
            }
            return true
        } catch (Exception e) {
            println "error ${e}"
            log.error " exception ${e}"
            return false
        }
    }




}
