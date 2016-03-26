package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class ServiceController {
def exportService
def filterPaneService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    renderList()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = Service.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+Service.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,Service.list(params), [:], [:])
        }
        render template:"list" ,model:[serviceInstanceList: filterPaneService.filter( params, Service ), serviceInstanceTotal: filterPaneService.count( params, Service ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def list() {
    renderList()
}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ serviceInstanceList: filterPaneService.filter( params, Service ),
                    serviceInstanceTotal: filterPaneService.count( params, Service ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
def create() {
    switch (request.method) {
        case 'GET':
           render template:'create', model:[serviceInstance: new Service(params)]
            break
        case 'POST':
            def serviceInstance = new Service(params)
            if (!serviceInstance.save(flush: true)) {
                render template: 'create', model: [serviceInstance: serviceInstance]
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'service.label', default: 'Service'), serviceInstance.toString()])
            def result=[id:serviceInstance.id]
            render result as JSON
            break
    }
}

def show() {
    def serviceInstance = Service.get(params.id)
    if (!serviceInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])
        renderList()
    }

    render template:'show',model:[serviceInstance: serviceInstance]
}

def edit() {
    switch (request.method) {
        case 'GET':
            def serviceInstance = Service.get(params.id)
            if (!serviceInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])
                renderList()
            }

            render template:'edit',model:[serviceInstance: serviceInstance]
            break
        case 'POST':
            def serviceInstance = Service.get(params.id)
            if (!serviceInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])
                renderList()
            }

            if (params.version) {
                def version = params.version.toLong()
                if (serviceInstance.version > version) {
                        serviceInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'service.label', default: 'Service')] as Object[],
                                "Another user has updated this Service while you were editing")
                    render template: 'edit', model: [serviceInstance: serviceInstance]
                }
            }

            serviceInstance.properties = params

            if (!serviceInstance.save(flush: true)) {
                render template: 'edit', model: [serviceInstance: serviceInstance]
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'service.label', default: 'Service'), serviceInstance.toString()])
            render template: 'show', model:[serviceInstance: Service.get(params.id)]
            break
    }
}

def delete() {
    def serviceInstance = Service.get(params.id)
    if (!serviceInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'service.label', default: 'Service'), params.id])
        renderList()
    }

    try {
        serviceInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'service.label', default: 'Service'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'service.label', default: 'Service'), params.id, e.message])
        render template: 'show', model:[serviceInstance: serviceInstance]
    }
}
}
