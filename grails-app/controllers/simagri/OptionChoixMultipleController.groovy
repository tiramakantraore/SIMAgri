package simagri

import org.springframework.dao.DataIntegrityViolationException


class OptionChoixMultipleController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = OptionChoixMultiple.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + OptionChoixMultiple.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, OptionChoixMultiple.list(params), [:], [:])
        }
        [optionChoixMultipleInstanceList: filterPaneService.filter(params, OptionChoixMultiple), optionChoixMultipleInstanceTotal: filterPaneService.count(params, OptionChoixMultiple), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [optionChoixMultipleInstanceList: filterPaneService.filter(params, OptionChoixMultiple),
                        optionChoixMultipleInstanceTotal: filterPaneService.count(params, OptionChoixMultiple),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [optionChoixMultipleInstance: new OptionChoixMultiple(params)]
                break
            case 'POST':
                def optionChoixMultipleInstance = new OptionChoixMultiple(params)
                if (!optionChoixMultipleInstance.save(flush: true)) {
                    render template: 'create', model: [optionChoixMultipleInstance: optionChoixMultipleInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), optionChoixMultipleInstance.toString()])
                redirect action: 'show', id: optionChoixMultipleInstance.id

                break
        }
    }

    def show() {
        def optionChoixMultipleInstance = OptionChoixMultiple.get(params.id)
        if (!optionChoixMultipleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id])
            renderList()
        }

        [optionChoixMultipleInstance: optionChoixMultipleInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def optionChoixMultipleInstance = OptionChoixMultiple.get(params.id)
                if (!optionChoixMultipleInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id])
                    renderList()
                }

                [optionChoixMultipleInstance: optionChoixMultipleInstance]
                break
            case 'POST':
                def optionChoixMultipleInstance = OptionChoixMultiple.get(params.id)
                if (!optionChoixMultipleInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (optionChoixMultipleInstance.version > version) {
                        optionChoixMultipleInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple')] as Object[],
                                "Another user has updated this OptionChoixMultiple while you were editing")
                        render template: 'edit', model: [optionChoixMultipleInstance: optionChoixMultipleInstance]
                    }
                }

                optionChoixMultipleInstance.properties = params

                if (!optionChoixMultipleInstance.save(flush: true)) {
                    render template: 'edit', model: [optionChoixMultipleInstance: optionChoixMultipleInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), optionChoixMultipleInstance.toString()])
                redirect action: 'show', id: optionChoixMultipleInstance.id
                break
        }
    }

    def delete() {
        def optionChoixMultipleInstance = OptionChoixMultiple.get(params.id)
        if (!optionChoixMultipleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id])
            renderList()
        }

        try {
            optionChoixMultipleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'optionChoixMultiple.label', default: 'OptionChoixMultiple'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
