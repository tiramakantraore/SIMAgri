import simagri.Operateur
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_operateur_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/operateur/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('render','g',10,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',13,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('filterButton','filterpane',15,['text':("Rechercher")],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',20,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'operateur.nom.label', default: 'Nom'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',25,['update':("listContent"),'action':("list"),'property':("prefixes"),'title':(message(code: 'operateur.prefixes.label', default: 'Prefixes'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("emailContact"),'title':(message(code: 'operateur.emailContact.label', default: 'Email Contact'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',35,['update':("listContent"),'action':("list"),'property':("telephoneContact"),'title':(message(code: 'operateur.telephoneContact.label', default: 'Telephone Contact'))],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',37,['update':("listContent"),'action':("list"),'property':("siteWeb"),'title':(message(code: 'operateur.siteWeb.label', default: 'Site Web'))],-1)
printHtmlPart(7)
for( operateurInstance in (operateurInstanceList) ) {
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('remoteLink','g',43,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(operateurInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(10)
expressionOut.print(fieldValue(bean: operateurInstance, field: "nom"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: operateurInstance, field: "prefixes"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: operateurInstance, field: "emailContact"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: operateurInstance, field: "telephoneContact"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: operateurInstance, field: "siteWeb"))
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('remotePaginate','util',56,['update':("listContent"),'action':("list"),'total':(operateurInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',59,['dialog':("true"),'domain':("simagri.Operateur")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278003607L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
