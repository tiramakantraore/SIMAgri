package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class SendsmsController {
    def smsDecoderService
        def exportService
        def filterPaneService
        static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
		def benchmark= { closure ->
			def start = System.currentTimeMillis()
			def resultat=closure()
			def end = System.currentTimeMillis()
		//	println "Temps d'execution   ${(end-start)/60} sec."
			return resultat
		}
    def index() {
	
        def message=params?.message?:""
        def from_phone=params?.from_addr?:""
		def start = System.currentTimeMillis()
        def user=Utilisateur.findByMobilePhone(from_phone)
        try{
        smsDecoderService.decodeIt(message,from_phone)
		if (user){
			def end = System.currentTimeMillis()
			def dureeEnS=(end-start)
		new SMSLogger(isIn:true,message:message,user:user,date:new Date(),dureeEnS:dureeEnS,direction:"MO",operateur:user?.operateur?.nom,fromPhone:grailsApplication.config.grails.vusionshortCode,toPhone:from_phone,reseau:user?.reseauPrincipal).save(flush:true)
		}
		
        }catch (Exception e) {
            log.error("Echec de la requete ${message} Mobile ${from_phone} cause interne")
           
        }
		
    }

        def renderList() {
            params.max = Math.min(params.max ? params.int('max') : 25, 100)
            if (params?.format && params.format != "html") {
                params.max = SmsToPhone.count()
                response.contentType = grailsApplication.config.grails.mime.types[params.format]
                response.setHeader("Content-disposition", "attachment; filename=SmsToPhone.${params.extension}")
                exportService.export(params.format, response.outputStream, SmsToPhone.list(params), [:], [:])
            }
          render template:'list', model:[smsToReseauxInstanceList: filterPaneService.filter(params, SmsToPhone), smsToReseauxInstanceTotal: filterPaneService.count(params, SmsToPhone), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

        }
    def list() {
        renderList()
    }
    def listSMS() {
        renderList()
    }
        def filter = {
            if (!params.max) params.max = 10
            render( template:'list',
                    model: [smsToReseauxInstanceList: filterPaneService.filter(params, SmsToPhone),
                            smsToReseauxInstanceTotal: filterPaneService.count(params, SmsToPhone),
                            filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                            params: params])
        }

        def create() {
            switch (request.method) {
                case 'GET':
                   render template:'create', model:[smsToReseauxInstance: new SmsToPhone(params)]
                    break
                case 'POST':
                    def smsToReseauxInstance = new SmsToPhone(params)
                    if (!smsToReseauxInstance.save(flush: true)) {
                        render template: 'create', model: [smsToReseauxInstance: smsToReseauxInstance]
                    }
                    def messagesms=smsToReseauxInstance?.yourTextMessage
                    def toPhoneNumber=smsToReseauxInstance?.toPhoneNumber
                    def user=Utilisateur.findByMobilePhone(toPhoneNumber)
                    def start = System.currentTimeMillis()
                    def resultat=smsDecoderService.decodeIt(messagesms,toPhoneNumber)
               //      sendSMSService.execute(resultat,toPhoneNumber)
                    if (user){
                        def end = System.currentTimeMillis()
                        def dureeEnS=(end-start)
                        new SMSLogger(isIn:true,message:messagesms,user:user,date:new Date(),dureeEnS:dureeEnS,direction:"MO",operateur:user?.operateur?.nom,fromPhone:grailsApplication.config.grails.vusionshortCode,toPhone:toPhoneNumber).save(flush:true)
                    }


                    flash.message = "Resultat de la requête ${resultat}"
                    def result=[id:smsToReseauxInstance.id]
                    render result as JSON
            }
        }

        def show() {
            def smsToReseauxInstance = SmsToPhone.get(params.id)
            if (!smsToReseauxInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id])
                renderList()
            }

           render template:'show', model:[smsToReseauxInstance: smsToReseauxInstance]
        }

        def edit() {
            switch (request.method) {
                case 'GET':
                    def smsToReseauxInstance = SmsToPhone.get(params.id)
                    if (!smsToReseauxInstance) {
                        flash.message = message(code: 'default.not.found.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id])
                        renderList()
                    }

                    render template:'edit',model:[smsToReseauxInstance: smsToReseauxInstance]
                    break
                case 'POST':
                    def smsToReseauxInstance = SmsToPhone.get(params.id)
                    if (!smsToReseauxInstance) {
                        flash.message = message(code: 'default.not.found.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id])
                        renderList()
                    }

                    if (params.version) {
                        def version = params.version.toLong()
                        if (smsToReseauxInstance.version > version) {
                            smsToReseauxInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                    [message(code: 'SmsToPhone.label', default: 'SmsToPhone')] as Object[],
                                    "Another user has updated this SmsToPhone while you were editing")
                            render template: 'edit', model: [smsToReseauxInstance: smsToReseauxInstance]
                        }
                    }

                    smsToReseauxInstance.properties = params
                    def messagesms=smsToReseauxInstance?.yourTextMessage
                    def toPhoneNumber=smsToReseauxInstance?.toPhoneNumber
                    def resultat=smsDecoderService.decodeIt(messagesms,toPhoneNumber)



                    flash.message = "Resultat de la requête ${resultat}"
                    render template:'show',model:[smsToReseauxInstance: smsToReseauxInstance]
                    break
            }
        }

        def delete() {
            def smsToReseauxInstance = SmsToPhone.get(params.id)
            if (!smsToReseauxInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id])
                renderList()
            }

            try {
                smsToReseauxInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id])
                renderList()
            }
            catch (DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SmsToPhone.label', default: 'SmsToPhone'), params.id, e.message])
                redirect action: 'show', model:[id: params.id]
            }
        }
    }

