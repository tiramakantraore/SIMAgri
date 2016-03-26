package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class StockMagazinController {
    def exportService
    def filterPaneService
    def transactionDate
    def myUtilityService
    def magazinCreateService
    def myFormHelperService
    def  populateIt = {
        try{
            def maxRows =Math.min(params.rows ? params.int('rows') : 500, 1000)
            def currentPage = Integer.valueOf(params.page?:1)

            def results
            def idTemp=new Long("-1")
            Long magazinId= params.magazinId>""?params.magazinId.toLong():new Long("-1")

            def magazin=Magazin.get(magazinId)
            results =magazin?.produits?.collect{
                    [ cell: [idTemp--, it.nom,"",it.mesure?.code,it.commentaire], id: idTemp-- ]


                }
            def totalRows = results?.size()
            def numberOfPages = Math.ceil(totalRows?:0 / maxRows as double)
            def jsonData = [rows: results?.sort{it.nom}, page: currentPage, records: totalRows, total: numberOfPages]
            render jsonData as JSON
        }
        catch(Exception e){
            log.error  " exception ${e}"
            render false
        }
    }

    def populateValidateJSON() {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

            def stockMagazinHolders = StockMagazin.createCriteria().list(max: maxRows, offset: rowOffset) {

                eq("isValidated",false)
                and {
                    eq('isRejected',false)
                }

                if (params.auteur) ilike('auteur.nom', "%${params.auteur}%")
                if (params.magazin) ilike('magazin.nom', "%${params.magazin}%")
                if (params.stock) ilike('stock', "%${params.stock}%")
                if (params.mesure) ilike('mesure', "%${params.mesure}%")
                if (params.date) ilike('date', "%${params.date}%")
                createAlias("magazin","magazin")
                createAlias("produit","produit")
                order('date', 'desc')
                order('magazin.nom', 'asc')
                order('produit.nom', 'asc')
            }

            def totalRows = stockMagazinHolders.totalCount
            def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

            def results = stockMagazinHolders?.collect {

                [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.date,it.auteur?.toString(), it.magazin?.nom, it.produit?.nom,it.stock,it.mesure?.code,it?.commentaire], id: it.id]


            }

            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch (Exception e){
            log.error  " exception ${e}"
            render false
        }

    }


    def populateStocksRejetesJSON() {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
            def user=myUtilityService.getCurrent()
            def stockMagazinHolders = StockMagazin.createCriteria().list(max: maxRows, offset: rowOffset) {
             eq('isRejected',true)
                if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                    and {
                        eq('reseau',user?.reseauPrincipal)

                    }
                }

                if (params.auteur) ilike('auteur.nom', "%${params.auteur}%")
                if (params.magazin) ilike('magazin.nom', "%${params.magazin}%")
                if (params.stock) ilike('stock', "%${params.stock}%")
                if (params.mesure) ilike('mesure', "%${params.mesure}%")
                if (params.date) ilike('date', "%${params.date}%")
                createAlias("magazin","magazin")
                createAlias("produit","produit")
                order('date', 'desc')
                order('magazin.nom', 'asc')
                order('produit.nom', 'asc')
            }

            def totalRows = stockMagazinHolders.totalCount
            def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

            def results = stockMagazinHolders?.collect {

                [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.date, it.magazin?.nom, it.produit?.nom,it.stock,it.mesure?.code], id: it.id]

            }

            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch (Exception e){
            log.error  " exception ${e}"
            render false
        }

    }
    def valider= {
      render  magazinCreateService.valider(params)
    }


    def rejeter= {
        render  magazinCreateService.rejeter(params)
    }
    def abandonner={
      render magazinCreateService.abandonner(params)
    }


    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
    def validerStocks(){
      render template:'validerStocks'

    }
    def saisie() {

        def user=myUtilityService.getCurrent()
        user?.attach()
        def userMagazinList=[]
        def userMagazinId=user?.magazins?.id?.flatten()
        if (!userMagazinId)
            userMagazinId=Magazin.list().id?.flatten();
        if (userMagazinId){
            userMagazinList=Magazin.createCriteria().list() {
                'in' ('id',userMagazinId)
                order("nom", "asc")
            }
        }
        def sizeOfList=userMagazinList?.size()?:0
        switch (request.method) {
            case 'GET':

                [userMagazinList:userMagazinList?.sort{[it.nom]},ctnerTemplate:myFormHelperService.getCheckBoxTemplate(sizeOfList)]
                break
            case 'POST':

                if (!magazinCreateService.mettreAJourStock(params)) {
                    [model: [userMagazinList:userMagazinList]]
                }
                render true

                break
        }
    }
    def index() {
        renderList()
    }
    def listValides() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params+[ 'filter.op.isValidated' : 'Equal',filter:['op.isValidated':'Equal', op:[isValidated:'Equal'], 'isValidated':true]]

        def stockMagazinInstanceValideList=filterPaneService.filter(newParams, StockMagazin)
        def stockMagazinInstanceValideTotal=filterPaneService.count(newParams, StockMagazin)
        if (params?.format && params.format != "html") {
            params.max = stockMagazinInstanceValideTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"StockMagazin-valides".uniquify(".${params.extension}"))

            List fields = [ "magazin","produit","date","stock","mesure"]
            Map labels = ["magazin":"MAGAZIN","produit":"PRODUIT", "date": "Date", "stock": "STOCK","mesure":"MESURE"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES STOCKS VALIDES", "column.widths": [0.28, 0.42, 0.10, 0.1, 0.1],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, stockMagazinInstanceValideList, fields, labels,formatters,parameters)
        }else {
           render template:'list',model:[stockMagazinInstanceValideList: stockMagazinInstanceValideList, stockMagazinInstanceValideTotal: stockMagazinInstanceValideTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

        }

    }
    def listRejetes() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params+[ 'filter.op.isRejected' : 'Equal',filter:['op.isRejected':'Equal', op:[isRejected:'Equal'], 'isRejected':true]]

        def stockMagazinInstanceList=filterPaneService.filter(newParams, StockMagazin)
        def stockMagazinInstanceTotal=filterPaneService.count(newParams, StockMagazin)
        if (params?.format && params.format != "html") {
            params.max = stockMagazinInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"StockMagazin-rejetes".uniquify(".${params.extension}"))

            List fields = [ "magazin","produit","date","stock","mesure"]
            Map labels = ["magazin":"MAGAZIN","produit":"PRODUIT", "date": "Date", "stock": "STOCK","mesure":"MESURE"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES STOCKS VALIDES", "column.widths": [0.28, 0.42, 0.10, 0.1, 0.1],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, stockMagazinInstanceList, fields, labels,formatters,parameters)
        }
        [stockMagazinInstanceList: stockMagazinInstanceList, stockMagazinInstanceTotal: stockMagazinInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def newParams=params+[ 'filter.op.isValidated' : 'Equal',filter:['op.isValidated':'Equal', op:[isValidated:'Equal'], 'isValidated':false]]

        def stockMagazinInstanceList=filterPaneService.filter(newParams, StockMagazin)
        def stockMagazinInstanceTotal=filterPaneService.count(newParams, StockMagazin)
        if (params?.format && params.format != "html") {
            params.max = stockMagazinInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"StockMagazin-a-valider".uniquify(".${params.extension}"))

            List fields = [ "magazin","produit","date","stock","mesure"]
            Map labels = ["magazin":"MAGAZIN","produit":"PRODUIT", "date": "Date", "stock": "STOCK","mesure":"MESURE"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES STOCKS A VALIDER", "column.widths": [0.28, 0.42, 0.10, 0.1, 0.1],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, stockMagazinInstanceList, fields, labels,formatters,parameters)
        }else {
            render template:"list" , model:[stockMagazinInstanceList: stockMagazinInstanceList, stockMagazinInstanceTotal: stockMagazinInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

        }

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [stockMagazinInstanceList: filterPaneService.filter(params, StockMagazin),
                        stockMagazinInstanceTotal: filterPaneService.count(params, StockMagazin),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [stockMagazinInstance: new StockMagazin(params)]
                break
            case 'POST':
                def stockMagazinInstance = new StockMagazin(params)
                if (!stockMagazinInstance.save(flush: true)) {
                    render template: 'create', model: [stockMagazinInstance: stockMagazinInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), stockMagazinInstance.toString()])
                def result=[id:stockMagazinInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def stockMagazinInstance = StockMagazin.get(params.id)
        if (!stockMagazinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id])
            renderList()
        }

        [stockMagazinInstance: stockMagazinInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def stockMagazinInstance = StockMagazin.get(params.id)
                if (!stockMagazinInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id])
                    renderList()
                }
                render template:'edit',model:[stockMagazinInstance: stockMagazinInstance,update:params.update]
                break
            case 'POST':
                def stockMagazinInstance = StockMagazin.get(params.id)

                if (!stockMagazinInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (stockMagazinInstance.version > version) {
                        stockMagazinInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'stockMagazin.label', default: 'StockMagazin')] as Object[],
                                "Another user has updated this StockMagazin while you were editing")
                        render template: 'edit', model: [stockMagazinInstance: stockMagazinInstance,update:params.update]
                    }
                }

                stockMagazinInstance.properties = params

                if (!stockMagazinInstance.save(flush: true)) {
                    render template: 'edit', model: [stockMagazinInstance: stockMagazinInstance,update:params.update]
                }
                flash.message = message(code: 'default.updated.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), stockMagazinInstance.toString()])
                render template: 'show', model:[stockMagazinInstance: StockMagazin.get(params.id),update:params.update,id:params.id]
                break
        }
    }

    def delete() {
        def stockMagazinInstance = StockMagazin.get(params.id)
        if (!stockMagazinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id])
            renderList()
        }

        try {
            stockMagazinInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockMagazin.label', default: 'StockMagazin'), params.id, e.message])
            render template: 'show', model:[stockMagazinInstance: StockMagazin.get(params.id)]
        }
    }
}
