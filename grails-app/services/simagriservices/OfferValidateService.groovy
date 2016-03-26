package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import simagri.Offre

@Transactional
class OfferValidateService {
    def valider(params) {
        if (params.selectedList != "null") {
            def transactionDate = new Date()
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
//students in request params is parsed to json objects and stored in the List
            try {
                selectedList.each {
                    Offre offers = Offre.get(it.toLong())
                    offers.isValidated = true
                    offers.dateValidation = transactionDate
                    offers.save_me()
                }

                return selectedList.size() > 0

            }
            catch (Exception e) {
                log.debug(e)
                return false
            }
        }
    }
    def abandonner(params) {
        try {
        def deleteList=[]
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {
            deleteList<<it.toLong()

        }

        deleteList.each{
            Offre.executeUpdate("DELETE Offre c where c.id = :offreId", [offreId: it])
        }
            return true
        }catch (Exception e) {
            log.error  " exception ${e}"
            println "log error ${e}"
            return  false
        }
    }
    def rejeter(params) {
        if (params.selectedList != "null") {
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            try {
                selectedList.each {
                    Offre offers = Offre.get(it?.toLong())
                    offers.isRejected = true
                    offers.save(failOnError: true)

                }
                return selectedList.size() > 0
            }
            catch (Exception e) {
                log.debug(e)
                return false
            }

        }
        return true
    }
}
