package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.dao.OptimisticLockingFailureException
import simagri.ApplicationRole
import simagri.Performance
import simagri.Utilisateur

@Transactional
class PerformanceService {
    def refuser(params) {
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List

        selectedList.each {

            def utilisateur = Utilisateur.get(it.id.toLong())
            try {

                utilisateur.performance = null
                utilisateur.save( failOnError: true)
            }
            catch (OptimisticLockingFailureException e) {
                ApplicationRole.merge(utilisateur)
                log.error " exception ${e}"
                return false
            }

        }
        return true
    }
    def valider(params) {
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {

            def utilisateur = Utilisateur.get(it.id.toLong())
            try {
                def performance = Performance.findByNom(it.contratPerformance)
                if (performance)
                    utilisateur.performance = performance
                utilisateur.save(failOnError: true)
            }
            catch (OptimisticLockingFailureException e) {
                Utilisateur.merge(utilisateur)
                log.error " exception ${e}"
                return false
            }

        }
        return true
    }

    def autoriser(params) {
        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
        //students in request params is parsed to json objects and stored in the List
        selectedList.each {
            def performance = Performance.findByNom(params.performanceId)
            def utilisateur = Utilisateur.get(it.id.toLong())
            try {
                utilisateur.performance = performance
                utilisateur.save( failOnError: true)
            }
            catch (OptimisticLockingFailureException e) {
                ApplicationRole.merge(utilisateur)
                log.error " exception ${e}"
                return false
            }

        }
        return true
    }
}
