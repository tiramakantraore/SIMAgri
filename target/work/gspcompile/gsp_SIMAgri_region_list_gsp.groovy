import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_region_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/region/_list.gsp" }
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
invokeTag('remoteSortableColumn','util',19,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'region.nom.label', default: 'Nom'))],-1)
printHtmlPart(5)
for( regionInstance in (regionInstanceList) ) {
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('remoteLink','g',36,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(regionInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(8)
expressionOut.print(fieldValue(bean: regionInstance, field: "nom"))
printHtmlPart(9)
}
printHtmlPart(10)
invokeTag('remotePaginate','util',45,['update':("listContent"),'action':("list"),'total':(regionInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(11)
invokeTag('formats','export',45,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(12)
invokeTag('filterPane','filterpane',46,['dialog':("true"),'domain':("simagri.Region")],-1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278234297L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
