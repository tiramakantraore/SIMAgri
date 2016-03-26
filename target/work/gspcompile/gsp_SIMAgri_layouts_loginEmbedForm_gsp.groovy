import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_loginEmbedForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_loginEmbedForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
createTagBody(1, {->
printHtmlPart(0)
createTagBody(2, {->
printHtmlPart(1)
expressionOut.print(session.codePlaceHolderLogin)
printHtmlPart(2)
if(true && (flash.messageEchecLogin)) {
printHtmlPart(3)
createTagBody(4, {->
expressionOut.print(flash.messageEchecLogin)
})
invokeTag('alert','bootstrap',17,[:],4)
printHtmlPart(4)
expressionOut.print(createLink(uri: '/resetPassword'))
printHtmlPart(5)
invokeTag('message','g',19,['code':("passwordForgot.text"),'default':("Forgot password?")],-1)
printHtmlPart(6)
}
printHtmlPart(7)
invokeTag('message','g',25,['code':("springSecurity.login.button"),'default':("Entrer")],-1)
printHtmlPart(8)
expressionOut.print(createLink(uri: '/signUp'))
printHtmlPart(9)
invokeTag('message','g',31,['code':("creer_un_compte.text"),'default':("S'Inscrire")],-1)
printHtmlPart(10)
invokeTag('message','g',38,['code':("springSecurity.login.remember.me.label")],-1)
printHtmlPart(11)
expressionOut.print(rememberMeParameter)
printHtmlPart(12)
if(true && (hasCookie)) {
printHtmlPart(13)
}
printHtmlPart(14)
})
invokeTag('form','g',41,['controller':("login"),'action':("login"),'role':("form"),'name':("auth"),'method':("post"),'accept-charset':("UTF-8"),'autocomplete':("off")],2)
printHtmlPart(15)
})
invokeTag('ifNotLoggedIn','sec',41,[:],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458167644912L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
