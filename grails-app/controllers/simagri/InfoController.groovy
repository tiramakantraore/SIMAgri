package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class InfoController {
    def exportService
    def filterPaneService
    def myUtilityService
    def infosCreateService

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def populate() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
        def user=myUtilityService.getCurrent()

        def reseauPrincipal=user?.reseauPrincipal
        def infosHolders = Info.createCriteria().list(max: maxRows, offset: rowOffset) {
            eq('actif',false)
            if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                and {

                    auteur{
                        eq('reseauPrincipal',reseauPrincipal)
                    }
                }
                and {
                   eq('isRejected',false)
                }
            }
            order('date', 'desc')
            order('auteur', 'asc')
        }

        def totalRows = infosHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = infosHolders?.collect {


            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA","${it.auteur?.username}",it.date,it.titre,"${it.contenu?:""}",it.url ], id: it.id ]
        }



        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }
    def populateRejetes() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
        def user=myUtilityService.getCurrent()

        def reseauPrincipal=user?.reseauPrincipal
        def infosHolders = Info.createCriteria().list(max: maxRows, offset: rowOffset) {
            eq('actif',false)
            if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                and {

                    auteur{
                        eq('reseauPrincipal',reseauPrincipal)
                    }
                }

            }
            and {
                eq('isRejected',true)
            }
            order('date', 'desc')
            order('auteur', 'asc')
        }

        def totalRows = infosHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = infosHolders?.collect {


            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA","${it.auteur?.username}",it.date,it.titre,"${it.contenu?:""}",it.url ], id: it.id ]
        }



        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params
        def listInfos=Info.createCriteria().list(params){
            eq("actif",false)
            and {
                eq("isRejected",false)
            }
        }

        def infoInstanceTotal=listInfos?.size()
        if(newParams?.format && newParams.format != "html"){
            params.max = infoInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Infos-a-valider.${params.extension}")

            List fields = [ "auteur","titre","date","url"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "url": "URL"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES INFORMATIONS A VALIDER", "column.widths": [0.18, 0.32, 0.10, 0.4],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, listInfos, fields, labels,formatters,parameters)

        }else {
            render template:"list" ,
                    model: [infoInstanceList: listInfos, infoInstanceTotal: infoInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }

    }
    def list() {
        renderList()
    }
    def listValidees() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]

        def infoInstanceTotal=filterPaneService.count(newParams, Info)
        if(newParams?.format && newParams.format != "html"){
            params.max = infoInstanceTotal
            newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=InfosValidees.${params.extension}")

            List fields = [ "auteur","titre","date","url"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "url": "URL"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES INFORMATIONS VALIDEES", "column.widths": [0.18, 0.32, 0.10, 0.4],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, filterPaneService.filter(newParams, Info), fields, labels,formatters,parameters)

        }else {
            render template:'listValidees', model:[infoValideInstanceList: filterPaneService.filter(newParams, Info), infoValideInstanceTotal: infoInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }
    }
    def listRejetees() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params+[ 'filter.op.isRejected' : 'Equal',filter:['op.isRejected':'Equal', op:[isRejected:'Equal'], 'isRejected':true]]

        def infoInstanceTotal=filterPaneService.count(newParams, Info)
        if(newParams?.format && newParams.format != "html"){
            params.max = infoInstanceTotal
            newParams=params+[ 'filter.op.isRejected' : 'Equal',filter:['op.isRejected':'Equal', op:[isRejected:'Equal'], 'isRejected':true]]
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=InfosRejetes.${params.extension}")

            List fields = [ "auteur","titre","date","url"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "url": "URL"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES INFORMATIONS REJETEES", "column.widths": [0.18, 0.32, 0.10, 0.4],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, filterPaneService.filter(newParams, Info), fields, labels,formatters,parameters)

        }else {
            render template:'listRejetees', model:[infoInstanceList: filterPaneService.filter(newParams, Info), infoInstanceTotal: infoInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }
    }
    def listPublic() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Info.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Info".uniquify(".${params.extension}"))
            exportService.export(params.format, response.outputStream, Info.list(params), [:], [:])
        }
        def newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]
        def infoInstanceTotal=filterPaneService.count(newParams, Info)
        render template:'listPublic', model:[infoInstanceList: filterPaneService.filter(newParams, Info), infoInstanceTotal: infoInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

    }

    def filter = {
        if (!params.max) params.max = 10
        render template:'list',
                model: [infoInstanceList: filterPaneService.filter(params, Info),
                        infoInstanceTotal: filterPaneService.count(params, Info),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                def user=myUtilityService.getCurrent()
                def infoInstance= new Info(params)
                infoInstance.auteur=user
                [infoInstance: infoInstance]
                break
            case 'POST':
                render infosCreateService.creer(params)
        }
    }
    def saveByService() {
        if (params.updateType=="create"){
            render infosCreateService.creer(params)
        }
         else {
            render infosCreateService.modifier(params)
        }

    }
    def show() {
        def infoInstance = Info.get(params.id)
        if (!infoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'info.label', default: 'Info'), params.id])
            renderList()
        }

       render template:'show', model:[infoInstance: infoInstance]
    }
    def showPublic() {
        def infoInstance = Info.get(params.id)
        if (!infoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'info.label', default: 'Info'), params.id])
            renderList()
        }

        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)


        render view: "showPublic", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),infoInstance: infoInstance,
                                         pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires]

    }
    def abandonner={
        render infosCreateService.abandonner(params)
    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def infoInstance = Info.get(params.id)
                if (!infoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'info.label', default: 'Info'), params.id])
                    renderList()
                }

                render template:'edit',model:[infoInstance: infoInstance,update:params.update]
                break
            case 'POST':
                def infoInstance = Info.get(params.id)
                if (!infoInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'info.label', default: 'Info'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (infoInstance.version > version) {
                        infoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'info.label', default: 'Info')] as Object[],
                                "Another user has updated this Info while you were editing")
                        render template: 'edit', model: [infoInstance: infoInstance,update:params.update]
                    }
                }

                infoInstance.properties = params
                infoInstance.contenu=params.contenuCKEdit

                if (!infoInstance.save(flush: true)) {
                    render template: 'edit', model: [infoInstance: infoInstance,update:params.update]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'info.label', default: 'Info'), infoInstance.toString()])
                render template: 'show', model:[infoInstance: Info.get(params.id),update:params.update]
                break
        }
    }

    def delete() {
        def infoInstance = Info.get(params.id)
        if (!infoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'info.label', default: 'Info'), params.id])
            renderList()
        }

        try {
            infoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'info.label', default: 'Info'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'info.label', default: 'Info'), params.id, e.message])
            render template: 'show', model:[infoInstance: Info.get(params.id)]
        }
    }
    def validerInfo(){
         render template:'validerInfo',model:[]

    }
    def valider= {
      render  infosCreateService.valider(params)

    }

    def rejeter= {
        render infosCreateService.rejeter(params)
    }



}
