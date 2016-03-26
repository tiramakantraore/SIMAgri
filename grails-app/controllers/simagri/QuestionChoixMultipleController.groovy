package simagri

import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException


class QuestionChoixMultipleController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = QuestionChoixMultiple.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + QuestionChoixMultiple.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, QuestionChoixMultiple.list(params), [:], [:])
        }
        render template:"list" ,model:[questionChoixMultipleInstanceList: filterPaneService.filter(params, QuestionChoixMultiple), questionChoixMultipleInstanceTotal: filterPaneService.count(params, QuestionChoixMultiple), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [questionChoixMultipleInstanceList: filterPaneService.filter(params, QuestionChoixMultiple),
                        questionChoixMultipleInstanceTotal: filterPaneService.count(params, QuestionChoixMultiple),
                        filterParams: FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [questionChoixMultipleInstance: new QuestionChoixMultiple(params)]
                break
            case 'POST':
                def questionChoixMultipleInstance = new QuestionChoixMultiple(params)

                def _toBeRemoved = questionChoixMultipleInstance.options.findAll {!it}

                // if there are phones to be removed
                if (_toBeRemoved) {
                    questionChoixMultipleInstance.options.removeAll(_toBeRemoved)
                }

                //update my indexes
                questionChoixMultipleInstance.options.eachWithIndex(){phn, i ->
                    if(phn)
                        phn.numOrdre = i
                }
                if (!questionChoixMultipleInstance.save(flush: true)) {
                    render template: 'create', model: [questionChoixMultipleInstance: questionChoixMultipleInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), questionChoixMultipleInstance.toString()])
                redirect action: 'show', id: questionChoixMultipleInstance.id
                break
        }
    }

    def show() {
        def questionChoixMultipleInstance = QuestionChoixMultiple.get(params.id)
        if (!questionChoixMultipleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id])
            renderList()
        }

        [questionChoixMultipleInstance: questionChoixMultipleInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def questionChoixMultipleInstance = QuestionChoixMultiple.get(params.id)
                if (!questionChoixMultipleInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id])
                    renderList()
                }

                [questionChoixMultipleInstance: questionChoixMultipleInstance]
                break
            case 'POST':
                def questionChoixMultipleInstance = QuestionChoixMultiple.get(params.id)
                if (!questionChoixMultipleInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (questionChoixMultipleInstance.version > version) {
                        questionChoixMultipleInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple')] as Object[],
                                "Another user has updated this QuestionChoixMultiple while you were editing")
                        render template: 'edit', model: [questionChoixMultipleInstance: questionChoixMultipleInstance]
                    }
                }
                def aSupprimer = questionChoixMultipleInstance.options?.findAll()

                // if there are phones to be removed
                if (aSupprimer) {
                    questionChoixMultipleInstance.options.removeAll(aSupprimer)
                }
                questionChoixMultipleInstance.properties = params
                def _toBeRemoved = questionChoixMultipleInstance.options?.findAll {!it}

                // if there are phones to be removed
                if (_toBeRemoved) {
                    questionChoixMultipleInstance.options.removeAll(_toBeRemoved)
                }

                //update my indexes
                questionChoixMultipleInstance.options.eachWithIndex(){phn, i ->
                    if(phn)
                        phn.numOrdre = i
                }

                if (!questionChoixMultipleInstance.save(flush: true)) {
                    render template: 'edit', model: [questionChoixMultipleInstance: questionChoixMultipleInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), questionChoixMultipleInstance.toString()])
                redirect action: 'show', id: questionChoixMultipleInstance.id
                break
        }
    }

    def delete() {
        def questionChoixMultipleInstance = QuestionChoixMultiple.get(params.id)
        if (!questionChoixMultipleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id])
            renderList()
        }

        try {
            questionChoixMultipleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
