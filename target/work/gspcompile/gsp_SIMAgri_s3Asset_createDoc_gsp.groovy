import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_createDoc_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_createDoc.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',10,['class':("alert-info")],2)
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
invokeTag('message','g',17,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',18,['bean':(s3AssetInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',20,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',21,['bean':(s3AssetInstance)],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
expressionOut.print(s3AssetInstance?.title)
printHtmlPart(13)
})
invokeTag('field','f',30,['bean':("s3AssetInstance"),'property':("title"),'required':("true")],2)
printHtmlPart(14)
createTagBody(2, {->
printHtmlPart(15)
expressionOut.print(s3AssetInstance?.localPath)
printHtmlPart(13)
})
invokeTag('field','f',42,['bean':("s3AssetInstance"),'property':("localPath"),'required':("true")],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(17)
invokeTag('textArea','g',54,['name':("description"),'cols':("80"),'rows':("5"),'maxlength':("40"),'value':(s3AssetInstance?.description),'placeholder':("Description du document"),'class':("form-control")],-1)
printHtmlPart(18)
})
invokeTag('field','f',55,['bean':("s3AssetInstance"),'property':("description"),'required':("true")],2)
printHtmlPart(19)
})
invokeTag('form','g',63,['class':("form-horizontal"),'action':("save"),'enctype':("multipart/form-data")],1)
printHtmlPart(20)
invokeTag('message','g',67,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411553L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
