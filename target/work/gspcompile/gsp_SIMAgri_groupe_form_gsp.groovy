import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_groupe_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/groupe/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(reseauInstance?.nom)
printHtmlPart(2)
})
invokeTag('field','f',9,['bean':("reseauInstance"),'property':("nom"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(reseauInstance?.email)
printHtmlPart(2)
})
invokeTag('field','f',23,['bean':("reseauInstance"),'property':("email"),'required':("true")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(reseauInstance?.siteWeb)
printHtmlPart(2)
})
invokeTag('field','f',36,['bean':("reseauInstance"),'property':("siteWeb"),'required':("true")],1)
printHtmlPart(7)
invokeTag('field','f',46,['bean':("reseauInstance"),'property':("active"),'input-class':("check"),'input-autocomplete':("off")],-1)
printHtmlPart(7)
invokeTag('field','f',56,['bean':("reseauInstance"),'property':("estReseau"),'input-class':("check"),'input-autocomplete':("off")],-1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448078626202L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
