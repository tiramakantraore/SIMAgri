import org.springframework.validation.FieldError
import  simagri.Quizz
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizzedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'quizz.label', default: 'Quizz'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("edit.quizz")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',20,['code':("edit.quizz")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],3)
printHtmlPart(5)
}
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(7)
createTagBody(4, {->
printHtmlPart(8)
if(true && (error in FieldError)) {
printHtmlPart(9)
expressionOut.print(error.field)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('message','g',31,['error':(error)],-1)
printHtmlPart(12)
})
invokeTag('eachError','g',31,['bean':(quizzInstance),'var':("error")],4)
printHtmlPart(13)
})
invokeTag('alert','bootstrap',33,['class':("alert-error")],3)
printHtmlPart(5)
})
invokeTag('hasErrors','g',35,['bean':(quizzInstance)],2)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
invokeTag('hiddenField','g',42,['name':("version"),'value':(quizzInstance?.version)],-1)
printHtmlPart(16)
invokeTag('render','g',45,['template':("form")],-1)
printHtmlPart(17)
expressionOut.print(createLink(controller:'quizz', action:'edit'))
printHtmlPart(18)
invokeTag('message','g',56,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(19)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'))
printHtmlPart(20)
invokeTag('message','g',61,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(21)
})
invokeTag('form','g',61,['class':("well small form-horizontal"),'role':("form"),'action':("edit"),'id':(quizzInstance?.id)],2)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',63,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410499L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
