import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(s3AssetInstance?.title)
printHtmlPart(2)
})
invokeTag('field','f',7,['bean':("s3AssetInstance"),'property':("title"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(s3AssetInstance?.localPath)
printHtmlPart(2)
})
invokeTag('field','f',19,['bean':("s3AssetInstance"),'property':("localPath"),'required':("true")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('textArea','g',31,['name':("description"),'cols':("80"),'rows':("5"),'maxlength':("40"),'value':(s3AssetInstance?.description),'placeholder':("Description du document"),'class':("form-control")],-1)
printHtmlPart(7)
})
invokeTag('field','f',32,['bean':("s3AssetInstance"),'property':("description"),'required':("true")],1)
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1412563079953L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
