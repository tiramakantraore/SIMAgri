import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_lieux_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/lieux/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("title.lieux")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',10,['code':("create.lieux")],-1)
printHtmlPart(3)
})
invokeTag('remoteLink','g',11,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',14,['code':("geoCodeAll.location")],-1)
printHtmlPart(3)
})
invokeTag('remoteLink','g',15,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("geoCodeAll"),'update':("listContent"),'class':("create")],1)
printHtmlPart(4)
invokeTag('message','g',25,['code':("list.lieux")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',33,['class':("alert-info")],2)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('filterButton','filterpane',35,['text':("Rechercher")],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',41,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'lieux.nom.label', default: 'Nom'))],-1)
printHtmlPart(9)
invokeTag('message','g',43,['code':("lieux.commune.label"),'default':("Commune")],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("longitude"),'title':(message(code: 'lieux.longitude.label', default: 'Longitude'))],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',47,['update':("listContent"),'action':("list"),'property':("latitude"),'title':(message(code: 'lieux.latitude.label', default: 'Latitude'))],-1)
printHtmlPart(12)
for( lieuxInstance in (lieuxInstanceList) ) {
printHtmlPart(13)
createClosureForHtmlPart(14, 2)
invokeTag('remoteLink','g',58,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(lieuxInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(15)
expressionOut.print(fieldValue(bean: lieuxInstance, field: "nom"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: lieuxInstance, field: "commune"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: lieuxInstance, field: "longitude"))
printHtmlPart(16)
expressionOut.print(fieldValue(bean: lieuxInstance, field: "latitude"))
printHtmlPart(17)
}
printHtmlPart(18)
invokeTag('remotePaginate','util',75,['update':("listContent"),'action':("list"),'total':(lieuxInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(19)
invokeTag('formats','export',78,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(20)
invokeTag('filterPane','filterpane',82,['dialog':("true"),'domain':("simagri.Lieux")],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993254L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
