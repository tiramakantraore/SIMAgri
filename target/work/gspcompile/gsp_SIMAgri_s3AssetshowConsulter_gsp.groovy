import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3AssetshowConsulter_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/showConsulter.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("pageLayout")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
expressionOut.print(s3AssetInstance?.title)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',6,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',16,['class':("alert-info")],3)
printHtmlPart(2)
}
printHtmlPart(2)
invokeTag('display','f',18,['bean':("s3AssetInstance"),'property':("title")],-1)
printHtmlPart(2)
invokeTag('display','f',19,['bean':("s3AssetInstance"),'property':("description")],-1)
printHtmlPart(4)
invokeTag('createLinkTo','s3',21,['asset':(s3AssetInstance)],-1)
printHtmlPart(5)
})
invokeTag('captureBody','sitemesh',37,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419346710928L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
