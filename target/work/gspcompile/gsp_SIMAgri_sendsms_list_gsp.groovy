import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sendsms_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sendsms/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader")],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('filterButton','filterpane',8,['text':("Rechercher")],-1)
printHtmlPart(3)
invokeTag('remoteSortableColumn','util',14,['update':("listContent"),'action':("list"),'property':("yourTextMessage"),'title':(message(code: 'smsToReseaux.yourTextMessage.label', default: 'Your Text Message'))],-1)
printHtmlPart(4)
for( smsToReseauxInstance in (smsToReseauxInstanceList) ) {
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('remoteLink','g',25,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(smsToReseauxInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(7)
expressionOut.print(fieldValue(bean: smsToReseauxInstance, field: "yourTextMessage"))
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('remotePaginate','util',36,['update':("listContent"),'action':("list"),'total':(smsToReseauxInstanceTotal)],-1)
printHtmlPart(10)
invokeTag('filterPane','filterpane',43,['dialog':("true"),'domain':("simagri.SmsToReseaux")],-1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418407602819L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
