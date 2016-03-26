import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_base_checklist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/base/_checklist.gsp" }
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
expressionOut.print(containerClass)
printHtmlPart(3)
if(true && (referenceCollection?.size()>0)) {
printHtmlPart(4)
loop:{
int i = 0
for( instance in (referenceCollection) ) {
printHtmlPart(5)
expressionOut.print(liClass)
printHtmlPart(6)
if(true && (onClickCheck=="null")) {
printHtmlPart(7)
invokeTag('checkBox','g',10,['id':("${instanceName}_${instance?.id}"),'name':("${instanceName}_${instance?.id}"),'value':(instance?.id),'checked':(checked),'class':("myCheckBoxstyle icon-check")],-1)
printHtmlPart(8)
}
else {
printHtmlPart(7)
invokeTag('checkBox','g',13,['id':("${instanceName}_${instance?.id}"),'name':("${instanceName}_${instance?.id}"),'value':(instance?.id),'checked':(checked),'class':("myCheckBoxstyle icon-check"),'onclick':(onClickCheck)],-1)
printHtmlPart(9)
}
printHtmlPart(10)
expressionOut.print(instanceName)
printHtmlPart(11)
expressionOut.print(instance?.id)
printHtmlPart(12)
expressionOut.print(instance?.toString())
printHtmlPart(13)
expressionOut.print(instance?.toString())
printHtmlPart(14)
i++
}
}
printHtmlPart(15)
}
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423793548839L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
