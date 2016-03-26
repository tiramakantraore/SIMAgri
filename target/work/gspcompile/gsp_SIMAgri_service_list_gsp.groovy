import simagri.Service
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_service_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/service/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',12,['code':("create.service")],-1)
printHtmlPart(3)
})
invokeTag('remoteLink','g',13,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],1)
printHtmlPart(4)
invokeTag('message','g',20,['code':("list.service")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',29,['class':("alert-info")],2)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('filterButton','filterpane',31,['text':("Rechercher")],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',37,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'service.nom.label', default: 'Nom'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',39,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'service.description.label', default: 'Description'))],-1)
printHtmlPart(10)
for( serviceInstance in (serviceInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',50,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(serviceInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: serviceInstance, field: "nom"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: serviceInstance, field: "description"))
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('remotePaginate','util',63,['update':("listContent"),'action':("list"),'total':(serviceInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(17)
invokeTag('filterPane','filterpane',70,['dialog':("true"),'domain':("simagri.Service")],-1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993576L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
