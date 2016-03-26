import simagri.Performance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_performance_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/performance/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',8,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',12,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'performance.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',24,['update':("listContent"),'action':("list"),'property':("nbOffre"),'title':(message(code: 'performance.nbOffre.label', default: 'Nb Offre'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("nbPrix"),'title':(message(code: 'performance.nbPrix.label', default: 'Nb Prix'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',35,['update':("listContent"),'action':("list"),'property':("nbAlerte"),'title':(message(code: 'performance.nbAlerte.label', default: 'Nb Alerte'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',37,['update':("listContent"),'action':("list"),'property':("nbContact"),'title':(message(code: 'performance.nbContact.label', default: 'Nb Contact'))],-1)
printHtmlPart(6)
for( performanceInstance in (performanceInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',43,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(performanceInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: performanceInstance, field: "nom"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: performanceInstance, field: "nbOffre"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: performanceInstance, field: "nbPrix"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: performanceInstance, field: "nbAlerte"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: performanceInstance, field: "nbContact"))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',56,['update':("listContent"),'action':("list"),'total':(performanceInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('formats','export',58,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',61,['dialog':("true"),'domain':("simagri.Performance")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278234355L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
