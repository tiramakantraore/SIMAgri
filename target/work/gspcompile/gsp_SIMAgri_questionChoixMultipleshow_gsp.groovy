import simagri.QuestionChoixMultiple
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_questionChoixMultipleshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/questionChoixMultiple/show.gsp" }
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
invokeTag('message','g',8,['code':("show.questionChoixMultiple")],-1)
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
invokeTag('message','g',16,['code':("title.questionChoixMultiple")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.questionChoixMultiple")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',26,['code':("create.questionChoixMultiple")],-1)
printHtmlPart(7)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',36,['code':("show.questionChoixMultiple")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (questionChoixMultipleInstance?.quiz)) {
printHtmlPart(14)
invokeTag('message','g',46,['code':("questionChoixMultiple.quiz.label"),'default':("Quiz")],-1)
printHtmlPart(15)
createTagBody(3, {->
expressionOut.print(questionChoixMultipleInstance?.quiz?.encodeAsHTML())
})
invokeTag('link','g',49,['controller':("quizz"),'action':("show"),'id':(questionChoixMultipleInstance?.quiz?.id)],3)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (questionChoixMultipleInstance?.question)) {
printHtmlPart(18)
invokeTag('message','g',56,['code':("questionChoixMultiple.question.label"),'default':("Question")],-1)
printHtmlPart(19)
invokeTag('fieldValue','g',59,['bean':(questionChoixMultipleInstance),'field':("question")],-1)
printHtmlPart(20)
}
printHtmlPart(21)
if(true && (questionChoixMultipleInstance?.actif)) {
printHtmlPart(22)
invokeTag('message','g',68,['code':("questionChoixMultiple.actif.label"),'default':("Actif")],-1)
printHtmlPart(23)
invokeTag('formatBoolean','g',71,['boolean':(questionChoixMultipleInstance?.actif)],-1)
printHtmlPart(24)
}
printHtmlPart(25)
if(true && (questionChoixMultipleInstance?.commentaireBonneReponse)) {
printHtmlPart(22)
invokeTag('message','g',77,['code':("questionChoixMultiple.commentaireBonneReponse.label"),'default':("Commentaire bonne réponse")],-1)
printHtmlPart(23)
invokeTag('fieldValue','g',80,['bean':(questionChoixMultipleInstance),'field':("commentaireBonneReponse")],-1)
printHtmlPart(20)
}
printHtmlPart(26)
if(true && (questionChoixMultipleInstance?.commentaireMauvaiseReponse)) {
printHtmlPart(14)
invokeTag('message','g',87,['code':("questionChoixMultiple.commentaireMauvaiseReponse.label"),'default':("Commentaire mauvaise réponse")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',90,['bean':(questionChoixMultipleInstance),'field':("commentaireMauvaiseReponse")],-1)
printHtmlPart(27)
}
printHtmlPart(26)
if(true && (questionChoixMultipleInstance?.options)) {
printHtmlPart(28)
invokeTag('message','g',97,['code':("questionChoixMultiple.options.label"),'default':("Options")],-1)
printHtmlPart(29)
for( p in (questionChoixMultipleInstance?.options) ) {
printHtmlPart(30)
createTagBody(4, {->
expressionOut.print(p?.encodeAsHTML())
})
invokeTag('link','g',100,['controller':("optionChoixMultiple"),'action':("show"),'id':(p.id)],4)
printHtmlPart(31)
}
printHtmlPart(32)
}
printHtmlPart(33)
createTagBody(2, {->
printHtmlPart(34)
invokeTag('hiddenField','g',111,['name':("id"),'value':(questionChoixMultipleInstance?.id)],-1)
printHtmlPart(35)
createTagBody(3, {->
printHtmlPart(36)
invokeTag('message','g',115,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(37)
})
invokeTag('link','g',116,['class':("btn"),'action':("edit"),'id':(questionChoixMultipleInstance?.id)],3)
printHtmlPart(38)
})
invokeTag('form','g',119,[:],2)
printHtmlPart(39)
})
invokeTag('captureBody','sitemesh',124,[:],1)
printHtmlPart(40)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069510489L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
