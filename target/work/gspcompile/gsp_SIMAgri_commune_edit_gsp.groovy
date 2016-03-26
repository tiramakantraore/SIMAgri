import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_commune_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/commune/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
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
invokeTag('message','g',13,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',14,['bean':(communeInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',16,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',17,['bean':(communeInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',20,['name':("id"),'value':(communeInstance?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',21,['name':("version"),'value':(communeInstance?.version)],-1)
printHtmlPart(12)
invokeTag('field','f',22,['bean':("communeInstance"),'property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(12)
invokeTag('field','f',23,['bean':("communeInstance"),'property':("province"),'input-class':("form-control")],-1)
printHtmlPart(14)
invokeTag('render','g',24,['template':("/partials/btnEdit")],-1)
printHtmlPart(15)
})
invokeTag('form','g',25,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416953972689L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
