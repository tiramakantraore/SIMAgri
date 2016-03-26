package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class MagazinController {
    def exportService
    def filterPaneService
    def magazinService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def updateByJSON(){
        if (params.id!="null"){


            def produits=MagazinProduit.createCriteria().list {
                eq("magazin.id",new Long (params.id?:"0"))
                createAlias('produit','produit')
                projections {
                    distinct("produit.id")
                }
            }

            def resultsproduits=[]
            produits.each{
                resultsproduits <<"produits_${it}"
            }

            def jsonData = [produits:resultsproduits,isEmpty:resultsproduits.isEmpty()]
            render jsonData as JSON
        }
        else {
            render false
        }

    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {

            params.max = Magazin.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename"+"Magazin".uniquify(".${params.extension}"))


            List fields = ["code","nom","localite","description"]
            Map labels = ["code":"CODE","nom":"NOM", "localite": "LIEUX", "description": "DESCRIPTION"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }
            Map formatters = [code:humanCase]
            Map parameters = [title: "LISTE DES MAGAZINS", "column.widths": [0.1,0.3, 0.25, 0.35],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Magazin.list(params), fields, labels,formatters,parameters)

        }
        render template:"list" ,model:[magazinInstanceList: filterPaneService.filter(params, Magazin), magazinInstanceTotal: filterPaneService.count(params, Magazin), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [magazinInstanceList: filterPaneService.filter(params, Magazin),
                        magazinInstanceTotal: filterPaneService.count(params, Magazin),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
               render template:'create',model: [magazinInstance: new Magazin(params)]
                break
            case 'POST':
                def magazinInstance = new Magazin(params)
                def produitsIds=[]
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['produits': produitsIds]

                if (!magazinService.save(newparams,magazinInstance)) {
                    render template: 'create', model: [magazinInstance: magazinInstance,isCreation:true]
                }else {

                    flash.message = message(code: 'default.created.message', args: [message(code: 'magazin.label', default: 'Magazin'), magazinInstance.toString()])
                    def result=[id:magazinInstance.id]
                    render result as JSON
                }


                break
        }
    }

    def show() {
        def magazinInstance = Magazin.get(params.id)
        if (!magazinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id])
            renderList()
        }

       render template:'show',model: [magazinInstance: magazinInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def magazinInstance = Magazin.get(params.id)
                if (!magazinInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id])
                    renderList()
                }

                render template:'edit',model:[magazinInstance: magazinInstance]
                break
            case 'POST':
                def magazinInstance = Magazin.get(params.id)
                if (!magazinInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (magazinInstance.version > version) {
                        magazinInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'magazin.label', default: 'Magazin')] as Object[],
                                "Another user has updated this Magazin while you were editing")
                        render template: 'edit', model: [magazinInstance: magazinInstance]
                    }
                }
                def produitsIds=[]
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['produits': produitsIds]

                if (!magazinService.update(newparams,magazinInstance)) {
                    render template: 'edit', model: [magazinInstance: magazinInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'magazin.label', default: 'Magazin'), magazinInstance.id])
                render template: 'show', model:[magazinInstance: Magazin.get(params.id)]
                break
        }
    }

    def delete() {
        def magazinInstance = Magazin.get(params.id)
        if (!magazinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id])
            renderList()
        }

        try {
            magazinInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'magazin.label', default: 'Magazin'), params.id, e.message])
            render template: 'show',  model:[magazinInstance: magazinInstance]
        }
    }
}
