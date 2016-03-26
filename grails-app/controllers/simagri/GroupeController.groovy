package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class GroupeController {
    def exportService
    def filterPaneService
    def reseauService
    def myUtilityService

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def mettreAJour() {
        Reseau.updateParent()
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Reseau.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Groupes".uniquify(".${params.extension}"))



            List fields = ["nom","nomParent","produits","markets"]
            Map labels = ["nom":"NOM GROUPE","nomParent":"GROUPE PARENT", "produits":"PRODUITS","markets":"MARCHES"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [nom:humanCase,nomParent:humanCase]
            Map parameters = [title: "LISTE DES GROUPES", "column.widths": [0.3,0.2, 0.25, 0.25],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Reseau.list(params), fields, labels,formatters,parameters)
        }
        def user=myUtilityService.getCurrent()
        def newParams=params
        newParams=params+[ 'filter.op.estReseau' : 'Equal',filter:['op.estReseau':'Equal', op:[estReseau:'Equal'], 'estReseau':false]]
        render template:"list" ,
                model:[reseauInstanceList: filterPaneService.filter(newParams, Reseau), reseauInstanceTotal: filterPaneService.count(params, Reseau), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }
    def listAsTree = {
        def results=[]
        def resauxHolders = Reseau.createCriteria().list() {
            projections {
                createAlias('parent','parent')
                property('nom','nom')
                property('parent.nom','parent.nom')
            }
            order('nom', 'asc')
        }
        JSON.use('deep')
        render resauxHolders as JSON
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [reseauInstanceList: filterPaneService.filter(params, Reseau),
                        reseauInstanceTotal: filterPaneService.count(params, Reseau),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':

                render template:'create',model:[reseauInstance: new Reseau(params),isCreation:true]
                break
            case 'POST':
                def reseauInstance = new Reseau(params)
                def marketIds=[]
                def produitsIds=[]
                def reseauParent= Reseau.get(params?.parent)
                reseauInstance.parent=reseauParent
                params.each {
                    if (it.key.startsWith("markets_"))
                        marketIds << (it.key - "markets_")

                }
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['markets': marketIds]+ ['produits': produitsIds]
                if (!reseauService.save(newparams,reseauInstance)) {
                    flash.message =reseauInstance.errors
                     render template: 'create', model: [reseauInstance: reseauInstance,isCreation:true]

                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'groupe.label', default: 'Reseau'), reseauInstance.toString()])
                def result=[id:reseauInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def reseauInstance = Reseau.get(params.id)
        if (!reseauInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id])
            renderList()
        }
        def marketsId=reseauInstance.markets?.id
        def produitsId=reseauInstance.produits?.id
        def mesMembres=reseauInstance.membres
       render template:'show',model:[reseauInstance: reseauInstance,isCreation:false,marketsId:marketsId,produitsId:produitsId,mesMembres:mesMembres]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def reseauInstance = Reseau.get(new Long(params?.id?:"0"))
                if (!reseauInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id])
                    renderList()

                }
                def marketsId=reseauInstance.markets?.id
                def produitsId=reseauInstance.produits?.id
                def user = myUtilityService.getCurrent()
                def mesMembres
                if (user.isSuperviseur) {
                    mesMembres=Utilisateur.createCriteria().list {
                        order('nomComplet', 'asc')
                    }
                }else {
                    mesMembres=reseauInstance.membres.sort{it.nomComplet}
                }

                render template:'edit', model:[reseauInstance: reseauInstance,isCreation:false,marketsId:marketsId,produitsId:produitsId,mesMembres:mesMembres]
                break
            case 'POST':
                def reseauInstance = Reseau.get(params?.id)
                def marketIds=[]
                def produitsIds=[]
                def membresIds=[]

                def reseauParent= Reseau.get(params?.parent)
                reseauInstance.parent=reseauParent
                params.each {
                    if (it.key.startsWith("markets_"))
                        marketIds << (it.key - "markets_")

                }
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                params.each {
                    if (it.key.startsWith("membres_"))
                        membresIds << (it.key - "membres_")

                }
                def newparams=params + ['markets': marketIds]+ ['produits': produitsIds]+ ['membres': membresIds]

                if (!reseauInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id])
                    renderList()
                }

                if (newparams.version) {
                    def version = newparams.version.toLong()
                    if (reseauInstance.version > version) {
                        reseauInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'groupe.label', default: 'Reseau')] as Object[],
                                "Another user has updated this Reseau while you were editing")
                        render template: 'edit', model: [reseauInstance: reseauInstance]
                    }
                }
                if (!reseauService.update(newparams,reseauInstance)) {
                    render template: 'edit', model: [reseauInstance: reseauInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'groupe.label', default: 'Groupe'), reseauInstance.toString()])

                render template:'show', model:[reseauInstance: reseauInstance,isCreation:false,marketsId:marketIds,produitsId:produitsIds]
                break
        }
    }

    def delete() {
        def reseauInstance = Reseau.get(params.id)
        def user = myUtilityService.getCurrent()
        if (user.isAdmin) {
                ReseauMarche.removeAll(reseauInstance)
                ReseauProduit.removeAll(reseauInstance)
                if (!reseauInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id])
                    renderList()
                }

                try {
                    reseauInstance.delete(flush: true)
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id])
                    renderList()
                }
                catch (DataIntegrityViolationException e) {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'groupe.label', default: 'Reseau'), params.id, e.message])
                    render template: 'show', model:[reseauInstance: Reseau.get(params.id)]
                }
        }else {
            flash.message = message(code: 'not.authorized.toDelete')
        }
    }
}
