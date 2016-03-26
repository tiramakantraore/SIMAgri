package simagri

import org.springframework.dao.DataIntegrityViolationException


class GeonameController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        render template:"list" ,  model:[geonameInstanceList: Geoname.list(params), geonameInstanceTotal: Geoname.count()]
    }

    def create() {
        [geonameInstance: new Geoname(params)]
    }

    def save() {
        def geonameInstance = new Geoname(params)
        if (!geonameInstance.save(flush: true)) {
            render(view: "create", model: [geonameInstance: geonameInstance])
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'geoname.label', default: 'Geoname'), geonameInstance.toString()])
        redirect(action: "show", id: geonameInstance.id)
    }

    def show() {
        def geonameInstance = Geoname.get(params.id)
        if (!geonameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id])
            redirect(action: "list")
        }

        [geonameInstance: geonameInstance]
    }

    def edit() {
        def geonameInstance = Geoname.get(params.id)
        if (!geonameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id])
            redirect(action: "list")
        }

        [geonameInstance: geonameInstance]
    }

    def update() {
        def geonameInstance = Geoname.get(params.id)
        if (!geonameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id])
            redirect(action: "list")
        }

        if (params.version) {
            def version = params.version.toLong()
            if (geonameInstance.version > version) {
                geonameInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'geoname.label', default: 'Geoname')] as Object[],
                        "Another user has updated this Geoname while you were editing")
                render(view: "edit", model: [geonameInstance: geonameInstance])
            }
        }

        geonameInstance.properties = params

        if (!geonameInstance.save(flush: true)) {
            render(view: "edit", model: [geonameInstance: geonameInstance])
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'geoname.label', default: 'Geoname'), geonameInstance.toString()])
        redirect(action: "show", id: geonameInstance.id)
    }

    def delete() {
        def geonameInstance = Geoname.get(params.id)
        if (!geonameInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id])
            redirect(action: "list")
        }

        try {
            geonameInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'geoname.label', default: 'Geoname'), params.id, e.message])
            redirect(action: "show", id: params.id)
        }
    }
}
