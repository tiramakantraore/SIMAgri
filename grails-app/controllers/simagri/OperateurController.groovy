package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class OperateurController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Operateur.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Operateur.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Operateur.list(params), [:], [:])
        }
        render template:"list" ,model:[operateurInstanceList: filterPaneService.filter(params, Operateur), operateurInstanceTotal: filterPaneService.count(params, Operateur), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [operateurInstanceList: filterPaneService.filter(params, Operateur),
                        operateurInstanceTotal: filterPaneService.count(params, Operateur),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [operateurInstance: new Operateur(params)]
                break
            case 'POST':
                def operateurInstance = new Operateur(params)
                if (!operateurInstance.save(flush: true)) {
                    render template: 'create', model: [operateurInstance: operateurInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'operateur.label', default: 'Operateur'), operateurInstance.toString()])
                def result=[id:operateurInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def operateurInstance = Operateur.get(params.id)
        if (!operateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id])
            renderList()
        }

        [operateurInstance: operateurInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def operateurInstance = Operateur.get(params.id)
                if (!operateurInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id])
                    renderList()
                }

               render template:'edit',model: [operateurInstance: operateurInstance]
                break
            case 'POST':
                def operateurInstance = Operateur.get(params.id)
                if (!operateurInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (operateurInstance.version > version) {
                        operateurInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'operateur.label', default: 'Operateur')] as Object[],
                                "Another user has updated this Operateur while you were editing")
                        render template: 'edit', model: [operateurInstance: operateurInstance]
                    }
                }

                operateurInstance.properties = params

                if (!operateurInstance.save(flush: true)) {
                    render template: 'edit', model: [operateurInstance: operateurInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'operateur.label', default: 'Operateur'), operateurInstance.toString()])
                render template: 'show', operateurInstance: Operateur.get(params.id)
                break
        }
    }

    def delete() {
        def operateurInstance = Operateur.get(params.id)
        if (!operateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id])
            renderList()
        }

        try {
            operateurInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'operateur.label', default: 'Operateur'), params.id, e.message])
            render template: 'show', model:[operateurInstance: operateurInstance]
        }
    }
}
