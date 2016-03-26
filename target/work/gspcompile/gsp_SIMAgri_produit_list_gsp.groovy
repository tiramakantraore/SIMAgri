import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_produit_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/produit/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',12,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',15,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',15,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'produit.code.label', default: 'Code'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',24,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'produit.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("nomScientifique"),'title':(message(code: 'produit.nomScientifique.label', default: 'Nom Scientifique'))],-1)
printHtmlPart(6)
invokeTag('message','g',32,['code':("produit.categorie.label"),'default':("Categorie")],-1)
printHtmlPart(7)
invokeTag('message','g',35,['code':("produit.mesure.label"),'default':("Mesure")],-1)
printHtmlPart(8)
for( produitInstance in (produitInstanceList) ) {
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('remoteLink','g',45,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(produitInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(11)
expressionOut.print(fieldValue(bean: produitInstance, field: "code"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: produitInstance, field: "nom"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: produitInstance, field: "nomScientifique"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: produitInstance, field: "categorie"))
printHtmlPart(13)
expressionOut.print(fieldValue(bean: produitInstance, field: "mesure"))
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('remotePaginate','util',59,['update':("listContent"),'action':("list"),'total':(produitInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(16)
invokeTag('formats','export',61,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(17)
invokeTag('filterPane','filterpane',62,['dialog':("true"),'domain':("simagri.Produit"),'excludeProperties':("commentaire")],-1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278234250L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
