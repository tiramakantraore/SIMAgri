import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='dynamic-db-config-property', version='0.4.0.9')
class gsp_dynamicDbConfigProperty_configPropertyedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/dynamic-db-config-property-0.4.0.9/grails-app/views/configProperty/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main.gsp")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'configProperty.label', default: 'ConfigProperty'))],-1)
printHtmlPart(1)
invokeTag('render','g',7,['template':("head")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
createClosureForHtmlPart(4, 2)
invokeTag('link','g',13,['class':(""),'controller':("configProperty"),'action':("list")],2)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',16,['class':(""),'controller':("configProperty"),'action':("create")],2)
printHtmlPart(7)
if(true && (flash.message)) {
printHtmlPart(8)
expressionOut.print(flash.message)
printHtmlPart(9)
}
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(12)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(13)
expressionOut.print(error.field)
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('message','g',26,['error':(error)],-1)
printHtmlPart(16)
})
invokeTag('eachError','g',27,['bean':(configPropertyInstance),'var':("error")],3)
printHtmlPart(17)
})
invokeTag('hasErrors','g',29,['bean':(configPropertyInstance)],2)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('hiddenField','g',31,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',32,['name':("version"),'value':(configPropertyInstance?.version)],-1)
printHtmlPart(18)
invokeTag('render','g',33,['template':("form")],-1)
printHtmlPart(19)
invokeTag('actionSubmit','g',35,['class':("save"),'action':("update"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(20)
invokeTag('actionSubmit','g',36,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'formnovalidate':(""),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(21)
})
invokeTag('form','g',38,['method':("post"),'class':("columns")],2)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',41,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1394465028000L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
