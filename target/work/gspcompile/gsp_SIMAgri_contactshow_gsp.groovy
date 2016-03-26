import simagri.Contact
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_contactshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/contact/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'contact.label', default: 'Contact'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("show.contact")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("title.contact")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.contact")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',26,['code':("create.contact")],-1)
printHtmlPart(7)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',36,['code':("show.contact")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (contactInstance?.nom)) {
printHtmlPart(15)
invokeTag('message','g',47,['code':("contact.nom.label"),'default':("Nom")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',51,['bean':(contactInstance),'field':("nom")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (contactInstance?.email)) {
printHtmlPart(15)
invokeTag('message','g',60,['code':("contact.email.label"),'default':("Email")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',64,['bean':(contactInstance),'field':("email")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (contactInstance?.telephone)) {
printHtmlPart(15)
invokeTag('message','g',73,['code':("contact.telephone.label"),'default':("Telephone")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',77,['bean':(contactInstance),'field':("telephone")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (contactInstance?.sujet)) {
printHtmlPart(15)
invokeTag('message','g',86,['code':("contact.sujet.label"),'default':("Sujet")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',90,['bean':(contactInstance),'field':("sujet")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (contactInstance?.message)) {
printHtmlPart(15)
invokeTag('message','g',99,['code':("contact.message.label"),'default':("Message")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',103,['bean':(contactInstance),'field':("message")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (contactInstance?.publier)) {
printHtmlPart(19)
invokeTag('message','g',112,['code':("contact.publier.label"),'default':("Publier")],-1)
printHtmlPart(20)
invokeTag('formatBoolean','g',116,['boolean':(contactInstance?.publier)],-1)
printHtmlPart(21)
}
printHtmlPart(22)
createTagBody(2, {->
printHtmlPart(23)
createTagBody(3, {->
printHtmlPart(24)
invokeTag('message','g',128,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(25)
})
invokeTag('link','g',129,['class':("btn"),'action':("edit"),'id':(contactInstance?.id)],3)
printHtmlPart(26)
})
invokeTag('form','g',132,[:],2)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',137,[:],1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069511462L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
