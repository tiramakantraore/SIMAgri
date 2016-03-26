import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sendsms_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sendsms/_create.gsp" }
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
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',15,['code':("create.smsToReseaux"),'class':("col-sm-6 col-md-6 offset-6")],-1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',19,['class':("alert-info")],3)
printHtmlPart(5)
}
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(7)
createTagBody(4, {->
printHtmlPart(8)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(9)
expressionOut.print(error.field)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('message','g',26,['error':(error)],-1)
printHtmlPart(12)
})
invokeTag('eachError','g',27,['bean':(smsToReseauxInstance),'var':("error")],4)
printHtmlPart(13)
})
invokeTag('alert','bootstrap',29,['class':("alert-error")],3)
printHtmlPart(5)
})
invokeTag('hasErrors','g',30,['bean':(smsToReseauxInstance)],2)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
invokeTag('render','g',33,['template':("form")],-1)
printHtmlPart(16)
invokeTag('render','g',34,['template':("/partials/btnCreer")],-1)
printHtmlPart(17)
})
invokeTag('form','g',35,['class':("well small form-horizontal"),'action':("create")],2)
printHtmlPart(18)
})
invokeTag('captureBody','sitemesh',41,[:],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422762283651L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
