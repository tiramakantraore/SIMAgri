import simagri.MonImage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_monImage_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/monImage/_list.gsp" }
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
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'monImage.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',24,['update':("listContent"),'action':("list"),'property':("photo"),'title':(message(code: 'monImage.photo.label', default: 'Photo'))],-1)
printHtmlPart(6)
for( monImageInstance in (monImageInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',36,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(monImageInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: monImageInstance, field: "nom"))
printHtmlPart(10)
expressionOut.print(createLink(controller: 'monImage', action: 'showImg',params:[id:monImageInstance?.id]))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',46,['update':("listContent"),'action':("list"),'total':(monImageInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('filterPane','filterpane',48,['dialog':("true"),'domain':("simagri.MonImage")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278003826L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
