package simagri

import grails.converters.JSON
import grails.transaction.Transactional
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.bind.ServletRequestDataBinder
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor

import javax.servlet.http.HttpServletRequest

@Transactional(readOnly = true)
class PageUtilisateurController {
def exportService
def filterPaneService
def myUtilityService
static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

def index() {
    renderList()
}
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = PageUtilisateur.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+PageUtilisateur.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,PageUtilisateur.list(params), [:], [:])
        }

        render template:"list" ,model:  [pageUtilisateurInstanceList: filterPaneService.filter( params, PageUtilisateur ), pageUtilisateurInstanceTotal: filterPaneService.count( params, PageUtilisateur ),filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),params:params ]

    }
def list() {
    renderList()
}
    def listePartenaires() {
        params.max = 100
        Map newParams=params+[ 'filter.op.destinationType' : 'Equal',filter:['op.destinationType':'Equal', op:[destinationType:'Equal'], 'destinationType':DestinationType.PAGE_PARTENAIRE]]
        def pageUtilisateurInstanceTotal=filterPaneService.count(newParams, PageUtilisateur)
        if(params?.format && params.format != "html"){
            newParams.max = pageUtilisateurInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+PageUtilisateur.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,PageUtilisateur.list(newParams), [:], [:])
        }
        def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)




        [pageUtilisateurInstanceList: filterPaneService.filter( newParams, PageUtilisateur ), pageUtilisateurInstanceTotal: pageUtilisateurInstanceTotal,filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams),pageAccueilInstance:PageAccueil.findByEstPrincipal(true),params:newParams, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires ]

    }


def filter = {
    if(!params.max) params.max = 10
    render( template:'list',
            model:[ pageUtilisateurInstanceList: filterPaneService.filter( params, PageUtilisateur ),
                    pageUtilisateurInstanceTotal: filterPaneService.count( params, PageUtilisateur ),
                    filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                    params:params ] )
}
    @Transactional
    def create() {
        switch (request.method) {
            case 'GET':
                render template: "create", model: [pageUtilisateurInstance: new PageUtilisateur(params)]
                break
            case 'POST':
                def newParams= [:] << params
                newParams.remove('photo')

                def pageUtilisateurInstance = new PageUtilisateur(newParams)

                if (!params.photo?.isEmpty()) {
                    MultipartFile photo = params.photo
                    if (photo?.getBytes()?.size() > 0) {
                        pageUtilisateurInstance.photo = photo.getBytes()
                    }
                }

                def user=myUtilityService.getCurrent()
                pageUtilisateurInstance.proprietaire=user?.reseauPrincipal
                if (!pageUtilisateurInstance.save(flush: true)) {
                    render template: 'create', model: [pageUtilisateurInstance: pageUtilisateurInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), pageUtilisateurInstance.toString()])
                def result=[id:pageUtilisateurInstance.id]
                render result as JSON
                break
        }


    }
//    protected void initBinder(HttpServletRequest request,
//                              ServletRequestDataBinder binder) throws Exception {
//
//        binder.registerCustomEditor(byte[].class,
//                new ByteArrayMultipartFileEditor());
//    }

def renderShow() {
    def pageUtilisateurInstance=PageUtilisateur.get(params.id)
    render(template:"show" ,model: [pageUtilisateurInstance: pageUtilisateurInstance])
}
def imagePage = {
        def pageUtilisateurInstance = PageUtilisateur.get(params.id)
        def img=pageUtilisateurInstance?.photo // a byte[], File or InputStream
        if (img) {
        render(file: img, contentType: 'image/png')
        }else {
            render false
        }
}
def show() {
    def pageUtilisateurInstance = PageUtilisateur.get(params.id)
    if (!pageUtilisateurInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
        renderList()
    }

    render template:"show" ,model: [pageUtilisateurInstance: pageUtilisateurInstance]


}
    def showPage() {
        def pageUtilisateurInstance = PageUtilisateur.get(params.id)
        if (!pageUtilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
            renderList()
        }


        def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        render template:'showPage', model:  [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pageUtilisateurInstance:pageUtilisateurInstance, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires
                                           ]

    }
def showPublic() {
        def pageUtilisateurInstance = PageUtilisateur.get(params.id)
        if (!pageUtilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
            renderList()
        }
    def developer=grailsApplication.config.grails.simagriDeveloper
    def organisation=grailsApplication.config.grails.organisation
    def TelOrganisation=grailsApplication.config.grails.TelOrganisation
    def FaxOrganisation=grailsApplication.config.grails.FaxOrganisation
    def MobileOrganisation=grailsApplication.config.grails.MobileOrganisation
    def EmailOrganisation=grailsApplication.config.grails.EmailOrganisation

    def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
    def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
    def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
    render view:'showPublic', model:  [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pageUtilisateurInstance:pageUtilisateurInstance, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires
                                              ,developer:developer,
                                              organisation:organisation,TelOrganisation:TelOrganisation,FaxOrganisation:FaxOrganisation,MobileOrganisation:MobileOrganisation,
                                              EmailOrganisation:EmailOrganisation]
    }
    def showByDestinationType() {
        def pageUtilisateurInstance = PageUtilisateur.findByDestinationType(DestinationType.valueOfName(params.DestinationType))
        if (!pageUtilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
            renderList()
        }

        def developer=grailsApplication.config.grails.simagriDeveloper
        def organisation=grailsApplication.config.grails.organisation
        def TelOrganisation=grailsApplication.config.grails.TelOrganisation
        def FaxOrganisation=grailsApplication.config.grails.FaxOrganisation
        def MobileOrganisation=grailsApplication.config.grails.MobileOrganisation
        def EmailOrganisation=grailsApplication.config.grails.EmailOrganisation

        def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
        render view:'showByDestination', model:  [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pageUtilisateurInstance:pageUtilisateurInstance, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires
                                                  ,developer:developer,
                                                  organisation:organisation,TelOrganisation:TelOrganisation,FaxOrganisation:FaxOrganisation,MobileOrganisation:MobileOrganisation,
                                                  EmailOrganisation:EmailOrganisation]

    }
    def showByPartnerType() {
        def pageUtilisateurInstance = PageUtilisateur.findByTypePartenaire(TypePartenaire.valueOfName(params.typePartenaire))
        if (!pageUtilisateurInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
            renderList()
        }
        def developer=grailsApplication.config.grails.simagriDeveloper
        def organisation=grailsApplication.config.grails.organisation
        def TelOrganisation=grailsApplication.config.grails.TelOrganisation
        def FaxOrganisation=grailsApplication.config.grails.FaxOrganisation
        def MobileOrganisation=grailsApplication.config.grails.MobileOrganisation
        def EmailOrganisation=grailsApplication.config.grails.EmailOrganisation

        def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)
        render view:'showByDestination', model:  [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pageUtilisateurInstance:pageUtilisateurInstance, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires
                                                  ,developer:developer,
                                                   organisation:organisation,TelOrganisation:TelOrganisation,FaxOrganisation:FaxOrganisation,MobileOrganisation:MobileOrganisation,
                                                   EmailOrganisation:EmailOrganisation]


    }
    @Transactional
    def edit() {
        switch (request.method) {
            case 'GET':
                def pageUtilisateurInstance = PageUtilisateur.get(params.id)
                render template: "edit", model: [pageUtilisateurInstance: pageUtilisateurInstance]
                break
            case 'POST':
                def pageUtilisateurInstance = PageUtilisateur.get(params.id)

                if (!pageUtilisateurInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (pageUtilisateurInstance.version > version) {
                        pageUtilisateurInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur')] as Object[],
                                "Another user has updated this PageUtilisateur while you were editing")
                        render template:"edit" ,model: [pageUtilisateurInstance:pageUtilisateurInstance]
                    }
                }

                def newParams= [:] << params
                newParams.remove('photo')
                pageUtilisateurInstance.properties = newParams
                pageUtilisateurInstance.contenu=newParams.contenuCKEdit



                if (!params.photo?.isEmpty()) {
                    MultipartFile photo = params.photo
                    if (photo?.getBytes()?.size() > 0) {
                        pageUtilisateurInstance.photo = photo.getBytes()
                    }
                }
                if (pageUtilisateurInstance.removeImage()){
                    pageUtilisateurInstance.photo =null
                }

                if (!pageUtilisateurInstance.save(flush: true)) {
                    render template:"edit" ,model: [pageUtilisateurInstance: pageUtilisateurInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), pageUtilisateurInstance.toString()])
                render template:"show" ,model: [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),pageUtilisateurInstance: PageUtilisateur.get(params.id)]
                break
        }
    }


def delete() {
    def pageUtilisateurInstance = PageUtilisateur.get(params.id)
    if (!pageUtilisateurInstance) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
        renderList()
    }

    try {
        pageUtilisateurInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id])
        renderList()
    }
    catch (DataIntegrityViolationException e) {
        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pageUtilisateur.label', default: 'PageUtilisateur'), params.id, e.message])
        render template: 'show', model:[pageUtilisateurInstance: pageUtilisateurInstance]
    }
}
}
