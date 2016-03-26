import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizz_createQuizz_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/_createQuizz.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
createTagBody(3, {->
printHtmlPart(4)
if(true && (error in FieldError)) {
printHtmlPart(5)
expressionOut.print(error.field)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',16,['error':(error)],-1)
printHtmlPart(8)
})
invokeTag('eachError','g',17,['bean':(quizzInstance),'var':("error")],3)
printHtmlPart(9)
})
invokeTag('alert','bootstrap',19,['class':("alert-error")],2)
printHtmlPart(1)
})
invokeTag('hasErrors','g',20,['bean':(quizzInstance)],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('field','f',24,['bean':("quizzInstance"),'property':("titre"),'id':("quizz_titre"),'input-class':("form-control")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',26,['name':("questions[${i}]?.id"),'value':(detail?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',27,['name':("questions[${i}]?.deleted"),'value':("false")],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',28,['name':("questions[${i}]?.new"),'value':(detail?.id == null?'true':'false')],-1)
printHtmlPart(14)
invokeTag('render','g',31,['template':("/quizz/details"),'model':(['sondageInstance':sondageInstance])],-1)
printHtmlPart(15)
})
invokeTag('form','g',34,['class':("well small form-horizontal"),'role':("form"),'name':("quizzForm")],1)
printHtmlPart(16)
invokeTag('message','g',37,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411943L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
