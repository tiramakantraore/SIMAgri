package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.joda.time.DateTime
import org.springframework.dao.OptimisticLockingFailureException
import simagri.Info
@Transactional
class InfosCreateService {
    def myUtilityService
    def modifier(def params) {
        try {
            def user = myUtilityService.getCurrent()
            user?.attach()
            def infoInstance

            Map formData = JSON.parse(params.formData?.toString()) as Map
            def contenuInfo=params.contenu
            //students in request params is parsed to json objects and stored in the List

            infoInstance = Info.get(formData.id)
            def theDate=new DateTime()
            infoInstance.date=theDate.toDate()

            if (!infoInstance.dateExpiration)
                infoInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
            infoInstance.auteur=user
            infoInstance.contenu=contenuInfo
            infoInstance.reseau=user?.reseauPrincipal
            infoInstance.save(flush:true)
            return true

        } catch (Exception e) {
            println "error ${e}"
            log.error " exception ${e}"
            return false
        }
    }
    def creer(def params) {
        try {
            def user = myUtilityService.getCurrent()
            user?.attach()
            def infoInstance

            Map formData = JSON.parse(params.formData?.toString()) as Map
            def contenuInfo=params.contenu
            //students in request params is parsed to json objects and stored in the List

            infoInstance = new Info(formData)
                def theDate=new DateTime()
                    infoInstance.date=theDate.toDate()

                if (!infoInstance.dateExpiration)
                    infoInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
                infoInstance.auteur=user
                infoInstance.contenu=contenuInfo
                infoInstance.reseau=user?.reseauPrincipal
                infoInstance.save(flush:true)
                return true

        } catch (Exception e) {
            println "error ${e}"
            log.error " exception ${e}"
            return false
        }
    }
    def valider(params) {
        if (params.selectedList != "null") {

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
//students in request params is parsed to json objects and stored in the List
            try {
                selectedList.each {
                    Info infos = Info.get(it.toLong())
                    infos.actif = true
                    infos.save()
                }

                return selectedList.size() > 0

            }
            catch (Exception e) {
                log.error(e)
                return false
            }
        }
    }
    def rejeter(params) {
        if (params.selectedList != "null") {

            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
//students in request params is parsed to json objects and stored in the List
            try {
                selectedList.each {
                    def infos = Info.get(it.toLong())
                    infos.isRejected=true
                    infos.actif=false
                    infos.save(flush:true)
                }
                return true

            }
            catch (Exception e) {
                log.error " exception ${e}"
                return false
            }

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

                Info.executeUpdate("DELETE Info c where c.id = :infoId", [infoId: it])
            }
            return true
        }catch (Exception e) {
            log.error  " exception ${e}"
            println "log error ${e}"
            return  false
        }
    }

}
