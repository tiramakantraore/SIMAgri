import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLog_tableau_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/_tableau.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('remoteSortableColumn','util',6,['update':("listContent"),'action':("list"),'property':("theOperator"),'title':(message(code: 'SMSLogger.operateur.label', default: 'Opérateur'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',8,['update':("listContent"),'action':("list"),'property':("theUserMobilePhone"),'title':(message(code: 'SMSLogger.theUserMobilePhone.label', default: 'Phone'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',10,['update':("listContent"),'action':("list"),'property':("date"),'title':(message(code: 'SMSLogger.date.label', default: 'Date'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',12,['update':("listContent"),'action':("list"),'property':("direction"),'title':(message(code: 'SMSLogger.directionShort.label', default: 'Type SMS'))],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',14,['update':("listContent"),'action':("list"),'property':("theUser"),'title':(message(code: 'SMSLogger.theUser.label', default: 'User'))],-1)
printHtmlPart(2)
for( SMSLoggerInstance in (SMSLoggerInstanceList) ) {
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('remoteLink','g',26,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("show"),'update':("listContent"),'method':("GET"),'id':(SMSLoggerInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(5)
expressionOut.print(SMSLoggerInstance?.theOperator)
printHtmlPart(6)
expressionOut.print(SMSLoggerInstance?.theUserMobilePhone)
printHtmlPart(7)
invokeTag('formatDate','g',38,['date':(SMSLoggerInstance.date),'type':("datetime"),'style':("MEDIUM"),'timeStyle':("MEDIUM")],-1)
printHtmlPart(8)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "direction"))
printHtmlPart(9)
expressionOut.print(fieldValue(bean: SMSLoggerInstance, field: "theUser"))
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('remotePaginate','util',52,['update':("listContent"),'action':("listPaginate"),'total':(SMSLoggerInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1426121591532L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
