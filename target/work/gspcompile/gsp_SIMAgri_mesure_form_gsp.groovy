import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_mesure_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/mesure/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
invokeTag('field','f',2,['property':("code"),'input-class':("form-control")],-1)
printHtmlPart(0)
invokeTag('field','f',3,['property':("name"),'input-class':("form-control")],-1)
printHtmlPart(0)
invokeTag('field','f',4,['property':("valeur"),'input-class':("form-control")],-1)
printHtmlPart(0)
invokeTag('field','f',5,['property':("isLocal"),'input-class':("check")],-1)
printHtmlPart(0)
invokeTag('field','f',6,['property':("isConvertible"),'input-class':("check")],-1)
printHtmlPart(0)
invokeTag('field','f',7,['property':("description"),'input-class':("form-control")],-1)
printHtmlPart(1)
})
invokeTag('with','f',8,['bean':("mesureInstance")],1)
printHtmlPart(2)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423824191123L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
