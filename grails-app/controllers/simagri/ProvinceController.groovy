package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class ProvinceController {
    def exportService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Province.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + "Provinces".uniquify(".${params.extension}"))


            List fields = ["nom", "region"]
            Map labels = ["nom": "NOM", "region": "REGION"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [nom: humanCase]
            Map parameters = [title: "LISTE DES PROVINCES", "column.widths": [0.6, 0.4], "pdf.orientation": "portrait"]
            exportService.export(params.format, response.outputStream, Province.list(params), fields, labels, formatters, parameters)
        }
        render template: "list", model: [provinceInstanceList: Province.list(params), provinceInstanceTotal: Province.count()]
    }


    def list() {
        renderList()
    }

    def create() {
        switch (request.method) {
            case 'GET':
               render template:'create', model:[provinceInstance: new Province(params)]
                break
            case 'POST':
                def provinceInstance = new Province(params)
                if (!provinceInstance.save(flush: true)) {
                    render template: 'create', model: [provinceInstance: provinceInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'province.label', default: 'Province'), provinceInstance.toString()])
                def result=[id:provinceInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def provinceInstance = Province.get(params.id)
        if (!provinceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'province.label', default: 'Province'), params.id])
            renderList()
        }

       render template:'show', model:[provinceInstance: provinceInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def provinceInstance = Province.get(params.id)
                if (!provinceInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'province.label', default: 'Province'), params.id])
                    renderList()
                }

               render template:'edit', model:[provinceInstance: provinceInstance]
                break
            case 'POST':
                def provinceInstance = Province.get(params.id)
                if (!provinceInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'province.label', default: 'Province'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (provinceInstance.version > version) {
                        provinceInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'province.label', default: 'Province')] as Object[],
                                "Another user has updated this Province while you were editing")
                        render template: 'edit', model: [provinceInstance: provinceInstance]
                    }
                }

                provinceInstance.properties = params

                if (!provinceInstance.save(flush: true)) {
                    render template: 'edit', model: [provinceInstance: provinceInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'province.label', default: 'Province'), provinceInstance.toString()])
                render template: 'show', model:[provinceInstance: Province.get(params.id)]
                break
        }
    }

    def delete() {
        def provinceInstance = Province.get(params.id)
        if (!provinceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'province.label', default: 'Province'), params.id])
            renderList()
        }

        try {
            provinceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'province.label', default: 'Province'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'province.label', default: 'Province'), params.id, e.message])
            render template: 'show', model:[provinceInstance: provinceInstance]
        }
    }
}
