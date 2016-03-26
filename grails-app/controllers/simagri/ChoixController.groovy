package simagri

import org.springframework.dao.DataIntegrityViolationException


class ChoixController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Choix.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Choix.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Choix.list(params), [:], [:])
        }
        render template:"list" ,
                [choixInstanceList: filterPaneService.filter(params, Choix), choixInstanceTotal: filterPaneService.count(params, Choix), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [choixInstanceList: filterPaneService.filter(params, Choix),
                        choixInstanceTotal: filterPaneService.count(params, Choix),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [choixInstance: new Choix(params)]
                break
            case 'POST':
                def choixInstance = new Choix(params)
                if (!choixInstance.save(flush: true)) {
                    render template: 'create', model: [choixInstance: choixInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'choix.label', default: 'Choix'), choixInstance.toString()])
                redirect action: 'show', id: choixInstance.id
                break
        }
    }

    def show() {
        def choixInstance = Choix.get(params.id)
        if (!choixInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'choix.label', default: 'Choix'), params.id])
            renderList()
        }

        [choixInstance: choixInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def choixInstance = Choix.get(params.id)
                if (!choixInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'choix.label', default: 'Choix'), params.id])
                    renderList()
                }

                [choixInstance: choixInstance]
                break
            case 'POST':
                def choixInstance = Choix.get(params.id)
                if (!choixInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'choix.label', default: 'Choix'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (choixInstance.version > version) {
                        choixInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'choix.label', default: 'Choix')] as Object[],
                                "Another user has updated this Choix while you were editing")
                        render template: 'edit', model: [choixInstance: choixInstance]
                    }
                }

                choixInstance.properties = params

                if (!choixInstance.save(flush: true)) {
                    render template: 'edit', model: [choixInstance: choixInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'choix.label', default: 'Choix'), choixInstance.toString()])
                redirect action: 'show', id: choixInstance.id
                break
        }
    }

    def delete() {
        def choixInstance = Choix.get(params.id)
        if (!choixInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'choix.label', default: 'Choix'), params.id])
            renderList()
        }

        try {
            choixInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'choix.label', default: 'Choix'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'choix.label', default: 'Choix'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
