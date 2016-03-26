import simagri.MonImage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_monImage_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/monImage/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',3,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',17,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',18,['bean':(monImageInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',20,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',21,['bean':(monImageInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',25,['name':("version"),'value':(monImageInstance?.version)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',26,['name':("id"),'value':(monImageInstance?.id)],-1)
printHtmlPart(14)
invokeTag('render','g',29,['template':("form")],-1)
printHtmlPart(15)
invokeTag('render','g',31,['template':("/partials/btnEdit")],-1)
printHtmlPart(16)
})
invokeTag('form','g',35,['class':("well small form-horizontal"),'action':("edit"),'enctype':("multipart/form-data")],1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435016524L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
