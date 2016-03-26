package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.OptimisticLockingFailureException


class PerformanceController {
def exportService
def filterPaneService
def performanceService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    renderList()
}
def listOfPerformance() {
        def lst = Performance.findAll()
        StringBuffer buf = new StringBuffer("<select>")
        lst.each{
            buf.append('<option value=" ').append(it.id).append('">')
            buf.append(it.nom)
            buf.append('</option>')
        }
        buf.append('</select>')

        render buf.toString()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = Performance.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+Performance.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,Performance.list(params), [:], [:])
        }
        render template:"list" ,model:[performanceInstanceList: filterPaneService.filter( params, Performance ), performanceInstanceTotal: filterPaneService.count( params, Performance ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def list() {
    renderList()
}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ performanceInstanceList: filterPaneService.filter( params, Performance ),
                    performanceInstanceTotal: filterPaneService.count( params, Performance ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
    public bindPerformances(Performance performanceInstance) {
            performanceInstance.nbOffre=new BigDecimal(params.nbOffre?:"0.0")
            performanceInstance.nbPrix=new BigDecimal(params.nbPrix?:"0.0")
            performanceInstance.nbAlerte=new BigDecimal(params.nbAlerte?:"0.0")
            performanceInstance.nbContact=new BigDecimal(params.nbContact?:"0.0")
    }
def create() {
    switch (request.method) {
        case 'GET':
            render template:'create', model:[performanceInstance: new Performance(params)]
            break
        case 'POST':
            def performanceInstance = new Performance(params)
            bindPerformances(performanceInstance)

            if (!performanceInstance.save(flush: true)) {
                render template: 'create', model: [performanceInstance: performanceInstance]
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'performance.label', default: 'Performance'), performanceInstance.toString()])
            def result=[id:performanceInstance.id]
            render result as JSON
            break
    }
}

def show() {
    def performanceInstance = Performance.get(params.id)
    if (!performanceInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'performance.label', default: 'Performance'), params.id])
        renderList()
    }

    [performanceInstance: performanceInstance]
}

def edit() {
    switch (request.method) {
        case 'GET':
            def performanceInstance = Performance.get(params.id)
            if (!performanceInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'performance.label', default: 'Performance'), params.id])
                renderList()
            }

            render template:'edit', model:[performanceInstance: performanceInstance]
            break
        case 'POST':
            def performanceInstance = Performance.get(params.id)
            if (!performanceInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'performance.label', default: 'Performance'), params.id])
                renderList()
            }

            if (params.version) {
                def version = params.version.toLong()
                if (performanceInstance.version > version) {
                        performanceInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'performance.label', default: 'Performance')] as Object[],
                                "Another user has updated this Performance while you were editing")
                    render template: 'edit', model: [performanceInstance: performanceInstance]
                }
            }

            performanceInstance.properties = params
            bindPerformances(performanceInstance)

            if (!performanceInstance.save(flush: true)) {
                render template: 'edit', model: [performanceInstance: performanceInstance]
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'performance.label', default: 'Performance'), performanceInstance.toString()])
            render template:'show', model:[performanceInstance: performanceInstance]
            break
    }
}

def delete() {
    def performanceInstance = Performance.get(params.id)
    if (!performanceInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'performance.label', default: 'Performance'), params.id])
        renderList()
    }

    try {
        performanceInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'performance.label', default: 'Performance'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'performance.label', default: 'Performance'), params.id, e.message])
        render template:'show', model:[performanceInstance: performanceInstance]
    }
}

    def  populate = {
       def maxRows =Math.min(params.rows ? params.int('rows') : 10, 100)
        def currentPage = Integer.valueOf(params.page?:1)
        def results=[]
        def performance=Performance.findByNom(params.performanceId )
        def enqueteurs
        if (performance) {
            enqueteurs = Utilisateur.findAllByPerformanceAndRole(performance,"enqueteur")
        }else {
            enqueteurs = Utilisateur.findAllByRole("enqueteur")
        }
        def totalRows = enqueteurs?.size()?:0
        if (totalRows>0){
            results = enqueteurs?.collect {

                [ cell: [it.id, it.login,it.performance?.nom ], id: it.id ]
            }
        }

        totalRows = results?.size()
        def numberOfPages = Math.ceil(totalRows?:0 / maxRows)
        def jsonData = [rows: results, page: currentPage, records: totalRows, total: numberOfPages]

        render jsonData as JSON

    }


    def autoriser= {

     render   performanceService.autoriser(params)
    }


    def refuser= {
        render   performanceService.refuser(params)
    }


    def valider= {

        render  valider(params)
    }


    def validerPerformance(){

    }


}
