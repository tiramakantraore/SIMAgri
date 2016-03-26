package simagri

import grails.converters.JSON
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException


class SondageController {
    def exportService
    def filterPaneService
    def sondageService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def saveByService(){
         render sondageService.execute(params)
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Sondage.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Sondage.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Sondage.list(params), [:], [:])
        }
        render template:"list" , model:[sondageInstanceList: filterPaneService.filter(params, Sondage), sondageInstanceTotal: filterPaneService.count(params, Sondage), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }
    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [sondageInstanceList: filterPaneService.filter(params, Sondage),
                        sondageInstanceTotal: filterPaneService.count(params, Sondage),
                        filterParams: FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [sondageInstance: new Sondage(params),numero:1]
                break
            case 'POST':
                def sondageInstance = new Sondage(params)
                Sondage.list().each{
                    it.actif=false
                    it.save();
                }
                sondageInstance.actif=true;
                def _toBeRemoved = sondageInstance.details?.findAll {!it}

                // if there are phones to be removed
                if (_toBeRemoved) {
                    sondageInstance.details.removeAll(_toBeRemoved)
                }
                //update my indexes
                sondageInstance.details.eachWithIndex(){phn, i ->
                    if(phn)
                        phn.numOrdre = i
                }
                if (!sondageInstance.save(flush: true)) {
                    render template: 'create', model: [sondageInstance: sondageInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'sondage.label', default: 'Sondage'), sondageInstance.toString()])
                def result=[id:sondageInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def sondageInstance = Sondage.get(params.id)
        if (!sondageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id])
            renderList()
        }

        [sondageInstance: sondageInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def sondageInstance = Sondage.get(params.id)
                if (!sondageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id])
                    renderList()
                }

                [sondageInstance: sondageInstance]
                break
            case 'POST':
                def sondageInstance = Sondage.get(params.id)
                if (sondageInstance.actif==null)
                    sondageInstance.actif=false
                if (!sondageInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (sondageInstance.version > version) {
                        sondageInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'sondage.label', default: 'Sondage')] as Object[],
                                "Another user has updated this Sondage while you were editing")
                        render template: 'edit', model: [sondageInstance: sondageInstance]
                    }
                }
                Sondage.list().each{
                    it.actif=false
                    it.save();
                }
                sondageInstance.actif=params.actif;
                sondageInstance.properties = params
                def _toBeRemoved = sondageInstance.details.findAll {!it}

                // if there are phones to be removed
                if (_toBeRemoved) {
                    sondageInstance.details.removeAll(_toBeRemoved)
                }

                //update my indexes
                sondageInstance.details.eachWithIndex(){phn, i ->
                    if(phn)
                        phn.numOrdre = i
                }
                if (!sondageInstance.save(flush: true)) {
                    render template: 'edit', model: [sondageInstance: sondageInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'sondage.label', default: 'Sondage'), sondageInstance.toString()])
                redirect action: 'show', id: sondageInstance.id
                break
        }
    }

    def delete() {
        def sondageInstance = Sondage.get(params.id)
        if (!sondageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id])
            renderList()
        }

        try {
            sondageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sondage.label', default: 'Sondage'), params.id, e.message])
            render template: 'show', model:[sondageInstance: sondageInstance]
        }
    }
}
