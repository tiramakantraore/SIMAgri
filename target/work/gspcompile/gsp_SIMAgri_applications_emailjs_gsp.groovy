import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_emailjs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_emailjs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(latitude)
printHtmlPart(1)
expressionOut.print(longitude)
printHtmlPart(2)
expressionOut.print(zoom)
printHtmlPart(3)
expressionOut.print(createLink(controller:'autoComplete', action:'updateDestinatairesFromReseaux'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'autoComplete', action:'searchDestinataires'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'reseau', action:'getSimpleTree'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'smsToReseaux', action:'sendEmailByService'))
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442326645499L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
