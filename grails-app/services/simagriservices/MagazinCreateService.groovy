package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import simagri.Magazin
import simagri.Mesure
import simagri.Produit
import simagri.StockMagazin

@Transactional
class MagazinCreateService {
def myUtilityService
    def mettreAJourStock(def params) {
        try {
            def magazin = Magazin.get(params.magazinId?.toLong())
            def listefromDate = params.date.tokenize('/')
            Integer fromDay = listefromDate[0].toInteger() ?: 1
            Integer fromMonth = listefromDate[1].toInteger() ?: 1
            Integer fromYear = listefromDate[2].toInteger() ?: 1890
            Date fromDate = new Date(fromYear - 1900, fromMonth - 1, fromDay)
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            def auteur=myUtilityService.getCurrent()
            auteur?.attach()
            //students in request params is parsed to json objects and stored in the List
            selectedList.each{

                BigDecimal stock=(it.stock>"" && (!it.stock.contains("input")))?new BigDecimal(it.stock?.toString()):new BigDecimal("0")
                def stockMagazin
                def mesure=Mesure.findByCode(it.mesure)
                def produit=Produit.findByNom(it.nomProduit)
                if ((stock>0)&&(mesure)&&(produit))
                {
                    stockMagazin = new StockMagazin()
                    stockMagazin.magazin = magazin
                    stockMagazin.mesure = mesure
                    stockMagazin.produit=produit
                    stockMagazin.stock=stock
                    stockMagazin.commentaire=it.commentaire
                    stockMagazin.isValidated = false
                    stockMagazin.date=fromDate
                    stockMagazin.auteur=auteur
                    stockMagazin.isValidated=false
                    stockMagazin.save(failOnError: true)

                }
                return true
              }
        } catch (Exception e) {
            println "error ${e}"
            log.error " exception ${e}"
            return false
        }
    }

    def valider(params) {
        try {
            if (params.selectedList != "null") {
                List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
//students in request params is parsed to json objects and stored in the List
                selectedList.each {
                    def stockMagazin = StockMagazin.get(it.toLong())
                    stockMagazin.isValidated = true
                    stockMagazin.save()
                }
                return true
            } else
                return false
        } catch (Exception e) {
            log.error " exception ${e}"
            return false
        }
    }
    def  abandonner(params){
        try {
        def deleteList=[]
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {
            deleteList<<it.toLong()

        }

        deleteList.each{
            def stockMag=StockMagazin.get(it)
            stockMag.delete(flush: true)
        }
        }
        catch (Exception e) {
            log.error " exception ${e}"
            return false
        }
    }
    def rejeter(params) {
        if (params.selectedList != "null") {
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
//students in request params is parsed to json objects and stored in the List
            try {
                selectedList.each {
                    def stockMagazin = StockMagazin.get(it.toLong())
                    stockMagazin.isRejected = true
                    stockMagazin.save()

                }
            }
            catch (Exception e) {
                log.error " exception ${e}"
                return false
            }

        }
        return true
    }

}
