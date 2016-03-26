import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'configKey', 'formError'))
printHtmlPart(1)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'configKey', 'error'))
printHtmlPart(2)
invokeTag('message','g',7,['code':("configProperty.configKey.label"),'default':("configKey")],-1)
printHtmlPart(3)
invokeTag('textField','g',10,['class':("form-text"),'name':("configKey"),'value':(configPropertyInstance?.configKey)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'value', 'formError'))
printHtmlPart(1)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'value', 'error'))
printHtmlPart(5)
invokeTag('message','g',17,['code':("configProperty.value.label"),'default':("Value")],-1)
printHtmlPart(3)
invokeTag('textField','g',20,['class':("form-text"),'name':("value"),'value':(configPropertyInstance?.value)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'description', 'formError'))
printHtmlPart(1)
expressionOut.print(hasErrors(bean: configPropertyInstance, field: 'description', 'error'))
printHtmlPart(6)
invokeTag('message','g',27,['code':("configProperty.description.label"),'default':("Description")],-1)
printHtmlPart(3)
invokeTag('textArea','g',30,['name':("description"),'value':(configPropertyInstance?.description)],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413209948000L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
