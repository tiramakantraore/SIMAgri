import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageAccueillistPjax_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageAccueil/listPjax.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("accueilLayout")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',5,['code':("list.listes")],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
invokeTag('resources','ckeditor',12,[:],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('render','g',15,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],3)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('filterButton','filterpane',24,['text':("Rechercher")],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',30,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'pageAccueil.nom.label', default: 'Nom'))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',32,['update':("listContent"),'action':("list"),'property':("pagePrix"),'title':(message(code: 'pageAccueil.pagePrix.label', default: 'Page prix'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',34,['update':("listContent"),'action':("list"),'property':("pageOffre"),'title':(message(code: 'pageAccueil.pageOffre.label', default: 'Page offre'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("pageStock"),'title':(message(code: 'pageAccueil.pageStock.label', default: 'Page stock'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',38,['update':("listContent"),'action':("list"),'property':("pageReseau"),'title':(message(code: 'pageAccueil.pageReseau.label', default: 'Page réseau'))],-1)
printHtmlPart(10)
for( pageAccueilInstance in (pageAccueilInstanceList) ) {
printHtmlPart(11)
createClosureForHtmlPart(12, 3)
invokeTag('remoteLink','g',46,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(pageAccueilInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(13)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "nom"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pagePrix"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageOffre"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageStock"))
printHtmlPart(14)
expressionOut.print(fieldValue(bean: pageAccueilInstance, field: "pageReseau"))
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('remotePaginate','util',67,['update':("listContent"),'action':("list"),'total':(pageAccueilInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(17)
invokeTag('filterPane','filterpane',74,['dialog':("true"),'domain':("simagri.PageUtilisateur")],-1)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',76,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457819808659L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
