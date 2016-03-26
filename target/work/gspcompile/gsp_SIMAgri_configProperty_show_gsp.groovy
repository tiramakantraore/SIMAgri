import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createClosureForHtmlPart(2, 1)
invokeTag('link','g',8,['class':(""),'controller':("configProperty"),'action':("list")],1)
printHtmlPart(3)
createClosureForHtmlPart(4, 1)
invokeTag('link','g',12,['class':(""),'controller':("configProperty"),'action':("create")],1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
expressionOut.print(flash.message)
printHtmlPart(7)
}
printHtmlPart(8)
if(true && (configPropertyInstance?.configKey)) {
printHtmlPart(9)
invokeTag('message','g',21,['code':("configProperty.configKey.label"),'default':("configKey")],-1)
printHtmlPart(10)
invokeTag('fieldValue','g',25,['bean':(configPropertyInstance),'field':("configKey")],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (configPropertyInstance?.value)) {
printHtmlPart(13)
invokeTag('message','g',30,['code':("configProperty.value.label"),'default':("Value")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',32,['bean':(configPropertyInstance),'field':("value")],-1)
printHtmlPart(11)
}
printHtmlPart(15)
if(true && (configPropertyInstance?.description)) {
printHtmlPart(16)
invokeTag('message','g',39,['code':("configProperty.description.label"),'default':("Description")],-1)
printHtmlPart(17)
invokeTag('fieldValue','g',44,['bean':(configPropertyInstance),'field':("description")],-1)
printHtmlPart(11)
}
printHtmlPart(18)
createTagBody(1, {->
printHtmlPart(19)
invokeTag('hiddenField','g',46,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(20)
invokeTag('actionSubmit','g',48,['class':("edit"),'action':("edit"),'value':(message(code: 'default.button.edit.label', default: 'Edit'))],-1)
printHtmlPart(20)
invokeTag('actionSubmit','g',56,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(21)
})
invokeTag('form','g',57,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418410330280L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
