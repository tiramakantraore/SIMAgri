import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_magazin_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/magazin/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'magazin.label', default: 'Magazin'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.magazin")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('render','g',13,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',23,['class':("alert-info")],3)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('filterButton','filterpane',24,['text':("Rechercher")],-1)
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',32,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'magazin.code.label', default: 'Code'))],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',38,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'magazin.nom.label', default: 'Nom'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',42,['update':("listContent"),'action':("list"),'property':("localite"),'title':(message(code: 'magazin.localite.label', default: 'Lieux'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',48,['update':("listContent"),'action':("list"),'property':("produits"),'title':(message(code: 'magazin.produits.label', default: 'Produits'))],-1)
printHtmlPart(11)
for( magazinInstance in (magazinInstanceList) ) {
printHtmlPart(12)
createClosureForHtmlPart(13, 3)
invokeTag('remoteLink','g',56,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(magazinInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(14)
expressionOut.print(fieldValue(bean: magazinInstance, field: "code"))
printHtmlPart(15)
expressionOut.print(fieldValue(bean: magazinInstance, field: "nom"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: magazinInstance, field: "localite"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: magazinInstance, field: "produits"))
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',67,['update':("listContent"),'action':("list"),'total':(magazinInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',70,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',72,['dialog':("true"),'domain':("simagri.Magazin")],-1)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',72,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423545716257L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
