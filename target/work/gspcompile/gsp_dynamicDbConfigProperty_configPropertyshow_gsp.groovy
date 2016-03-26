import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='dynamic-db-config-property', version='0.4.0.9')
class gsp_dynamicDbConfigProperty_configPropertyshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/dynamic-db-config-property-0.4.0.9/grails-app/views/configProperty/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main.gsp")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'configProperty.label', default: 'ConfigProperty'))],-1)
printHtmlPart(2)
invokeTag('render','g',8,['template':("head")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',14,['class':(""),'controller':("configProperty"),'action':("list")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',17,['class':(""),'controller':("configProperty"),'action':("create")],2)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
expressionOut.print(flash.message)
printHtmlPart(10)
}
printHtmlPart(11)
if(true && (configPropertyInstance?.configKey)) {
printHtmlPart(12)
invokeTag('message','g',28,['code':("configProperty.configKey.label"),'default':("configKey")],-1)
printHtmlPart(13)
invokeTag('fieldValue','g',30,['bean':(configPropertyInstance),'field':("configKey")],-1)
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (configPropertyInstance?.value)) {
printHtmlPart(16)
invokeTag('message','g',37,['code':("configProperty.value.label"),'default':("Value")],-1)
printHtmlPart(17)
invokeTag('fieldValue','g',39,['bean':(configPropertyInstance),'field':("value")],-1)
printHtmlPart(14)
}
printHtmlPart(18)
if(true && (configPropertyInstance?.description)) {
printHtmlPart(19)
invokeTag('message','g',46,['code':("configProperty.description.label"),'default':("Description")],-1)
printHtmlPart(20)
invokeTag('fieldValue','g',48,['bean':(configPropertyInstance),'field':("description")],-1)
printHtmlPart(14)
}
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(22)
invokeTag('hiddenField','g',56,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(23)
invokeTag('actionSubmit','g',57,['class':("edit"),'action':("edit"),'value':(message(code: 'default.button.edit.label', default: 'Edit'))],-1)
printHtmlPart(23)
invokeTag('actionSubmit','g',58,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(24)
})
invokeTag('form','g',60,[:],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',63,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413209994000L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
