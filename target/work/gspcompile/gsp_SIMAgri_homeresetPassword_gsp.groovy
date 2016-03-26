import org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_homeresetPassword_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/resetPassword.gsp" }
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
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("indexLayout")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',12,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',21,['code':("reinitialisationpwd.text"),'default':("Reset password")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('hiddenField','g',24,['name':("parentText")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',24,['name':("parentIdSaved")],-1)
printHtmlPart(7)
if(true && (flash.messageErreur)) {
printHtmlPart(9)
createTagBody(4, {->
expressionOut.print(flash.messageErreur)
})
invokeTag('alert','bootstrap',26,['class':("alert-info")],4)
printHtmlPart(7)
}
printHtmlPart(10)
invokeTag('render','g',28,['template':("resetPwdForm")],-1)
printHtmlPart(11)
invokeTag('message','g',32,['code':("resetBtn.text"),'default':("Reset")],-1)
printHtmlPart(12)
})
invokeTag('form','g',33,['controller':("login"),'action':("resetPassword"),'name':("resetPassword"),'method':("post"),'accept-charset':("UTF-8")],2)
printHtmlPart(13)
expressionOut.print(createLink(controller:"home", action:'findUserByMobile'))
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',68,[:],1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442327865419L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
