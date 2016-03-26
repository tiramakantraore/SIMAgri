import org.springframework.validation.FieldError
import  simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_infocreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'info.label', default: 'Info'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("create.info")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
invokeTag('resources','ckeditor',8,[:],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.info")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.info")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
invokeTag('message','g',26,['code':("create.info")],-1)
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
createTagBody(2, {->
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(12)
createTagBody(4, {->
printHtmlPart(13)
if(true && (error in FieldError)) {
printHtmlPart(14)
expressionOut.print(error.field)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('message','g',44,['error':(error)],-1)
printHtmlPart(17)
})
invokeTag('eachError','g',45,['bean':(infoInstance),'var':("error")],4)
printHtmlPart(18)
})
invokeTag('alert','bootstrap',46,['class':("alert-error")],3)
printHtmlPart(10)
})
invokeTag('hasErrors','g',46,['bean':(infoInstance)],2)
printHtmlPart(19)
createTagBody(2, {->
printHtmlPart(20)
invokeTag('render','g',50,['template':("form")],-1)
printHtmlPart(21)
invokeTag('render','g',54,['template':("/info/infojs")],-1)
printHtmlPart(22)
expressionOut.print(createLink(controller:'info', action:'create'))
printHtmlPart(23)
invokeTag('message','g',59,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(24)
})
invokeTag('form','g',59,['class':("well small form-horizontal"),'action':("create")],2)
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
public static final long LAST_MODIFIED = 1423423410399L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
