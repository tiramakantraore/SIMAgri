import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueil_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/_list.gsp" }
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
invokeTag('filterButton','filterpane',14,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',22,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'pageAccueil.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("pagePrix"),'title':(message(code: 'pageAccueil.pagePrix.label', default: 'Page prix'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',33,['update':("listContent"),'action':("list"),'property':("pageOffre"),'title':(message(code: 'pageAccueil.pageOffre.label', default: 'Page offre'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',38,['update':("listContent"),'action':("list"),'property':("pageStock"),'title':(message(code: 'pageAccueil.pageStock.label', default: 'Page stock'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',40,['update':("listContent"),'action':("list"),'property':("pageReseau"),'title':(message(code: 'pageAccueil.pageReseau.label', default: 'Page réseau'))],-1)
printHtmlPart(7)
for( pageAccueilInstance in (pageAccueilInstanceList) ) {
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('remoteLink','g',46,['onLoading':("showSpinner();"),'onComplete':("hideSpinner();if(supports_history_api()){history.pushState(null, null, '${createLink(controller:'pageAccueil', action:'edit')}');}"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(pageAccueilInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(10)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "nom"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pagePrix"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageOffre"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageStock"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageReseau"))
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('remotePaginate','util',59,['update':("listContent"),'action':("list"),'total':(pageAccueilInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',61,['dialog':("true"),'domain':("simagri.PageUtilisateur")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457832690108L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
