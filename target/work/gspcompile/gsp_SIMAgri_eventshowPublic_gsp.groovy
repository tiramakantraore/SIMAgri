import simagri.Event
import org.joda.time.Instant
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_eventshowPublic_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/event/showPublic.gsp" }
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
invokeTag('message','g',27,['code':("show.event")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',30,['class':("alert-info")],3)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('formatDate','g',36,['date':(new Instant(occurrenceStart).toDate()),'format':("E, MMM d, hh:mma")],-1)
printHtmlPart(8)
invokeTag('formatDate','g',39,['date':(new Instant(occurrenceEnd).toDate()),'format':("E, MMM d, hh:mma")],-1)
printHtmlPart(9)
if(true && (eventInstance?.location)) {
printHtmlPart(10)
invokeTag('message','g',49,['code':("event.location.label"),'default':("Location")],-1)
printHtmlPart(11)
invokeTag('fieldValue','g',56,['bean':(eventInstance),'field':("location")],-1)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (eventInstance?.description)) {
printHtmlPart(14)
invokeTag('message','g',64,['code':("event.description.label"),'default':("Description")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',68,['bean':(eventInstance),'field':("description")],-1)
printHtmlPart(12)
}
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('hiddenField','g',69,['name':("id"),'value':(eventInstance?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',71,['name':("occurrenceStart"),'value':(occurrenceStart)],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',74,['name':("occurrenceEnd"),'value':(occurrenceEnd)],-1)
printHtmlPart(17)
})
invokeTag('form','g',75,[:],2)
printHtmlPart(18)
if(true && (eventInstance.isRecurring)) {
printHtmlPart(6)
invokeTag('render','g',76,['template':("deletePopup"),'model':("model")],-1)
printHtmlPart(2)
}
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',77,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435016481L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
