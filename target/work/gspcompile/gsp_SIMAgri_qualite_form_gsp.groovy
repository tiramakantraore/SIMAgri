import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_qualite_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/qualite/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
invokeTag('field','f',2,['property':("code"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(0)
invokeTag('field','f',3,['property':("nom"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(0)
invokeTag('field','f',4,['property':("description"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(1)
})
invokeTag('with','f',5,['bean':("qualiteInstance")],1)
printHtmlPart(1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423528948071L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
