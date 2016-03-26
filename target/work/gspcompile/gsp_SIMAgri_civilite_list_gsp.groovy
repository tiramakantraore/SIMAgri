import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_civilite_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/civilite/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([beanName:'civilite',canCreate:true,isList:true])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('filterButton','filterpane',9,['text':("Rechercher")],-1)
printHtmlPart(3)
invokeTag('remoteSortableColumn','util',14,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'civilite.code.label', default: 'Code'))],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',15,['update':("listContent"),'action':("list"),'property':("libelle"),'title':(message(code: 'civilite.libelle.label', default: 'Libelle'))],-1)
printHtmlPart(5)
for( civiliteInstance in (civiliteInstanceList) ) {
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('remoteLink','g',26,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(civiliteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(8)
expressionOut.print(fieldValue(bean: civiliteInstance, field: "code"))
printHtmlPart(9)
expressionOut.print(fieldValue(bean: civiliteInstance, field: "libelle"))
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('remotePaginate','util',37,['update':("listContent"),'action':("list"),'total':(civiliteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(12)
invokeTag('formats','export',40,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(13)
invokeTag('filterPane','filterpane',44,['dialog':("true"),'domain':("simagri.Civilite")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419277688347L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
