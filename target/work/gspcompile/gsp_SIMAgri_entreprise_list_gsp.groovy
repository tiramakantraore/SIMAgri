import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_entreprise_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/entreprise/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([canCreate:true,isList:true])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',13,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
invokeTag('filterButton','filterpane',14,['text':("Rechercher")],-1)
printHtmlPart(3)
invokeTag('remoteSortableColumn','util',22,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'entreprise.nom.label', default: 'Nom'))],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',27,['update':("listContent"),'action':("list"),'property':("site_web"),'title':(message(code: 'entreprise.site_web.label', default: 'Siteweb'))],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',33,['update':("listContent"),'action':("list"),'property':("email"),'title':(message(code: 'entreprise.email.label', default: 'Email'))],-1)
printHtmlPart(5)
for( entrepriseInstance in (entrepriseInstanceList) ) {
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('remoteLink','g',43,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(entrepriseInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(8)
expressionOut.print(fieldValue(bean: entrepriseInstance, field: "nom"))
printHtmlPart(9)
expressionOut.print(fieldValue(bean: entrepriseInstance, field: "site_web"))
printHtmlPart(9)
expressionOut.print(fieldValue(bean: entrepriseInstance, field: "email"))
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('remotePaginate','util',53,['update':("listContent"),'action':("list"),'total':(entrepriseInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(12)
invokeTag('formats','export',54,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(13)
invokeTag('filterPane','filterpane',56,['dialog':("true"),'domain':("simagri.Entreprise")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419278003580L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
