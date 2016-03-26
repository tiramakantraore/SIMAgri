import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesure_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesure/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',7,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',11,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',18,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'mesure.code.label', default: 'Code'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',25,['update':("listContent"),'action':("list"),'property':("name"),'title':(message(code: 'mesure.name.label', default: 'Name'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("isLocal"),'title':(message(code: 'mesure.isLocal.label', default: 'Is Local'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',35,['update':("listContent"),'action':("list"),'property':("isConvertible"),'title':(message(code: 'mesure.isConvertible.label', default: 'Is Local'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',37,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'mesure.description.label', default: 'Description'))],-1)
printHtmlPart(7)
for( mesureInstance in (mesureInstanceList) ) {
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('remoteLink','g',43,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(mesureInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(10)
expressionOut.print(fieldValue(bean: mesureInstance, field: "code"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: mesureInstance, field: "name"))
printHtmlPart(11)
invokeTag('formatBoolean','g',50,['boolean':(mesureInstance.isLocal)],-1)
printHtmlPart(12)
invokeTag('formatBoolean','g',51,['boolean':(mesureInstance.isConvertible)],-1)
printHtmlPart(11)
expressionOut.print(fieldValue(bean: mesureInstance, field: "description"))
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('remotePaginate','util',56,['update':("listContent"),'action':("list"),'total':(mesureInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(15)
invokeTag('formats','export',58,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(16)
invokeTag('filterPane','filterpane',60,['dialog':("true"),'domain':("simagri.Mesure")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423824758174L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
