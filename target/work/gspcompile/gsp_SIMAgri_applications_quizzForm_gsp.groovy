import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_quizzForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_quizzForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',6,['code':("creationQuizz")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('textArea','g',23,['name':("description"),'id':("description"),'rows':("4"),'maxlength':("250"),'class':("form-control")],-1)
printHtmlPart(3)
invokeTag('block','dynamic',29,['itemId':("questionId"),'min':("1"),'max':("20"),'addBtnId':("addQuestion"),'removeBtnLabel':("Supprimer question"),'removeOffset':("-8"),'limitReachedMsg':("Nombre limite de questions atteinte"),'removeBtnId':("deleteQuestion"),'template':("question"),'tabprefix':("questions")],-1)
printHtmlPart(4)
invokeTag('deferredScripts','asset',31,[:],-1)
printHtmlPart(5)
})
invokeTag('form','g',32,['class':("form-horizontal"),'role':("form"),'name':("quizzForm")],1)
printHtmlPart(6)
invokeTag('message','g',39,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(7)
invokeTag('render','g',42,['template':("/applications/quizjs")],-1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411620L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
