import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_province_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/province/_list.gsp" }
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
invokeTag('message','g',18,['code':("province.region.label"),'default':("Region")],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',23,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'province.nom.label', default: 'Nom'))],-1)
printHtmlPart(6)
for( provinceInstance in (provinceInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',39,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(provinceInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: provinceInstance, field: "region"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: provinceInstance, field: "nom"))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',48,['update':("listContent"),'action':("list"),'total':(provinceInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('formats','export',49,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',50,['dialog':("true"),'associatedProperties':("region.nom"),'domain':("simagri.Province")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278234384L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
