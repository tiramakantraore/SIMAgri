import com.dbconfig.ConfigProperty
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_configProperty_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/configProperty/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createClosureForHtmlPart(1, 1)
invokeTag('link','g',7,['class':(""),'controller':("configProperty"),'action':("list")],1)
printHtmlPart(2)
createClosureForHtmlPart(3, 1)
invokeTag('link','g',11,['class':(""),'controller':("configProperty"),'action':("create")],1)
printHtmlPart(4)
if(true && (flash.message)) {
printHtmlPart(5)
expressionOut.print(flash.message)
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(10)
expressionOut.print(error.field)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',21,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',21,['bean':(configPropertyInstance),'var':("error")],2)
printHtmlPart(14)
})
invokeTag('hasErrors','g',21,['bean':(configPropertyInstance)],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(15)
invokeTag('hiddenField','g',25,['name':("id"),'value':(configPropertyInstance?.id)],-1)
printHtmlPart(15)
invokeTag('hiddenField','g',26,['name':("version"),'value':(configPropertyInstance?.version)],-1)
printHtmlPart(15)
invokeTag('render','g',26,['template':("form")],-1)
printHtmlPart(16)
invokeTag('actionSubmit','g',30,['class':("save"),'action':("update"),'value':(message(code: 'default.button.update.label', default: 'Update'))],-1)
printHtmlPart(17)
invokeTag('actionSubmit','g',35,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'formnovalidate':(""),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(18)
})
invokeTag('form','g',35,['method':("post"),'class':("columns")],1)
printHtmlPart(19)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418410123194L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
