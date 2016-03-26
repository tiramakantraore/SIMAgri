import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_createDoc_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_createDoc.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',9,['code':("create.s3Asset")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',13,['class':("alert-info")],3)
printHtmlPart(2)
}
printHtmlPart(4)
invokeTag('field','f',20,['bean':("s3AssetInstance"),'property':("title"),'required':("true"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
expressionOut.print(property)
printHtmlPart(7)
expressionOut.print(it.value)
printHtmlPart(8)
})
invokeTag('field','f',32,['bean':("s3AssetInstance"),'property':("localPath"),'required':("true"),'input-class':("form-control"),'input-autocomplete':("off")],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
invokeTag('textArea','g',48,['name':(property),'cols':("80"),'rows':("5"),'maxlength':("255"),'value':(it.value),'placeholder':("Description du document"),'class':("form-control")],-1)
printHtmlPart(11)
})
invokeTag('field','f',48,['bean':("s3AssetInstance"),'property':("description"),'required':("true")],2)
printHtmlPart(12)
invokeTag('render','g',54,['template':("/partials/asyncBtn"),'model':([theactionName:'saveDoc',btnClass:'btn-primary',btnName:'default.button.envoyer.label',inputField:'localPath',successMessage:'Le téléchargement  a réussi'])],-1)
printHtmlPart(13)
})
invokeTag('form','g',54,['class':("form-horizontal"),'action':("saveDoc"),'method':("post"),'enctype':("multipart/form-data")],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448173012610L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
