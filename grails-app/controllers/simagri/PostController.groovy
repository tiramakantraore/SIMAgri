package simagri

import org.springframework.dao.DataIntegrityViolationException


class PostController {
    def exportService
    def filterPaneService
    def postManagerService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Post.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Post.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Post.list(params), [:], [:])
        }
        render template:"list" , model:[postInstanceList: filterPaneService.filter(params, Post), postInstanceTotal: filterPaneService.count(params, Post), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }
    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [postInstanceList : filterPaneService.filter(params, Post),
                        postInstanceTotal: filterPaneService.count(params, Post),
                        filterParams     : org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params           : params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                [postInstance: new Post(params)]
                break
            case 'POST':
                def postInstance=new Post(params)
                if (!postManagerService.addPost(params)) {
                    render template: 'create', model: [postInstance: postInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'post.label', default: 'Post'), params?.title?:""])
                redirect action: 'show', id: postInstance.id
                break
        }
    }

    def show() {
        def postInstance = Post.get(params.id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
            renderList()
        }

        [postInstance: postInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def postInstance = Post.get(params.id)
                if (!postInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
                    renderList()
                }

                [postInstance: postInstance]
                break
            case 'POST':
                def postInstance = Post.get(params.id)
                if (!postInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (postInstance.version > version) {
                        postInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'post.label', default: 'Post')] as Object[],
                                "Another user has updated this Post while you were editing")
                        render template: 'edit', model: [postInstance: postInstance]
                    }
                }

                postInstance.properties = params

                if (!postInstance.save(flush: true)) {
                    render template: 'edit', model: [postInstance: postInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'post.label', default: 'Post'), postInstance.toString()])
                redirect action: 'show', id: postInstance.id
                break
        }
    }

    def delete() {
        def postInstance = Post.get(params.id)
        if (!postInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'post.label', default: 'Post'), params.id])
            renderList()
        }

        try {
            postInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'post.label', default: 'Post'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'post.label', default: 'Post'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
