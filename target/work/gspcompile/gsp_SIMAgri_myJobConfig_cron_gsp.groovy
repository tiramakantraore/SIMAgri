import simagri.MyJobConfig
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_myJobConfig_cron_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/myJobConfig/_cron.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("title.myJobConfig")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',10,['code':("changeCron.myJobConfig")],-1)
printHtmlPart(3)
})
invokeTag('link','g',12,['class':("create"),'action':("cron")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',18,['code':("startCron.myJobConfig")],-1)
printHtmlPart(3)
})
invokeTag('link','g',19,['class':("create"),'action':("start")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',24,['code':("stopCron.myJobConfig")],-1)
printHtmlPart(3)
})
invokeTag('link','g',25,['class':("create"),'action':("stop")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',30,['code':("edit.myJobConfig")],-1)
printHtmlPart(3)
})
invokeTag('link','g',31,['class':("create"),'action':("edit")],1)
printHtmlPart(5)
invokeTag('message','g',35,['code':("changeCron.myJobConfig")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',37,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (myJobConfigInstance?.cron)) {
printHtmlPart(9)
invokeTag('message','g',41,['code':("myJobConfig.cron.label"),'default':("Cron")],-1)
printHtmlPart(10)
invokeTag('fieldValue','g',45,['bean':(myJobConfigInstance),'field':("cron")],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (myJobConfigInstance?.statut)) {
printHtmlPart(9)
invokeTag('message','g',52,['code':("myJobConfig.statut.label"),'default':("Statut")],-1)
printHtmlPart(10)
invokeTag('fieldValue','g',57,['bean':(myJobConfigInstance),'field':("statut")],-1)
printHtmlPart(11)
}
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('hiddenField','g',60,['name':("id"),'value':(myJobConfigInstance?.id)],-1)
printHtmlPart(15)
invokeTag('message','g',67,['code':("default.button.executer.label"),'default':("Create")],-1)
printHtmlPart(16)
invokeTag('message','g',78,['code':("default.button.start.label"),'default':("Démarrer")],-1)
printHtmlPart(17)
invokeTag('message','g',82,['code':("default.button.stop.label"),'default':("Arrêter")],-1)
printHtmlPart(18)
})
invokeTag('form','g',83,['class':("well small"),'action':("cron")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410586L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
