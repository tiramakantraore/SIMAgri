package simagri
class  MyCommand {

    List<QCMCommand> qcm

    public String toString() {
        return "qcm: $qcm"
    }
}

public  class QCMCommand {
    String question
    String commentaireBonneReponse
    String commentaireMauvaiseReponse


}
import grails.converters.JSON
import groovy.json.JsonSlurper
import org.springframework.dao.DataIntegrityViolationException


class QuizzController {
    def exportService
    def filterPaneService
    def quizzService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = Quizz.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + Quizz.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, Quizz.list(params), [:], [:])
        }
        render template:"list" , model:[quizzInstanceList: filterPaneService.filter(params, Quizz), quizzInstanceTotal: filterPaneService.count(params, Quizz), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }
    def saveByService(){

       render quizzService.execute(params)
    }
    def filter = {
        if (!params.max) params.max = 10
        render( template:'list',
                model: [quizzInstanceList: filterPaneService.filter(params, Quizz),
                        quizzInstanceTotal: filterPaneService.count(params, Quizz),
                        filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                        params: params])
    }

    def create() {
        switch (request.method) {
            case 'GET':
                def quizzInstance= new Quizz(params)
                [quizzInstance: quizzInstance]
                break
            case 'POST':
                def quizzInstance = new Quizz(params)
//                Quizz.list().each{
//                    it.actif=false
//                    it.save();
//                }
                quizzInstance.actif=params.actif;
                if (!quizzInstance.save(flush:true)) {
                    render template: 'create', model: [quizzInstance: quizzInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'quizz.label', default: 'Quizz'), quizzInstance.toString()])
                redirect action: 'show', id: quizzInstance.id
                break
        }
    }

    def show() {
        def quizzInstance = Quizz.get(params.id)
        if (!quizzInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id])
            renderList()
        }

        [quizzInstance: quizzInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def quizzInstance = Quizz.get(params.id)
                if (!quizzInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id])
                    renderList()
                }

                [quizzInstance: quizzInstance]
                break
            case 'POST':
                def quizzInstance = Quizz.get(params.id)
                if (!quizzInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id])
                    renderList()
                }
//                Quizz.list().each{
//                    it.actif=false
//                    it.save();
//                }
                quizzInstance.actif=params.actif;
                quizzInstance.properties = params
                if (params.version) {
                    def version = params.version.toLong()
                    if (quizzInstance.version > version) {
                        quizzInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'quizz.label', default: 'Quizz')] as Object[],
                                "Another user has updated this Quizz while you were editing")
                        render template: 'edit', model: [quizzInstance: quizzInstance]
                    }
                }

                quizzInstance.properties = params

                if (!quizzInstance.save(flush: true)) {
                    render template: 'edit', model: [quizzInstance: quizzInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'quizz.label', default: 'Quizz'), quizzInstance.toString()])
                redirect action: 'show', id: quizzInstance.id
                break
        }
    }

    def delete() {
        def quizzInstance = Quizz.get(params.id)
        if (!quizzInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id])
            renderList()
        }

        try {
            quizzInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quizz.label', default: 'Quizz'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
