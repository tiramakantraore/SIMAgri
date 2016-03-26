import simagri.Quizz
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizzlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'quizz.label', default: 'Quizz'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.quizz")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.quizz")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',21,['code':("create.quizz")],-1)
printHtmlPart(7)
})
invokeTag('link','g',22,['class':("create"),'action':("create")],2)
printHtmlPart(8)
invokeTag('message','g',31,['code':("list.quizz")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',35,['class':("alert-info")],3)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('filterButton','filterpane',37,['text':("Rechercher")],-1)
printHtmlPart(12)
invokeTag('remoteSortableColumn','util',43,['update':("listContent"),'action':("list"),'property':("titre"),'title':(message(code: 'quizz.titre.label', default: 'Titre'))],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("description"),'title':(message(code: 'quizz.description.label', default: 'Description'))],-1)
printHtmlPart(14)
for( quizzInstance in (quizzInstanceList) ) {
printHtmlPart(15)
createClosureForHtmlPart(16, 3)
invokeTag('remoteLink','g',56,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(quizzInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(17)
expressionOut.print(fieldValue(bean: quizzInstance, field: "titre"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: quizzInstance, field: "description"))
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('remotePaginate','util',69,['update':("listContent"),'action':("list"),'total':(quizzInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(21)
invokeTag('filterPane','filterpane',76,['dialog':("true"),'domain':("simagri.Quizz")],-1)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',77,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993813L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
