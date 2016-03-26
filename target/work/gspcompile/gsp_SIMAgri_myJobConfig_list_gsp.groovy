import simagri.MyJobConfig
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_myJobConfig_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/myJobConfig/_list.gsp" }
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
invokeTag('filterButton','filterpane',10,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',16,['update':("listContent"),'action':("list"),'property':("operationId"),'title':(message(code: 'myJobConfig.operationId.label', default: 'Operation Id'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',18,['update':("listContent"),'action':("list"),'property':("cron"),'title':(message(code: 'myJobConfig.cron.label', default: 'Cron'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',20,['update':("listContent"),'action':("list"),'property':("groupJob"),'title':(message(code: 'myJobConfig.groupJob.label', default: 'Group Job'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',22,['update':("listContent"),'action':("list"),'property':("jobClass"),'title':(message(code: 'myJobConfig.jobClass.label', default: 'Job Class'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',24,['update':("listContent"),'action':("list"),'property':("statut"),'title':(message(code: 'myJobConfig.statut.label', default: 'Statut'))],-1)
printHtmlPart(6)
for( myJobConfigInstance in (myJobConfigInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',35,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("myJobConfig"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(myJobConfigInstance.id),'params':([update:'listContent']),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: myJobConfigInstance, field: "operationId"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: myJobConfigInstance, field: "cron"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: myJobConfigInstance, field: "groupJob"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: myJobConfigInstance, field: "jobClass"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: myJobConfigInstance, field: "statut"))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',55,['update':("listContent"),'action':("list"),'total':(myJobConfigInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('filterPane','filterpane',57,['dialog':("true"),'domain':("simagri.MyJobConfig")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422831675077L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
