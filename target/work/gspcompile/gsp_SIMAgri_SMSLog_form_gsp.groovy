import simagri.SMSLogger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLog_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: SMSLoggerInstance, field: 'message', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("SMSLogger.message.label"),'default':("Message")],-1)
printHtmlPart(2)
invokeTag('textField','g',10,['name':("message"),'maxlength':("200"),'required':(""),'value':(SMSLoggerInstance?.message)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: SMSLoggerInstance, field: 'date', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("SMSLogger.date.label"),'default':("Date")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',18,['name':("date"),'precision':("day"),'value':(SMSLoggerInstance?.date)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: SMSLoggerInstance, field: 'isIn', 'error'))
printHtmlPart(5)
invokeTag('message','g',23,['code':("SMSLogger.isIn.label"),'default':("Is In")],-1)
printHtmlPart(6)
invokeTag('checkBox','g',26,['name':("isIn"),'value':(SMSLoggerInstance?.isIn)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: SMSLoggerInstance, field: 'user', 'error'))
printHtmlPart(7)
invokeTag('message','g',31,['code':("SMSLogger.user.label"),'default':("User")],-1)
printHtmlPart(2)
invokeTag('select','g',34,['id':("user"),'name':("user.id"),'from':(simagri.Utilisateur.list()),'optionKey':("id"),'required':(""),'value':(SMSLoggerInstance?.user?.id),'class':("many-to-one")],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1371508676354L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
