import simagri.Contact
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_contact_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/contact/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',4,['property':("nom")],-1)
printHtmlPart(1)
invokeTag('field','f',5,['property':("email")],-1)
printHtmlPart(1)
invokeTag('field','f',6,['property':("telephone")],-1)
printHtmlPart(1)
invokeTag('field','f',7,['property':("sujet")],-1)
printHtmlPart(1)
invokeTag('field','f',8,['property':("message")],-1)
printHtmlPart(1)
invokeTag('field','f',9,['property':("publier")],-1)
printHtmlPart(2)
})
invokeTag('with','f',10,['bean':("contactInstance")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1389417456503L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
