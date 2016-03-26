package simagri

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException


class LieuxController {
    def exportService
    def filterPaneService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Lieux.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Lieux".uniquify(".${params.extension}"))


            List fields = ["nom","commune","longitude","latitude"]
            Map labels = ["nom":"NOM","commune":"COMMUNE", "longitude": "LONGITUDE","latitude":"LATITUDE"]
            // Formatter closure
            def humanCase = {
                domain, value ->
                    return value?.humanify()
            }

            Map formatters = [code:humanCase]
            Map parameters = [title: "LISTE DES LIEUX", "column.widths": [0.4,0.2, 0.2, 0.2],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, Lieux.list(params), fields, labels,formatters,parameters)
        }
        render template:"list" , model:[lieuxInstanceList: filterPaneService.filter(params, Lieux), lieuxInstanceTotal: filterPaneService.count(params, Lieux), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
      renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render template:'list',
                model: [lieuxInstanceList: filterPaneService.filter(params, Lieux),
                        lieuxInstanceTotal: filterPaneService.count(params, Lieux),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params]
    }

    def create() {
        switch (request.method) {
            case 'GET':
               render template:'create', model:[lieuxInstance: new Lieux(params)]
                break
            case 'POST':
                def lieuxInstance = new Lieux(params)
                bindcoordonnes(lieuxInstance)

                if (!lieuxInstance.save(flush: true)) {
                    render template: 'create', model: [lieuxInstance: lieuxInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'lieux.label', default: 'Lieux'), lieuxInstance.toString()])
                def result=[id:lieuxInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def lieuxInstance = Lieux.get(params.id)
        if (!lieuxInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id])
            renderList()
        }

        render template:'show',model:[lieuxInstance: lieuxInstance]
    }
    public bindcoordonnes(Lieux lieuInstance) {
        if (params?.latitude == null)
            lieuInstance.latitude = new BigDecimal("0.0")
        else
            lieuInstance.latitude = new BigDecimal(params?.latitude ?: "0.0")
        if (params?.longitude == null)
            lieuInstance.longitude = new BigDecimal("0.0")
        else
            lieuInstance.longitude = new BigDecimal(params?.longitude ?: "0.0")
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def lieuxInstance = Lieux.get(params.id)
                if (!lieuxInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id])
                    renderList()
                }

                render template:'edit',model:[lieuxInstance: lieuxInstance]
                break
            case 'POST':
                def lieuxInstance = Lieux.get(params.id)


                if (!lieuxInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (lieuxInstance.version > version) {
                        lieuxInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'lieux.label', default: 'Lieux')] as Object[],
                                "Another user has updated this Lieux while you were editing")
                        render template: 'edit', model: [lieuxInstance: lieuxInstance]
                    }
                }

                lieuxInstance.properties = params
                bindcoordonnes(lieuxInstance)

                lieuxInstance.validate()
                lieuxInstance.errors.fieldErrors.each {
                    log.info("erreur : ${it}")
                }
                if (!lieuxInstance.save(flush: true)) {
                    render template: 'edit', model: [lieuxInstance: lieuxInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'lieux.label', default: 'Lieux'), lieuxInstance.toString()])
                render template: 'show', model:[lieuxInstance: Lieux.get(params.id)]
                break
        }
    }
    def geoCodeAll(){
        def geoname
        Lieux.list().each{location->
            geoname=Geoname.findByName(location.nom)
            if (geoname)
            {
                location.latitude=geoname.latitude
                location.longitude=geoname.longitude
            }
            else {
                location.latitude=0
                location.longitude=0
            }
        }
        redirect(action: "list")

    }
    def delete() {
        def lieuxInstance = Lieux.get(params.id)
        if (!lieuxInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id])
            renderList()
        }

        try {
            lieuxInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lieux.label', default: 'Lieux'), params.id, e.message])
            render template: 'show', model:[lieuxInstance: lieuxInstance]
        }
    }
}
