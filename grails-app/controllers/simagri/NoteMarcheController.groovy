package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class NoteMarcheController {
    def exportService
    def filterPaneService
    def myUtilityService
    def noteMarcheCreateService

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def showImg = {
        def imageInstance = NoteMarche.get(params.id)
        def img=imageInstance?.photo // a byte[], File or InputStream
        render(file: img, contentType: 'image/png')

    }
    def populate() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
        def user=myUtilityService.getCurrent()

        def reseauPrincipal=user?.reseauPrincipal
        def noteMarcheHolders = NoteMarche.createCriteria().list(max: maxRows, offset: rowOffset) {
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

        def totalRows = noteMarcheHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = noteMarcheHolders?.collect {


            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA","${it.auteur?.username}",it.date,it.titre,"${it.contenu?:""}",it.longitude,it.latitude ], id: it.id ]
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
        def noteMarcheHolders = NoteMarche.createCriteria().list(max: maxRows, offset: rowOffset) {
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

        def totalRows = noteMarcheHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = noteMarcheHolders?.collect {


            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA","${it.auteur?.username}",it.date,it.titre,"${it.contenu?:""}",it.longitude,it.latitude,it.id], id: it.id ]
        }



        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params
        def listNoteMarche=NoteMarche.createCriteria().list(params){
            eq("actif",false)
            and {
                eq("isRejected",false)
            }
        }

        def noteMarcheInstanceTotal=listNoteMarche?.size()
        if(newParams?.format && newParams.format != "html"){
            params.max = noteMarcheInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Infos-a-valider.${params.extension}")

            List fields = [ "auteur","titre","date","longitude","latitude"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "longitude": "Longitude","latitude":"Latitude"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES NOTES A VALIDER", "column.widths": [0.18, 0.22, 0.10, 0.4,0.05,0.05],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, listNoteMarche, fields, labels,formatters,parameters)

        }else {
            render template:"list" ,
                    model: [noteMarcheInstanceList: listNoteMarche, noteMarcheInstanceTotal: noteMarcheInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }

    }
    def list() {
        renderList()
    }
    def listValidees() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]

        def noteMarcheInstanceTotal=filterPaneService.count(newParams, NoteMarche)
        if(newParams?.format && newParams.format != "html"){
            params.max = noteMarcheInstanceTotal
            newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=noteMarchesValidees.${params.extension}")

            List fields = [ "auteur","titre","date","longitude","latitude"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "longitude": "Longitude","latitude":"Latitude"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES NOTES VALIDEES", "column.widths": [0.18, 0.22, 0.10, 0.4,0.05,0.05],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, filterPaneService.filter(newParams, NoteMarche), fields, labels,formatters,parameters)

        }else {
            render template:'listValidees', model:[noteMarcheValideInstanceList: filterPaneService.filter(newParams, NoteMarche), noteMarcheValideInstanceTotal: noteMarcheInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }
    }
    def listRejetees() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params+[ 'filter.op.isRejected' : 'Equal',filter:['op.isRejected':'Equal', op:[isRejected:'Equal'], 'isRejected':true]]

        def noteMarcheInstanceTotal=filterPaneService.count(newParams, NoteMarche)
        if(newParams?.format && newParams.format != "html"){
            params.max = noteMarcheInstanceTotal
            newParams=params+[ 'filter.op.isRejected' : 'Equal',filter:['op.isRejected':'Equal', op:[isRejected:'Equal'], 'isRejected':true]]
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=noteMarchesRejetes.${params.extension}")

            List fields = [ "auteur","titre","date","longitude","latitude"]
            Map labels = ["auteur":"AUTEUR","titre":"TITRE", "date": "Date", "longitude": "Longitude","latitude":"Latitude"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES NOTES REJETEES", "column.widths": [0.18, 0.32, 0.10, 0.4],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, filterPaneService.filter(newParams, NoteMarche), fields, labels,formatters,parameters)

        }else {
            render template:'listRejetees', model:[noteMarcheInstanceList: filterPaneService.filter(newParams, NoteMarche), noteMarcheInstanceTotal: noteMarcheInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }
    }
    def listPublic() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = NoteMarche.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"NoteMarche".uniquify(".${params.extension}"))
            exportService.export(params.format, response.outputStream, NoteMarche.list(params), [:], [:])
        }
        def newParams=params+[ 'filter.op.actif' : 'Equal',filter:['op.actif':'Equal', op:[actif:'Equal'], 'actif':true]]
        def noteMarcheInstanceTotal=filterPaneService.count(newParams, NoteMarche)
        render template:'listPublic', model:[noteMarcheInstanceList: filterPaneService.filter(newParams, NoteMarche), noteMarcheInstanceTotal: noteMarcheInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

    }

    def filter = {
        if (!params.max) params.max = 10
        render template:'list',
                model: [noteMarcheInstanceList: filterPaneService.filter(params, NoteMarche),
                        noteMarcheInstanceTotal: filterPaneService.count(params, NoteMarche),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                def user=myUtilityService.getCurrent()
                def noteMarcheInstance= new NoteMarche(params)
                noteMarcheInstance.auteur=user
                [noteMarcheInstance: noteMarcheInstance]
                break
            case 'POST':
                render noteMarcheCreateService.creer(params)
        }
    }
    def saveByService() {
        if (params.updateType=="create"){
            render noteMarcheCreateService.creer(params)
        }
         else {
            render noteMarcheCreateService.modifier(params)
        }

    }
    def show() {
        def noteMarcheInstance = NoteMarche.get(params.id)
        if (!noteMarcheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
            renderList()
        }

       render template:'show', model:[noteMarcheInstance: noteMarcheInstance]
    }
    def showPublic() {
        def noteMarcheInstance = NoteMarche.get(params.id)
        if (!noteMarcheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
            renderList()
        }

        def pagesAv = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires = PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)


        render view: "showPublic", model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),noteMarcheInstance: noteMarcheInstance,
                                         pagesAv: pagesAv, pagesSIMAgri: pagesSIMAgri, pagesPartenaires: pagesPartenaires]

    }
    def abandonner={
        render noteMarcheCreateService.abandonner(params)
    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def noteMarcheInstance = NoteMarche.get(params.id)
                if (!noteMarcheInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
                    renderList()
                }

                render template:'edit',model:[noteMarcheInstance: noteMarcheInstance,update:params.update]
                break
            case 'POST':
                def noteMarcheInstance = NoteMarche.get(params.id)
                if (!noteMarcheInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (noteMarcheInstance.version > version) {
                        noteMarcheInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'noteMarche.label', default: 'Note de marché')] as Object[],
                                "Another user has updated this Info while you were editing")
                        render template: 'edit', model: [noteMarcheInstance: noteMarcheInstance,update:params.update]
                    }
                }

                noteMarcheInstance.properties = params
                noteMarcheInstance.contenu=params.contenuCKEdit

                if (!noteMarcheInstance.save(flush: true)) {
                    render template: 'edit', model: [noteMarcheInstance: noteMarcheInstance,update:params.update]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), noteMarcheInstance.toString()])
                render template: 'show', model:[noteMarcheInstance: NoteMarche.get(params.id),update:params.update]
                break
        }
    }

    def delete() {
        def noteMarcheInstance = NoteMarche.get(params.id)
        if (!noteMarcheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
            renderList()
        }

        try {
            noteMarcheInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'noteMarche.label', default: 'Note de marché'), params.id, e.message])
            render template: 'show', model:[noteMarcheInstance: NoteMarche.get(params.id)]
        }
    }
    def validerNote(){
         render template:'validerNote',model:[]

    }
    def valider= {
      render  noteMarcheCreateService.valider(params)

    }

    def rejeter= {
        render noteMarcheCreateService.rejeter(params)
    }



}
