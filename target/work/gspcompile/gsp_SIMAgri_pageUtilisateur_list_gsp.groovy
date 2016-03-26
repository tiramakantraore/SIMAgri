import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_pageUtilisateur_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/pageUtilisateur/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
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
invokeTag('remoteSortableColumn','util',17,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'pageUtilisateur.nom.label', default: 'Nom'))],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("destinationType"),'title':(message(code: 'pageUtilisateur.destinationType.label', default: 'Destination Type'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',21,['update':("listContent"),'action':("list"),'property':("photo"),'title':(message(code: 'monImage.photo.label', default: 'Photo'))],-1)
printHtmlPart(6)
for( pageUtilisateurInstance in (pageUtilisateurInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',31,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(pageUtilisateurInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: pageUtilisateurInstance, field: "nom"))
printHtmlPart(10)
expressionOut.print(message(code: pageUtilisateurInstance?.destinationType, default: pageUtilisateurInstance?.destinationType))
printHtmlPart(11)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageUtilisateurInstance?.id]))
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('remotePaginate','util',47,['update':("listContent"),'action':("list"),'total':(pageUtilisateurInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',54,['dialog':("true"),'domain':("simagri.PageUtilisateur")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1444682137343L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
