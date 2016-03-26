package simagri

import org.springframework.dao.DataIntegrityViolationException


class ContactController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Contact.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Contact.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Contact.list(params), [:], [:])
        }
        render template:"list" ,
                model:[contactInstanceList: filterPaneService.filter(params, Contact), contactInstanceTotal: filterPaneService.count(params, Contact), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [contactInstanceList: filterPaneService.filter(params, Contact),
                        contactInstanceTotal: filterPaneService.count(params, Contact),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [contactInstance: new Contact(params)]
                break
            case 'POST':
                def contactInstance = new Contact(params)
                if (!contactInstance.save(flush: true)) {
                    redirect controller:'home', action: 'contact', model: [contactInstance: contactInstance]
                 //   render template: 'create', model: [contactInstance: contactInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.toString()])
                redirect controller:'home', action: 'index'
                break
        }
    }

    def show() {
        def contactInstance = Contact.get(params.id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
            renderList()
        }

        [contactInstance: contactInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def contactInstance = Contact.get(params.id)
                if (!contactInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
                    renderList()
                }

                [contactInstance: contactInstance]
                break
            case 'POST':
                def contactInstance = Contact.get(params.id)
                if (!contactInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (contactInstance.version > version) {
                        contactInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'contact.label', default: 'Contact')] as Object[],
                                "Another user has updated this Contact while you were editing")
                        render template: 'edit', model: [contactInstance: contactInstance]
                    }
                }

                contactInstance.properties = params

                if (!contactInstance.save(flush: true)) {
                    render template: 'edit', model: [contactInstance: contactInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'contact.label', default: 'Contact'), contactInstance.toString()])
                redirect action: 'show', id: contactInstance.id
                break
        }
    }

    def delete() {
        def contactInstance = Contact.get(params.id)
        if (!contactInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
            renderList()
        }

        try {
            contactInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contact.label', default: 'Contact'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
