import simagri.MyJobConfig
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_myJobConfig_start_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/myJobConfig/_start.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('message','g',7,['code':("title.myJobConfig")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',11,['code':("edit.myJobConfig")],-1)
printHtmlPart(4)
})
invokeTag('link','g',13,['class':("create"),'action':("edit")],1)
printHtmlPart(5)
invokeTag('message','g',19,['code':("startCron.myJobConfig")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',21,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (myJobConfigInstance?.cron)) {
printHtmlPart(9)
invokeTag('message','g',25,['code':("myJobConfig.cron.label"),'default':("Cron")],-1)
printHtmlPart(10)
invokeTag('fieldValue','g',29,['bean':(myJobConfigInstance),'field':("cron")],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (myJobConfigInstance?.statut)) {
printHtmlPart(9)
invokeTag('message','g',36,['code':("myJobConfig.statut.label"),'default':("Statut")],-1)
printHtmlPart(10)
invokeTag('fieldValue','g',41,['bean':(myJobConfigInstance),'field':("statut")],-1)
printHtmlPart(11)
}
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('hiddenField','g',44,['name':("id"),'value':(myJobConfigInstance?.id)],-1)
printHtmlPart(15)
invokeTag('message','g',53,['code':("default.button.executer.label"),'default':("Create")],-1)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('message','g',62,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(18)
})
invokeTag('link','g',63,['class':("btn"),'action':("edit"),'id':(myJobConfigInstance?.id)],2)
printHtmlPart(19)
invokeTag('message','g',66,['code':("default.button.stop.label"),'default':("Arrêter")],-1)
printHtmlPart(20)
})
invokeTag('form','g',67,['class':("well small"),'action':("cron")],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435016459L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
