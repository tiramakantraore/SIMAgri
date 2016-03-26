import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_info_infostemplate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/info/_infostemplate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (infoInstance?.contenu)) {
printHtmlPart(1)
invokeTag('set','g',4,['var':("infoURL"),'value':(infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id ))],-1)
printHtmlPart(2)
expressionOut.print(infoURL)
printHtmlPart(3)
expressionOut.print(infoInstance?.titre)
printHtmlPart(4)
invokeTag('display','prettytime',10,['date':(infoInstance?.date)],-1)
printHtmlPart(5)
expressionOut.print(infoInstance?.contenu?.prettify())
printHtmlPart(1)
if(true && (infoInstance?.url?.contains('youtube'))) {
printHtmlPart(6)
invokeTag('video','g',14,['videoKey':(infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""),'height':("350px")],-1)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('set','g',17,['var':("infoURL"),'value':(infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id ))],-1)
printHtmlPart(9)
expressionOut.print(infoURL)
printHtmlPart(10)
}
else if(true && ("${infoInstance?.url} && ${infoInstance?.url?.contains('youtube')}")) {
printHtmlPart(11)
invokeTag('set','g',25,['var':("infoURL"),'value':(infoInstance?.url?:g.createLink(controller:"info", action:"showPublic", id:infoInstance.id ))],-1)
printHtmlPart(2)
expressionOut.print(infoURL)
printHtmlPart(3)
expressionOut.print(infoInstance?.titre)
printHtmlPart(12)
if(true && (infoInstance?.url?.contains('youtube'))) {
printHtmlPart(6)
invokeTag('video','g',33,['videoKey':(infoInstance?.url?.split('=')?.size()>1?infoInstance?.url?.split('=')[1]:""),'height':("350px")],-1)
printHtmlPart(7)
}
printHtmlPart(13)
}
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458682405150L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
