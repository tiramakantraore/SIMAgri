package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class MesureCorrespondanceController {
    def exportService
    def filterPaneService
    def myUtilityService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }

    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = MesureCorrespondance.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + MesureCorrespondance.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, MesureCorrespondance.list(params), [:], [:])
        }
        render template:"list" ,model:[mesureCorrespondanceInstanceList: filterPaneService.filter(params, MesureCorrespondance), mesureCorrespondanceInstanceTotal: filterPaneService.count(params, MesureCorrespondance), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }


    def saisie() {

        switch (request.method) {
            case 'GET':
                render template:"tableCorrespondance",model:[]
                break
            case 'POST':
                try {

                    if (params.selectedList != "null") {
                        List<JSON> selectedList = JSON.parse(params.selectedList?.toString()) as List<JSON>
                        //students in request params is parsed to json objects and stored in the List
                        selectedList.each {
                            def depart=Mesure.findByName(it.mesureDepart)
                            def destination=Mesure.findByName(it.mesureDestination)

                            if (depart && destination){
                                def mapMesures=MesureCorrespondance.findByMesureDepartAndMesureDestination(depart,destination)
                                if (!mapMesures){
                                    mapMesures=new MesureCorrespondance()
                                    mapMesures.mesureDepart=depart
                                    mapMesures.mesureDestination=destination
                                }
                                mapMesures.equivalence=new BigDecimal(it?.equivalence?.toString()?:"0.0")
                                println " mapMesures ${mapMesures}"
                                mapMesures.save(flush:true)
                                render true
                            }
                            else {
                                render false
                            }

                        }
                        render selectedList.size() > 0
                    }
                }
                catch (Exception e) {
                    log.error " exception ${e}"
                    render false
                }
                break
        }
    }

    def populate() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)
        def idTemp=-1
        def departMesureList=Mesure.withCriteria {

            ne('code','tete')
            and {
                eq('isConvertible',true)
            }
            order('valeur', 'desc')

        }

        def combinaisons=[departMesureList, departMesureList].combinations()
        def results=[]
        def listOfMes=[]
        combinaisons.toSet().each{paire->
            def depart=paire[0] as Mesure
            def destination=paire[1] as Mesure
            if (depart.code!=destination.code) {
//                def dejaEnregistre=listOfMes.find{it.left==destination && it.right==depart}
//                if (!dejaEnregistre) {
                 def mapMesures = MesureCorrespondance.findByMesureDepartAndMesureDestination(depart, destination)
                results << [cell: [idTemp--, depart.name, destination.name, mapMesures?.equivalence ?: 0.0], id: idTemp--]
//                }
//                listOfMes<<[left:depart,right:destination]

            }
        }
        def totalRows = results.size()
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]
        render jsonData as JSON

    }

    def populatesave() {

        def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)
        def idTemp=-1
        def departMesureList=Mesure.withCriteria {
              eq('isConvertible',true)


        }
        def collatePartition=0
        def results=[]
        departMesureList.each{depart->
            def destinationMesureList=Mesure.withCriteria {
                ne('id',depart.id)
                and {
                    ne('code','tete')
                }
            }
            destinationMesureList.each{destination->
                def mapMesures=MesureCorrespondance.findByMesureDepartAndMesureDestination(depart,destination)
                results<< [ cell: [idTemp--,depart.name,destination.name,mapMesures?.equivalence?:0.0], id:idTemp-- ]
            }
        }
        def totalRows = results.size()
        def numberOfPages = Math.ceil(totalRows?:0/ maxRows)

        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]
        render jsonData as JSON

    }

    def list() {
        renderList()
    }
    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [mesureCorrespondanceInstanceList : filterPaneService.filter(params, MesureCorrespondance),
                        mesureCorrespondanceInstanceTotal: filterPaneService.count(params, MesureCorrespondance),
                        filterParams                     : org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params                           : params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create',model:[mesureCorrespondanceInstance: new MesureCorrespondance(params)]
                break
            case 'POST':
                def mesureCorrespondanceInstance = new MesureCorrespondance(params)
                mesureCorrespondanceInstance.equivalence=new BigDecimal(params.equivalence?:"0.0")
                if (!mesureCorrespondanceInstance.save(flush: true)) {
                    render template: 'create', model: [mesureCorrespondanceInstance: mesureCorrespondanceInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), mesureCorrespondanceInstance.toString()])
                def result=[id:mesureCorrespondanceInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def mesureCorrespondanceInstance = MesureCorrespondance.get(params.id)
        if (!mesureCorrespondanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id])
            renderList()
        }

        render template:show, model:[mesureCorrespondanceInstance: mesureCorrespondanceInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def mesureCorrespondanceInstance = MesureCorrespondance.get(params.id)
                if (!mesureCorrespondanceInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id])
                    renderList()
                }

                render template:'edit', model:[mesureCorrespondanceInstance: mesureCorrespondanceInstance]
                break
            case 'POST':
                def mesureCorrespondanceInstance = MesureCorrespondance.get(params.id)
                if (!mesureCorrespondanceInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (mesureCorrespondanceInstance.version > version) {
                        mesureCorrespondanceInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance')] as Object[],
                                "Another user has updated this MesureCorrespondance while you were editing")
                        render template: 'edit', model: [mesureCorrespondanceInstance: mesureCorrespondanceInstance]
                    }
                }

                mesureCorrespondanceInstance.properties = params
                mesureCorrespondanceInstance.equivalence=new BigDecimal(params.equivalence?:"0.0")

                if (!mesureCorrespondanceInstance.save(flush: true)) {
                    render template: 'edit', model: [mesureCorrespondanceInstance: mesureCorrespondanceInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), mesureCorrespondanceInstance.toString()])
                render template: 'show', model:[mesureCorrespondanceInstance: MesureCorrespondance.get(params.id)]
                break
        }
    }

    def delete() {
        def mesureCorrespondanceInstance = MesureCorrespondance.get(params.id)
        if (!mesureCorrespondanceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id])
            renderList()
        }

        try {
            mesureCorrespondanceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mesureCorrespondance.label', default: 'MesureCorrespondance'), params.id, e.message])
            render template: 'show', model:[mesureCorrespondanceInstance: MesureCorrespondance.get(params.id)]
        }
    }
}
