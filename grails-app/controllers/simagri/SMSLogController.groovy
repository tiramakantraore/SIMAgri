package simagri

import grails.converters.JSON
import org.grails.plugin.filterpane.FilterPaneUtils
import org.springframework.dao.DataIntegrityViolationException


class SMSLogController {
    def exportService
    def filterPaneService
    def myFormHelperService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)

        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html" && session.filterParams) {
            params.max = filterPaneService.count(params, SMSLogger)
            def smsLoggerInstanceList= filterPaneService.filter(session.filterParams, SMSLogger)
            session.filterParams.max = filterPaneService.count(params, SMSLogger)
            export(response: response, extension: params.extension, format: params.format, exportList: smsLoggerInstanceList)
        }else {
            session.filterParams = params
        }
        render template:"list" , model:[SMSLoggerInstanceList: filterPaneService.filter(session.filterParams, SMSLogger), SMSLoggerInstanceTotal: filterPaneService.count(session.filterParams, SMSLogger),reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), filterParams: FilterPaneUtils.extractFilterParams(session.filterParams), params: session.filterParams]

    }
    def renderListWithPeriode() {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        def smsLoggerInstanceList
        def SMSLoggerInstanceTotal
        def listefromDate
        def listetoDate




        def fromDay
        def fromMonth
        def fromYear

        def toDay
        def toMonth
        def toYear
        String reseauId
        Date fromDate
        Date toDate

        if (params.fromDate){
             listefromDate=params.fromDate?.tokenize('/')
            listetoDate=params.toDate?.tokenize('/')




            fromDay=listefromDate[0].toInteger()?:1
            fromMonth=listefromDate[1].toInteger()?:1
            fromYear=listefromDate[2].toInteger()?:1890

            toDay=listetoDate[0].toInteger()?:1
            toMonth=listetoDate[1].toInteger()?:1
            toYear=listetoDate[2].toInteger()?:1890
            reseauId=params.reseauId?:"1"
            fromDate=new Date(fromYear-1900,fromMonth-1,fromDay)
            toDate=new Date(toYear-1900,toMonth-1,toDay)
            session['reseauId']=reseauId
            session['fromDate']=fromDate
            session['toDate']=toDate
        }else {
            reseauId=session['reseauId']
            fromDate=session['fromDate'] as Date
            toDate=session['toDate'] as Date
        }



        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)

        if (params?.format!=null && params.format != "html") {
            smsLoggerInstanceList=SMSLogger.createCriteria().list() {
                if (reseauId)
                eq('reseau.id',new Long(reseauId?:"1"))
                and {
                    between("date", fromDate-1, toDate+1)
                }
            }
            SMSLoggerInstanceTotal=smsLoggerInstanceList?.size()
            params.max = SMSLoggerInstanceTotal
            session.filterParams.max = filterPaneService.count(params, SMSLogger)
            export(response: response, extension: params.extension, format: params.format, exportList: smsLoggerInstanceList)
        }else {
            smsLoggerInstanceList=SMSLogger.createCriteria().list(params) {
                if (reseauId)
                eq('reseau.id',new Long(reseauId?:"1"))
                and {
                    between("date", fromDate-1, toDate+1)
                }
            }
            SMSLoggerInstanceTotal=smsLoggerInstanceList?.size()
            session.filterParams = params
        }
        render template:"tableau" , model:[SMSLoggerInstanceList: smsLoggerInstanceList, isFirstLoad: true,reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), SMSLoggerInstanceTotal: SMSLoggerInstanceTotal, filterParams: FilterPaneUtils.extractFilterParams(session.filterParams), params: session.filterParams]
    }

    def export = {attrs ->

        def response = attrs.response
        List fields = ["reseau", "theOperator","theUserMobilePhone","date","direction","theUser","message"]
        Map labels = ["reseau": "Réseau","theOperator": "Opérateur","theUserMobilePhone":"Mobile","date":"Date", "direction": "Direction", "theUser": "Utilisateur","message":"Message"]

        def humanCase = {
            domain, value ->
                return value?.humanify()
        }
        def dateFormat ={
            domain, value ->
                return value?.getAsDay()
        }


        Map formatters = [theUser:humanCase,date:dateFormat]

        Map parameters = [title: "JOURNAL DES SMS", "column.widths": [0.10,0.15, 0.15, 0.09, 0.07, 0.25,0.2]]

        response.contentType = grailsApplication.config.grails.mime.types[attrs.format]
        response.setHeader("Content-disposition", "attachment; filename="+"Journal-des-sms".uniquify(".${params.extension}"))
      //  response.contentType="application/octet-stream"

        exportService.export(attrs.format, response.outputStream, attrs.exportList, fields, labels,formatters,parameters)

    }

    def list() {
        renderList()
    }
    def listWithPeriode() {
        renderListWithPeriode()
    }

    def renderListPaginate() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html" && session.filterParams) {
            params.max = filterPaneService.count(params, SMSLogger)
            def smsLoggerInstanceList= filterPaneService.filter(session.filterParams, SMSLogger)
            session.filterParams.max = filterPaneService.count(params, SMSLogger)
            export(response: response, extension: params.extension, format: params.format, exportList: smsLoggerInstanceList)
        }else {
            session.filterParams = params
        }
        def reseauList=Reseau.findAllByEstReseauAndActive(true,true)
        render template:"list" , model:[SMSLoggerInstanceList: filterPaneService.filter(session.filterParams, SMSLogger),isFirstLoad:false,reseauList:reseauList,ctnerTemplate: myFormHelperService.getCheckBoxTemplate(reseauList?.size()), SMSLoggerInstanceTotal: filterPaneService.count(session.filterParams, SMSLogger), filterParams: FilterPaneUtils.extractFilterParams(session.filterParams), params: session.filterParams]
    }
    def listPaginate() {
        renderListPaginate()
    }

    def filter = {
//        if (!params.max) params.max = 10

        def SMSLoggerInstanceTotal=filterPaneService.count(params, SMSLogger)
        def SMSLoggerInstanceList= filterPaneService.filter(params, SMSLogger)
//        def filterParams=params
//        if(params?.format && params.format != "html" )
//        {
//            SMSLoggerInstanceTotal=filterPaneService.count(params, SMSLogger)
//            SMSLoggerInstanceList= filterPaneService.filter(params, SMSLogger)
//
//            export(response: response, extension: params.extension, format: params.format, exportList: SMSLoggerInstanceList)
//        }
//        else{
//            SMSLoggerInstanceList= filterPaneService.filter(params, SMSLogger)
//        }
//        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html" && session.filterParams) {
            params.max = SMSLoggerInstanceTotal
            session.filterParams.max = SMSLoggerInstanceTotal
            export(response: response, extension: params.extension, format: params.format, exportList: SMSLoggerInstanceList)
        }else {
            session.filterParams = params
        }
        render template:"list" , model:[SMSLoggerInstanceList: SMSLoggerInstanceList, SMSLoggerInstanceTotal: SMSLoggerInstanceTotal, filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(session.filterParams), params: session.filterParams]

//        renderList()

    }

    def create() {
        switch (request.method) {
            case 'GET':
                [SMSLoggerInstance: new SMSLogger(params)]
                break
            case 'POST':
                def SMSLoggerInstance = new SMSLogger(params)
                if (!SMSLoggerInstance.save(flush: true)) {
                    render template: 'create', model: [SMSLoggerInstance: SMSLoggerInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), SMSLoggerInstance.toString()])
                def result=[id:SMSLoggerInstance.id]
                render result as JSON
                break
        }
    }

    def show() {
        def SMSLoggerInstance = SMSLogger.get(params.id)
        if (!SMSLoggerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id])
            renderList()
        }

       render template:'show',  model:[SMSLoggerInstance: SMSLoggerInstance]
    }

    def edit() {
        switch (request.method) {
            case 'GET':
                def SMSLoggerInstance = SMSLogger.get(params.id)
                if (!SMSLoggerInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id])
                    renderList()
                }

                [SMSLoggerInstance: SMSLoggerInstance]
                break
            case 'POST':
                def SMSLoggerInstance = SMSLogger.get(params.id)
                if (!SMSLoggerInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id])
                    renderList()
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (SMSLoggerInstance.version > version) {
                        SMSLoggerInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'SMSLogger.label', default: 'SMSLogger')] as Object[],
                                "Another user has updated this SMSLogger while you were editing")
                        render template: 'edit', model: [SMSLoggerInstance: SMSLoggerInstance]
                    }
                }

                SMSLoggerInstance.properties = params

                if (!SMSLoggerInstance.save(flush: true)) {
                    render template: 'edit', model: [SMSLoggerInstance: SMSLoggerInstance]
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), SMSLoggerInstance.toString()])
                redirect action: 'show', id: SMSLoggerInstance.id
                break
        }
    }

    def delete() {
        def SMSLoggerInstance = SMSLogger.get(params.id)
        if (!SMSLoggerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id])
            renderList()
        }

        try {
            SMSLoggerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id])
            renderList()
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'SMSLogger.label', default: 'SMSLogger'), params.id, e.message])
            redirect action: 'show', id: params.id
        }
    }
}
