package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import simagri.Commune


class CommuneController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }

    def list() {
         renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Commune.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Commune".uniquify(".${params.extension}"))


            List fields = ["nom","province"]
            Map labels = ["nom":"NOM","province":"PROVINCE"]
            // Formatter closure
            def humanCase = { domain, value -> return value?.humanify() }

            Map formatters = [nom:humanCase]
            Map parameters = [title: "LISTE DES COMMUNES", "column.widths": [0.6,0.4],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Commune.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" ,model:[communeInstanceList: filterPaneService.filter(params, Commune), communeInstanceTotal: filterPaneService.count(params, Commune), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [communeInstanceList: filterPaneService.filter(params, Commune),
                        communeInstanceTotal: filterPaneService.count(params, Commune),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[communeInstance: new Commune(params)]
                break
            case 'POST':
                def communeInstance = new Commune(params)
                if (!communeInstance.save(flush: true)) {
                    render template: 'create', model: [communeInstance: communeInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'commune.label', default: 'Commune'), communeInstance.toString()])
                def result=[id:communeInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def communeInstance = Commune.get(params.id)
        if (!communeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commune.label', default: 'Commune'), params.id])
            renderList()
        }

        render template:'show',model:[communeInstance: communeInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def communeInstance = Commune.get(params.id)
                if (!communeInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'commune.label', default: 'Commune'), params.id])
                    renderList()
                }

                render template:'edit',model:[communeInstance: communeInstance]
                break
            case 'POST':
                def communeInstance = Commune.get(params.id)
                if (!communeInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'commune.label', default: 'Commune'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (communeInstance.version > version) {
                        communeInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'commune.label', default: 'Commune')] as Object[],
                                "Another user has updated this Commune while you were editing")
                        render template: 'edit', model: [communeInstance: communeInstance]
                    }
                }

                communeInstance.properties = params

                if (!communeInstance.save(flush: true)) {
                    render template: 'edit', model: [communeInstance: communeInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'commune.label', default: 'Commune'), communeInstance.toString()])
                def result=[id:communeInstance.id]
                render template:'show',model:[communeInstance: communeInstance]
                break
        }
    }

    def delete() {
        def communeInstance = Commune.get(params.id)
        if (!communeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'commune.label', default: 'Commune'), params.id])
            renderList()
            return
        }

        try {
            communeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'commune.label', default: 'Commune'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'commune.label', default: 'Commune'), params.id, e.message])
            render template: 'show', model:[communeInstance: communeInstance]
        }
    }
}
