import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_categorieProduit_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/categorieProduit/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('message','g',11,['code':("create.categorieProduit")],-1)
printHtmlPart(2)
})
invokeTag('remoteLink','g',12,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],1)
printHtmlPart(3)
invokeTag('message','g',18,['code':("list.categorieProduit")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',24,['class':("alert-info")],2)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('filterButton','filterpane',26,['text':("Rechercher")],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',32,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'categorieProduit.nom.label', default: 'Nom'))],-1)
printHtmlPart(8)
invokeTag('message','g',34,['code':("categorieProduit.mesures.label"),'default':("Mesures")],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("actif"),'title':(message(code: 'categorieProduit.actif.label', default: 'Actif'))],-1)
printHtmlPart(10)
for( categorieProduitInstance in (categorieProduitInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('remoteLink','g',46,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(categorieProduitInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: categorieProduitInstance, field: "nom"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: categorieProduitInstance, field: "mesures"))
printHtmlPart(14)
invokeTag('formatBoolean','g',50,['boolean':(categorieProduitInstance.actif)],-1)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('remotePaginate','util',55,['update':("listContent"),'action':("list"),'total':(categorieProduitInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(17)
invokeTag('formats','export',57,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(18)
invokeTag('filterPane','filterpane',59,['dialog':("true"),'domain':("simagri.CategorieProduit")],-1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418673877796L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
