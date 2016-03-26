import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='dynamic-db-config-property', version='0.4.0.9')
class gsp_dynamicDbConfigProperty_configPropertylist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/dynamic-db-config-property-0.4.0.9/grails-app/views/configProperty/list.gsp" }
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
expressionOut.print(request.contextPath)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',30,[:],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',35,['class':(""),'controller':("configProperty"),'action':("list")],2)
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('link','g',38,['class':(""),'controller':("configProperty"),'action':("create")],2)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',51,['code':("configProperty.config.name.label"),'default':("Config Name")],-1)
printHtmlPart(14)
invokeTag('message','g',53,['code':("configProperty.config.file.value.label"),'default':("Config File Value")],-1)
printHtmlPart(15)
invokeTag('message','g',55,['code':("configProperty.db.value.label"),'default':("Database Value")],-1)
printHtmlPart(15)
invokeTag('message','g',57,['code':("configProperty.current.value.label"),'default':("Current Value")],-1)
printHtmlPart(16)
loop:{
int i = 0
for( comparedProperty in (comparedProperties) ) {
printHtmlPart(17)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(18)
if(true && (isInDb || comparedProperty.dbProperty)) {
printHtmlPart(19)
expressionOut.print(i)
printHtmlPart(20)
expressionOut.print(comparedProperty.configKey)
printHtmlPart(21)
}
else {
printHtmlPart(22)
invokeTag('checkBox','g',71,['name':("checkbox_${i}"),'value':(comparedProperty.configKey),'checked':("false"),'onclick':(remoteFunction(action:'addToFrequentlyUsedList', params: [configKey:comparedProperty.configKey, value:comparedProperty.fileProperty], onSuccess: "clicked('checkbox_${i}', '${comparedProperty.configKey}', '${comparedProperty.fileProperty}', data)" ))],-1)
printHtmlPart(23)
}
printHtmlPart(24)
if(true && (comparedProperty.dbId != null)) {
printHtmlPart(22)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
})
invokeTag('link','g',75,['action':("show"),'id':(comparedProperty.dbId)],4)
printHtmlPart(23)
}
else {
printHtmlPart(22)
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
printHtmlPart(23)
}
printHtmlPart(25)
expressionOut.print(fieldValue(bean: comparedProperty, field: "fileProperty"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: comparedProperty, field: "dbProperty"))
printHtmlPart(26)
expressionOut.print(fieldValue(bean: comparedProperty, field: "currentProperty"))
printHtmlPart(27)
i++
}
}
printHtmlPart(28)
expressionOut.print(totalNum)
printHtmlPart(29)
invokeTag('javascript','g',94,['src':("app.js")],-1)
printHtmlPart(30)
})
invokeTag('captureBody','sitemesh',97,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1414092905299L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
