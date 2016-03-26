package simagri

import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException


class PriceHolderController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def  priceHolderInstanceTotal=PriceHolder.findAllByIsValidatedAndIsRejected(false,false)?.size()
        def priceHolderInstanceList= PriceHolder.findAllByIsValidatedAndIsRejected(false,false,params)
        if (params?.format && params.format != "html") {
            params.max = priceHolderInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: priceHolderInstanceList)

        }
        else {
            render template:'list', model:[priceHolderInstanceList: priceHolderInstanceList, priceHolderInstanceTotal: priceHolderInstanceTotal]
        }

    }
    def list() {
        renderList()
    }
    def export = {attrs ->
        def  priceHolderInstanceTotal=PriceHolder.findAllByIsValidatedAndIsRejected(false,false,params).size()
        def priceHolderList=PriceHolder.findAllByIsValidatedAndIsRejected(false,false,params)
        params.max=priceHolderInstanceTotal
        def response = attrs.response
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        response.setHeader("Content-disposition", "attachment; filename="+"Prix-a-valider".uniquify(".${params.extension}"))
        List fields = [ "sourcePrix","date","marche","produit","globalPrice"]
        Map labels = ["sourcePrix":"SOURCE","date":"DATE",
                "marche": "MARCHE", "produit": "PRODUIT",
                "globalPrice":"Prix"]

        def humanCase = {
            domain, value ->
                return value?.humanify()
        }
        def dateFormat ={
            domain, value ->
                return value?.getAsDay()
        }

        Map formatters = [sourcePrix:humanCase,date:dateFormat]

        Map parameters = [title: "LISTE DES PRIX A  VALIDER", "column.widths": [0.15, 0.12, 0.2, 0.30, 0.32]]

        exportService.export(params.format, response.outputStream, priceHolderList, fields, labels,formatters,parameters)
    }

    def filter = {
        if (!params.max) params.max = 10


        def prixInstanceTotal
        def prixInstanceList
        def filterParams=params
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
            render( template:'list',
                    model: [
                            prixInstanceList: prixInstanceList,
                            prixInstanceTotal: prixInstanceTotal,
                            params: params])
        }


    }
    def abandonner() {
        def deleteList=[]
        PriceHolder.findAllByIsRejected(true).each {
            deleteList<<it.id
        }
        deleteList.each{
            def priceHolder=PriceHolder.get(it)
            Prix.executeUpdate("DELETE Prix c where c.priceHolder = :priceHolder", [priceHolder: priceHolder])
            priceHolder.delete(flush: true)
        }
		flash.message="Supression réussie des prix rejetés"
        renderList()

    }



    def create() {
        switch (request.method) {
            case 'GET':
                [priceHolderInstance: new PriceHolder(params)]
                break
            case 'POST':
                def priceHolderInstance = new PriceHolder(params)
                if (!priceHolderInstance.save(flush: true)) {
                    render view: 'index', model: [priceHolderInstance: priceHolderInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), priceHolderInstance.toString()])
                render template: 'show', model:[priceHolderInstance: PriceHolder.get(params.id)]
                break
        }
    }

    def show() {
        def priceHolderInstance = PriceHolder.get(params.id)
        if (!priceHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id])
            renderList()
        }

        render template:'show',model:[priceHolderInstance: priceHolderInstance]
    }
    public bindprices(PriceHolder priceHolderInstance) {
        if (params?.prixGros == null)
            priceHolderInstance.prixGros = new BigDecimal("0.0")
        else
            priceHolderInstance.prixGros = new BigDecimal(params?.prixGros ?: "0.0")
        if (params?.prixDetail == null)
            priceHolderInstance.prixDetail = new BigDecimal("0.0")
        else
            priceHolderInstance.prixDetail = new BigDecimal(params?.prixDetail ?: "0.0")

    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def priceHolderInstance = PriceHolder.get(params.id)
                log.info("instance : $priceHolderInstance params $params")
                if (!priceHolderInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id])
                    renderList()
                }

                render template:'edit',model:[priceHolderInstance: priceHolderInstance,update:params.update]
                break
            case 'POST':
                def priceHolderInstance = PriceHolder.get(params.id)
                if (!priceHolderInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (priceHolderInstance.version > version) {
                        priceHolderInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'priceHolder.label', default: 'PriceHolder')] as Object[],
                                "Another user has updated this PriceHolder while you were editing")
                        render template: 'edit', model: [priceHolderInstance: priceHolderInstance]
                    }
                }

                priceHolderInstance.properties = params
                bindprices(priceHolderInstance)
                if (!priceHolderInstance.save(flush: true)) {
                    render template: 'edit', model: [priceHolderInstance: priceHolderInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), priceHolderInstance.toString()])
                render template: 'show', model:[priceHolderInstance: PriceHolder.get(params.id),update:params.update]
                break
        }
    }

    def delete() {
        def priceHolderInstance = PriceHolder.get(params.id)
        if (!priceHolderInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id])
            renderList()
        }

        try {
            priceHolderInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'priceHolder.label', default: 'PriceHolder'), params.id, e.message])
            render template: 'show', model:[priceHolderInstance: PriceHolder.get(params.id)]
        }
    }
}
