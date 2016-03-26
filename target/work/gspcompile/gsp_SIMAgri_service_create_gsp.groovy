import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_service_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/service/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader")],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',5,['class':("alert-info")],2)
printHtmlPart(1)
}
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
createTagBody(3, {->
printHtmlPart(4)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(5)
expressionOut.print(error.field)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',12,['error':(error)],-1)
printHtmlPart(8)
})
invokeTag('eachError','g',13,['bean':(serviceInstance),'var':("error")],3)
printHtmlPart(9)
})
invokeTag('alert','bootstrap',15,['class':("alert-error")],2)
printHtmlPart(1)
})
invokeTag('hasErrors','g',16,['bean':(serviceInstance)],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('render','g',19,['template':("form")],-1)
printHtmlPart(11)
invokeTag('render','g',20,['template':("/partials/btnCreer")],-1)
printHtmlPart(12)
})
invokeTag('form','g',21,['class':(" form-horizontal"),'action':("create")],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417646075875L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
