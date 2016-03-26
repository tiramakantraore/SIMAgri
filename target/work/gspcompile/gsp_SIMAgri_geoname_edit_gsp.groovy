import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_geoname_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/geoname/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(1)
expressionOut.print(createLink(uri: '/home'))
printHtmlPart(2)
invokeTag('message','g',9,['code':("default.home.label")],-1)
printHtmlPart(3)
createTagBody(1, {->
invokeTag('message','g',11,['code':("list.geoname")],-1)
})
invokeTag('link','g',11,['class':("list"),'action':("list")],1)
printHtmlPart(4)
createTagBody(1, {->
invokeTag('message','g',12,['code':("create.geoname")],-1)
})
invokeTag('link','g',12,['class':("create"),'action':("create")],1)
printHtmlPart(5)
invokeTag('message','g',17,['code':("edit.geoname")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
expressionOut.print(flash.message)
printHtmlPart(8)
}
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(12)
expressionOut.print(error.field)
printHtmlPart(13)
}
printHtmlPart(14)
invokeTag('message','g',25,['error':(error)],-1)
printHtmlPart(15)
})
invokeTag('eachError','g',26,['bean':(geonameInstance),'var':("error")],2)
printHtmlPart(16)
})
invokeTag('hasErrors','g',28,['bean':(geonameInstance)],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(17)
invokeTag('hiddenField','g',30,['name':("id"),'value':(geonameInstance?.id)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',31,['name':("version"),'value':(geonameInstance?.version)],-1)
printHtmlPart(18)
invokeTag('render','g',33,['template':("form")],-1)
printHtmlPart(19)
invokeTag('actionSubmit','g',37,['class':("save"),'action':("update"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(20)
invokeTag('actionSubmit','g',40,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'formnovalidate':(""),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(21)
})
invokeTag('form','g',42,['method':("post")],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416230092311L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
