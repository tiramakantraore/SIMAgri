package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class ActiviteController {
def exportService
def filterPaneService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    renderList()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = Activite.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=Activite.${params.extension}")
            exportService.export(params.format, response.outputStream,Activite.list(params), [:], [:])
        }
        render template:"list" ,model:[activiteInstanceList: filterPaneService.filter( params, Activite ), activiteInstanceTotal: filterPaneService.count( params, Activite ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def list() {
    renderList()
}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ activiteInstanceList: filterPaneService.filter( params, Activite ),
                    activiteInstanceTotal: filterPaneService.count( params, Activite ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
def create() {
    switch (request.method) {
        case 'GET':
            render template:'create',model:[activiteInstance: new Activite(params)]
            break
        case 'POST':
            def activiteInstance = new Activite(params)
            if (!activiteInstance.save(flush: true)) {
                render template: 'create', model: [activiteInstance: activiteInstance]
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'activite.label', default: 'Activite'), activiteInstance.toString()])
            def result=[id:activiteInstance.id]
            render result as JSON
            break
    }
}

def show() {
    def activiteInstance = Activite.get(params.id)
    if (!activiteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
        renderList()
    }

    render template:'show',model:[activiteInstance: activiteInstance]
}

def edit() {
    switch (request.method) {
        case 'GET':
            def activiteInstance = Activite.get(params.id)
            if (!activiteInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
                renderList()
            }

           render template:'edit', model:[activiteInstance: activiteInstance]
            break
        case 'POST':
            def activiteInstance = Activite.get(params.id)
            if (!activiteInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
                renderList()
            }

            if (params.version) {
                def version = params.version.toLong()
                if (activiteInstance.version > version) {
                        activiteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'activite.label', default: 'Activite')] as Object[],
                                "Another user has updated this Activite while you were editing")
                    render template: 'edit', model: [activiteInstance: activiteInstance]
                }
            }

            activiteInstance.properties = params

            if (!activiteInstance.save(flush: true)) {
                render template: 'edit', model: [activiteInstance: activiteInstance]
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'activite.label', default: 'Activite'), activiteInstance.toString()])
            render template:'show', model:[activiteInstance: activiteInstance]
            break
    }
}

def delete() {
    def activiteInstance = Activite.get(params.id)
    if (!activiteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
        renderList()
    }

    try {
        activiteInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'activite.label', default: 'Activite'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'activite.label', default: 'Activite'), params.id, e.message])
        render template: 'show', model:[activiteInstance: activiteInstance]
    }
}
}
