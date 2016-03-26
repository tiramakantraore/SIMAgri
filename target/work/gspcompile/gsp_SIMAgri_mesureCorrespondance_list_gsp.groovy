import simagri.MesureCorrespondance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesureCorrespondance_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesureCorrespondance/_list.gsp" }
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
invokeTag('filterButton','filterpane',11,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('message','g',18,['code':("mesureCorrespondance.mesureDepart.label"),'default':("Mesure Depart")],-1)
printHtmlPart(5)
invokeTag('message','g',22,['code':("mesureCorrespondance.mesureDestination.label"),'default':("Mesure Destination")],-1)
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',28,['update':("listContent"),'action':("list"),'property':("equivalence"),'title':(message(code: 'mesureCorrespondance.equivalence.label', default: 'Equivalence'))],-1)
printHtmlPart(7)
for( mesureCorrespondanceInstance in (mesureCorrespondanceInstanceList) ) {
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('remoteLink','g',40,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(mesureCorrespondanceInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(10)
expressionOut.print(fieldValue(bean: mesureCorrespondanceInstance, field: "mesureDepart"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: mesureCorrespondanceInstance, field: "mesureDestination"))
printHtmlPart(11)
expressionOut.print(fieldValue(bean: mesureCorrespondanceInstance, field: "equivalence"))
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('remotePaginate','util',50,['update':("listContent"),'action':("list"),'total':(mesureCorrespondanceInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',54,['dialog':("true"),'domain':("simagri.MesureCorrespondance")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278003693L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
