package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class QualiteController {
def exportService
def filterPaneService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    renderList()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = Qualite.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Qualites".uniquify(".${params.extension}"))
            List fields = ["code","nom","description"]
            Map labels = ["code":"CODE","nom":"NOM",  "description": "DESCRIPTION"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase]
            Map parameters = [title: "LISTE DES QUALITES", "column.widths": [0.1,0.4,  0.5],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Qualite.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" , model:[qualiteInstanceList: filterPaneService.filter( params, Qualite ), qualiteInstanceTotal: filterPaneService.count( params, Qualite ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def list() {
    renderList()
}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ qualiteInstanceList: filterPaneService.filter( params, Qualite ),
                    qualiteInstanceTotal: filterPaneService.count( params, Qualite ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
def create() {
    switch (request.method) {
        case 'GET':
            render template:'create',model:[qualiteInstance: new Qualite(params)]
            break
        case 'POST':
            def qualiteInstance = new Qualite(params)
            if (!qualiteInstance.save(flush: true)) {
                render template: 'create', model: [qualiteInstance: qualiteInstance]
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'qualite.label', default: 'Qualite'), qualiteInstance.id])
            def result=[id:qualiteInstance.id]
            render result as JSON
            break
    }
}

def show() {
    def qualiteInstance = Qualite.get(params.id)
    if (!qualiteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id])
        renderList()
    }

    render template: 'show', model:[qualiteInstance: qualiteInstance]
}

def edit() {
    switch (request.method) {
        case 'GET':
            def qualiteInstance = Qualite.get(params.id)
            if (!qualiteInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id])
                renderList()
            }

            render template: 'edit',model:[qualiteInstance: qualiteInstance]
            break
        case 'POST':
            def qualiteInstance = Qualite.get(params.id)
            if (!qualiteInstance) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id])
                renderList()
            }

            if (params.version) {
                def version = params.version.toLong()
                if (qualiteInstance.version > version) {
                        qualiteInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'qualite.label', default: 'Qualite')] as Object[],
                                "Another user has updated this Qualite while you were editing")
                    render template: 'edit', model: [qualiteInstance: qualiteInstance]
                }
            }

            qualiteInstance.properties = params

            if (!qualiteInstance.save(flush: true)) {
                render template: 'edit', model: [qualiteInstance: qualiteInstance]
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: 'qualite.label', default: 'Qualite'), qualiteInstance.id])
            render template: 'show', model:[qualiteInstance: Qualite.get(params.id)]
            break
    }
}

def delete() {
    def qualiteInstance = Qualite.get(params.id)
    if (!qualiteInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id])
        renderList()
    }

    try {
        qualiteInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'qualite.label', default: 'Qualite'), params.id, e.message])
        render template: 'show', model:[qualiteInstance: qualiteInstance]
    }
}
}
