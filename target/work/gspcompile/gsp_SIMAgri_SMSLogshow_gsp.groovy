import simagri.SMSLogger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLogshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'SMSLogger.label', default: 'SMSLogger'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.SMSLogger")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.SMSLogger")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.SMSLogger")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
invokeTag('message','g',31,['code':("show.SMSLogger")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',35,['class':("alert-info")],3)
printHtmlPart(10)
}
printHtmlPart(11)
if(true && (SMSLoggerInstance?.message)) {
printHtmlPart(12)
invokeTag('message','g',42,['code':("SMSLogger.message.label"),'default':("Message")],-1)
printHtmlPart(13)
invokeTag('fieldValue','g',46,['bean':(SMSLoggerInstance),'field':("message")],-1)
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (SMSLoggerInstance?.date)) {
printHtmlPart(12)
invokeTag('message','g',55,['code':("SMSLogger.date.label"),'default':("Date")],-1)
printHtmlPart(13)
invokeTag('formatDate','g',59,['date':(SMSLoggerInstance?.date)],-1)
printHtmlPart(16)
}
printHtmlPart(15)
if(true && (SMSLoggerInstance?.isIn)) {
printHtmlPart(12)
invokeTag('message','g',66,['code':("SMSLogger.isIn.label"),'default':("Is In")],-1)
printHtmlPart(13)
invokeTag('formatBoolean','g',70,['boolean':(SMSLoggerInstance?.isIn)],-1)
printHtmlPart(16)
}
printHtmlPart(15)
if(true && (SMSLoggerInstance?.user)) {
printHtmlPart(12)
invokeTag('message','g',77,['code':("SMSLogger.user.label"),'default':("User")],-1)
printHtmlPart(13)
createTagBody(3, {->
expressionOut.print(SMSLoggerInstance?.user?.encodeAsHTML())
})
invokeTag('link','g',81,['controller':("utilisateur"),'action':("show"),'id':(SMSLoggerInstance?.user?.id)],3)
printHtmlPart(17)
}
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
invokeTag('hiddenField','g',89,['name':("id"),'value':(SMSLoggerInstance?.id)],-1)
printHtmlPart(20)
})
invokeTag('form','g',94,[:],2)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',99,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069511125L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
