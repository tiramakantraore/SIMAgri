import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_userjs_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_userjs.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(createLink(controller:'utilisateur', action:'editProfile'))
printHtmlPart(1)
expressionOut.print(createLink(controller:'utilisateur', action:'edit'))
printHtmlPart(2)
expressionOut.print(createLink(controller:'utilisateur', action:'create'))
printHtmlPart(3)
expressionOut.print(createLink(controller:'autoComplete', action:'updateProduitsFromCategories'))
printHtmlPart(4)
expressionOut.print(createLink(controller:'autoComplete', action:'updateMarchesFromRegion'))
printHtmlPart(5)
expressionOut.print(createLink(controller:'autoComplete', action:'updateMarchesFromRegion'))
printHtmlPart(6)
expressionOut.print(createLink(controller:'autoComplete', action:'marcheJSON'))
printHtmlPart(7)
expressionOut.print(createLink(controller:'autoComplete', action:'produitJSON'))
printHtmlPart(8)
expressionOut.print(createLink(controller:'utilisateur', action:'updateByJSON'))
printHtmlPart(9)
expressionOut.print(createLink(controller:'reseau', action:'getSimpleTree'))
printHtmlPart(10)
expressionOut.print(createLink(controller:"home", action:'findUserByMobile'))
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650455L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
