package simagri

import grails.converters.JSON
import grails.transaction.Transactional
import org.springframework.dao.DataIntegrityViolationException


class MonImageController {
    def exportService
    def filterPaneService
    def myUtilityService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = MonImage.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + MonImage.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, MonImage.list(params), [:], [:])
        }
        render template:'list', model:[monImageInstanceList: filterPaneService.filter(params, MonImage), monImageInstanceTotal: filterPaneService.count(params, MonImage), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }
    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [monImageInstanceList : filterPaneService.filter(params, MonImage),
                        monImageInstanceTotal: filterPaneService.count(params, MonImage),
                        filterParams         : org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params               : params])
    }
    def showImg = {
        def imageInstance = MonImage.get(params.id)
        def img=imageInstance?.photo // a byte[], File or InputStream
        render(file: img, contentType: 'image/png')

    }
    def renderImg = {
        def imageInstance = MonImage.get(params.id)

        response.setHeader('Cache-Control', 'no-cache')
        response.contentType = 'image/png'
        response.outputStream << imageInstance?.photo
        response.outputStream.flush()

    }
    @Transactional
    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[monImageInstance: new MonImage(params)]
                break
            case 'POST':
                def monImageInstance = new MonImage(params)
                def user=myUtilityService.getCurrent()
                monImageInstance.proprietaire=user?.reseauPrincipal
                if (!monImageInstance.save(flush: true)) {
                    render template: 'create', model: [monImageInstance: monImageInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'monImage.label', default: 'MonImage'), monImageInstance.toString()])
                def result=[id:monImageInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def monImageInstance = MonImage.get(params.id)
        if (!monImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id])
            renderList()
        }

       render template:'show', model:[monImageInstance: monImageInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def monImageInstance = MonImage.get(params.id)
                if (!monImageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id])
                    renderList()
                }

                render template:'edit',model:[monImageInstance: monImageInstance]
                break
            case 'POST':
                def monImageInstance = MonImage.get(params.id)
                if (!monImageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (monImageInstance.version > version) {
                        monImageInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'monImage.label', default: 'MonImage')] as Object[],
                                "Another user has updated this MonImage while you were editing")
                        render template: 'edit', model: [monImageInstance: monImageInstance]
                    }
                }


                def newParams= [:] << params
                newParams.remove('photo')
                monImageInstance.properties = newParams
                def photo = request.getFile('photo')
                if (photo?.getBytes()?.size() > 0) {
                    monImageInstance.photo = photo.getBytes()
                }

                if (!monImageInstance.save(flush: true)) {
                    render template: 'edit', model: [monImageInstance: monImageInstance]

                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'monImage.label', default: 'MonImage'), monImageInstance.toString()])
                render template: 'show', model: [monImageInstance: monImageInstance]
                break
        }
    }

    def delete() {
        def monImageInstance = MonImage.get(params.id)
        if (!monImageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id])
            renderList()
        }

        try {
            monImageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'monImage.label', default: 'MonImage'), params.id, e.message])
            render template: 'show', model: [monImageInstance: monImageInstance]
        }
    }
}
