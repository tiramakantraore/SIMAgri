package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.OptimisticLockingFailureException


class MarcheController {
    def exportService
    def filterPaneService
    def marcheService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def listOfMarche() {
        def lst = Marche.findAll()
        StringBuffer buf = new StringBuffer("<select>")
        lst.each{
            buf.append('<option value=" ').append(it.id).append('">')
            buf.append(it.nom)
            buf.append('</option>')
        }
        buf.append('</select>')

        render buf.toString()
    }
    def updateByJSON(){
        if (params.id!="null"){


            def produits=MarcheProduit.createCriteria().list {
                eq("marche.id",new Long (params.id?:"0"))
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
    def listJSON() {
        def listMarches = Marche.findAll()
        def source=listMarches.collect{[id:it.id,text:it.nom]}
        def id=listMarches?.id?.flatten()
        def data=[id:id,source:source]
        render   data as JSON
   }
    def getListByJSON(){
        if (params.id!="null"){
            Utilisateur user=myUtilityService.getCurrent()
            user?.attach()
            marches=user?.markets;
            def resultsmarches=[]
            marches.each{
                resultsmarches <<"markets_${it}"
            }

            def jsonData = [marches: resultsmarches,isEmpty:resultsmarches.isEmpty()]
            render jsonData as JSON
        }
        else {
            render false
        }

    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Marche.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Marche".uniquify(".${params.extension}"))

            List fields = ["code","nom","region","location","produits"]
            Map labels = ["code":"CODE","nom":"NOM","region":"REGION", "location": "LIEUX","produits":"PRODUITS"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase,nom:humanCase]
            Map parameters = [title: "Marchés", "column.widths": [0.1,0.2, 0.2, 0.15, 0.35],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Marche.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" ,model:[marcheInstanceList: filterPaneService.filter(params, Marche), marcheInstanceTotal: filterPaneService.count(params, Marche), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [marcheInstanceList: filterPaneService.filter(params, Marche),
                        marcheInstanceTotal: filterPaneService.count(params, Marche),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
               render template:'create',model: [marcheInstance: new Marche(params),isCreation:true]
                break
            case 'POST':
                def marcheInstance = new Marche(params)
                def produitsIds=[]
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['produits': produitsIds]

                if (!marcheService.save(newparams,marcheInstance)) {
                    render template: 'create', model: [marcheInstance: marcheInstance,isCreation:true]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'marche.label', default: 'Marche'), marcheInstance.toString()])
                def result=[id:marcheInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def marcheInstance = Marche.get(params.id)
        if (!marcheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'marche.label', default: 'Marche'), params.id])
            renderList()
        }

        render template:'show',model:[marcheInstance: marcheInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def marcheInstance = Marche.get(params.id)
                if (!marcheInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'marche.label', default: 'Marche'), params.id])
                    renderList()
                }
                def produitsId=marcheInstance.produits?.id
                def mesMembresAutorises=marcheInstance.membresAutorises
                def utilisateursQuiSuivent=marcheInstance.utilisateurs
                render template:'edit',model:[marcheInstance: marcheInstance,isCreation:false,produitsId:produitsId,mesMembresAutorises:mesMembresAutorises,utilisateursQuiSuivent:utilisateursQuiSuivent]
                break
            case 'POST':
                def marcheInstance = Marche.get(params.id)
                println "marcheInstance ${marcheInstance} params.id ${params.id}"
                if (!marcheInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'marche.label', default: 'Marche'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (marcheInstance.version > version) {
                        marcheInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'marche.label', default: 'Marche')] as Object[],
                                "Another user has updated this Marche while you were editing")
                        render template: 'edit', model: [marcheInstance: marcheInstance]
                    }
                }
                def produitsIds=[]
                params.each {
                    if (it.key.startsWith("produits_"))
                        produitsIds << (it.key - "produits_")

                }
                def newparams=params + ['produits': produitsIds]

                if (!marcheService.update(newparams,marcheInstance)) {
                    render template: 'edit', model: [marcheInstance: marcheInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'marche.label', default: 'Marche'), marcheInstance.toString()])
                render template: 'show', model:[marcheInstance: marcheInstance]
     //           renderList()
                break
        }
    }

    def delete() {
        def marcheInstance = Marche.get(params.id)
        if (!marcheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'marche.label', default: 'Marche'), params.id])
            renderList()
        }

        try {
            marcheInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'marche.label', default: 'Marche'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'marche.label', default: 'Marche'), params.id, e.message])
            render template: 'show', model:[marcheInstance: marcheInstance]
        }
    }

    def map(){
        def  locationList=Marche.list().location?.findAll{location->location.nom!=null && location.longitude!=null && location.latitude!=null}

        [locationList: locationList]
    }

    def  populate = {


        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)
        def results=[]
        def marche=Marche.findByNom(params.marcheId )
        def enqueteurs
        if (marche) {
            enqueteurs = Utilisateur.findAllByMarcheEnqueteurAndRole(marche,"enqueteur")
        }else {
            enqueteurs = Utilisateur.findAllByRole("enqueteur")
        }
        def totalRows = enqueteurs?.size()?:0
        if (totalRows>0){
            results = enqueteurs?.collect {

                [ cell: [it.id, it.login,it.marcheEnqueteur?.nom ], id: it.id ]
            }
        }

        totalRows = results?.size()
        def numberOfPages = Math.ceil(totalRows?:0 / maxRows)
        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }


    def autoriser= {

        render  marcheService.autoriser(params)
    }

    def refuser= {

        render  marcheService.refuser(params)
    }

    def valider= {
      render  marcheService.valider(params)
    }

    def validerMarcheEnqueteur(){

    }

}
