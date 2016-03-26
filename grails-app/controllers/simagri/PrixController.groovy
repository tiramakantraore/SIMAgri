package simagri

import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException


class PrixController {
    def exportService
    def filterPaneService

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def prixInstanceTotal=filterPaneService.count(params, Prix)
        if (params?.format && params.format != "html") {
            params.max = prixInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: Prix.list(params))

        }else {
            render template:"list" ,model:[prixInstanceList: filterPaneService.filter(params, Prix), prixInstanceTotal: prixInstanceTotal, filterParams:  FilterPaneUtils.extractFilterParams(params), params: params]

        }


    }
    def list() {
        renderList()
    }

    def export = {attrs ->

            def response = attrs.response
            params.max = Prix.count()

            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Prix-valides".uniquify(".${params.extension}"))
            List fields = [ "sourcePrix","date","categorieTarifaire","marche","produit","prixMesure"]
            Map labels = ["sourcePrix":"SOURCE","date":"DATE", "categorieTarifaire": "CATEGORIE",
                     "marche": "MARCHE", "produit": "PRODUIT",
                    "prixMesure":"Prix"]

            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [:]

            Map parameters = [title: "LISTE DES PRIX VALIDES", "column.widths": [0.15, 0.12, 0.2, 0.20, 0.32, 0.10]]

            exportService.export(params.format, response.outputStream, Prix.list(params), fields, labels,formatters,parameters)

        }


    def filter = {
        if (!params.max) params.max = 10


        def prixInstanceTotal
        def prixInstanceList
        if(params?.format && params.format != "html" && session.filterParams)
        {
            prixInstanceTotal=filterPaneService.count(session.filterParams, Prix)
            session.filterParams.max = prixInstanceTotal
            prixInstanceList= filterPaneService.filter(session.filterParams, Prix)
            export(response: response, extension: params.extension, format: params.format, exportList: prixInstanceList)
        }
        else{
            prixInstanceList= filterPaneService.filter(params, Prix)
            prixInstanceTotal=filterPaneService.count(params, Prix)
            session.filterParams = params
            render template:'list',
                    model: [
                            prixInstanceList: prixInstanceList,
                            prixInstanceTotal: prixInstanceTotal,
                            params: params]
        }


    }

    def create() {
        switch (request.method) {
            case 'GET':
                [prixInstance: new Prix(params)]
                break
            case 'POST':
                def prixInstance = new Prix(params)
                if (!prixInstance.save(flush: true)) {
                    render template: 'create', model: [prixInstance: prixInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'prix.label', default: 'Prix'), prixInstance.toString()])
                render template: 'show', model:[prixInstance: Prix.get(params.id)]
                break
        }
    }

    def show() {
        def prixInstance = Prix.get(params.id)
        if (!prixInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prix.label', default: 'Prix'), params.id])
            renderList()
        }

        render template:'show',model:[prixInstance: prixInstance]
    }
    public bindprices(Prix prixInstance) {
        if (params?.montant == null)
            prixInstance.montant = new BigDecimal("0.0")
        else
            prixInstance.montant = new BigDecimal(params?.montant ?: "0.0")


    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def prixInstance = Prix.get(params.id)
                if (!prixInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'prix.label', default: 'Prix'), params.id])
                    renderList()
                }

                render template:'edit',model:[prixInstance: prixInstance,update:params.update]
                break
            case 'POST':
                def prixInstance = Prix.get(params.id)
                if (!prixInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'prix.label', default: 'Prix'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (prixInstance.version > version) {
                        prixInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'prix.label', default: 'Prix')] as Object[],
                                "Another user has updated this Prix while you were editing")
                        render template: 'edit', model: [prixInstance: prixInstance]
                    }
                }

                prixInstance.properties = params
                bindprices(prixInstance)
                if (!prixInstance.save(flush: true)) {
                    render template: 'edit', model: [prixInstance: prixInstance,update:params.update]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'prix.label', default: 'Prix'), prixInstance.toString()])
                render template: 'show', model:[prixInstance: Prix.get(params.id),update:params.update]
                break
        }
    }

    def delete() {
        def prixInstance = Prix.get(params.id)
        if (!prixInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'prix.label', default: 'Prix'), params.id])
            renderList()
        }

        try {
            prixInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'prix.label', default: 'Prix'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'prix.label', default: 'Prix'), params.id, e.message])
            render template:'show',model:[prixInstance: prixInstance]
        }
    }
}
