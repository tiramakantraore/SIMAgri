import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_homesignup_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/signup.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
invokeTag('captureTitle','sitemesh',9,[:],-1)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("appsLayout")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',37,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',47,['code':("createAnAccount.text"),'default':("Create an account")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('hiddenField','g',48,['name':("parentText")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',49,['name':("parentIdSaved")],-1)
printHtmlPart(7)
if(true && (flash.messageErreur)) {
printHtmlPart(9)
createTagBody(4, {->
expressionOut.print(flash.messageErreur)
})
invokeTag('alert','bootstrap',51,['class':("alert-info")],4)
printHtmlPart(7)
}
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(9)
createTagBody(4, {->
printHtmlPart(11)
createTagBody(5, {->
printHtmlPart(12)
if(true && (error in FieldError)) {
printHtmlPart(13)
expressionOut.print(error.field)
printHtmlPart(14)
}
printHtmlPart(15)
invokeTag('message','g',59,['error':(error)],-1)
printHtmlPart(16)
})
invokeTag('eachError','g',60,['bean':(utilisateurInstance),'var':("error")],5)
printHtmlPart(17)
})
invokeTag('alert','bootstrap',60,['class':("alert-error")],4)
printHtmlPart(7)
})
invokeTag('hasErrors','g',60,['bean':(utilisateurInstance)],3)
printHtmlPart(18)
invokeTag('hiddenField','g',68,['name':("groupsId")],-1)
printHtmlPart(19)
invokeTag('message','g',70,['code':("choisir.reseau")],-1)
printHtmlPart(20)
invokeTag('render','g',78,['template':("signupform")],-1)
printHtmlPart(21)
invokeTag('message','g',83,['code':("signUp.text"),'default':("Sign Up")],-1)
printHtmlPart(22)
invokeTag('message','g',86,['code':("reset.text"),'default':("reset")],-1)
printHtmlPart(23)
})
invokeTag('form','g',87,['action':("signup"),'name':("signupForm")],2)
printHtmlPart(24)
expressionOut.print(createLink(controller:'reseau', action:'getSignUpTree'))
printHtmlPart(25)
expressionOut.print(createLink(controller:"home", action:'findUserByMobile'))
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',203,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456841068040L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
