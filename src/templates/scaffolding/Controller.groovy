<%=packageName ? "package ${packageName}\n\n" : ''%>import org.springframework.dao.DataIntegrityViolationException


class ${className}Controller {
def exportService
def filterPaneService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    redirect action: 'list', params: params
}

def list() {
    params.max = Math.min(params.max ? params.int('max') : 5, 100)
    if(params?.format && params.format != "html"){
        params.max = ${className}.count()
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        response.setHeader("Content-disposition", "attachment; filename="+${className}.uniquify(".\${params.extension}"))

        exportService.export(params.format, response.outputStream,${className}.list(params), [:], [:])
    }
    [${propertyName}List: filterPaneService.filter( params, ${className} ), ${propertyName}Total: filterPaneService.count( params, ${className} ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

}
def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ ${propertyName}List: filterPaneService.filter( params, ${className} ),
                    ${propertyName}Total: filterPaneService.count( params, ${className} ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
def create() {
    switch (request.method) {
        case 'GET':
            [${propertyName}: new ${className}(params)]
            break
        case 'POST':
            def ${propertyName} = new ${className}(params)
            if (!${propertyName}.save(flush: true)) {
                render template: 'create', model: [${propertyName}: ${propertyName}]
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.toString()])
            redirect action: 'show', id: ${propertyName}.id
            break
    }
}

def show() {
    def ${propertyName} = ${className}.get(params.id)
    if (!${propertyName}) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
        redirect action: 'list'
        return
    }

    [${propertyName}: ${propertyName}]
}

def edit() {
    switch (request.method) {
        case 'GET':
            def ${propertyName} = ${className}.get(params.id)
            if (!${propertyName}) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
                redirect action: 'list'
                return
            }

            [${propertyName}: ${propertyName}]
            break
        case 'POST':
            def ${propertyName} = ${className}.get(params.id)
            if (!${propertyName}) {
                flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
                redirect action: 'list'
                return
            }

            if (params.version) {
                def version = params.version.toLong()
                if (${propertyName}.version > version) {<% def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className) %>
                        ${propertyName}.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
                                "Another user has updated this ${className} while you were editing")
                    render template: 'edit', model: [${propertyName}: ${propertyName}]
                    return
                }
            }

            ${propertyName}.properties = params

            if (!${propertyName}.save(flush: true)) {
                render template: 'edit', model: [${propertyName}: ${propertyName}]
                return
            }

            flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.toString()])
            redirect action: 'show', id: ${propertyName}.id
            break
    }
}

def delete() {
    def ${propertyName} = ${className}.get(params.id)
    if (!${propertyName}) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
        redirect action: 'list'
        return
    }

    try {
        ${propertyName}.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
        redirect action: 'list'
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id, e.message])
        redirect action: 'show', id: params.id
    }
}
}
