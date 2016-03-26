import simagri.QuestionChoixMultiple
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_questionChoixMultiple_formEdit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/questionChoixMultiple/_formEdit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'quiz', 'error'))
printHtmlPart(1)
invokeTag('message','g',6,['code':("questionChoixMultiple.quiz.label"),'default':("Quiz")],-1)
printHtmlPart(2)
invokeTag('select','g',10,['id':("quiz"),'name':("quiz.id"),'from':(simagri.Quizz.list()),'optionKey':("id"),'required':(""),'value':(questionChoixMultipleInstance?.quiz?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'question', 'error'))
printHtmlPart(4)
invokeTag('message','g',17,['code':("questionChoixMultiple.question.label"),'default':("Question")],-1)
printHtmlPart(2)
invokeTag('textField','g',20,['name':("question"),'maxlength':("150"),'required':(""),'value':(questionChoixMultipleInstance?.question),'class':("col-sm-7 col-md-7 offset-5"),'autocomplete':("off")],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'actif', 'error'))
printHtmlPart(6)
invokeTag('message','g',28,['code':("questionChoixMultiple.actif.label"),'default':("Actif")],-1)
printHtmlPart(7)
invokeTag('checkBox','g',31,['name':("actif"),'value':(questionChoixMultipleInstance?.actif)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'commentaireBonneReponse', 'error'))
printHtmlPart(8)
invokeTag('message','g',38,['code':("questionChoixMultiple.commentaireBonneReponse.label"),'default':("Commentaire Bonne Reponse")],-1)
printHtmlPart(7)
invokeTag('textArea','g',42,['name':("commentaireBonneReponse"),'cols':("40"),'rows':("5"),'maxlength':("250"),'value':(questionChoixMultipleInstance?.commentaireBonneReponse)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'commentaireMauvaiseReponse', 'error'))
printHtmlPart(9)
invokeTag('message','g',50,['code':("questionChoixMultiple.commentaireMauvaiseReponse.label"),'default':("Commentaire Mauvaise Reponse")],-1)
printHtmlPart(7)
invokeTag('textArea','g',54,['name':("commentaireMauvaiseReponse"),'cols':("40"),'rows':("5"),'maxlength':("250"),'value':(questionChoixMultipleInstance?.commentaireMauvaiseReponse)],-1)
printHtmlPart(10)
expressionOut.print(hasErrors(bean: questionChoixMultipleInstance, field: 'options', 'error'))
printHtmlPart(11)
invokeTag('message','g',60,['code':("questionChoixMultipleInstance.options.label"),'default':("Options")],-1)
printHtmlPart(12)
invokeTag('render','g',62,['template':("optionsEdit"),'model':(['questionChoixMultipleInstance':questionChoixMultipleInstance])],-1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1407035061398L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
