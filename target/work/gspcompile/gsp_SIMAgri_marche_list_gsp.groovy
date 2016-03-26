import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_marche_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/marche/_list.gsp" }
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
invokeTag('remoteSortableColumn','util',18,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'marche.code.label', default: 'Code'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',23,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'marche.nom.label', default: 'Nom'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',28,['update':("listContent"),'action':("list"),'property':("region"),'title':(message(code: 'marche.region.label', default: 'Region'))],-1)
printHtmlPart(7)
invokeTag('message','g',32,['code':("marche.location.label"),'default':("Location")],-1)
printHtmlPart(8)
invokeTag('message','g',34,['code':("marche.produits.label"),'default':("Produits")],-1)
printHtmlPart(9)
for( marcheInstance in (marcheInstanceList) ) {
printHtmlPart(10)
createClosureForHtmlPart(11, 2)
invokeTag('remoteLink','g',41,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(marcheInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(12)
expressionOut.print(fieldValue(bean: marcheInstance, field: "code"))
printHtmlPart(13)
expressionOut.print(fieldValue(bean: marcheInstance, field: "nom"))
printHtmlPart(13)
expressionOut.print(fieldValue(bean: marcheInstance, field: "region"))
printHtmlPart(13)
expressionOut.print(fieldValue(bean: marcheInstance, field: "location"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: marcheInstance, field: "produits"))
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('remotePaginate','util',55,['update':("listContent"),'action':("list"),'total':(marcheInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(17)
invokeTag('formats','export',57,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(18)
invokeTag('filterPane','filterpane',61,['dialog':("true"),'associatedProperties':("region.nom"),'domain':("simagri.Marche")],-1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423505600743L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
