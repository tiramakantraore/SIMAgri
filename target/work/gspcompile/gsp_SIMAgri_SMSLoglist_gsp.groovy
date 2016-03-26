import simagri.SMSLogger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLoglist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/list.gsp" }
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
invokeTag('message','g',8,['code':("list.SMSLogger")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',19,['code':("list.SMSLogger")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',23,['class':("alert-info")],3)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('filterButton','filterpane',25,['text':("Rechercher")],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',31,['update':("listContent"),'action':("list"),'property':("operateur"),'title':(message(code: 'SMSLogger.operateur.label', default: 'Opérteur'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',33,['update':("listContent"),'action':("list"),'property':("message"),'title':(message(code: 'SMSLogger.message.label', default: 'Message'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',35,['update':("listContent"),'action':("list"),'property':("date"),'title':(message(code: 'SMSLogger.date.label', default: 'Date'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',37,['update':("listContent"),'action':("list"),'property':("direction"),'title':(message(code: 'SMSLogger.direction.label', default: 'Type SMS'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',39,['update':("listContent"),'action':("list"),'property':("User"),'title':(message(code: 'SMSLogger.user.label', default: 'User'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',41,['update':("listContent"),'action':("list"),'property':("fromPhone"),'title':(message(code: 'SMSLogger.fromPhone.label', default: 'N° Source'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',43,['update':("listContent"),'action':("list"),'property':("toPhone"),'title':(message(code: 'SMSLogger.toPhone.label', default: 'N° Destination'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("dureeEnS"),'title':(message(code: 'SMSLogger.dureeEnS.label', default: 'Durée (ms)'))],-1)
printHtmlPart(13)
for( SMSLoggerInstance in (SMSLoggerInstanceList) ) {
printHtmlPart(14)
createClosureForHtmlPart(15, 3)
invokeTag('link','g',54,['action':("show"),'id':(SMSLoggerInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(16)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "operateur"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "message"))
printHtmlPart(17)
invokeTag('formatDate','g',61,['date':(SMSLoggerInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "direction"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "user"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "fromPhone"))
printHtmlPart(20)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "toPhone"))
printHtmlPart(21)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "dureeEnS"))
printHtmlPart(22)
}
printHtmlPart(23)
invokeTag('remotePaginate','util',76,['update':("listContent"),'action':("list"),'total':(SMSLoggerInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('formats','export',78,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(25)
invokeTag('filterPane','filterpane',80,['dialog':("true"),'domain':("simagri.SMSLogger")],-1)
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',82,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993759L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
