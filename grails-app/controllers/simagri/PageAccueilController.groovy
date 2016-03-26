package simagri

import grails.converters.JSON
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PageAccueilController {
    def myFormHelperService
    def myUtilityService
    def filterPaneService
    def exportService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PageAccueil.list(params), model: [pageAccueilInstanceCount: PageAccueil.count(),pageAccueilInstance:PageAccueil.first()]
    }

    def show() {
        def pageAccueilInstance = PageAccueil.get(params.id)
        if (!pageAccueilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageAccueil.label', default: 'PageAccueil'), params.id])
            renderList()
        }
        def sizeOfImageList=MonImage.list().size()
        render(template:"show" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)
        ])
    }


    def updateByJSON(){
        if (params.id!="null"){

            def mesImages=PageAccueilMonImage.createCriteria().list {
                eq("pageAccueil.id",new Long (params.id?.toString()?:"0"))
                createAlias('monImage','monImage')
                projections {
                    distinct("monImage.id")
                }
            }

            def resultsimages=[]
            mesImages.each{
                resultsimages <<"mesImages_${it}"
            }
           def pageAccueilInstance=PageAccueil.get(params.id)

            def jsonData = [mesImages: resultsimages,isEmpty:false,pagePrixId:pageAccueilInstance?.pagePrixId,pageOffreId:pageAccueilInstance?.pageOffreId,pageStockId:pageAccueilInstance?.pageStockId,
            pageReseauId:pageAccueilInstance?.pageReseauId,banniereId:pageAccueilInstance?.banniereId]
            render jsonData as JSON
        }
        else {
            render false
        }

    }
    def renderList(){
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = PageAccueil.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+PageAccueil.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,PageAccueil.list(params), [:], [:])
        }
                render(template:"list" ,model: [pageAccueilInstanceList: filterPaneService.filter( params, PageAccueil ), pageAccueilInstanceTotal: filterPaneService.count( params, PageAccueil ),filterParams: FilterPaneUtils.extractFilterParams(params),params:params ]

        )


    }
    def list(){
        renderList()
   }

    def listPjax(){
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if(params?.format && params.format != "html"){
            params.max = PageAccueil.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+PageAccueil.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream,PageAccueil.list(params), [:], [:])
        }
        render(view: "listPjax" ,model: [pageAccueilInstanceList: filterPaneService.filter( params, PageAccueil ), pageAccueilInstanceTotal: filterPaneService.count( params, PageAccueil ),filterParams: FilterPaneUtils.extractFilterParams(params),params:params ]

        )

    }


    @Transactional
    def create() {
        switch (request.method) {
            case 'GET':
                def pageAccueilInstance=new PageAccueil (params)
                def sizeOfImageList=MonImage.count()

                render template:"create" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)]
                break
            case 'POST':
                def pageAccueilInstance = new PageAccueil(params)
                def imagesIds=[]
                params.each {
                    if (it.key.startsWith("mesImages_"))
                        imagesIds << (it.key - "mesImages_")

                }
                pageAccueilInstance.save()
                PageAccueilMonImage.removeAll(pageAccueilInstance)

                def mesImages=MonImage.createCriteria().list(){

                    inList("id",myUtilityService.toLong(imagesIds))
                }
                mesImages.each{image->
                    PageAccueilMonImage.create(pageAccueilInstance, image as MonImage,false)

                }
                flash.message = message(code: 'default.created.message', args: [message(code: 'pageAccueilInstance.label', default: 'PageAccueil'), pageAccueilInstance.toString()])
                def result=[id:pageAccueilInstance.id]
                render result as JSON
                break
        }
    }


    @Transactional
    def edit() {
        switch (request.method) {
            case 'GET':
                def pageAccueilInstance=PageAccueil.get(params.id)
                def sizeOfImageList=MonImage?.list()?.size()
                render(template:"edit" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)
                ])
                break
            case 'POST':
                def pageAccueilInstance = PageAccueil.get(params.theId)
                if (!pageAccueilInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageAccueil.label', default: 'pageAccueil'), params.id])

                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (pageAccueilInstance.version > version) {
                        pageAccueilInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'pageAccueil.label', default: 'PageAccueil')] as Object[],
                                "Another user has updated this PageAccueil while you were editing")

                        def sizeOfImageList=MonImage.list().size()
                        render(template:"edit" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)
                        ])
                    }
                }
                pageAccueilInstance.properties = params
                def imagesIds=[]
                params.each {
                    if (it.key.startsWith("mesImages_"))
                        imagesIds << (it.key - "mesImages_")

                }
                pageAccueilInstance.save()
                PageAccueilMonImage.removeAll(pageAccueilInstance)

                def mesImages=MonImage.createCriteria().list(){

                    inList("id",myUtilityService.toLong(imagesIds))
                }
                mesImages.each{image->
                    PageAccueilMonImage.create(pageAccueilInstance, image as MonImage,false)

                }


                if (!pageAccueilInstance.save(flush: true,failOnError:true)) {
                    def sizeOfImageList=MonImage.list().size()
                    render(template:"edit" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)
                    ])
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'pageAccueil.label', default: 'pageAccueil'), pageAccueilInstance.toString()])

                def sizeOfImageList=MonImage.list().size()
                render(template:"show" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)
                ])
                break
        }
    }



    @Transactional
    def delete() {
        def pageAccueilInstance = PageAccueil.get(params.id)
        if (!pageAccueilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageAccueil.label', default: 'pageAccueil'), params.id])
            renderList()
        }

        try {
            pageAccueilInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pageAccueil.label', default: 'pageAccueil'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pageAccueil.label', default: 'pageAccueil'), params.id, e.message])
            def sizeOfImageList=MonImage.list().size()
            render template:"show" ,model: [pageAccueilInstance:pageAccueilInstance,ctnerTemplateImage:myFormHelperService.getCheckBoxTemplate(sizeOfImageList)]

        }
    }

}
