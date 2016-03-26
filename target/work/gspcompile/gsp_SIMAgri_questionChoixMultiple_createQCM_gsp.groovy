import org.springframework.validation.FieldError
import  simagri.QuestionChoixMultiple
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_questionChoixMultiple_createQCM_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/questionChoixMultiple/_createQCM.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(questionChoixMultipleInstance?.question)
printHtmlPart(1)
invokeTag('textArea','g',28,['name':("commentaireBonneReponse"),'rows':("3"),'maxlength':("250"),'value':(questionChoixMultipleInstance?.commentaireBonneReponse),'class':("form-control")],-1)
printHtmlPart(2)
invokeTag('textArea','g',36,['name':("commentaireMauvaiseReponse"),'rows':("3"),'maxlength':("250"),'value':(questionChoixMultipleInstance?.commentaireMauvaiseReponse),'class':("form-control")],-1)
printHtmlPart(3)
invokeTag('render','g',37,['template':("/questionChoixMultiple/option"),'model':(['option':null,'i':'-1','hidden':true])],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1411975918176L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
