package simagri

import grails.converters.JSON
import org.grails.plugin.filterpane.FilterPaneUtils
import org.joda.time.DateTime
import org.springframework.dao.DataIntegrityViolationException

class OffreController {
    def exportService
    def filterPaneService
    def pushOffresReseauService
    def myUtilityService
    def offerValidateService
    def offreCreateService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def rejeter= {
      render  offerValidateService.rejeter(params)
    }


    def valider= {
        render  offerValidateService.valider(params)

    }

    def validerOffre(){
       render template:'validerOffre'

    }
    def abandonner={
        render offerValidateService.abandonner(params)
    }

    def populateRejetes() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
        def user=myUtilityService.getCurrent()
        // def membresRacine=user.noeudsRacine*.tousMesMembres.id.flatten()?:[new Long("-1")]
        def reseauPrincipal=user?.reseauPrincipal
        def offerHolders = Offre.createCriteria().list(max: maxRows, offset: rowOffset) {
            eq('isRejected',true)

            if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                and {

                    operateur{
                        eq('reseauPrincipal',reseauPrincipal)
//                        inList('id',membresRacine)
                    }
                }
            }
            if (params.operateur) ilike('operateur', "%${params.auteur}%")
            order('typeOffre', 'desc')
            order('auteur', 'asc')
        }

        def totalRows = offerHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = offerHolders?.collect {

            if (params.auteur) ilike('auteur', "%${params.auteur}%")
            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.typeOffre,"${it.auteur?.username} ${it.contact}",it.produit.nom,"${it.quantite} ${it.mesure?.code}",it.prixUnitaireGros,it.prixTotalGros,it.date,it.commentaire], id: it.id ]
        }

        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }

    def populate() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
        def user=myUtilityService.getCurrent()
       // def membresRacine=user.noeudsRacine*.tousMesMembres.id.flatten()?:[new Long("-1")]
        def reseauPrincipal=user?.reseauPrincipal
        def offerHolders = Offre.createCriteria().list(max: maxRows, offset: rowOffset) {
            eq('isValidated',false)
            and {
                eq("isRejected",false)

            }
            if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                and {

                    operateur{
                        eq('reseauPrincipal',reseauPrincipal)
//                        inList('id',membresRacine)
                    }
                }
            }
            order('date', 'desc')
            order('typeOffre', 'desc')
            order('auteur', 'asc')
        }

        def totalRows = offerHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = offerHolders?.collect {

            if (params.auteur) ilike('auteur', "%${params.auteur}%")
            [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.typeOffre,"${it.auteur?.username} ${it.contact}",it.produit.nom,"${it.quantite} ${it.mesure?.code}",it.prixUnitaireGros,it.prixTotalGros,it.date,it.commentaire], id: it.id ]
        }

        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }

    def executeAlerteOffre={
        pushOffresReseauService.fire()


    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def listOffres=Offre.createCriteria().list(params){
            eq("isValidated",false)
            and {
                eq("isRejected",false)
            }
        }

        def offreInstanceTotal=listOffres?.size()

        if (params?.format && params.format != "html") {

            export(response: response, extension: params.extension, format: params.format, exportList: listOffres)

        }else {
            render template:"list" , model:[offreInstanceList: listOffres, offreInstanceTotal: offreInstanceTotal, params: params]

        }

    }

    def list() {
        renderList()
    }

    def listValidees() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        Map newParams=params+[ 'filter.op.isValidated' : 'Equal',filter:['op.isValidated':'Equal', op:[isValidated:'Equal'], 'isValidated':true]]
        def offreInstanceTotal=filterPaneService.count(newParams, Offre)
        if (params?.format && params.format != "html") {
            newParams.max = offreInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: Offre.list(newParams))

        }
        render template:"list" ,model:[offreValideInstanceList: filterPaneService.filter(newParams, Offre), offreValideInstanceTotal: offreInstanceTotal, filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams]

    }

    def export = {attrs ->

        def response = attrs.response

        List fields = [ "typeOffre","auteur","contact","date","statutOffre","produit","quantite","mesure","prixUnitGrosDetail","montantGlobalGrosDetail"]
        Map labels = ["typeOffre": "TYPE","auteur":"AUTEUR","contact":"CONTACT","date":"DATE", "statutOffre": "STATUT", "produit": "PRODUIT",
                "quantite":"QTE","mesure":"UNITE MESURE","prixUnitGrosDetail":"PRIX UNITAIRE",
                "montantGlobalGrosDetail":"MONTANT TOTAL"]

        def humanCase = {
            domain, value ->
                return value?.humanify()
        }
        def dateFormat ={
            domain, value ->
                return value?.getAsDay()
        }


        Map formatters = [typeOffre:humanCase,date:dateFormat]

        Map parameters = [title: "LISTE DES OFFRES A VALIDER", "column.widths": [0.05, 0.19, 0.09, 0.08, 0.05, 0.20, 0.06,0.09, 0.09, 0.11]]

        response.contentType = grailsApplication.config.grails.mime.types[attrs.format]
        response.setHeader("Content-disposition", "attachment; filename="+"Offres-a-valider".uniquify(".${params.extension}"))


        exportService.export(attrs.format, response.outputStream, attrs.exportList, fields, labels,formatters,parameters)

    }

    def filter = {
        if (!params.max) params.max = 10
        if (params.filter) {
            params.filter.op.isValidated = "Equal"
            params.filter.isValidated = false
            params.filter.op.isRejected = "Equal"
            params.filter.isRejected = false
        }

        def offreInstanceTotal=filterPaneService.count(params, Offre)
        def offreInstanceList
        def filterParams=params
        if(params?.format && params.format != "html" && session.filterParams)
        {
            offreInstanceTotal=filterPaneService.count(session.filterParams, Offre)
            session.filterParams.max = offreInstanceTotal
            offreInstanceList= filterPaneService.filter(session.filterParams, Offre)

            export(response: response, extension: params.extension, format: params.format, exportList: offreInstanceList)
        }
        else{
            offreInstanceList= filterPaneService.filter(params, Offre)
            session.filterParams = params
        }

        render( template:'list',
                model: [offreInstanceList: offreInstanceList,
                        offreInstanceTotal: offreInstanceTotal,
                        params: params])
    }
    def filterValidees = {
        if (!params.max) params.max = 10
        if (params.filter) {
            params.filter.op.isValidated = "Equal"
            params.filter.isValidated = true
        }

        def offreInstanceTotal=filterPaneService.count(params, Offre)
        def offreInstanceList
        def filterParams=params
        if(params?.format && params.format != "html" && session.filterParams)
        {
            offreInstanceTotal=filterPaneService.count(session.filterParams, Offre)
            session.filterParams.max = offreInstanceTotal
            offreInstanceList= filterPaneService.filter(session.filterParams, Offre)

            export(response: response, extension: params.extension, format: params.format, exportList: offreInstanceList)
        }
        else{
            offreInstanceList= filterPaneService.filter(params, Offre)
            session.filterParams = params
        }




        render( template:'list',
                model: [offreInstanceList: offreInstanceList,
                        offreInstanceTotal: filterPaneService.count(params, Offre),
                        params: params])
    }



    def rss = {
        // Chargement des albums


        def sortIndex='date'
        def sortOrder='desc'
        def offresTop10 = Offre.createCriteria().list(max:10) {
            eq("isValidated", true)
            and {
                ge("dateExpiration", new Date())

            }
            order(sortIndex, sortOrder)
        }


        // la méthode render est disponible sur tous les contrôleurs
        render(feedType:"rss", feedVersion:"2.0") {
            title = "Flux RSS sur les offres" // le titre du flux
            link = createLink (controller:'offre', action:'listEnCours', absolute:true) // le lien pour aller sur la liste des albums
            description = "Flux RSS sur les offres" // la description globale du flux

            offresTop10.each() { album -> // pour chaque album dans la liste
                entry(offresTop10.intitule) { // créer une entrée
                    link = createLink(controller:'album', action:'show', id:album.id, absolute:true)
                    // le lien de l'entrée (vers le site en absolu)
                    album.toString() // et le corps de l'entrée
                }
            }
        }
    }


    def create() {
        switch (request.method) {
            case 'GET':
                def offreInstance = new Offre(params)
                def theDate=new DateTime()
                def user=myUtilityService.getCurrent()
                offreInstance.operateur=user
                offreInstance.date=theDate.toDate()
                offreInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
                offreInstance.statut="En_cours"
                [offreInstance: offreInstance]
                break
            case 'POST':
                def offreInstance = new Offre(params)
                def theDate=new DateTime()
                def theDateOfParams=new Date(params?.date?.toString())
                def user=myUtilityService.getCurrent()
                if (theDateOfParams>theDate.toDate())
                offreInstance.date=theDate.toDate()
                if (offreInstance.date>offreInstance.dateExpiration)
                offreInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
                offreInstance.isValidated=false
                offreInstance.operateur=user
                offreInstance.isRejected=false
                if (!offreInstance.auteur)
                    offreInstance.auteur= user
                offreInstance.statut="En_cours"
                if (!offreInstance.contact)
                    offreInstance.contact=offreInstance.auteur?.mobilePhone?:""

                bindprices(offreInstance)

                offreInstance.reseau=user?.reseauPrincipal
                if (!offreInstance.save(flush: true)) {
                    render template: 'create', model: [offreInstance: offreInstance]
                }

                flash.message = message(code: 'default.created.message', args: ['', offreInstance.toString()])
                def result=[id:offreInstance.id]
                render result as JSON
                break
        }
    }

    def saveByService() {
        render offreCreateService.creer(params)
    }
    def resume(){

    }
    public bindprices(Offre offreInstance) {
        if (params?.prixUnitaire == null)
            offreInstance.prixUnitaire = new BigDecimal("0.0")
        else
            offreInstance.prixUnitaire = new BigDecimal(params?.prixUnitaire ?: "0.0")


        if (params?.prixTotal == null)
            offreInstance.prixTotal = new BigDecimal("0.0")
        else
            offreInstance.prixTotal = new BigDecimal(params?.prixTotal ?: "0.0")

        if (params?.prixUnitaireGros == null)
            offreInstance.prixUnitaireGros = new BigDecimal("0.0")
        else
            offreInstance.prixUnitaireGros = new BigDecimal(params?.prixUnitaireGros ?: "0.0")
        if (params?.prixTotalGros == null)
            offreInstance.prixTotalGros = new BigDecimal("0.0")
        else
            offreInstance.prixTotalGros = new BigDecimal(params?.prixTotalGros ?: "0.0")
    }

    def show() {
        def offreInstance = Offre.get(params.id)
        if (!offreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
            renderList()
        }

        render template:'show',model:[offreInstance: offreInstance]
    }
    def showPub() {
        def offreInstance = Offre.get(params.id)
        if (!offreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
            renderList()
        }

        [offreInstance: offreInstance]
    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def offreInstance = Offre.get(params.id)
                if (!offreInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
                    renderList()
                }

                render template:'edit',model:[offreInstance: offreInstance,update:params.update]
                break
            case 'POST':
                def offreInstance = Offre.get(params.id)
                if (!offreInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (offreInstance.version > version) {
                        offreInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'offre.label', default: 'Offre')] as Object[],
                                "Another user has updated this Offre while you were editing")
                        render template: 'edit', model: [offreInstance: offreInstance]
                    }
                }

                offreInstance.properties = params
                bindprices(offreInstance)
                if (!offreInstance.contact)
                    offreInstance.contact=offreInstance.auteur?.mobilePhone?:""
                if (!offreInstance.save(flush: true)) {
                    render template: 'edit', model: [offreInstance: offreInstance,update:params.update]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'offre.label', default: 'Offre'), offreInstance.toString()])
                render template: 'show', model:[offreInstance: Offre.get(params.id),update:params.update]
                break
        }
    }

    def delete() {
        def offreInstance = Offre.get(params.id)
        if (!offreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
            renderList()
        }

        try {
            offreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'offre.label', default: 'Offre'), params.id, e.message])
            render template: 'show',model:[offreInstance:offreInstance]
        }
    }
}
