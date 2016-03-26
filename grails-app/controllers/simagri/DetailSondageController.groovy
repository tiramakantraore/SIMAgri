package simagri

import org.springframework.dao.DataIntegrityViolationException


class DetailSondageController {
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
        [detailSondageInstanceList: filterPaneService.filter(params, Choix), detailSondageInstanceTotal: filterPaneService.count(params, Choix), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Choix.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Choix.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Choix.list(params), [:], [:])
        }
        [detailSondageInstanceList: filterPaneService.filter(params, Choix), detailSondageInstanceTotal: filterPaneService.count(params, Choix), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [detailSondageInstanceList: filterPaneService.filter(params, Choix),
                        detailSondageInstanceTotal: filterPaneService.count(params, Choix),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [detailSondageInstance: new Choix(params)]
                break
            case 'POST':
                def detailSondageInstance = new Choix(params)
                if (!detailSondageInstance.save(flush: true)) {
                    render template: 'create', model: [detailSondageInstance: detailSondageInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'detailSondage.label', default: 'Choix'), detailSondageInstance.toString()])
                redirect action: 'show', id: detailSondageInstance.id
                break
        }
    }

    def show() {
        def detailSondageInstance = Choix.get(params.id)
        if (!detailSondageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id])
            renderList()
        }

        [detailSondageInstance: detailSondageInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def detailSondageInstance = Choix.get(params.id)
                if (!detailSondageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id])
                    renderList()
                }

                [detailSondageInstance: detailSondageInstance]
                break
            case 'POST':
                def detailSondageInstance = Choix.get(params.id)
                if (!detailSondageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (detailSondageInstance.version > version) {
                        detailSondageInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'detailSondage.label', default: 'Choix')] as Object[],
                                "Another user has updated this Choix while you were editing")
                        render template: 'edit', model: [detailSondageInstance: detailSondageInstance]
                    }
                }

                detailSondageInstance.properties = params

                if (!detailSondageInstance.save(flush: true)) {
                    render template: 'edit', model: [detailSondageInstance: detailSondageInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'detailSondage.label', default: 'Choix'), detailSondageInstance.toString()])
                redirect action: 'show', id: detailSondageInstance.id
                break
        }
    }

    def delete() {
        def detailSondageInstance = Choix.get(params.id)
        if (!detailSondageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id])
            renderList()
        }

        try {
            detailSondageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'detailSondage.label', default: 'Choix'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
