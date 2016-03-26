import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_groupe_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupe/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('filterButton','filterpane',11,['text':("Rechercher")],-1)
printHtmlPart(3)
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("parent"),'title':(message(code: 'reseau.parent.label', default: 'Parent'))],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',26,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'reseau.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('message','g',29,['code':("reseau.nbTotalMembres.label"),'default':("Nbre membres")],-1)
printHtmlPart(6)
for( reseauInstance in (reseauInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',38,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(reseauInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: reseauInstance, field: "parent"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: reseauInstance, field: "nom"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: reseauInstance, field: "nbMembres"))
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('remotePaginate','util',47,['update':("listContent"),'action':("list"),'total':(reseauInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(14)
invokeTag('formats','export',47,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(15)
invokeTag('filterPane','filterpane',49,['dialog':("true"),'domain':("simagri.Reseau"),'excludeProperties':("commentaire")],-1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278003746L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
