package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException



class EntrepriseController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Entreprise.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Entreprise".uniquify(".${params.extension}"))
            exportService.export(params.format, response.outputStream, Entreprise.list(params), [:], [:])
        }
        render template:"list" , model:[entrepriseInstanceList: filterPaneService.filter(params, Entreprise), entrepriseInstanceTotal: filterPaneService.count(params, Entreprise), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [entrepriseInstanceList: filterPaneService.filter(params, Entreprise),
                        entrepriseInstanceTotal: filterPaneService.count(params, Entreprise),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[entrepriseInstance: new Entreprise(params)]
                break
            case 'POST':
                def entrepriseInstance = new Entreprise(params)
                if (!entrepriseInstance.save(flush: true)) {
                    render template: 'create', model: [entrepriseInstance: entrepriseInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), entrepriseInstance.toString()])
                def result=[id:entrepriseInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def entrepriseInstance = Entreprise.get(params.id)
        if (!entrepriseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id])
            renderList()
        }

        render template:show,model:[entrepriseInstance: entrepriseInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def entrepriseInstance = Entreprise.get(params.id)
                if (!entrepriseInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id])
                    renderList()
                }

               render template:'edit',model:[entrepriseInstance: entrepriseInstance]
                break
            case 'POST':
                def entrepriseInstance = Entreprise.get(params.id)
                if (!entrepriseInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (entrepriseInstance.version > version) {
                        entrepriseInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'entreprise.label', default: 'Entreprise')] as Object[],
                                "Another user has updated this Entreprise while you were editing")
                        render template: 'edit', model: [entrepriseInstance: entrepriseInstance]
                    }
                }

                entrepriseInstance.properties = params

                if (!entrepriseInstance.save(flush: true)) {
                    render template: 'edit', model: [entrepriseInstance: entrepriseInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), entrepriseInstance.toString()])
                render template: 'show',  model: [entrepriseInstance: Entreprise.get(params.id)]
                break
        }
    }

    def delete() {
        def entrepriseInstance = Entreprise.get(params.id)
        if (!entrepriseInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id])
            renderList()
        }

        try {
            entrepriseInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entreprise.label', default: 'Entreprise'), params.id, e.message])
            redirect action: 'show', model:[id: params.id]
        }
    }
}
