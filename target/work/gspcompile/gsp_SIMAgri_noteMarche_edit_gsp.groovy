import org.springframework.validation.FieldError
import  simagri.NoteMarche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_noteMarche_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/noteMarche/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('resources','ckeditor',3,[:],-1)
printHtmlPart(1)
invokeTag('message','g',9,['code':("edit.noteMarche")],-1)
printHtmlPart(2)
if(true && (flash.message)) {
printHtmlPart(3)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',13,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(5)
createTagBody(3, {->
printHtmlPart(6)
if(true && (error in FieldError)) {
printHtmlPart(7)
expressionOut.print(error.field)
printHtmlPart(8)
}
printHtmlPart(9)
invokeTag('message','g',20,['error':(error)],-1)
printHtmlPart(10)
})
invokeTag('eachError','g',21,['bean':(noteMarcheInstance),'var':("error")],3)
printHtmlPart(11)
})
invokeTag('alert','bootstrap',23,['class':("alert-error")],2)
printHtmlPart(3)
})
invokeTag('hasErrors','g',24,['bean':(noteMarcheInstance)],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(13)
invokeTag('hiddenField','g',27,['name':("version"),'value':(noteMarcheInstance?.version)],-1)
printHtmlPart(14)
invokeTag('hiddenField','g',28,['name':("id"),'value':(noteMarcheInstance?.id)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',29,['name':("update"),'value':(update)],-1)
printHtmlPart(15)
invokeTag('render','g',31,['template':("/partials/btnEdit"),'model':([update:update])],-1)
printHtmlPart(16)
invokeTag('render','g',32,['template':("formEdit")],-1)
printHtmlPart(16)
invokeTag('render','g',33,['template':("/info/infojs")],-1)
printHtmlPart(14)
invokeTag('render','g',34,['template':("/partials/btnEdit"),'model':([update:update])],-1)
printHtmlPart(17)
})
invokeTag('form','g',35,['class':("well small form-horizontal"),'controller':("noteMarche"),'action':("edit")],1)
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456718165885L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
