package simagri

import org.grails.plugin.filterpane.FilterPaneUtils
import org.quartz.CronScheduleBuilder
import org.quartz.CronTrigger
import org.quartz.Job
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.TriggerKey
import org.quartz.impl.StdScheduler
import org.quartz.impl.StdSchedulerFactory
import org.quartz.impl.matchers.GroupMatcher
import simagriservices.PushPricesReseauService

import static org.quartz.CronScheduleBuilder.*;
import org.springframework.dao.DataIntegrityViolationException
import MyJobs.*


class MyJobConfigController {
    def exportService
    def filterPaneService
    Scheduler quartzScheduler
    def JobManagerService
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        renderList()
    }
    def renderList() {
        params.max = Math.min(params.max ? params.int('max') : 25, 100)
        if (params?.format && params.format != "html") {
            params.max = MyJobConfig.count()
            response.contentType = grailsApplication.config.grails.mime.types[params.format]
            response.setHeader("Content-disposition", "attachment; filename=" + MyJobConfig.uniquify(".${params.extension}"))

            exportService.export(params.format, response.outputStream, MyJobConfig.list(params), [:], [:])
        }
       render template:'list', model:[myJobConfigInstanceList: filterPaneService.filter(params, MyJobConfig), myJobConfigInstanceTotal: filterPaneService.count(params, MyJobConfig), filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params), params: params]

    }
    def list() {
        renderList()
    }

    def filter = {
        if (!params.max) params.max = 10
        render  template:'list',
                model: [myJobConfigInstanceList: filterPaneService.filter(params, MyJobConfig),
                        myJobConfigInstanceTotal: filterPaneService.count(params, MyJobConfig),
                        filterParams: FilterPaneUtils.extractFilterParams(params),
                        params: params]
    }

    def create() {
        switch (request.method) {
            case 'GET':
                render template:'create',model:[myJobConfigInstance: new MyJobConfig(params)]
                break
            case 'POST':
                def myJobConfigInstance = new MyJobConfig(params)
                if (!myJobConfigInstance.save(flush: true)) {
                    render template: 'create', model: [myJobConfigInstance: myJobConfigInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'myJobConfig.label', default: 'MyJobConfig'), myJobConfigInstance.toString()])
                render template:'show',model:[id: myJobConfigInstance.id]
                break
        }
    }
    def reschedule={
        def jobKeys= quartzScheduler.getJobKeys(GroupMatcher.groupEquals("myJobs"))
        jobKeys.each{jobKey ->

        def triggerKeys=quartzScheduler.getTriggersOfJob(jobKey)
        triggerKeys.each{ trigger->
            if (quartzScheduler.checkExists(trigger.key)){

                CronTrigger trigg = TriggerBuilder.newTrigger()
                        .withIdentity("trigg".uniquify(),trigger.key.group)
                        .withSchedule(CronScheduleBuilder.cronSchedule(MyJobConfig.first().cron))
                        .build();

                quartzScheduler.rescheduleJob(trigger.key,trigg)
                sleep(2000)

            }
        }
        }
    }
    def show() {
        def myJobConfigInstance = MyJobConfig.first()
        if (!myJobConfigInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'myJobConfig.label', default: 'MyJobConfig'), params.id])
            renderList()
        }

       render template:'show', model:[myJobConfigInstance: myJobConfigInstance]
    }
    // Inject the quartzScheduler bean

    def edit() {
        switch (request.method) {
            case 'GET':
                def myJobConfigInstance = MyJobConfig.first()
                if (!myJobConfigInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'myJobConfig.label', default: 'MyJobConfig'), params.id])
                    renderList()

                }
                def cronExplication="0 0 12 * * ? ==> Fire at 12pm (noon) every day"
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 ? * * ==> Fire at 10:15am every day "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 * * ? ==> Fire at 10:15am every day "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 ? * MON-FRI ==> Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 ? * MON-FRI ==> Fire at 10:15am every Monday, Tuesday, Wednesday, Thursday and Friday "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 L * ? ==> Fire at 10:15am on the last day of every month "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 ? * 6L 2002-2005 ==> Fire at 10:15am on every last friday of every month during the years 2002, 2003, 2004 and 2005 "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 15 10 ? * 6#3 ==> Fire at 10:15am on the third Friday of every month "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 0 12 1/5 * ? ==> Fire at 12pm (noon) every 5 days every month, starting on the first day of the month. "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+=" 0 11 11 11 11 ? ==> Fire every November 11th at 11:11am. "
                cronExplication+=System.getProperty('line.separator')
                cronExplication+="0 0/5 14,18 * * ? ==> Fire every 5 minutes starting at 2pm and ending at 2:55pm, AND fire every 5 minutes starting at 6pm and ending at 6:55pm, every day "
                def cronFormat="cronExpression: s m h D M W Y"
                flash.message =cronExplication
                render template:'edit',   model: [myJobConfigInstance: myJobConfigInstance,cronExplication:cronExplication,cronFormat:cronFormat]
                break
            case 'POST':
                def myJobConfigInstance = MyJobConfig.get(params.id)
                if (!myJobConfigInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'myJobConfigNotCreated.label', default: 'MyJobConfig'), params.id])
                    renderList()
                }




                if (params.version) {
                    def version = params.version.toLong()
                    if (myJobConfigInstance.version > version) {
                        myJobConfigInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'myJobConfig.label', default: 'MyJobConfig')] as Object[],
                                "Another user has updated this MyJobConfig while you were editing")
                        render template: 'edit', model: [myJobConfigInstance: myJobConfigInstance]
                    }
                }

                myJobConfigInstance.properties = params

                if (!myJobConfigInstance.save(flush: true)) {
                    render template: 'edit', model: [myJobConfigInstance: myJobConfigInstance]
                }
                reschedule()
                flash.message = message(code: 'default.updated.message', args: [message(code: 'myJobConfig.label', default: 'MyJobConfig'), myJobConfigInstance.toString()])
                render template:'show', model:[id: myJobConfigInstance.id]
                break
        }
    }

    def start() {
        switch (request.method) {
            case 'GET':
                def myJobConfigInstance = MyJobConfig.first()


                render template:'start',model:[myJobConfigInstance: myJobConfigInstance]
                break
            case 'POST':
                def myJobConfigInstance = MyJobConfig.first()
                 myJobConfigInstance.statut="Démarré"
                 myJobConfigInstance.save(flush:true)

                quartzScheduler.resumeAll()

                reschedule()

                if (quartzScheduler.isStarted()) {
                flash.message = message(code: 'job.isStarted.message',default:"Le job a démarré")
                render template:'show',model:[]
                break
        }
    }
    }



    def cron() {
        switch (request.method) {
            case 'GET':
                def myJobConfigInstance = MyJobConfig.first()
                if (!myJobConfigInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'myJobConfigNotCreated.label', default: 'MyJobConfig'), params.id])
                    renderList()
                }

                render template:'cron',model: [myJobConfigInstance: myJobConfigInstance]
                break
            case 'POST':
                def myJobConfigInstance = MyJobConfig.first()
                quartzScheduler.resumeAll()
                   myJobConfigInstance.statut="Démarré"

                reScheduleJob ()
                if (quartzScheduler.isStarted()) {
                    flash.message = message(code: 'job.cronChanged.message',default:"La programmation a changé")
                    render template: 'show', model:[id: params.id]
                    break
                }
        }
    }
    def stop() {
        switch (request.method) {
            case 'GET':
                def myJobConfigInstance = MyJobConfig.first()
                if (!myJobConfigInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'myJobConfigNotCreated.label', default: 'MyJobConfig'), params.id])
                    renderList()
                }

                render template:'stop',model:[myJobConfigInstance: myJobConfigInstance]
                break
            case 'POST':
                def myJobConfigInstance = MyJobConfig.first()
                if (quartzScheduler.isStarted()) {
                    quartzScheduler.pauseAll()

                    myJobConfigInstance.statut="Eteind"
                    myJobConfigInstance.save(flush:true)
                }

                if (quartzScheduler.isShutdown())
                flash.message = message(code: 'job.isShutdown.message',default:"Le job a s'est éteint")
                render template: 'show', model:[id: params.id]
                break
        }
    }



}
