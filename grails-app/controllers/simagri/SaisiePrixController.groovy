package simagri

import grails.converters.JSON


class SaisiePrixController {
    def myUtilityService
    def priceCreateService
    def filterPaneService
    def exportService
    def marcheService
    def myFormHelperService
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def  priceHolderInstanceTotal=PriceHolder.findAll{isValidated==false}.size()
        def priceHolderInstanceList= PriceHolder.findAllByIsValidated(false,params)
        if (params?.format && params.format != "html") {
            params.max = priceHolderInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: priceHolderInstanceList)

        }
        render template:"list" ,model:[priceHolderInstanceList: priceHolderInstanceList, priceHolderInstanceTotal: priceHolderInstanceTotal]

    }
    def populateRejeteJSON() {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
            def user=myUtilityService.getCurrent()
            def reseauPrincipal=user?.reseauPrincipal
            def priceHolders = PriceHolder.createCriteria().list(max: maxRows, offset: rowOffset) {
                eq("isValidated",false)
                and {
                    eq("isRejected",true)
                }
                if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                    and {

                        auteur{
                            eq('reseauPrincipal',reseauPrincipal)
                        }
                    }
                }
                createAlias('produit','produit')
                if (params.auteur) ilike('auteur', "%${params.auteur}%")
                if (params.nomMarche) ilike('nomMarche', "%${params.nomMarche}%")
                if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")
                order('nomMarche', 'asc')
                order('auteur', 'asc')
                order('produit.nom', 'asc')
//                maxResults(maxRows)
//                firstResult(rowOffset)
            }

            def totalRows = priceHolders.totalCount
            def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

            def separator=" FCFA/ "
            def results = priceHolders?.collect {
                if (params.auteur) ilike('auteur', "%${params.auteur}%")
                if (params.nomMarche) ilike('nomMarche', "%${params.nomMarche}%")
                if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")

                [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.nomMarche?:"","${it.auteur?.username} ${it.auteur?.mobilePhone?:""}",it.nomProduit,it.sourcePrix,"${it.prixGros}${separator}${it.mesureGros?.code}","${it.prixDetail}${separator}${it.mesureDetail?.code}",it.date,it.commentaire], id: it.id ]
            }

            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch(Exception e){
            log.error  " exception ${e}"
            render false
        }
    }
    def populateValidateJSON() {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
            def user=myUtilityService.getCurrent()
            def reseauPrincipal=user?.reseauPrincipal
//            def membresRacine=user.noeudsRacine*.tousMesMembres.id.flatten()?:[new Long("-1")]
            def priceHolders = PriceHolder.createCriteria().list(max: maxRows, offset: rowOffset) {
                createAlias('auteur','auteur')
                createAlias('auteur.reseauPrincipal','reseau')
                createAlias('produit','produit')
                eq("isValidated",false)
                and {
                    eq("isRejected",false)
                }

                if (!(user?.isSuperviseur || user?.isSuperAdmin)) {
                    and {
                        eq('reseau',reseauPrincipal)

                    }
                }

//
//                if (params.auteur) ilike('auteur', "%${params.auteur}%")
//                if (params.nomMarche) ilike('nomMarche', "%${params.nomMarche}%")
//                if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")
                order('date', 'desc')
                order('reseau', 'asc')
                order('nomMarche', 'asc')
                order('auteur', 'asc')
                order('produit.nom', 'asc')
//                maxResults(maxRows)
//                firstResult(rowOffset)
            }

            def totalRows = priceHolders.totalCount
            def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

            def separator=" FCFA/ "
            def results = priceHolders?.collect {
                if (params.auteur) ilike('auteur', "%${params.auteur}%")
                if (params.nomMarche) ilike('nomMarche', "%${params.nomMarche}%")
                if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")

                [ cell: [it.id,it.auteur?.reseauPrincipal?.nom?:"NA",it.marche?.nom?:"","${it.auteur?.username} ${it.auteur?.mobilePhone?:""}",it.nomProduit,it.sourcePrix,"${it.prixGros}${separator}${it.mesureGros?.code}","${it.prixDetail}${separator}${it.mesureDetail?.code}",it.date,it.commentaire], id: it.id ]
            }

            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch(Exception e){
            log.error  " exception ${e}"
            render false
        }
    }

    def  populateIt = {
        try {
            def maxRows =Math.min(params.rows ? params.int('rows') : 100, 100)
            def currentPage = Integer.valueOf(params.page?:1)

            def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows
            def results
            def idTemp=-1
            def marketId
            if (params.marketId){
                marketId= params.marketId?.toLong()?:new Long("-1")
            }
            else
                marketId= new Long("-1")
            def marche=Marche.get(marketId)
            def totalRows =0

//            def prix = Prix.createCriteria().list(max: maxRows, offset: rowOffset) {
//                eq('auteur',user)
//                and {
//                    eq('marche.id',marketId)
//                }
//                and {
//                    eq('statut','EnCours')
//
//                }
//                createAlias('produit','produit')
//                order('produit.nom', 'asc')
//            }


//            def totalRows = prix?.totalCount?:0
//            if (totalRows>0){
//                results = prix?.collect {
//                    if (params.nomProduit) ilike('nomProduit', "%${params.nomProduit}%")
//
//                    [ cell: [it.id, it.produit?.nom,it.prixGros?:"",it.mesureGros?.code,it.prixDetail?:"",it.mesureDetail?.code,it.commentaire], id: it.id ]
//                }
//            }else{
                def listeProduits=marche?.mesProduits
//                if (listeProduits==[]) {
//                    listeProduits=user?.mesProduits
//                }
                def sortedProduits=listeProduits?.sort{it.nom}

                results =sortedProduits.collect{
                    [ cell: [idTemp--, it.nom,"",it.mesure?.code,"",it.mesure?.code,it.commentaire], id: idTemp-- ]

                }

//            }
            totalRows = results?.size()
            def numberOfPages = Math.ceil(totalRows?:0 / maxRows as double)
            def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

            render jsonData as JSON
        }catch(Exception e){
            log.error  " exception ${e}"
            render false
        }

    }

    def export = {attrs ->
        def  priceHolderInstanceTotal=PriceHolder.findAll{isValidated==false}.size()
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

        Map parameters = [title: "LISTE DES PRIX A  VALIDER", "column.widths": [0.15, 0.12, 0.2, 0.20, 0.32, 0.10]]

        exportService.export(params.format, response.outputStream, PriceHolder.list(params), fields, labels,formatters,parameters)
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
        }

        render( template:'list',
                model: [
                        prixInstanceList: prixInstanceList,
                        prixInstanceTotal: prixInstanceTotal,
                        params: params])
    }
    def validerPrix(){
      render template:'validerPrix'

    }
    def valider={


        render priceCreateService.validerPrix(params)
    }
    def abandonner={


        render priceCreateService.abandonner(params)
    }

    def rejeter= {
        render priceCreateService.rejeterPrix(params)
    }
    def save() {
        render priceCreateService.mettreAJourPrix(params)

    }
    def saisie() {

        def user=myUtilityService.getCurrent()
        user?.attach()
        def userMarketsList=[]
        def userMarketId=user?.mesMarches?.id?.flatten()
        if (!userMarketId)
            userMarketId=Marche.list().id?.flatten();
        if (userMarketId){
            userMarketsList=Marche.createCriteria().list() {
                'in' ('id',userMarketId)
                order("nom", "asc")
            }
        }
        redirect controller:'mettreEnLigne', action: 'showPrix', model:[userMarketsList:userMarketsList]
    }
}
