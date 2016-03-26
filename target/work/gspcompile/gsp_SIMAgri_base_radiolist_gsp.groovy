import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_base_radiolist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/base/_radiolist.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
if(true && (title)) {
printHtmlPart(0)
expressionOut.print(title)
printHtmlPart(1)
}
printHtmlPart(2)
if(true && (limitWidth)) {
printHtmlPart(3)
expressionOut.print(containerClass)
printHtmlPart(4)
expressionOut.print(limitWidth)
printHtmlPart(5)
}
else {
printHtmlPart(3)
expressionOut.print(containerClass)
printHtmlPart(6)
}
printHtmlPart(7)
loop:{
int i = 0
for( instance in (referenceCollection) ) {
printHtmlPart(8)
if(true && (isArray)) {
printHtmlPart(9)
invokeTag('set','g',14,['var':("valueName"),'value':(instance)],-1)
printHtmlPart(9)
invokeTag('set','g',15,['var':("radioName"),'value':(radioName)],-1)
printHtmlPart(9)
invokeTag('set','g',16,['var':("radioId"),'value':("${radioName}_${instance}")],-1)
printHtmlPart(9)
invokeTag('set','g',17,['var':("title"),'value':(instance)],-1)
printHtmlPart(8)
}
else {
printHtmlPart(10)
if(true && (isEnum)) {
printHtmlPart(11)
invokeTag('set','g',21,['var':("valueName"),'value':(instance?.name())],-1)
printHtmlPart(11)
invokeTag('set','g',22,['var':("radioName"),'value':(instanceName)],-1)
printHtmlPart(11)
invokeTag('set','g',23,['var':("radioId"),'value':("${instanceName}_${valueName}")],-1)
printHtmlPart(11)
invokeTag('set','g',24,['var':("title"),'value':(g.message(code:instance?.name(), default:instance?.name()))],-1)
printHtmlPart(12)
}
else {
printHtmlPart(13)
invokeTag('set','g',28,['var':("valueName"),'value':(instance?.id)],-1)
printHtmlPart(14)
invokeTag('set','g',29,['var':("radioName"),'value':(instanceName)],-1)
printHtmlPart(14)
invokeTag('set','g',30,['var':("radioId"),'value':("${instanceName}_${instance?.id}")],-1)
printHtmlPart(14)
invokeTag('set','g',31,['var':("title"),'value':(instance?.toString())],-1)
printHtmlPart(10)
}
printHtmlPart(8)
}
printHtmlPart(8)
invokeTag('set','g',34,['var':("checked"),'value':(defaultValue==valueName)],-1)
printHtmlPart(15)
expressionOut.print(liClass)
printHtmlPart(16)
if(true && (onClickRadio=="null")) {
printHtmlPart(9)
invokeTag('radio','g',38,['id':(radioId),'name':(radioName),'value':(valueName),'checked':(checked),'class':("myRadiostyle")],-1)
printHtmlPart(8)
}
else {
printHtmlPart(9)
invokeTag('radio','g',41,['id':(radioId),'name':(radioName),'value':(valueName),'checked':(checked),'class':("myRadiostyle"),'onclick':(onClickRadio)],-1)
printHtmlPart(17)
}
printHtmlPart(18)
expressionOut.print(radioId)
printHtmlPart(19)
expressionOut.print(title)
printHtmlPart(20)
expressionOut.print(title)
printHtmlPart(21)
i++
}
}
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415650922126L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
