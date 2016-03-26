import org.springframework.validation.FieldError
import  simagri.Event
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_eventedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/event/edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'event.label', default: 'Evenement'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("edit.event")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(4)
createClosureForHtmlPart(5, 2)
invokeTag('link','g',14,['action':("index"),'class':("calendar")],2)
printHtmlPart(6)
createClosureForHtmlPart(7, 2)
invokeTag('link','g',15,['action':("create"),'class':("create")],2)
printHtmlPart(8)
invokeTag('message','g',28,['code':("edit.event")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',32,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(10)
createTagBody(3, {->
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
if(true && (error in FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',43,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',43,['bean':(eventInstance),'var':("error")],4)
printHtmlPart(19)
})
invokeTag('alert','bootstrap',46,['class':("alert-error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',47,['bean':(marcheInstance)],2)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('hiddenField','g',54,['name':("id"),'value':(eventInstance?.id)],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',56,['name':("version"),'value':(eventInstance?.version)],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',58,['name':("editType"),'value':("")],-1)
printHtmlPart(21)
invokeTag('render','g',60,['template':("form")],-1)
printHtmlPart(22)
invokeTag('actionSubmit','g',66,['class':("btn-flat  btn-primary ${eventInstance.isRecurring ? 'recurring' : ''}"),'action':("edit"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(23)
invokeTag('actionSubmit','g',70,['class':("btn-flat  btn-primary ${eventInstance.isRecurring ? 'recurring' : ''}"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'formnovalidate':("")],-1)
printHtmlPart(24)
})
invokeTag('form','g',71,['class':("well small form-horizontal main "),'method':("post")],2)
printHtmlPart(11)
if(true && (eventInstance.isRecurring)) {
printHtmlPart(10)
invokeTag('render','g',72,['template':("deletePopup"),'model':("model")],-1)
printHtmlPart(10)
invokeTag('render','g',72,['template':("editPopup"),'model':("model")],-1)
printHtmlPart(11)
}
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',72,[:],1)
printHtmlPart(26)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418153025325L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
