import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizz_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('field','f',2,['bean':("quizzInstance"),'property':("titre"),'input-class':("form-control")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('checkBox','g',6,['name':("actif"),'value':(quizzInstance.actif),'class':("form-control")],-1)
printHtmlPart(3)
})
invokeTag('field','f',8,['property':("actif"),'label':("Sondage actif ?")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('textArea','g',11,['name':("description"),'value':(quizzInstance.description),'class':("form-control")],-1)
printHtmlPart(3)
})
invokeTag('field','f',13,['property':("description")],1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1407102306996L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
