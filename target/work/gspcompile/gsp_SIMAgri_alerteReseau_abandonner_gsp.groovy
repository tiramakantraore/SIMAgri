import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_abandonner_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_abandonner.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',4,['code':("abondonnerRejets.alerte")],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',22,['name':("selectedList")],-1)
printHtmlPart(2)
expressionOut.print(createLink(controller:'alerteReseau', action:'populateRejetesJSON'))
printHtmlPart(3)
expressionOut.print(createLink(controller:'alerteReseau', action:'abandonner'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'alerteReseau', action:'populateRejetesJSON'))
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442326350619L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
