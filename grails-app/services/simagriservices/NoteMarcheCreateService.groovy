package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import org.joda.time.DateTime
import simagri.NoteMarche

@Transactional
class NoteMarcheCreateService {
    def myUtilityService
    def modifier(def params) {
        try {
            def user = myUtilityService.getCurrent()
            user?.attach()
            def infoInstance

            Map formData = JSON.parse(params.formData?.toString()) as Map
            def contenuInfo=params.contenu
            //students in request params is parsed to json objects and stored in the List

            infoInstance = NoteMarche.get(formData.id)
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

            infoInstance = new NoteMarche(formData)
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
                    NoteMarche noteMarche = NoteMarche.get(it.toLong())
                    noteMarche.actif = true
                    noteMarche.save(flush:true)
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
                    def noteMarche = NoteMarche.get(it.toLong())
                    noteMarche.isRejected=true
                    noteMarche.actif=false
                    noteMarche.save(flush:true)
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

                NoteMarche.executeUpdate("DELETE NoteMarche c where c.id = :infoId", [infoId: it])
            }
            return true
        }catch (Exception e) {
            log.error  " exception ${e}"
            println "log error ${e}"
            return  false
        }
    }

}
