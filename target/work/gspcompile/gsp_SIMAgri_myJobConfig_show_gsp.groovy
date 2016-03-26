import simagri.MyJobConfig
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_myJobConfig_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/myJobConfig/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',8,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
if(true && (myJobConfigInstance?.cron)) {
printHtmlPart(4)
invokeTag('message','g',13,['code':("myJobConfig.cron.label"),'default':("Cron")],-1)
printHtmlPart(5)
invokeTag('fieldValue','g',15,['bean':(myJobConfigInstance),'field':("cron")],-1)
printHtmlPart(6)
}
printHtmlPart(7)
if(true && (myJobConfigInstance?.statut)) {
printHtmlPart(4)
invokeTag('message','g',22,['code':("myJobConfig.statut.label"),'default':("Statut")],-1)
printHtmlPart(5)
invokeTag('fieldValue','g',24,['bean':(myJobConfigInstance),'field':("statut")],-1)
printHtmlPart(6)
}
printHtmlPart(8)
expressionOut.print(new Date())
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
invokeTag('hiddenField','g',32,['name':("id"),'value':(myJobConfigInstance?.id)],-1)
printHtmlPart(11)
invokeTag('render','g',34,['template':("/partials/btnShow")],-1)
printHtmlPart(2)
})
invokeTag('form','g',35,['class':("well small"),'action':("cron")],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422830034283L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
