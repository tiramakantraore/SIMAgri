import simagri.Contact
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_contactlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/contact/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'contact.label', default: 'Contact'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("list.contact")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(1)
invokeTag('includes','filterpane',8,[:],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',10,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',18,['code':("title.contact")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',23,['code':("create.contact")],-1)
printHtmlPart(6)
})
invokeTag('remoteLink','g',24,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],2)
printHtmlPart(7)
invokeTag('message','g',34,['code':("list.contact")],-1)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',38,['class':("alert-info")],3)
printHtmlPart(10)
}
printHtmlPart(10)
invokeTag('filterButton','filterpane',40,['text':("Rechercher")],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',46,['update':("listContent"),'action':("list"),'property':("nom"),'title':(message(code: 'contact.nom.label', default: 'Nom'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',48,['update':("listContent"),'action':("list"),'property':("email"),'title':(message(code: 'contact.email.label', default: 'Email'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',51,['update':("listContent"),'action':("list"),'property':("telephone"),'title':(message(code: 'contact.telephone.label', default: 'Telephone'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',53,['update':("listContent"),'action':("list"),'property':("sujet"),'title':(message(code: 'contact.sujet.label', default: 'Sujet'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',56,['update':("listContent"),'action':("list"),'property':("message"),'title':(message(code: 'contact.message.label', default: 'Message'))],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',59,['update':("listContent"),'action':("list"),'property':("publier"),'title':(message(code: 'contact.publier.label', default: 'Publier'))],-1)
printHtmlPart(13)
for( contactInstance in (contactInstanceList) ) {
printHtmlPart(14)
createClosureForHtmlPart(15, 3)
invokeTag('remoteLink','g',69,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(contactInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(16)
expressionOut.print(fieldValue(bean: contactInstance, field: "nom"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: contactInstance, field: "email"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: contactInstance, field: "telephone"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: contactInstance, field: "sujet"))
printHtmlPart(17)
expressionOut.print(fieldValue(bean: contactInstance, field: "message"))
printHtmlPart(17)
invokeTag('formatBoolean','g',82,['boolean':(contactInstance.publier)],-1)
printHtmlPart(18)
}
printHtmlPart(19)
invokeTag('remotePaginate','util',90,['update':("listContent"),'action':("list"),'total':(contactInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(20)
invokeTag('formats','export',92,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',96,['dialog':("true"),'domain':("simagri.Contact")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',97,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993772L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
