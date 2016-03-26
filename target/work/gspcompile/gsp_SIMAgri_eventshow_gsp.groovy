import simagri.Event
import org.joda.time.Instant
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_eventshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/event/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',10,['var':("entityName"),'value':(message(code: 'commune.label', default: 'Commune'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',11,['code':("show.event")],-1)
})
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(2)
invokeTag('require','r',12,['module':("calendar")],-1)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',18,['action':("index"),'class':("calendar")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',19,['action':("create"),'class':("create")],2)
printHtmlPart(8)
invokeTag('message','g',33,['code':("show.event")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',36,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('formatDate','g',46,['date':(new Instant(occurrenceStart).toDate()),'format':("E, MMM d, hh:mma")],-1)
printHtmlPart(13)
invokeTag('formatDate','g',49,['date':(new Instant(occurrenceEnd).toDate()),'format':("E, MMM d, hh:mma")],-1)
printHtmlPart(14)
if(true && (eventInstance?.location)) {
printHtmlPart(15)
invokeTag('message','g',57,['code':("event.location.label"),'default':("Location")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',63,['bean':(eventInstance),'field':("location")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (eventInstance?.description)) {
printHtmlPart(19)
invokeTag('message','g',68,['code':("event.description.label"),'default':("Description")],-1)
printHtmlPart(20)
invokeTag('fieldValue','g',74,['bean':(eventInstance),'field':("description")],-1)
printHtmlPart(17)
}
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('hiddenField','g',76,['name':("id"),'value':(eventInstance?.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',77,['name':("occurrenceStart"),'value':(occurrenceStart)],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',79,['name':("occurrenceEnd"),'value':(occurrenceEnd)],-1)
printHtmlPart(22)
createTagBody(3, {->
printHtmlPart(23)
invokeTag('message','g',83,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(24)
})
invokeTag('link','g',87,['class':("btn"),'action':("edit"),'id':(eventInstance?.id)],3)
printHtmlPart(25)
invokeTag('message','g',93,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(26)
})
invokeTag('form','g',95,[:],2)
printHtmlPart(27)
if(true && (eventInstance.isRecurring)) {
printHtmlPart(11)
invokeTag('render','g',97,['template':("deletePopup"),'model':("model")],-1)
printHtmlPart(2)
}
printHtmlPart(28)
})
invokeTag('captureBody','sitemesh',97,[:],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423409980L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
