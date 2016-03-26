import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_configProperty_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/configProperty/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('render','g',2,['template':("/partials/showHeader")],-1)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',5,['class':(""),'controller':("configProperty"),'action':("list")],1)
printHtmlPart(3)
createClosureForHtmlPart(4, 1)
invokeTag('link','g',8,['class':(""),'controller':("configProperty"),'action':("create")],1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
expressionOut.print(flash.message)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(11)
expressionOut.print(error.field)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',17,['error':(error)],-1)
printHtmlPart(14)
})
invokeTag('eachError','g',18,['bean':(configPropertyInstance),'var':("error")],2)
printHtmlPart(15)
})
invokeTag('hasErrors','g',20,['bean':(configPropertyInstance)],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(16)
invokeTag('hiddenField','g',22,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(16)
invokeTag('hiddenField','g',23,['name':("version"),'value':(configPropertyInstance?.version)],-1)
printHtmlPart(16)
invokeTag('render','g',24,['template':("form")],-1)
printHtmlPart(16)
invokeTag('render','g',25,['template':("/partials/btnEdit")],-1)
printHtmlPart(17)
})
invokeTag('form','g',27,['method':("post")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1433075820926L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
