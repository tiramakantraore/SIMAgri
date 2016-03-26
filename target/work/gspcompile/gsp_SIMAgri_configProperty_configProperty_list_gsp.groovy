import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_configProperty_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/configProperty/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
invokeTag('remoteFunction','g',12,['controller':("greeting"),'action':("greetName"),'update':("greetingBox"),'params':("'name='+name")],-1)
printHtmlPart(2)
expressionOut.print(request.contextPath)
printHtmlPart(3)
invokeTag('render','g',30,['template':("/partials/showHeader")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
expressionOut.print(flash.message)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('remoteSortableColumn','util',39,['update':("listContent"),'action':("list"),'property':("configKey"),'title':(message(code: 'configProperty.config.name.label', default: 'Nom paramètre'))],-1)
printHtmlPart(8)
invokeTag('message','g',41,['code':("configProperty.config.file.value.label"),'default':("Valeur dans le fichier")],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',44,['update':("listContent"),'action':("list"),'property':("dbProperty"),'title':(message(code: 'configProperty.db.value.label', default: 'Valeur dans la base'))],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',48,['update':("listContent"),'action':("list"),'property':("currentProperty"),'title':(message(code: 'configProperty.current.value.label', default: 'Valeur actuelle'))],-1)
printHtmlPart(11)
loop:{
int i = 0
for( comparedProperty in (comparedProperties) ) {
printHtmlPart(12)
if(true && (isInDb || comparedProperty.dbProperty)) {
printHtmlPart(13)
expressionOut.print(i)
printHtmlPart(14)
expressionOut.print(comparedProperty.configKey)
printHtmlPart(15)
}
else {
printHtmlPart(16)
invokeTag('checkBox','g',63,['name':("checkbox_${i}"),'value':(comparedProperty.configKey),'checked':("false"),'onclick':(remoteFunction(action:'addToFrequentlyUsedList', params: [configKey:comparedProperty.configKey, value:comparedProperty.fileProperty], onSuccess: "clicked('checkbox_${i}', '${comparedProperty.configKey}', '${comparedProperty.fileProperty}', data)" ))],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (comparedProperty.dbId != null)) {
printHtmlPart(19)
createTagBody(3, {->
printHtmlPart(20)
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
})
invokeTag('remoteLink','g',75,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("configProperty"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(comparedProperty.dbId),'params':([update:'listContent'])],3)
printHtmlPart(21)
}
else {
printHtmlPart(22)
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
printHtmlPart(23)
}
printHtmlPart(24)
expressionOut.print(fieldValue(bean: comparedProperty, field: "fileProperty"))
printHtmlPart(25)
expressionOut.print(fieldValue(bean: comparedProperty, field: "dbProperty"))
printHtmlPart(25)
expressionOut.print(fieldValue(bean: comparedProperty, field: "currentProperty"))
printHtmlPart(26)
i++
}
}
printHtmlPart(27)
expressionOut.print(totalNum)
printHtmlPart(28)
invokeTag('formats','export',84,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442329663258L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
