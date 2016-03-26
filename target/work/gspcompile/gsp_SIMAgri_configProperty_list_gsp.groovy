import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
expressionOut.print(request.contextPath)
printHtmlPart(2)
invokeTag('render','g',25,['template':("/partials/showHeader")],-1)
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(4)
expressionOut.print(flash.message)
printHtmlPart(5)
}
printHtmlPart(6)
invokeTag('remoteSortableColumn','util',36,['update':("listContent"),'action':("list"),'property':("configKey"),'title':(message(code: 'configProperty.config.name.label', default: 'Nom paramètre'))],-1)
printHtmlPart(7)
invokeTag('message','g',39,['code':("configProperty.config.file.value.label"),'default':("Valeur dans le fichier")],-1)
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',42,['update':("listContent"),'action':("list"),'property':("dbProperty"),'title':(message(code: 'configProperty.db.value.label', default: 'Valeur dans la base'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',51,['update':("listContent"),'action':("list"),'property':("currentProperty"),'title':(message(code: 'configProperty.current.value.label', default: 'Valeur actuelle'))],-1)
printHtmlPart(10)
loop:{
int i = 0
for( comparedProperty in (comparedProperties) ) {
printHtmlPart(11)
if(true && (isInDb || comparedProperty.dbProperty)) {
printHtmlPart(12)
expressionOut.print(i)
printHtmlPart(13)
expressionOut.print(comparedProperty.configKey)
printHtmlPart(14)
}
else {
printHtmlPart(15)
invokeTag('checkBox','g',65,['name':("checkbox_${i}"),'value':(comparedProperty.configKey),'checked':("false"),'onclick':(remoteFunction(action:'addToFrequentlyUsedList', params: [configKey:comparedProperty.configKey, value:comparedProperty.fileProperty], onSuccess: "clicked('checkbox_${i}', '${comparedProperty.configKey}', '${comparedProperty.fileProperty}', data)" ))],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (comparedProperty.dbId != null)) {
printHtmlPart(18)
createTagBody(3, {->
printHtmlPart(19)
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
})
invokeTag('remoteLink','g',72,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'controller':("configProperty"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(comparedProperty.dbId),'params':([update:'listContent']),'class':("btn-flat  btn-small")],3)
printHtmlPart(20)
}
else {
printHtmlPart(15)
expressionOut.print(fieldValue(bean: comparedProperty, field: "configKey"))
printHtmlPart(16)
}
printHtmlPart(21)
expressionOut.print(fieldValue(bean: comparedProperty, field: "fileProperty"))
printHtmlPart(22)
expressionOut.print(fieldValue(bean: comparedProperty, field: "dbProperty"))
printHtmlPart(22)
expressionOut.print(fieldValue(bean: comparedProperty, field: "currentProperty"))
printHtmlPart(23)
i++
}
}
printHtmlPart(24)
expressionOut.print(totalNum)
printHtmlPart(25)
invokeTag('formats','export',80,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442329663291L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
