import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layouts_mainHeader_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/_mainHeader.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('image','asset',5,['src':("logoSIMAgri.png"),'height':("100px")],-1)
printHtmlPart(2)
})
invokeTag('link','g',6,['controller':("home"),'action':("accueil")],1)
printHtmlPart(3)
if(true && (pageAccueilInstance?.banniere)) {
printHtmlPart(4)
expressionOut.print(createLink(controller: 'pageUtilisateur', action: 'imagePage',params:[id:pageAccueilInstance?.banniere?.id]))
printHtmlPart(5)
}
else {
printHtmlPart(6)
invokeTag('message','g',15,['code':("slogan.text"),'default':("VOUS N'ETES PAS CONNECTE")],-1)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
if(true && (user)) {
printHtmlPart(10)
invokeTag('message','g',26,['code':("connected.text"),'default':("Vous êtes connecté en tant que")],-1)
printHtmlPart(11)
expressionOut.print(user?.toString())
printHtmlPart(12)
invokeTag('message','g',29,['code':("numMobile.text"),'default':("Numéro Mobile :")],-1)
printHtmlPart(13)
expressionOut.print(user?.mobilePhone)
printHtmlPart(14)
invokeTag('message','g',30,['code':("profile.text"),'default':("Profile :")],-1)
printHtmlPart(11)
expressionOut.print(user?.role)
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (flash.password_changed)) {
printHtmlPart(17)
createTagBody(3, {->
expressionOut.print(flash.password_changed)
})
invokeTag('alert','bootstrap',31,['class':("alert-info")],3)
printHtmlPart(16)
}
printHtmlPart(16)
if(true && (flash.user_not_registred)) {
printHtmlPart(17)
createTagBody(3, {->
expressionOut.print(flash.user_not_registred)
})
invokeTag('alert','bootstrap',36,['class':("alert-info")],3)
printHtmlPart(16)
}
printHtmlPart(18)
expressionOut.print(createLink(uri: '/signOut'))
printHtmlPart(19)
invokeTag('message','g',41,['code':("logout.text"),'default':("Sortir")],-1)
printHtmlPart(20)
})
invokeTag('ifLoggedIn','sec',51,[:],1)
printHtmlPart(21)
invokeTag('render','g',52,['template':("/layouts/loginEmbedForm")],-1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456164729784L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
