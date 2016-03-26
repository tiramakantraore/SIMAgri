import simagri.Contact
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_contactedit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/contact/edit.gsp" }
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
invokeTag('message','g',7,['code':("edit.contact")],-1)
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
invokeTag('message','g',15,['code':("title.contact")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',19,['code':("list.contact")],-1)
printHtmlPart(6)
})
invokeTag('link','g',20,['class':("list"),'action':("list")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('message','g',25,['code':("create.contact")],-1)
printHtmlPart(6)
})
invokeTag('link','g',26,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',35,['code':("edit.contact")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',39,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',46,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',47,['bean':(contactInstance),'var':("error")],4)
printHtmlPart(19)
})
invokeTag('alert','bootstrap',49,['class':("alert-error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',50,['bean':(contactInstance)],2)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('hiddenField','g',54,['name':("version"),'value':(contactInstance?.version)],-1)
printHtmlPart(22)
invokeTag('render','g',56,['template':("form")],-1)
printHtmlPart(23)
expressionOut.print(createLink(controller:'contact', action:'edit'))
printHtmlPart(24)
invokeTag('message','g',60,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(25)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'))
printHtmlPart(26)
invokeTag('message','g',64,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(27)
})
invokeTag('form','g',68,['class':("well small form-horizontal"),'action':("edit"),'id':(contactInstance?.id)],2)
printHtmlPart(28)
})
invokeTag('captureBody','sitemesh',74,[:],1)
printHtmlPart(29)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410534L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
