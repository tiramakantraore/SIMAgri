package simagri

import grails.converters.JSON
import org.grails.plugin.filterpane.FilterPaneUtils
import org.joda.time.DateTime
import org.springframework.dao.DataIntegrityViolationException

class OffreValideeController {
    def exportService
    def filterPaneService
    def myUtilityService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }

    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        Map newParams=params+[ 'filter.op.isValidated' : 'Equal',filter:['op.isValidated':'Equal', op:[isValidated:'Equal'], 'isValidated':true]]
        def offreInstanceTotal=filterPaneService.count(newParams, Offre)
        if (params?.format && params.format != "html") {
            newParams.max = offreInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: Offre.list(newParams))

        }else {
            render template:"list" ,model:[offreValideInstanceList: filterPaneService.filter(newParams, Offre), offreValideInstanceTotal: offreInstanceTotal, filterParams: FilterPaneUtils.extractFilterParams(newParams), params: newParams]

        }

    }
    def list() {
        renderList()
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

        Map parameters = [title: "LISTE DES OFFRES VALIDEES", "column.widths": [0.05, 0.19, 0.09, 0.08, 0.05, 0.20, 0.06,0.09, 0.09, 0.11]]

        response.contentType = grailsApplication.config.grails.mime.types[attrs.format]
        response.setHeader("Content-disposition", "attachment; filename="+"Offres-validees".uniquify(".${params.extension}"))


        exportService.export(attrs.format, response.outputStream, attrs.exportList, fields, labels,formatters,parameters)

    }

    def filter = {
        if (!params.max) params.max = 10
        if (params.filter) {
            params.filter.op.isValidated = "Equal"
            params.filter.isValidated = true
        }

        def offreInstanceTotal
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

    def create() {
        switch (request.method) {
            case 'GET':
                def offreInstance = new Offre(params)
                def theDate=new DateTime()
                def user=myUtilityService.getCurrent()
                offreInstance.operateur=user
                offreInstance.date=theDate.toDate()
                offreInstance.dateExpiration=new Date(theDate.plusDays(15).toDate().toString())
//                flash.message="date ${offreInstance.date} date expiration = ${offreInstance.dateExpiration}"
                [offreInstance: offreInstance]
                break
            case 'POST':
                def offreInstance = new Offre(params)
                def theDate=new DateTime()
                def user=myUtilityService.getCurrent()
                offreInstance.date=theDate.toDate()
                offreInstance.isValidated=false
                offreInstance.operateur=user
                offreInstance.isRejected=false
                if (!offreInstance.auteur)
                    offreInstance.auteur= user

                offreInstance.statut="En_cours"
                if (!offreInstance.contact)
                    offreInstance.contact=offreInstance.auteur?.mobilePhone?:""
                if (!offreInstance.save(flush: true)) {
                    render template: 'create', model: [offreInstance: offreInstance]
                }

                flash.message = message(code: 'default.created.message', args: ['', offreInstance.toString()])
                def result=[id:offreInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def offreInstance = Offre.get(params.id)
        if (!offreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'offre.label', default: 'Offre'), params.id])
            renderList()
        }

        [offreInstance: offreInstance]
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
                if (!offreInstance.contact)
                    offreInstance.contact=offreInstance.auteur?.mobilePhone?:""
                if (!offreInstance.save(flush: true)) {
                    render template: 'edit', model: [offreInstance: offreInstance]
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
            render template: 'show', model:[offreInstance: Offre.get(params.id)]
        }
    }
}
