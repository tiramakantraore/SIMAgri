package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class MesureController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }

    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Mesure.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Mesure".uniquify(".${params.extension}"))
            List fields = [ "code","name","valeur"]
            Map labels = ["code": "CODE","name":"NOM", "valeur": "VALEUR"]
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase,nom:humanCase]

            Map parameters = [title: "LISTE DES UNITES DE MESURE", "column.widths": [0.25, 0.5,  0.25],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Mesure.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" ,model:[mesureInstanceList: filterPaneService.filter(params, Mesure), mesureInstanceTotal: filterPaneService.count(params, Mesure), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [mesureInstanceList: filterPaneService.filter(params, Mesure),
                        mesureInstanceTotal: filterPaneService.count(params, Mesure),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
               render template:'create', model:[mesureInstance: new Mesure(params)]
                break
            case 'POST':
                def mesureInstance = new Mesure(params)
                mesureInstance.valeur=new BigDecimal(params.valeur?:"0.0")

                if (!mesureInstance.save(flush: true)) {
                    render template: 'create', model: [mesureInstance: mesureInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'mesure.label', default: 'Mesure'), mesureInstance.toString()])
                def result=[id:mesureInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def mesureInstance = Mesure.get(params.id)
        if (!mesureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id])
            renderList()
        }
        render template: 'show', model:[mesureInstance: mesureInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def mesureInstance = Mesure.get(params.id)
                if (!mesureInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id])
                    renderList()
                }

                render template: 'edit',model:[mesureInstance: mesureInstance]
                break
            case 'POST':
                def mesureInstance = Mesure.get(params.id)
                mesureInstance.valeur=new BigDecimal(params.valeur?:"0.0")
                if (!mesureInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (mesureInstance.version > version) {
                        mesureInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'mesure.label', default: 'Mesure')] as Object[],
                                "Another user has updated this Mesure while you were editing")
                        render template: 'edit', model: [mesureInstance: mesureInstance]
                    }
                }

                mesureInstance.properties = params

                if (!mesureInstance.save(flush: true)) {
                    render template: 'edit', model: [mesureInstance: mesureInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'mesure.label', default: 'Mesure'), mesureInstance.toString()])
                render template: 'show', model:[mesureInstance: Mesure.get(params.id)]
                break
        }
    }
    def listOfMesure() {
        if (!session['listOfMesure']){


        def lst = Mesure.findAll()
        StringBuffer buf = new StringBuffer("<select>")
        lst.each{
            buf.append('<option value=" ').append(it.id).append('">')
            buf.append(it.code)
            buf.append('</option>')
        }
        buf.append('</select>')
        session['listOfMesure']=buf.toString()
        }

        render  session['listOfMesure']
    }
    def delete() {
        def mesureInstance = Mesure.get(params.id)
        if (!mesureInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id])
            renderList()
        }

        try {
            mesureInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'mesure.label', default: 'Mesure'), params.id, e.message])
            render template: 'show', model:[mesureInstance: mesureInstance]
        }
    }
}
