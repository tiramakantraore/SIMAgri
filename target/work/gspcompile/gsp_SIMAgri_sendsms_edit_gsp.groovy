import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sendsms_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sendsms/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('message','g',10,['code':("list.smsToReseaux")],-1)
printHtmlPart(2)
})
invokeTag('remoteLink','g',11,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("list"),'update':("listContent"),'method':("GET"),'class':("list")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',16,['code':("create.smsToReseaux")],-1)
printHtmlPart(2)
})
invokeTag('remoteLink','g',17,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],1)
printHtmlPart(5)
invokeTag('message','g',25,['code':("edit.smsToReseaux")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',32,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(10)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(11)
expressionOut.print(error.field)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',39,['error':(error)],-1)
printHtmlPart(14)
})
invokeTag('eachError','g',40,['bean':(smsToReseauxInstance),'var':("error")],3)
printHtmlPart(15)
})
invokeTag('alert','bootstrap',42,['class':("alert-error")],2)
printHtmlPart(7)
})
invokeTag('hasErrors','g',43,['bean':(smsToReseauxInstance)],1)
printHtmlPart(16)
createTagBody(1, {->
printHtmlPart(17)
invokeTag('hiddenField','g',46,['name':("version"),'value':(smsToReseauxInstance?.version)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',47,['name':("id"),'value':(smsToReseauxInstance?.id)],-1)
printHtmlPart(18)
invokeTag('render','g',48,['template':("form")],-1)
printHtmlPart(2)
invokeTag('render','g',49,['template':("/partials/btnEdit")],-1)
printHtmlPart(19)
})
invokeTag('form','g',50,['class':("well small form-horizontal"),'action':("edit")],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418408540339L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
