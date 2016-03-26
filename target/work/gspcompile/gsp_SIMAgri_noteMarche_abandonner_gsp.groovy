import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_abandonner_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_abandonner.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',4,['code':("abondonnerRejets.Notes")],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',22,['name':("selectedList")],-1)
printHtmlPart(2)
invokeTag('message','g',27,['code':("abondonnerRejets.Notes")],-1)
printHtmlPart(3)
expressionOut.print(createLink(controller:'noteMarche', action:'populateRejetes'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'noteMarche', action:'abandonner'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'noteMarche', action:'populateRejetes'))
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458912619490L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
