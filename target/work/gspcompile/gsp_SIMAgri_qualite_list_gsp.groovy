import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_qualite_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/qualite/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',9,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',11,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',13,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',20,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'qualite.code.label', default: 'Code'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',26,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'qualite.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',32,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'qualite.description.label', default: 'Description'))],-1)
printHtmlPart(6)
for( qualiteInstance in (qualiteInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',42,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(qualiteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: qualiteInstance, field: "code"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: qualiteInstance, field: "nom"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: qualiteInstance, field: "description"))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',52,['update':("listContent"),'action':("list"),'total':(qualiteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('formats','export',53,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',55,['dialog':("true"),'domain':("simagri.Qualite")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278234271L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
