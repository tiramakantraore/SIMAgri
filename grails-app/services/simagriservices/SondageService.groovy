package simagriservices

import grails.converters.JSON
import grails.transaction.Transactional
import simagri.*
@Transactional
class SondageService {
    def grailsApplication
    def messageSource  //pour acceder aux traductions
    def myUtilityService


    def execute(params) {
        try {

            Map formData = JSON.parse(params.formData?.toString()) as Map


            def sondageInstance = new Sondage(formData)
            def details=[]
            formData.each {
                if ((it.key?.indexOf('[')>-1)&&(it.key?.indexOf(']')>-1) && !(it.key?.endsWith("deleted") && it.value.equals("true"))&& (it.value)) {
                def cle=it.key?.substring(it.key?.indexOf('[')+1, it.key?.indexOf(']'))
                if (it.key?.endsWith(".choix"))
                    details << [id:cle,value:it.value]
                }

            }
            details?.each{
                sondageInstance.addToDetails(new Choix(numOrdre:it.id,choix:it.value,actif:true))


            }
            Sondage.list().each{
                it.actif=false
                it.save();
            }
            sondageInstance.actif=true;
            //update my indexes
            sondageInstance.details.eachWithIndex(){phn, i ->
                if(phn)
                    phn.numOrdre = i
            }
            return sondageInstance.save(failOnError: true)
        }catch (Exception e) {
            log.error  " exception ${e}"
            return  [aaData:null] as JSON
        }
    }

}
