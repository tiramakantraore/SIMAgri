import simagri.Sondage
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_sondage_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/sondage/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'sondage.label', default: 'Sondage'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("edit.sondage")],-1)
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
invokeTag('message','g',20,['code':("edit.sondage")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',24,['class':("alert-info")],3)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(6)
createTagBody(3, {->
printHtmlPart(9)
createTagBody(4, {->
printHtmlPart(10)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(11)
expressionOut.print(error.field)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',32,['error':(error)],-1)
printHtmlPart(14)
})
invokeTag('eachError','g',33,['bean':(sondageInstance),'var':("error")],4)
printHtmlPart(15)
})
invokeTag('alert','bootstrap',35,['class':("alert-error")],3)
printHtmlPart(7)
})
invokeTag('hasErrors','g',36,['bean':(sondageInstance)],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('hiddenField','g',39,['name':("sondageId")],-1)
printHtmlPart(17)
invokeTag('render','g',40,['template':("form")],-1)
printHtmlPart(18)
expressionOut.print(createLink(controller:'sondage', action:'edit'))
printHtmlPart(19)
invokeTag('message','g',45,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(20)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'))
printHtmlPart(21)
invokeTag('message','g',50,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(22)
})
invokeTag('form','g',53,['class':("well small form-horizontal"),'action':("edit"),'id':(sondageInstance?.id)],2)
printHtmlPart(23)
invokeTag('render','g',55,['template':("detail"),'model':(['detail':null,'i':'_clone','hidden':true])],-1)
printHtmlPart(24)
invokeTag('render','g',60,['template':("sondagejs")],-1)
printHtmlPart(2)
})
invokeTag('captureBody','sitemesh',61,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410637L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
