package simagriservices

import grails.transaction.Transactional
import grails.util.Environment
import simagri.SMSLogger
import simagri.Utilisateur
@Transactional
class SendSMSService {
    def grailsApplication
    def execute(String message, String from_addr) {
        def reponse=""
        def user=Utilisateur.findByMobilePhone(from_addr)
        if (Environment.current == Environment.TEST ) {
               log.debug(" Test : message  ${message} numero : ${from_addr} ")
               println " Test : message  ${message} numero : ${from_addr} "
        }else   {
                    if ((Environment.current == Environment.DEVELOPMENT )||
                       (Environment.current.name == 'devmali' )||
                            (Environment.current.name == 'demo' )){
                        println  " message : ${message} "

                    } else {
                                try {
                                    withRest(url: grailsApplication.config.grails.vusionURL) {
                                       reponse = get(path: grailsApplication.config.grails.vusionPath, query: [message: message,
                                         from_addr:grailsApplication.config.grails.vusionshortCode, to_addr: from_addr])

                                        }
                                    if (user){

                                        new SMSLogger(isIn:false,message:message,user:user,date:new Date(),dureeEnS:0,direction:"MT",operateur:user?.operateur?.nom,fromPhone:from_addr,toPhone:grailsApplication.config.grails.vusionshortCode,reseau:user?.reseauPrincipal).save()
                                    }

                                  } catch (Exception e) {
                                    log.debug(" Echec envoie SM  ${message} numero : ${from_addr} cause : ${e.message}")
                                }
                           }
                  }
    }
}