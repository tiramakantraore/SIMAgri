import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_smsToReseauxcreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/smsToReseaux/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'smsToReseaux.label', default: 'SmsToReseaux'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("create.smsToReseaux")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',23,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',27,['code':("create.smsToReseaux"),'class':("col-sm-6 col-md-6 offset-6")],-1)
printHtmlPart(5)
invokeTag('message','g',28,['code':("smsToReseaux.selectReseaux"),'class':("col-sm-6 col-md-6 offset-6")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',31,['class':("alert-info")],3)
printHtmlPart(8)
}
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(10)
createTagBody(4, {->
printHtmlPart(11)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(12)
expressionOut.print(error.field)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('message','g',38,['error':(error)],-1)
printHtmlPart(15)
})
invokeTag('eachError','g',39,['bean':(smsToReseauxInstance),'var':("error")],4)
printHtmlPart(16)
})
invokeTag('alert','bootstrap',41,['class':("alert-error")],3)
printHtmlPart(8)
})
invokeTag('hasErrors','g',42,['bean':(smsToReseauxInstance)],2)
printHtmlPart(17)
invokeTag('message','g',53,['code':("choisir.reseau")],-1)
printHtmlPart(18)
createTagBody(2, {->
printHtmlPart(19)
invokeTag('render','g',76,['template':("form")],-1)
printHtmlPart(19)
invokeTag('hiddenField','g',76,['name':("ReseauxIds")],-1)
printHtmlPart(20)
invokeTag('message','g',81,['code':("sendSMS.button.label"),'default':("Envoyer")],-1)
printHtmlPart(21)
})
invokeTag('form','g',83,['name':("create"),'class':("well small form-horizontal"),'action':("create")],2)
printHtmlPart(22)
})
invokeTag('captureBody','sitemesh',83,[:],1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423435079921L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
