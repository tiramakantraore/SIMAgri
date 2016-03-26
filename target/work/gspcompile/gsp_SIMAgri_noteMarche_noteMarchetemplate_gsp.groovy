import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_noteMarchetemplate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_noteMarchetemplate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (noteMarcheInstance.contenu)) {
printHtmlPart(1)
expressionOut.print(noteMarcheInstance.titre)
printHtmlPart(2)
invokeTag('display','prettytime',5,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(3)
expressionOut.print(noteMarcheInstance?.contenu?.prettify())
printHtmlPart(4)
if(true && (noteMarcheInstance?.url?.contains('youtube'))) {
printHtmlPart(5)
invokeTag('video','g',9,['videoKey':(noteMarcheInstance?.url?.split('=')?.size()>1?noteMarcheInstance?.url?.split('=')[1]:""),'height':("350px")],-1)
printHtmlPart(6)
}
printHtmlPart(7)
}
else if(true && ("${noteMarcheInstance.url} && ${noteMarcheInstance?.url?.contains('youtube')}")) {
printHtmlPart(8)
expressionOut.print(noteMarcheInstance.titre)
printHtmlPart(9)
invokeTag('video','g',17,['videoKey':(noteMarcheInstance?.url?.split('=')?.size()>1?noteMarcheInstance?.url?.split('=')[1]:""),'width':("100%"),'height':("350px")],-1)
printHtmlPart(10)
}
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1455374984908L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
