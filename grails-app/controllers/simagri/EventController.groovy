package simagri

import org.joda.time.DateTime
import org.joda.time.Instant

import grails.converters.JSON
import java.text.SimpleDateFormat

class EventController  {
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']


    def eventService

    def index = {

    }
    def agenda() {

    }
    def saveByService(){

        render eventService.saveForm(params)
    }
    def list = {

        def (startRange, endRange) = [Date.parse('yyyy-MM-dd', params.start?.toString()).time, Date.parse('yyyy-MM-dd', params.end?.toString()).time].collect {

            new Instant(it).toDate() }


        def events = Event.withCriteria {
            or {
                and {
                    eq("isRecurring", false)
                    between("startTime", startRange, endRange)
                }
                and {
                    eq("isRecurring", true)
                    or {
                        isNull("recurUntil")
                        ge("recurUntil", startRange)
                    }
                }
            }
        }

        // iterate through to see if we need to add additional Event instances because of recurring
        // events
        def eventList = []

        def displayDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        events.each {event ->

            def dates = eventService.findOccurrencesInRange(event, startRange, endRange)
            dates.each { date ->
                DateTime startTime = new DateTime(date)
                DateTime endTime = startTime.plusMinutes(event.durationMinutes)

                /*
                    start/end and occurrenceStart/occurrenceEnd are separate because fullCalendar will use the client's local timezone (which may be different than the server's timezone)
                    start/end are used to render the events on the calendar and the occurrenceStart/occurrenceEnd values are passed along to the show popup
                */

                eventList << [
                        id: event.id,
                        title: event.title,
                        allDay: false,
                        start: displayDateFormatter.format(startTime.toDate()),
                        end: displayDateFormatter.format(endTime.toDate()),
                        occurrenceStart: startTime.toInstant().millis,
                        occurrenceEnd: endTime.toInstant().millis
                ]
            }
        }
        render eventList as JSON

    }


    def create() {
        switch (request.method) {
            case 'GET':
                [eventInstance :new Event(params)]
                break
            case 'POST':
                def eventInstance = new Event(params)
                if (!eventInstance.save(flush: true)) {
                    render template: 'create', model: [eventInstance: eventInstance]
                }

                flash.message = message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Evenement'), eventInstance.toString()])
                def result=[id:eventInstance.id]
                render result as JSON
                break
        }
    }

    def show = {
        def (occurrenceStart, occurrenceEnd) = [params.long('occurrenceStart'), params.long('occurrenceEnd')]
        def eventInstance = Event.get(params.id)

        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else {
            def model = [eventInstance: eventInstance, occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd]

            if (request.xhr) {
                render(template: "showPopup", model: model)
            }
            else {
                model
            }
        }

    }
    def showPublic = {
        def (occurrenceStart, occurrenceEnd) = [params.long('occurrenceStart'), params.long('occurrenceEnd')]
        def eventInstance = Event.get(params.id)

        if (!eventInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else {
            def model = [eventInstance: eventInstance, occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd]

            if (request.xhr) {
                render(template: "showPopup", model: model)
            }
            else {
                model
            }
        }

    }
    def save = {
        def eventInstance = new Event(params)
        if (eventInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "show", id: eventInstance.id)
        }
        else {
            render(view: "create", model: [eventInstance: eventInstance])
        }

    }



    def edit() {
        switch (request.method) {
            case 'GET':
                def eventInstance = Event.get(params.id)
                if (!eventInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
                    redirect(action: "index")
                }

                [eventInstance: eventInstance]
                break
            case 'POST':
                def eventInstance = Event.get(params.id)
                if (!eventInstance) {
                    flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])
                    redirect(action: "index")
                }

                if (params.version) {
                    def version = params.version.toLong()
                    if (eventInstance.version > version) {
                        eventInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
                                [message(code: 'event.label', default: 'Evenement')] as Object[],
                                "Another user has updated this Evenement while you were editing")
                        redirect(action: "index")
                    }
                }

                eventInstance.properties = params
//                def (occurrenceStart, occurrenceEnd) = [params.long('occurrenceStart'), params.long('occurrenceEnd')]
//                if (!eventInstance.save(flush: true)) {
//                    render(view: "edit", model: [eventInstance: eventInstance])
//
//                    return
//                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Evenement'), eventInstance.id])

                redirect(action: "index")
//                break
        }
    }

    def update = {
        def eventInstance = Event.get(params.id)
        String editType = params.editType

        def result = eventService.updateEvent(eventInstance, editType, params)
        if (!result.error) {
            flash.message = "${message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])}"
            redirect(action: "index")
        }
        if (result.error == 'not.found') {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else if (result.error == 'has.errors') {
            render(view: "edit", model: [eventInstance: eventInstance])
        }

    }


    def delete = {
        def eventInstance = Event.get(params.id)
        String deleteType = params.deleteType
        Date occurrenceStart = new Instant(params.long('occurrenceStart')).toDate()

        def result = eventService.deleteEvent(eventInstance, occurrenceStart, deleteType)

        if (!result.error) {
            redirect(action: "index")
        }
        if (result.error == 'not.found') {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), params.id])}"
            redirect(action: "index")
        }
        else if (result.error == 'has.errors') {
            redirect(action: "index")
        }
    }


}