import simagri.QuestionChoixMultiple
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_questionChoixMultiplelist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/questionChoixMultiple/list.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'questionChoixMultiple.label', default: 'QuestionChoixMultiple'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.questionChoixMultiple")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',18,['code':("title.questionChoixMultiple")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('message','g',23,['code':("create.questionChoixMultiple")],-1)
printHtmlPart(8)
})
invokeTag('link','g',24,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',33,['code':("list.questionChoixMultiple")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',37,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('filterButton','filterpane',39,['text':("Rechercher")],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',45,['update':("listContent"),'action':("list"),'property':("question"),'title':(message(code: 'questionChoixMultiple.question.label', default: 'Question'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',47,['update':("listContent"),'action':("list"),'property':("actif"),'title':(message(code: 'questionChoixMultiple.actif.label', default: 'Actif'))],-1)
printHtmlPart(15)
invokeTag('message','g',49,['code':("questionChoixMultiple.quiz.label"),'default':("Quiz")],-1)
printHtmlPart(16)
for( questionChoixMultipleInstance in (questionChoixMultipleInstanceList) ) {
printHtmlPart(17)
createClosureForHtmlPart(18, 3)
invokeTag('remoteLink','g',60,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(questionChoixMultipleInstance.id),'class':("btn-flat  btn-small")],3)
printHtmlPart(19)
expressionOut.print(fieldValue(bean: questionChoixMultipleInstance, field: "question"))
printHtmlPart(20)
invokeTag('formatBoolean','g',65,['boolean':(questionChoixMultipleInstance.actif)],-1)
printHtmlPart(21)
expressionOut.print(fieldValue(bean: questionChoixMultipleInstance, field: "quiz"))
printHtmlPart(22)
}
printHtmlPart(23)
invokeTag('remotePaginate','util',76,['update':("listContent"),'action':("list"),'total':(questionChoixMultipleInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('filterPane','filterpane',83,['dialog':("true"),'domain':("simagri.QuestionChoixMultiple")],-1)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',84,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993632L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
