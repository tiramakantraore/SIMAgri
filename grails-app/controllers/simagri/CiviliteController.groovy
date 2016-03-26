package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException



class CiviliteController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Civilite.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Civilite".uniquify(".${params.extension}"))
            List fields = ["libelle"]
            Map labels = ["libelle":"LIBELLE"]
            // Formatter closure
            def humanCase = { domain, value -> return value?.humanify() }

            Map formatters = [libelle:humanCase]
            Map parameters = [title: "LISTE DES CIVILITES", "column.widths": [1],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Civilite.list(params), fields, labels,formatters,parameters)

        }
        render template:"list" ,model:[civiliteInstanceList: filterPaneService.filter(params, Civilite), civiliteInstanceTotal: filterPaneService.count(params, Civilite), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [civiliteInstanceList: filterPaneService.filter(params, Civilite),
                        civiliteInstanceTotal: filterPaneService.count(params, Civilite),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[civiliteInstance: new Civilite(params)]
                break
            case 'POST':
                def civiliteInstance = new Civilite(params)
                if (!civiliteInstance.save(flush: true)) {
                    render template: 'create', model: [civiliteInstance: civiliteInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'civilite.label', default: 'Civilite'), civiliteInstance.toString()])
                def result=[id:civiliteInstance.id]
                render result as JSON
                break
        }
    }



    def edit() {
        switch (request.method) {
            case 'GET':
                def civiliteInstance = Civilite.get(params.id)
                if (!civiliteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'civilite.label', default: 'Civilite'), params.id])
                    renderList()
                }

                render template:'edit', model:[civiliteInstance: civiliteInstance]
                break
            case 'POST':
                def civiliteInstance = Civilite.get(params.id)
                if (!civiliteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'civilite.label', default: 'Civilite'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (civiliteInstance.version > version) {
                        civiliteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'civilite.label', default: 'Civilite')] as Object[],
                                "Another user has updated this Civilite while you were editing")
                        render template: 'edit', model: [civiliteInstance: civiliteInstance]
                    }
                }

                civiliteInstance.properties = params

                if (!civiliteInstance.save(flush: true)) {
                    render template: 'edit', model: [civiliteInstance: civiliteInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'civilite.label', default: 'Civilite'), civiliteInstance.toString()])
                render template:'show', model:[civiliteInstance: civiliteInstance]
                break
        }
    }

    def delete() {
        def civiliteInstance = Civilite.get(params.id)
        if (!civiliteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'civilite.label', default: 'Civilite'), params.id])
            renderList()
        }

        try {
            civiliteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'civilite.label', default: 'Civilite'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'civilite.label', default: 'Civilite'), params.id, e.message])
            redirect action: 'edit', id: params.id
        }
    }
}
