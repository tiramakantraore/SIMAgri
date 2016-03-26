import simagri.EventRecurType
import   simagri.Event
import org.joda.time.Instant
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_event_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/event/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'event.label', default: 'Event'))],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('textField','g',14,['name':("title"),'value':(eventInstance?.title),'class':("form-control")],-1)
printHtmlPart(4)
})
invokeTag('field','f',15,['bean':("eventInstance"),'property':("title")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(formatDate(date: occurrenceStart ? new Instant(occurrenceStart).toDate() : eventInstance?.startTime, format: 'dd/MM/yyyy hh:mm a'))
printHtmlPart(7)
})
invokeTag('field','f',29,['bean':("eventInstance"),'property':("startTime")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
expressionOut.print(formatDate(date: occurrenceEnd ? new Instant(occurrenceEnd).toDate() : eventInstance?.endTime, format: 'dd/MM/yyyy hh:mm a'))
printHtmlPart(7)
})
invokeTag('field','f',41,['bean':("eventInstance"),'property':("endTime")],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('textField','g',47,['name':("location"),'value':(eventInstance?.location),'autocomplete':("off"),'class':("form-control")],-1)
printHtmlPart(11)
})
invokeTag('field','f',49,['bean':("eventInstance"),'property':("location")],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('textArea','g',55,['name':("description"),'rows':("5"),'value':(eventInstance?.description),'autocomplete':("off"),'class':("form-control")],-1)
printHtmlPart(11)
})
invokeTag('field','f',57,['bean':("eventInstance"),'property':("description")],1)
printHtmlPart(13)
invokeTag('select','g',65,['name':("recurType"),'from':(EventRecurType.values()),'optionValue':("name"),'value':(eventInstance?.recurType)],-1)
printHtmlPart(14)
invokeTag('select','g',69,['name':("recurInterval"),'from':(1..30),'value':(eventInstance?.recurInterval)],-1)
printHtmlPart(15)
expressionOut.print(eventInstance.recurType != EventRecurType.WEEKLY ? 'style="display:none"' : '')
printHtmlPart(16)
invokeTag('daysOfWeek','calendar',76,['name':("recurDaysOfWeek"),'selectedDays':(eventInstance?.recurDaysOfWeek)],-1)
printHtmlPart(17)
expressionOut.print((!eventInstance.recurCount && !eventInstance.recurUntil) ? 'checked="checked"' : '')
printHtmlPart(18)
expressionOut.print((eventInstance.recurCount) ? 'checked="checked"' : '')
printHtmlPart(19)
invokeTag('textField','g',86,['name':("recurCount"),'size':("3"),'value':(eventInstance?.recurCount)],-1)
printHtmlPart(20)
expressionOut.print((!eventInstance.recurCount && eventInstance.recurUntil) ? 'checked="checked"' : '')
printHtmlPart(21)
invokeTag('textField','g',89,['name':("recurUntil"),'size':("8"),'value':(formatDate(date: (eventInstance?.recurCount ? null : eventInstance?.recurUntil), format: 'MM/dd/yyyy hh:mm a'))],-1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423831217290L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
