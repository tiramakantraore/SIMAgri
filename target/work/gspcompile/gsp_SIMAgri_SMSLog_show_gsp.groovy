import simagri.SMSLogger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_SMSLog_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/SMSLog/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('render','g',6,['template':("/partials/showHeader"),'model':([canCreate:false,isList:null])],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],3)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('display','f',8,['bean':("SMSLoggerInstance"),'property':("user")],-1)
printHtmlPart(4)
invokeTag('display','f',9,['bean':("SMSLoggerInstance"),'property':("date")],-1)
printHtmlPart(4)
invokeTag('display','f',11,['bean':("SMSLoggerInstance"),'property':("message")],-1)
printHtmlPart(4)
invokeTag('display','f',14,['bean':("SMSLoggerInstance"),'property':("isIn")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',16,['name':("id"),'value':(SMSLoggerInstance?.id)],-1)
printHtmlPart(6)
})
invokeTag('form','g',16,[:],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1425328615197L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
