import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_smsToReseauxshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smsToReseaux/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'smsToReseaux.label', default: 'SmsToReseaux'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("show.smsToReseaux")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',15,['code':("title.smsToReseaux")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',19,['code':("list.smsToReseaux")],-1)
printHtmlPart(7)
})
invokeTag('link','g',20,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',25,['code':("create.smsToReseaux")],-1)
printHtmlPart(7)
})
invokeTag('link','g',26,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',35,['code':("show.smsToReseaux")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',39,['class':("alert-info")],3)
printHtmlPart(13)
}
printHtmlPart(14)
if(true && (smsToReseauxInstance?.reseaux)) {
printHtmlPart(15)
invokeTag('message','g',45,['code':("smsToReseaux.reseaux.label"),'default':("Reseaux")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',47,['bean':(smsToReseauxInstance),'field':("reseaux")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (smsToReseauxInstance?.yourTextMessage)) {
printHtmlPart(15)
invokeTag('message','g',53,['code':("smsToReseaux.yourTextMessage.label"),'default':("Your Text Message")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',55,['bean':(smsToReseauxInstance),'field':("yourTextMessage")],-1)
printHtmlPart(17)
}
printHtmlPart(19)
createTagBody(2, {->
printHtmlPart(20)
createTagBody(3, {->
printHtmlPart(21)
invokeTag('message','g',66,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(22)
})
invokeTag('link','g',67,['class':("btn"),'action':("edit"),'id':(smsToReseauxInstance?.id)],3)
printHtmlPart(23)
})
invokeTag('form','g',70,[:],2)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',75,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069512633L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
