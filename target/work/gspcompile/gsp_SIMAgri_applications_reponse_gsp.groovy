import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_reponse_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_reponse.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('textField','g',14,['name':("options[${i}]?.answerOption"),'maxlength':("150"),'required':("true"),'value':(option?.answerOption),'autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('checkBox','g',16,['name':("options[${i}]?.correctOption"),'maxlength':("150"),'value':(option?.correctOption),'autocomplete':("off")],-1)
printHtmlPart(2)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1412101183462L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
