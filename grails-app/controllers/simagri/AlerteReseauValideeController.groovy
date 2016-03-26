package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class AlerteReseauValideeController {
def exportService

def filterPaneService
def pushOffresReseauService
def pushPricesReseauService
def myUtilityService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

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
def list() {
    renderList()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = AlerteReseau.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")

            List fields = [ "typeAlerte","destinataires","produits","valide"]
            Map labels = ["typeAlerte":"Type Alerte", "produits": "PRODUITS","destinataires":"DESTINATAIRES","valide":"VALIDE"]


            Map formatters = [:]

            Map parameters = [title: "LISTE DES ALERTES VALIDEES", "column.widths": [0.05, 0.7, 0.12, 0.13],"pdf.orientation":"portrait"]

            exportService.export(params.format, response.outputStream, AlerteReseaux.list(params), fields, labels,formatters,parameters)

        }
        def user=myUtilityService.getCurrent()
        def listeAlerte=AlerteReseau.createCriteria().list(){
            eq('valide',false)

        }
        render template:"list" ,
                model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }

def listValidees() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)

    if(params?.format && params.format != "html"){
        params.max = AlerteReseau.count()
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        response.setHeader("Content-disposition", "attachment; filename=AlerteReseau.${params.extension}")

        List fields = [ "typeAlerte","destinataires","produits","valide"]
        Map labels = ["typeAlerte":"Type Alerte", "produits": "PRODUITS","destinataires":"DESTINATAIRES","valide":"VALIDE"]


        Map formatters = [:]

        Map parameters = [title: "LISTE DES ALERTES VALIDEES", "column.widths": [0.05, 0.7, 0.12, 0.13],"pdf.orientation":"portrait"]

        exportService.export(params.format, response.outputStream, AlerteReseaux.list(params), fields, labels,formatters,parameters)

    }
        def user=myUtilityService.getCurrent()
        def listeAlerte=AlerteReseau.createCriteria().list(){
            eq('valide',true)

        }
    render template:"listValidees" ,
            model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: listeAlerte.size(),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

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


        }
        render template:"listeexecute" ,
                model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

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
    render template:"listesuspendues" ,
            model:[alerteInstanceList: listeAlerte, alerteInstanceTotal: filterPaneService.count( params, AlerteReseau ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

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

                [alerteInstance: alerteInstance]
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

                def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
                def idReseaux=toList(listeParam?:[])
                def listeReseaux=Reseau.createCriteria().list(){
                    inList("id",idReseaux)
                }
                listeReseaux.eachWithIndex{reseau,i->
                    alerteInstance.addToReseaux(reseau)
                }



                if (!alerteInstance.save(flush: true)) {
                    render template: 'create', model: [alerteInstance: alerteInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), alerteInstance.toString()])
                def result=[id:alerteInstance.id]
                render result as JSON
                break
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
                def listeCategorie=alerteInstance?.categories?.id?.flatten()
                
                [alerteInstance: alerteInstance]
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
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), params.id])
                    redirect action: 'listAlertePrisAValider'
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (alerteInstance.version > version) {
                        alerteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'AlerteReseau.label', default: 'Alerte')] as Object[],
                                "Another user has updated this Alerte while you were editing")
                        render view: 'editPrix', model: [alerteInstance: alerteInstance]
                    }
                }
                alerteInstance.reseaux?.clear()
                alerteInstance.markets?.clear()
                alerteInstance.categories?.clear()
                alerteInstance.produits?.clear()
				alerteInstance.destinataires?.clear()
                alerteInstance.properties = params

                def listeParam=params?.ReseauxIds?.tokenize(',')?.collect{it as Long}
                def idReseaux=toList(listeParam?:[])
                def listeReseaux=Reseau.createCriteria().list(){
                    inList("id",idReseaux)
                }
                listeReseaux.eachWithIndex{reseau,i->
                    alerteInstance.addToReseaux(reseau)
                }
                if (!alerteInstance.save()) {
                    render view: 'editPrix', model: [alerteInstance: alerteInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'AlerteReseau.label', default: 'Alerte'), alerteInstance.toString()])
              render template: 'show', activiteInstance: Activite.get(params.id)
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
}
