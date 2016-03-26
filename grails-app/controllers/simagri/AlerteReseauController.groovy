package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class AlerteReseauController {
def exportService
def filterPaneService
def pushOffresReseauService
def pushPricesReseauService
def alerteService
def myUtilityService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
//    Boolean save( params, AlerteReseau alerteReseau) {
//        try {
//
//            def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
//            def idReseaux=toList(listeParam?:[new Long("0")])
//            def idMarches=(params?.produits?.size()>0)?myUtilityService.toLong(params?.markets?:"0"):[new Long("0")]
//            def idDestinataires=(params?.destinataires?.size()>0)?myUtilityService.toLong(params?.destinataires?:"0"):[new Long("0")]
//            def listeMarches=Marche.createCriteria().list(){
//                inList("id",idMarches)
//            }
//            def idProduits=(params?.produits?.size()>0)?myUtilityService.toLong(params?.produits?:"0"):[new Long("0")]
//
//            def listeProduits=Produit.createCriteria().list(){
//                inList("id",idProduits)
//            }
//            def listeReseaux=Reseau.createCriteria().list(){
//                inList("id",idReseaux)
//            }
//            if (alerteReseau?.save()) {
//                AlerteReseauReseau.removeAll(alerteReseau)
//                AlerteReseauDestinataire.removeAll(alerteReseau)
//                AlerteReseauMarche.removeAll(alerteReseau)
//                AlerteReseauProduit.removeAll(alerteReseau)
//
//                listeReseaux.each{reseau->
//                    AlerteReseauReseau.create(reseau as Reseau,alerteReseau,false)
//
//                }
//                listeMarches.each { marche ->
//                    AlerteReseauMarche.create(marche as Marche ,alerteReseau, false)
//                }
//                listeProduits.each { produit ->
//                    AlerteReseauProduit.create( produit as Produit,alerteReseau, false)
//                }
//                def listeDestinataires=Utilisateur.createCriteria().list(){
//
//                    inList("id",idDestinataires)
//                }
//
//                listeDestinataires.each{destinataire->
//                    AlerteReseauDestinataire.create(destinataire as Utilisateur,alerteReseau,false)
//
//                }
//                return true
//            }
//            else return false
//        }
//        catch(e) {
//
//            log.debug( " exception in ${this.getClass().simpleName}.Save ${e}")
//            return false
//        }
//    }
//    Boolean update(def params, AlerteReseau alerteReseau) {
//        try {
//            alerteReseau.properties = params
//            return save(params,alerteReseau)
//        }
//        catch(e) {
//
//            println " exception in ${this.getClass().simpleName}.update ${e}"
//            log.debug(e)
//            return false
//        }
//    }
   def sendByService(){
        render alerteService.execute(params)
    }
    def deleteByService(){
        render alerteService.delete(params)
    }
def index() {
    renderList()
}
    def deepListEdit = {checkedNodes,reseau, callback ->
        def theChildNodes = Reseau.createCriteria().list(){
            eq("parent.id",reseau.id)
            and {
                eq("active",true)
            }
            order('nom','asc')
        }
        if (theChildNodes ==[]) {
            [
                    id: reseau?.id,
                    checked:checkedNodes.find{it==reseau?.id}?true:false,
                    text: reseau?.nom
            ]
        }else
            [
                    id: reseau?.id,
                    checked:checkedNodes.find{it==reseau?.id}?true:false,
                    text: reseau?.nom,
                    items: theChildNodes?.collect {callback(checkedNodes,it, callback)}
            ]
    }

    private def createNoteTree(AlerteReseau alerte) {
        def checkedNodes=alerte?.reseaux*.id?:[]
        def root = Reseau.createCriteria().list(){
            isNull("parent")
            and {
                eq("active",true)
            }
            order('nom','asc')
        }
        root.collect {
            deepListEdit(checkedNodes,it, deepListEdit)
        }
    }
    def getEditTree(){
        def alerteId=params?.alerteId

        def alerte=AlerteReseau.get(alerteId?.toLong())
        render createNoteTree(alerte) as JSON

    }
def execute(){
    def alerteInstance = AlerteReseau.get(params.id)
    if (alerteInstance.isAlertePrix && alerteInstance.valide) {
        pushPricesReseauService.fireByAlerte(alerteInstance)
        flash.message="Les alertes de prix ont été bien exécutées"
    }  else {
        pushOffresReseauService.fireByAlerte(alerteInstance)
        flash.message="Les alertes d'offres ont été bien exécutées"
    }

}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = AlerteReseau.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")
            exportService.export(params.format, response.outputStream,AlerteReseau.list(params), [:], [:])
        } else {
            def listeAlerte=AlerteReseau.createCriteria().list(){
                eq('valide',false)
                and {
                    eq('isRejected',false)
                }

            }
            render template:"list" ,
                    model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]
        }

    }
def list() {
    renderList()
}
    def abandonner={
        render alerteService.abandonner(params)
    }

def listValidees() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
    def listeAlerte=AlerteReseau.createCriteria().list(){
        eq('valide',true)

    }
    if(params?.format && params.format != "html"){
        params.max = listeAlerte.size()
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")

        List fields = [ "typeAlerte","destinataires","markets","produits"]
        Map labels = ["typeAlerte":"Type Alerte","destinataires":"DESTINATAIRES", "markets": "MARCHES", "produits": "PRODUITS"]


        Map formatters = [:]

        Map parameters = [title: "LISTE DES ALERTES VALIDEES", "column.widths": [0.18, 0.2, 0.5, 0.12],"pdf.orientation":"portrait"]

        exportService.export(params.format, response.outputStream, listeAlerte, fields, labels,formatters,parameters)

    }else {
        render template:"listValidees" ,
                model:[alerteValideInstanceList: listeAlerte, alerteValideInstanceTotal: listeAlerte.size(),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }


    }
    def listeexecute() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        if(params?.format && params.format != "html"){
            params.max = AlerteReseau.count()

            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")
            exportService.export(params.format, response.outputStream,AlerteReseau.list(params), [:], [:])
        }
        def user=myUtilityService.getCurrent()
        def listeAlerte=AlerteReseau.createCriteria().list(){
            eq('valide',true)
            order('nom', 'asc')

        }
        render template:"listeexecute" ,
                model: [alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def listRejets() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        if(params?.format && params.format != "html"){
            params.max = AlerteReseau.count()

            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")
            exportService.export(params.format, response.outputStream,AlerteReseau.list(params), [:], [:])
        }
        def user=myUtilityService.getCurrent()
        def listeAlerte=AlerteReseau.createCriteria().list(){

                eq('isRejected',true)


        }
    render template:"listRejets" ,
        model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def listesuspendues() {
	params.max = Math.min(params.max ? params.int('max') : 25, 100)

	if(params?.format && params.format != "html"){
		params.max = AlerteReseau.count()

		response.contentType = grailsApplication.config.grails.mime.types[params.format]
		response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")
		exportService.export(params.format, response.outputStream,AlerteReseau.list(params), [:], [:])
	}
	def user=myUtilityService.getCurrent()
	def listeAlerte=AlerteReseau.createCriteria().list(){
        eq('suspendre',true)


	}
    render template:"listesuspendues" ,model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ alerteInstanceList: filterPaneService.filter( params, AlerteReseau ),
                    alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}


def create() {
        switch (request.method) {
            case 'GET':
                def user=myUtilityService.getCurrent()

                def alerteInstance= new AlerteReseau(params)
                alerteInstance.recevoirOffres=false
                alerteInstance.recevoirPrix=true
                alerteInstance.recevoirParSMS=true
                alerteInstance.recevoirParEmail=true
                alerteInstance.suspendre=false
                alerteInstance.valide=false
                alerteInstance.operateur=user

                [alerteInstance: alerteInstance,isCreation:true]
                break
            case 'POST':
                def user=myUtilityService.getCurrent()
                def alerteInstance = new AlerteReseau(params)
                if (alerteInstance.isAlertePrix){
                    alerteInstance.recevoirOffres=false
                    alerteInstance.recevoirPrix=true
                }else {
                    alerteInstance.recevoirOffres=true
                    alerteInstance.recevoirPrix=false
                }
                alerteInstance.operateur=user
                alerteInstance.suspendre=false
                alerteInstance.recevoirParEmail=true
                alerteInstance.valide=false
                alerteInstance.dateCreation=new Date()
                alerteInstance.recevoirParSMS=true
                if (params?.ReseauxIdsAlerte) {
                  def  newparams = params + ['ReseauxIds': params?.ReseauxIdsAlerte]
                if (!alerteService.save(newparams,alerteInstance)) {
                    render template: 'create', model: [alerteInstance: alerteInstance,isCreation:true]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), alerteInstance.toString()])
                    def result=[id:alerteInstance.id]
                    render result as JSON
                break
                    }else {
                    flash.message=message(code: 'default.reseauNonSelectionne.message')
                    render template: 'create', model: [alerteInstance: alerteInstance,isCreation:true]
                        }
        }
    }



def show() {
    def alerteInstance = AlerteReseau.get(params.id)
    if (!alerteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id])
        renderList()
    }

    [alerteInstance: alerteInstance]
}
    def toList(value) {
        [value].flatten().findAll { it != null }
    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def alerteInstance = AlerteReseau.get(params.id)
                if (!alerteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id])
                    redirect action: 'listAlertePrisAValider'
                }
                def alertId =alerteInstance?.id
                def marchesId=alerteInstance.markets?.id
                def produitsId=alerteInstance.produits?.id
                
               render template:'edit', model:[alerteInstance: alerteInstance,alertId:alertId,isCreation:false,marchesId:marchesId,produitsId:produitsId,update:params.update]
                break
            case 'POST':
                def alerteInstance = AlerteReseau.get(params.id)
                def user=myUtilityService.getCurrent()
                if (alerteInstance.isAlertePrix){
                    alerteInstance.recevoirOffres=false
                    alerteInstance.recevoirPrix=true
                }else {
                    alerteInstance.recevoirOffres=true
                    alerteInstance.recevoirPrix=false
                }
                alerteInstance.operateur=user
                if (!alerteInstance.suspendre)
                alerteInstance.suspendre=false
                alerteInstance.recevoirParEmail=true
                if (!alerteInstance.valide)
                alerteInstance.valide=false
                alerteInstance.recevoirParSMS=true
                if (!alerteInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'alerteReseau.label', default: 'Alerte'), params.id])

                    render template:'edit', model:[alerteInstance: alerteInstance,alertId:alerteInstance.id,isCreation:false]
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (alerteInstance.version > version) {
                        alerteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'alerteReseau.label', default: 'Alerte')] as Object[],
                                "Another user has updated this Alerte while you were editing")
                        render template:'edit', model:[alerteInstance: alerteInstance,alertId:alerteInstance.id,isCreation:false]
                    }
                }
                alerteInstance.properties = params

                if (!alerteService.update(params,alerteInstance)) {

                    render template:'edit', model:[alerteInstance: alerteInstance,alertId:alerteInstance.id,isCreation:false,update:params.update]
                }
                flash.message = message(code: 'default.updated.message', args: [message(code: 'alerteReseau.label', default: 'Alerte'), alerteInstance.toString()])
                render template:'show', model:[alerteInstance: alerteInstance,alertId:alerteInstance.id,isCreation:false,update:params.update]
                break
        }
    }

	def desactiver() {
		def desactiverList=[]
		AlerteReseau.findAllBySuspendre(false).each {
			desactiverList<<it.id
		}
		desactiverList.each{
			def alerte=AlerteReseau.get(it)
			alerte.suspendre=true
		}
		flash.message="Suspension réussie des alertes"
		redirect action: 'listValidees', params: params
	}
	def activer() {
		def activatedList=[]
		AlerteReseau.findAllBySuspendre(true).each {
			activatedList<<it.id
		}
		activatedList.each{
			def alerte=AlerteReseau.get(it)
			alerte.suspendre=false
		}
		flash.message="Activation des alertes réussie"
		redirect action: 'listValidees', params: params
	}

def delete() {
    def alerteInstance = AlerteReseau.get(params.id)
    if (!alerteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id])
        renderList()
    }

    try {
        alerteInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id, e.message])
        render template: 'show', activiteInstance: Activite.get(params.id)
    }
}
    def populateValidateJSON() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

        def alerteHolders = AlerteReseau.createCriteria().list(max: maxRows, offset: rowOffset) {

            eq("valide",false)
            and {
                eq("isRejected",false)
            }

        }.sort{[it.nom]}

        def totalRows = alerteHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = alerteHolders?.collect {

            [ cell: [it.id,it.operateur?.reseaux*.nomRacine,it.nom,"${it.destinataires.toString()}",it.produits.nom], id: it.id ]
        }


        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }
    def populateRejetesJSON() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)

        def rowOffset = currentPage == 1 ? 0 : (currentPage - 1) * maxRows

        def alerteHolders = AlerteReseau.createCriteria().list(max: maxRows, offset: rowOffset) {

                eq("isRejected",true)


        }.sort{[it.nom]}

        def totalRows = alerteHolders.totalCount
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def results = alerteHolders?.collect {

            [ cell: [it.id,it.operateur?.reseaux*.nomRacine,it.nom,"${it.destinataires.toString()}",it.produits.nom], id: it.id ]
        }


        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }
    def valider= {
      render  alerteService.valider(params)

    }

    def mettreajouralerte(params) {
        if (params.selectedList != "null") {
            def transactionDate = new Date()
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {
                AlerteReseau alerte = AlerteReseau.get(it.toLong())
                alerte.valide = true
                alerte.validationDate = transactionDate
                alerte.save(failOnError: true, flush: true)

                render true
            }

        }
    }
    def rejeter= {
        rejeterAlerte(params)

    }

    public rejeterAlerte(params) {
        if (params.selectedList != "null") {
            def transactionDate = new Date()
            List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
            //students in request params is parsed to json objects and stored in the List
            selectedList.each {
                AlerteReseau alerte = AlerteReseau.get(it.toLong())
                alerte.isRejected = true
                alerte.valide = false
                alerte.rejectedDate = transactionDate
                alerte.save(failOnError: true, flush: true)
                render true


            }
            render true
        }
    }

    def validerAlerte(){
      render template:'validerAlerte'
    }
}
