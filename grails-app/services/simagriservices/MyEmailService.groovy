package simagriservices

import grails.transaction.Transactional
import simagri.Utilisateur

class MyEmailService {
    def grailsApplication
    def myUtilityService
    def sendSMSService
    def mailService
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    @Transactional
    def execute(params) {
     try {
         def messagesms=params.yourTextMessage?.sansAccent()
         def destinataires=params.'destinataires[]'
         def objetEmail=params.objetEmail
         def destinatairesLong=[]
         destinatairesLong=myUtilityService.toLong(myUtilityService.toList(destinataires))
         def listeDestinataires=Utilisateur.createCriteria().list(){

             inList("id",destinatairesLong?:[new Long("0")])
         }
            def userList=listeDestinataires?.flatten()
            userList.each{ user->
                def toEmail=(user as Utilisateur).email
                def fromEmail=grailsApplication.config.grails.mail.default.from

                if (toEmail){
                    mailService?.sendMail {
                        subject " ${objetEmail}"
                        to "${toEmail}"
                        from "${fromEmail}"
                        body messagesms
                    }
                }

            }
        return true
    }catch (Exception e) {
        log.error  " exception ${e}"
        return  false
    }
    }

}