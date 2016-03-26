import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_question_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_question.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( num in (0..nbreponsesQuizz-1) ) {
printHtmlPart(1)
invokeTag('textField','g',25,['name':("rep_${num}.reponse"),'size':("60"),'required':("true"),'autocomplete':("off")],-1)
printHtmlPart(2)
invokeTag('checkBox','g',27,['name':("ok_${num}.bonneReponse"),'maxlength':("150"),'autocomplete':("off")],-1)
printHtmlPart(3)
}
printHtmlPart(4)
invokeTag('textArea','g',37,['name':("commentaireBonneReponse"),'rows':("3"),'maxlength':("250"),'class':("form-control")],-1)
printHtmlPart(5)
invokeTag('textArea','g',46,['name':("commentaireMauvaiseReponse"),'rows':("3"),'maxlength':("250"),'class':("form-control")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1412546099845L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
