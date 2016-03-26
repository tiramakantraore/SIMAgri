import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_activite_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/activite/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',8,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',14,['update':("listContent"),'action':("list"),'property':("code"),'title':(message(code: 'activite.code.label', default: 'Code'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',16,['update':("listContent"),'action':("list"),'property':("libelle"),'title':(message(code: 'activite.libelle.label', default: 'Libelle'))],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',18,['update':("listContent"),'action':("list"),'property':("commentaire"),'title':(message(code: 'activite.commentaire.label', default: 'Commentaire'))],-1)
printHtmlPart(6)
for( activiteInstance in (activiteInstanceList) ) {
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('remoteLink','g',29,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(activiteInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(9)
expressionOut.print(fieldValue(bean: activiteInstance, field: "code"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: activiteInstance, field: "libelle"))
printHtmlPart(10)
expressionOut.print(fieldValue(bean: activiteInstance, field: "commentaire"))
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('remotePaginate','util',45,['update':("listContent"),'action':("list"),'total':(activiteInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('formats','export',48,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',52,['dialog':("true"),'domain':("simagri.Activite")],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419277688375L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
