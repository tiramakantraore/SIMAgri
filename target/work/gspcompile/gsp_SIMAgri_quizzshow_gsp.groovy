import simagri.Quizz
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizzshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'quizz.label', default: 'Quizz'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.quizz")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.quizz")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.quizz")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',26,['code':("create.quizz")],-1)
printHtmlPart(7)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',36,['code':("show.quizz")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (quizzInstance?.titre)) {
printHtmlPart(14)
invokeTag('message','g',46,['code':("quizz.titre.label"),'default':("Titre")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',48,['bean':(quizzInstance),'field':("titre")],-1)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (quizzInstance?.description)) {
printHtmlPart(14)
invokeTag('message','g',54,['code':("quizz.description.label"),'default':("Description")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',56,['bean':(quizzInstance),'field':("description")],-1)
printHtmlPart(16)
}
printHtmlPart(18)
if(true && (quizzInstance?.questions)) {
printHtmlPart(19)
invokeTag('message','g',61,['code':("quizz.questions.label"),'default':("Questions")],-1)
printHtmlPart(20)
for( p in (quizzInstance?.questions) ) {
printHtmlPart(21)
createTagBody(4, {->
expressionOut.print(p?.encodeAsHTML())
})
invokeTag('link','g',64,['controller':("questionChoixMultiple"),'action':("show"),'id':(p.id)],4)
printHtmlPart(22)
}
printHtmlPart(23)
}
printHtmlPart(24)
createTagBody(2, {->
printHtmlPart(25)
invokeTag('hiddenField','g',72,['name':("id"),'value':(quizzInstance?.id)],-1)
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(27)
invokeTag('message','g',76,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(28)
})
invokeTag('link','g',77,['class':("btn"),'action':("edit"),'id':(quizzInstance?.id)],3)
printHtmlPart(29)
})
invokeTag('form','g',80,[:],2)
printHtmlPart(30)
})
invokeTag('captureBody','sitemesh',85,[:],1)
printHtmlPart(31)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069511582L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
