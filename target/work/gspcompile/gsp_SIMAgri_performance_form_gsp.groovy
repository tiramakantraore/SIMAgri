import simagri.Performance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_performance_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/performance/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',6,['property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',7,['property':("nbOffre"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',8,['property':("nbPrix"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',9,['property':("nbAlerte"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',10,['property':("nbContact"),'input-class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('with','f',11,['bean':("performanceInstance")],1)
printHtmlPart(3)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416957240889L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
