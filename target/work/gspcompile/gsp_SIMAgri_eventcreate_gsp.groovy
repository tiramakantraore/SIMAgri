import org.springframework.validation.FieldError
import  simagri.Event
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_eventcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/event/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'event.label', default: 'Event'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("createevent.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',12,['action':("index"),'class':("calendar")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',13,['action':("create"),'class':("create")],2)
printHtmlPart(8)
invokeTag('message','g',25,['code':("createevent.label")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',29,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
if(true && (error in FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',36,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',37,['bean':(eventInstance),'var':("error")],4)
printHtmlPart(19)
})
invokeTag('alert','bootstrap',39,['class':("alert-error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',40,['bean':(eventInstance)],2)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('render','g',46,['template':("form"),'model':("model")],-1)
printHtmlPart(22)
expressionOut.print(createLink(controller:'event', action:'create'))
printHtmlPart(23)
invokeTag('message','g',52,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(24)
})
invokeTag('form','g',56,['class':("well small form-horizontal main"),'action':("save"),'method':("post")],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',61,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410621L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
