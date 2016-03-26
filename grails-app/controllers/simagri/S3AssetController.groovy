package simagri

import org.grails.plugin.filterpane.FilterPaneUtils
import static grails.async.Promises.*

/* Copyright 2006-2007 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* For more information please visit www.cantinaconsulting.com
* or email info@cantinaconsulting.com
*/

class S3AssetController {

    def myAWSService
    def exportService
    def filterPaneService
    def  myUtilityService
    def hdImageService
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']
    def consulter = {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = S3Asset.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Document".uniquify(".${params.extension}"))
            List fields = ["title"]
            Map labels = ["title":"TITRE"]
            // Formatter closure
            def humanCase = { domain, value -> return value?.humanify() }

            Map formatters = [title:humanCase]
            Map parameters = [title: "LISTE DES DOCUMENTS", "column.widths": [1],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, S3Asset.list(params), fields, labels,formatters,parameters)

        }
        def sortIndex="id"
        def sortOrder="desc"
        def s3AssetList = S3Asset.createCriteria().list(params) {
            ne("status", "removed")
            order(sortIndex, sortOrder)
        }

        [s3AssetList: s3AssetList, s3AssetInstanceTotal: filterPaneService.count(params, S3Asset), filterParams: FilterPaneUtils.extractFilterParams(params), params: params]

    }

    def export = {attrs ->

        def response = attrs.response

        params.max = S3Asset.count()
        response.contentType = grailsApplication.config.grails.mime.types[params.format]
        response.setHeader("Content-disposition", "attachment; filename="+"DOCUMENTS".uniquify(".${params.extension}"))
        List fields = [ "Titre","description","Url"]
        Map labels = ["Titre":"TITRE","description":"DESCRIPTION", "localUrl": "URL"]

        def humanCase = {
            domain, value ->
                return value?.humanify()
        }

        Map formatters = [:]

        Map parameters = [title: "LISTE DES DOCUMENTS", "column.widths": [0.1, 0.5, 0.4]]

        exportService.export(params.format, response.outputStream, S3Asset.list(params), fields, labels,formatters,parameters)

    }
    def remoteFilter ={

        if (!params.max) params.max = 10


        def documentTotal= 0
        def documentList
        if(params?.format && params.format != "html" && session.filterParams)
        {
            documentTotal= filterPaneService.count(session.filterParams, S3Asset)
            documentList= filterPaneService.filter(session.filterParams, S3Asset)
            session.filterParams.max = documentTotal
            export(response: response, extension: params.extension, format: params.format, exportList: documentList)
        }
        else{
            documentList= filterPaneService.filter(params, S3Asset)
            documentTotal= filterPaneService.count(params, S3Asset)
            session.filterParams = params
        }

        render(template: 'documents' ,model:[ s3AssetList: documentList,documentTotal:documentTotal,params:params])
    }
    def filter ={

        if (!params.max) params.max = 10


        def documentTotal= 0
        def documentList
        if(params?.format && params.format != "html" && session.filterParams)
        {
             documentTotal= filterPaneService.count(session.filterParams, S3Asset)
             documentList= filterPaneService.filter(session.filterParams, S3Asset)
            session.filterParams.max = documentTotal
            export(response: response, extension: params.extension, format: params.format, exportList: documentList)
        }
        else{
            documentList= filterPaneService.filter(params, S3Asset)
            documentTotal= filterPaneService.count(params, S3Asset)
            session.filterParams = params
        }

        render(view: 'list' ,model:[ s3AssetList: documentList,documentTotal:documentTotal,params:params])
    }
    def listConsulter = {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = S3Asset.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Document".uniquify(".${params.extension}"))
            List fields = ["title"]
            Map labels = ["title":"TITRE"]
            // Formatter closure
            def humanCase = { domain, value -> return value?.humanify() }

            Map formatters = [title:humanCase]
            Map parameters = [title: "LISTE DES DOCUMENTS", "column.widths": [1],"pdf.orientation":"portrait"]
            exportService.export(params.format, response.outputStream, S3Asset.list(params), fields, labels,formatters,parameters)

        }

        [s3AssetList: filterPaneService.filter(params, S3Asset), s3AssetInstanceTotal: filterPaneService.count(params, S3Asset), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def renderList(){
        def user = myUtilityService.getCurrent()
        params.max = Math.min(params.max ? params.int('max') : 20, 100)
        Map newParams=params+[ 'filter.op.status' : 'Equal',filter:['op.status':'Equal', op:[status:'Equal']
                                                                    , 'status':S3Asset.STATUS_HOSTED]]
        //                , 'reseauId':user?.reseauPrincipalId]]


        def s3AssetInstanceTotal=filterPaneService.count(newParams, S3Asset)
        if (newParams?.format && newParams.format != "html") {
            newParams.max = s3AssetInstanceTotal
            response.contentType = grailsApplication.config.grails.mime.types[newParams.format]
            response.setHeader("Content-disposition", "attachment; filename="+"Document".uniquify(".${newParams.extension}"))
            List fields = ["title"]
            Map labels = ["title":"TITRE"]
            // Formatter closure
            def humanCase = { domain, value -> return value?.humanify() }

            Map formatters = [title:humanCase]
            Map parameters = [title: "LISTE DES DOCUMENTS", "column.widths": [1],"pdf.orientation":"portrait"]
            exportService.export(newParams.format, response.outputStream, S3Asset.list(newParams), fields, labels,formatters,parameters)

        }

        render template:"list" , model:[s3AssetList: filterPaneService.filter(newParams, S3Asset), s3AssetInstanceTotal: s3AssetInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(newParams), params: newParams]

    }
    def list () {
        renderList()
    }

    def show = {
        def s3AssetInstance = S3Asset.get(params.id)
        if (!s3AssetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 's3Asset.label', default: 'Document'), params.id])
            renderList()
        }

       render template:'show', model:[s3AssetInstance: s3AssetInstance]

    }
    def showConsulter = {
        def s3AssetInstance = S3Asset.get(params.id)

        if (!s3AssetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 's3Asset.label', default: 'Document'), params.id])
            renderList()
        }


        def pagesAv=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_AFRIQUE_VERTE)
        def pagesSIMAgri=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_SIMAGRI)
        def pagesPartenaires=PageUtilisateur.findAllByDestinationType(DestinationType.PAGE_PARTENAIRE)

        render view:'showConsulter', model:  [pageAccueilInstance: PageAccueil.findByEstPrincipal(true),s3AssetInstance: s3AssetInstance, pagesAv:pagesAv,pagesSIMAgri:pagesSIMAgri,pagesPartenaires:pagesPartenaires
                                             ]


    }

    def delete = {
        def s3AssetInstance = S3Asset.get(params.id)
        if(s3AssetInstance) {

//            myAWSService.delete(s3AssetInstance);
            s3AssetInstance.delete(flush: true)
            flash.message = "Document  supprimé"
            renderList()
        }
        else {
            flash.message = "Document de nom ${params.title} non trouvé"
            renderList()
        }
    }
    

    def edit = {
        def s3AssetInstance = S3Asset.get( params.id )

        if(!s3AssetInstance) {
            flash.message = "Document de nom ${params.title} non trouvé"
            renderList()
        }
        else {
            render template:'edit', model:[ s3AssetInstance : s3AssetInstance ]
        }
    }
    def edit() {
        switch (request.method) {
            case 'GET':
                def s3AssetInstance = S3Asset.get( params.id )

                if(!s3AssetInstance) {
                    flash.message = "Document de nom ${params.title} non trouvé"
                    renderList()
                }
                else {
                    render template:'edit', model:[ s3AssetInstance : s3AssetInstance ]
                }
                break
            case 'POST':
                def s3AssetInstance = S3Asset.get( params.id )
                if (!s3AssetInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 's3Asset.label', default: 'S3Asset'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (s3AssetInstance.version > version) {
                        s3AssetInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 's3Asset.label', default: 'S3Asset')] as Object[],
                                "Another user has updated this Reseau while you were editing")
                        render template: 'edit', model: [reseauInstance: s3AssetInstance]
                    }
                }

                if (!s3AssetInstance.save(flush: true)) {
                    render template: 'edit', model: [s3AssetInstance: s3AssetInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 's3AssetInstance.label', default: 'S3AssetInstance'), s3AssetInstance.toString()])
                render template: 'show', model:[s3AssetInstance: s3AssetInstance]
                break
        }
    }


    def create = {

        def s3AssetInstance = new S3Asset()
        s3AssetInstance.properties = params

        return [s3AssetInstance:s3AssetInstance]
    }

    def save = {
        def user = myUtilityService.getCurrent()

        def s3Asset = new S3Asset(params)
                    if(!s3Asset.hasErrors()) {

                        def f = request.getFile('myFile')

                        if(!f.empty) {
//                            task {
                                importer(f, s3Asset, user)
//                            }

                        } else {
                            s3Asset.properties=params
                            s3Asset.save(flush: true)
                            redirect(action:'index')
                        }

                        render(view:'show',model:[s3AssetInstance:s3Asset])

                    }
                    else {
                        render(view:'create',model:[s3AssetInstance:s3Asset])
                    }

    }


}
