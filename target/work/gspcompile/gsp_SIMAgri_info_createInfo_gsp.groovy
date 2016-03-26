import org.springframework.validation.FieldError
import  simagri.Info
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_info_createInfo_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/_createInfo.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(0)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',3,['class':("alert-info")],2)
printHtmlPart(0)
}
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(2)
createTagBody(3, {->
printHtmlPart(3)
if(true && (error in FieldError)) {
printHtmlPart(4)
expressionOut.print(error.field)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('message','g',10,['error':(error)],-1)
printHtmlPart(7)
})
invokeTag('eachError','g',11,['bean':(infoInstance),'var':("error")],3)
printHtmlPart(8)
})
invokeTag('alert','bootstrap',13,['class':("alert-error")],2)
printHtmlPart(0)
})
invokeTag('hasErrors','g',14,['bean':(infoInstance)],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
invokeTag('render','g',17,['template':("/info/form")],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',18,['name':("id"),'value':(infoInstance?.id)],-1)
printHtmlPart(12)
invokeTag('message','g',23,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(13)
})
invokeTag('form','g',27,['name':("info"),'class':(" form-horizontal")],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411294L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
