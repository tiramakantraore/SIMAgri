import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([beanName:'s3Asset',isEdit:true])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',6,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',13,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',14,['bean':(s3AssetInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',16,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',17,['bean':(s3AssetInstance)],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(11)
invokeTag('render','g',21,['template':("form")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',23,['name':("version"),'value':(s3AssetInstance?.version)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',24,['name':("id"),'value':(s3AssetInstance?.id)],-1)
printHtmlPart(14)
expressionOut.print(createLink(controller:'s3Asset', action:'edit'))
printHtmlPart(15)
invokeTag('message','g',28,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(16)
expressionOut.print(createLink(controller:'s3Asset', action:'delete'))
printHtmlPart(17)
invokeTag('message','g',33,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(18)
})
invokeTag('form','g',36,['class':("well small form-horizontal"),'action':("save"),'enctype':("multipart/form-data")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411355L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
