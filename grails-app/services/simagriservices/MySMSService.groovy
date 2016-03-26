package simagriservices

import grails.transaction.Transactional
import simagri.Reseau
import simagri.SmsToReseaux
import simagri.Utilisateur

class MySMSService {
    def grailsApplication
    def myUtilityService
    def sendSMSService
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    @Transactional
    def execute(params) {
     try {
        def smsToReseauxInstance = new SmsToReseaux(params)
         smsToReseauxInstance.yourTextMessage=params.yourTextMessage
        def messagesms=params.yourTextMessage?.sansAccent()
        def listeParam=params?.idReseaux?.tokenize(',')?.collect{it as Long}
        def idReseaux=myUtilityService.toList(listeParam?:[])
         if (idReseaux) {
         def destinataires=params.'destinataires[]'
           def destinatairesLong=[]
         destinatairesLong=myUtilityService.toLong(myUtilityService.toList(destinataires))
         def listeDestinataires=Utilisateur.createCriteria().list(){

             inList("id",destinatairesLong?:[new Long("0")])
         }

        def reseauxIdList=Reseau.createCriteria().list(){
            if (idReseaux) {
                inList("id",idReseaux)
            }
            else {
                eq("id",-1 as Long)
            }
        }

        reseauxIdList.eachWithIndex{reseau,i->
            smsToReseauxInstance.addToReseaux(reseau as Reseau)
        }
        def listOfSMS= messagesms?.mySMSList()
        listOfSMS.each{leSMS->

            def userList=listeDestinataires?.flatten()
            userList.each{ user->
                def toPhoneNumber=(user.mobilePhone?.toString()?.getRightPhone())
                sendSMSService.execute(leSMS,toPhoneNumber)
            }
        }
        return smsToReseauxInstance.save(flush:true,failOnError: true)
         }else return false
     }catch (Exception e) {
        log.error  " exception ${e}"
        return  false
    }
    }

}