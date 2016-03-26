import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_signupform_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_signupform.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(utilisateurInstance?.mobilePhone)
printHtmlPart(2)
expressionOut.print(message(code: 'login.placeHolder.label', default: 'Login'))
printHtmlPart(3)
})
invokeTag('field','f',16,['bean':("utilisateurInstance"),'property':("mobilePhone"),'required':("true")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(utilisateurInstance?.lastName)
printHtmlPart(6)
})
invokeTag('field','f',22,['bean':("utilisateurInstance"),'property':("lastName")],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
expressionOut.print(utilisateurInstance?.firstName)
printHtmlPart(9)
})
invokeTag('field','f',35,['bean':("utilisateurInstance"),'property':("firstName")],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('select','g',49,['name':("gender"),'from':(utilisateurInstance.constraints.gender.inList),'value':(utilisateurInstance?.gender),'valueMessagePrefix':("utilisateur.gender"),'class':("form-control")],-1)
printHtmlPart(12)
})
invokeTag('field','f',50,['bean':("utilisateurInstance"),'property':("gender")],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('countrySelect','g',66,['name':("country"),'value':(utilisateurInstance?.country),'default':("grails.defaultCountry"),'from':(['bfa','ben','civ', 'gha','gnb', 'mli','ner','sen','tgo']),'class':("form-control"),'noSelection':(['': 'Choisissez un pays'])],-1)
printHtmlPart(15)
})
invokeTag('field','f',68,['bean':("utilisateurInstance"),'property':("country")],1)
printHtmlPart(16)
createTagBody(1, {->
printHtmlPart(17)
expressionOut.print(utilisateurInstance?.email)
printHtmlPart(18)
})
invokeTag('field','f',75,['bean':("utilisateurInstance"),'property':("email")],1)
printHtmlPart(19)
createClosureForHtmlPart(20, 1)
invokeTag('field','f',87,['bean':("utilisateurInstance"),'property':("password")],1)
printHtmlPart(21)
createClosureForHtmlPart(22, 1)
invokeTag('field','f',104,['bean':("utilisateurInstance"),'property':("password"),'label':(g.message(code:"confirmPassword.text"))],1)
printHtmlPart(23)
invokeTag('message','g',112,['code':("haveAlreadyAnAccount.text"),'default':("Already have an account? ")],-1)
printHtmlPart(24)
expressionOut.print(createLink(uri: '/signIn'))
printHtmlPart(25)
invokeTag('message','g',113,['code':("cliquezici.text"),'default':("Sign In")],-1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418316254611L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
