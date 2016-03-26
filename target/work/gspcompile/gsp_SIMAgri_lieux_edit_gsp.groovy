import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_lieux_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/lieux/_edit.gsp" }
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
invokeTag('alert','bootstrap',7,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',14,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',15,['bean':(lieuxInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',17,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',18,['bean':(lieuxInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',21,['name':("version"),'value':(lieuxInstance?.version)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',22,['name':("id"),'value':(lieuxInstance?.id)],-1)
printHtmlPart(13)
invokeTag('field','f',23,['bean':("lieuxInstance"),'property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(13)
invokeTag('field','f',24,['bean':("lieuxInstance"),'property':("commune"),'input-class':("form-control")],-1)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(14)
expressionOut.print(property)
printHtmlPart(15)
expressionOut.print(it.value)
printHtmlPart(16)
})
invokeTag('field','f',27,['bean':("lieuxInstance"),'property':("longitude"),'input-class':("form-control")],2)
printHtmlPart(13)
invokeTag('field','f',28,['bean':("lieuxInstance"),'property':("latitude"),'input-class':("form-control")],-1)
printHtmlPart(17)
invokeTag('render','g',29,['template':("/partials/btnEdit")],-1)
printHtmlPart(18)
})
invokeTag('form','g',30,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417057239337L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
