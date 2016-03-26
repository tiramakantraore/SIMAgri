package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class RegionController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList(){
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Region.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Regions".uniquify(".${params.extension}"))



            List fields = ["nom"]
            Map labels = ["nom":"NOM DE LA REGION"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [nom:humanCase]
            Map parameters = [title: "LISTE DES REGIONS", "column.widths": [1],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Region.list(params), fields, labels,formatters,parameters)
        }
        def regionInstanceList=filterPaneService.filter(params, Region)
        render template:"list" , model:[regionInstanceList: regionInstanceList, regionInstanceTotal: filterPaneService.count(params, Region), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()

    }

    def filter = {
        if (!params.max) params.max = 10
        render template:'list',
                model: [regionInstanceList: filterPaneService.filter(params, Region),
                        regionInstanceTotal: filterPaneService.count(params, Region),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[regionInstance: new Region(params)]
                break
            case 'POST':
                def regionInstance = new Region(params)
                if (!regionInstance.save(flush: true)) {
                    render template: 'create', model: [regionInstance: regionInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.toString()])
                def result=[id:regionInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            renderList()
        }

        [regionInstance: regionInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def regionInstance = Region.get(params.id)
                if (!regionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
                    renderList()
                }

               render template:'edit', model:[regionInstance: regionInstance]
                break
            case 'POST':
                def regionInstance = Region.get(params.id)
                if (!regionInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (regionInstance.version > version) {
                        regionInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'region.label', default: 'Region')] as Object[],
                                "Another user has updated this Region while you were editing")
                        render template: 'edit', model: [regionInstance: regionInstance]
                    }
                }

                regionInstance.properties = params

                if (!regionInstance.save(flush: true)) {
                    render template: 'edit', model: [regionInstance: regionInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.toString()])
                render template:'show', model:[regionInstance: regionInstance]
                break
        }
    }

    def delete() {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            renderList()
        }

        try {
            regionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id, e.message])
            render template: 'show', model:[regionInstance: regionInstance]
        }
    }
}
