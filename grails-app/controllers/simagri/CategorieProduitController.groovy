package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException



class CategorieProduitController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = CategorieProduit.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"CategorieProduit".uniquify(".${params.extension}"))

            List fields = ["nom","mesure","actif","comment"]
            Map labels = ["nom":"NOM", "mesure": "UNITE DE MESURE","actif":"ACTIF","comment":"COMMENTAIRES"]
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase,nom:humanCase]

            Map parameters = [title: "LISTE DES CATEGORIES DE PRODUIT", "column.widths": [0.4, 0.1, 0.1,0.4],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, CategorieProduit.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" , model:[categorieProduitInstanceList: filterPaneService.filter(params, CategorieProduit), categorieProduitInstanceTotal: filterPaneService.count(params, CategorieProduit), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [categorieProduitInstanceList: filterPaneService.filter(params, CategorieProduit),
                        categorieProduitInstanceTotal: filterPaneService.count(params, CategorieProduit),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create', model:[categorieProduitInstance: new CategorieProduit(params)]
                break
            case 'POST':
                def categorieProduitInstance = new CategorieProduit(params)
                setMesures(categorieProduitInstance)
                if (!categorieProduitInstance.save(flush: true)) {
                    render template: 'create', model: [categorieProduitInstance: categorieProduitInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), categorieProduitInstance.toString()])
                def result=[id:categorieProduitInstance.id]
                render result as JSON
                break
        }
    }
    def voirLogo = {
        def categorieProduit = CategorieProduit.get(params.id)
        if (categorieProduit?.logo){
        response.contentType = "image/jpeg"
        response.contentLength = categorieProduit?.logo?.file.length
        response.outputStream.write(categorieProduit?.logo?.file)
        }
    }
    def show() {
        def categorieProduitInstance = CategorieProduit.get(params.id)
        if (!categorieProduitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id])
            renderList()
        }

        render template:'show',model:[categorieProduitInstance: categorieProduitInstance]
    }
    def updateByJSON(){
        if (params.id!="null"){

            def mesures=CategorieProduit.get(params.id)?.mesures

            def resultsmesures=[]
            mesures.each{
                resultsmesures <<"mesures_${it.id}"
            }
            def jsonData = [mesures: resultsmesures,isEmpty:false]
            render jsonData as JSON
        }
        else {
            render false
        }

    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def categorieProduitInstance = CategorieProduit.get(params.id)
                if (!categorieProduitInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id])
                    renderList()
                }

                render template:'edit',model: [categorieProduitInstance: categorieProduitInstance]
                break
            case 'POST':
                def categorieProduitInstance = CategorieProduit.get(params.id)
                if (!categorieProduitInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (categorieProduitInstance.version > version) {
                        categorieProduitInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'categorieProduit.label', default: 'CategorieProduit')] as Object[],
                                "Another user has updated this CategorieProduit while you were editing")
                        render template: 'edit', model: [categorieProduitInstance: categorieProduitInstance]
                    }
                }
                setMesures(categorieProduitInstance)
                categorieProduitInstance.properties=params


                if (!categorieProduitInstance.save(flush: true)) {
                    render template: 'edit', model: [categorieProduitInstance: categorieProduitInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), categorieProduitInstance.toString()])
                render template: 'show', model: [categorieProduitInstance: categorieProduitInstance]
        }
    }

    public setMesures(categorieProduitInstance) {
        def listeMesures = []


        def _toBeRemoved = (categorieProduitInstance as CategorieProduit).mesures

        // if there are phones to be removed
        if (_toBeRemoved) {
            (categorieProduitInstance as CategorieProduit).mesures.removeAll(_toBeRemoved)
        }
        params.each {
            if (it.key.startsWith("mesures_"))
                listeMesures << (it.key - "mesures_").toLong()

        }
        def mesures = Mesure.withCriteria {
            'in'('id', listeMesures)
        }
        mesures.each {
            categorieProduitInstance.addToMesures(it)
        }
    }

    def delete() {
        def categorieProduitInstance = CategorieProduit.get(params.id)
        if (!categorieProduitInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id])
            renderList()
        }

        try {
            categorieProduitInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categorieProduit.label', default: 'CategorieProduit'), params.id, e.message])
            render template: 'show', categorieProduitInstance: CategorieProduit.get(params.id)
        }
    }
}
