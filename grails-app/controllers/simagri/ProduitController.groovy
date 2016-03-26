package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ProduitController {
    def exportService
    def produitService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Produit.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Produits".uniquify(".${params.extension}"))
            List fields = [ "code","nom","categorie","mesure"]
            Map labels = ["code": "CODE","nom":"NOM", "categorie": "CATEGORIE", "mesure": "UNITE DE MESURE"]
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase,nom:humanCase]

            Map parameters = [title: "Produits", "column.widths": [0.25, 0.35, 0.15, 0.15],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Produit.list(params), fields, labels,formatters,parameters)

        }
        render template:"list" ,model:[produitInstanceList: filterPaneService.filter(params, Produit), produitInstanceTotal: filterPaneService.count(params, Produit), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 25
        render( template:'list',
                model: [produitInstanceList: filterPaneService.filter(params, Produit),
                        produitInstanceTotal: filterPaneService.count(params, Produit),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template :'create',model:[produitInstance: new Produit(params)]
                break
            case 'POST':
                def produitInstance = new Produit(params)

                if (!produitInstance.save(flush: true)) {
                    render template: 'create', model: [produitInstance: produitInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'produit.label', default: 'Produit'), produitInstance.toString()])
                def result=[id:produitInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def produitInstance = Produit.get(params.id)
        if (!produitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
            renderList()
        }

        render template:'show',model:[produitInstance: produitInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def produitInstance = Produit.get(params.id)
                if (!produitInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
                    renderList()
                }

                render template:'edit',model:[produitInstance: produitInstance]
                break
            case 'POST':
                def produitInstance = Produit.get(params.id)
                if (!produitInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (produitInstance.version > version) {
                        produitInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'produit.label', default: 'Produit')] as Object[],
                                "Another user has updated this Produit while you were editing")
                        render template: 'edit', model: [produitInstance: produitInstance]
                    }
                }

                produitInstance.properties = params

                if (!produitInstance.save(flush: true)) {
                    render template: 'edit', model: [produitInstance: produitInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'produit.label', default: 'Produit'), produitInstance.toString()])
                render template: 'show', model:[produitInstance: produitInstance]
                break
        }
    }
    def mettreAJour() {
        Produit.updateCategorie()
        renderList()
    }
    def delete() {

        if (produitService.delete(params)){
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
            renderList()
        }else {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
            render template: 'show', model:[produitInstance: Produit.get(params.id)]
        }

    }
}
