import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_notetemplate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_notetemplate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (noteMarcheInstance?.contenu)) {
printHtmlPart(1)
expressionOut.print(noteMarcheInstance?.titre)
printHtmlPart(2)
if(true && (noteMarcheInstance.photo)) {
printHtmlPart(3)
invokeTag('imageWithText','bill',7,['texte':("Photo"),'imageURL':(createLink(controller: 'noteMarche', action: 'showImg',params:[id:noteMarcheInstance?.id])),'imageHeight':("100")],-1)
printHtmlPart(1)
}
printHtmlPart(4)
invokeTag('display','prettytime',10,['date':(noteMarcheInstance?.date)],-1)
printHtmlPart(5)
expressionOut.print(noteMarcheInstance?.contenu?.prettify())
printHtmlPart(6)
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458762946143L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
