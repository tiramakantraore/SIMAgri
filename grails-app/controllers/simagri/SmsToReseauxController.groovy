package simagri

import org.springframework.dao.DataIntegrityViolationException


class SmsToReseauxController {
    def exportService
    def sendSMSService
    def mySMSService
    def myEmailService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = SmsToReseaux.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=SmsToReseaux.${params.extension}")
            exportService.export(params.format, response.outputStream, SmsToReseaux.list(params), [:], [:])
        }
        [smsToReseauxInstanceList: filterPaneService.filter(params, SmsToReseaux), smsToReseauxInstanceTotal: filterPaneService.count(params, SmsToReseaux), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [smsToReseauxInstanceList: filterPaneService.filter(params, SmsToReseaux),
                        smsToReseauxInstanceTotal: filterPaneService.count(params, SmsToReseaux),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def sendByService(){
        render mySMSService.execute(params)
    }
    def sendEmailByService(){
        render myEmailService.execute(params)
    }
    def create() {
        switch (request.method) {
            case 'GET':
                "params is ${params}"
                break
            case 'POST':
                def smsToReseauxInstance = new SmsToReseaux(params)
             
                def messagesms=smsToReseauxInstance?.yourTextMessage?.sansAccent()
                def listeParam=params?.ReseauxIdsSMS?.tokenize(',')?.collect{it as Long}
                def idReseaux=toList(listeParam?:[])
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
              def listOfSMS= messagesms.mySMSList()
                listOfSMS.each{leSMS->

                   def userList=smsToReseauxInstance?.destinataires?smsToReseauxInstance?.destinataires?.flatten():reseauxIdList*.membres.flatten()
                    userList.each{ user->
                        def toPhoneNumber=(user.mobilePhone?.toString()?.getRightPhone())
                        sendSMSService.execute(leSMS,toPhoneNumber)
	                      }
                }
				if (!smsToReseauxInstance.save(flush: true)) {
					render template: 'create', model: [smsToReseauxInstance: smsToReseauxInstance]
				}
            break
        }

    }

    def show() {
        def smsToReseauxInstance = SmsToReseaux.get(params.id)
        if (!smsToReseauxInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id])
            renderList()
        }

        [smsToReseauxInstance: smsToReseauxInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def smsToReseauxInstance = SmsToReseaux.get(params.id)
                if (!smsToReseauxInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id])
                    renderList()
                }

                [smsToReseauxInstance: smsToReseauxInstance]
                break
            case 'POST':
                def smsToReseauxInstance = SmsToReseaux.get(params.id)
                if (!smsToReseauxInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (smsToReseauxInstance.version > version) {
                        smsToReseauxInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'smsToReseaux.label', default: 'SmsToReseaux')] as Object[],
                                "Another user has updated this SmsToReseaux while you were editing")
                        render template: 'edit', model: [smsToReseauxInstance: smsToReseauxInstance]
                    }
                }

                smsToReseauxInstance.properties = params

                if (!smsToReseauxInstance.save(flush: true)) {
                    render template: 'edit', model: [smsToReseauxInstance: smsToReseauxInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), smsToReseauxInstance.toString()])
                redirect action: 'show', id: smsToReseauxInstance.id
                break
        }
    }

    def delete() {
        def smsToReseauxInstance = SmsToReseaux.get(params.id)
        if (!smsToReseauxInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id])
            renderList()
        }

        try {
            smsToReseauxInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'smsToReseaux.label', default: 'SmsToReseaux'), params.id, e.message])
            render template: 'show', model:[smsToReseauxInstance: smsToReseauxInstance]
        }
    }
}
