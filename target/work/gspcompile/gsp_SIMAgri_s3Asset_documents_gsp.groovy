import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_documents_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_documents.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('remoteSortableColumn','util',7,['update':("listContent"),'action':("list"),'property':("title"),'title':("Title")],-1)
printHtmlPart(1)
invokeTag('remoteSortableColumn','util',11,['update':("listContent"),'action':("list"),'property':("mimeType"),'title':("Type document")],-1)
printHtmlPart(2)
invokeTag('remoteSortableColumn','util',13,['update':("listContent"),'action':("list"),'property':("length"),'title':(message(code: 's3Asset.length.label', default: 'Taille en ko'))],-1)
printHtmlPart(3)
loop:{
int i = 0
for( s3Asset in (s3AssetList) ) {
printHtmlPart(4)
createTagBody(2, {->
expressionOut.print(s3Asset?.title?.encodeAsHTML())
})
invokeTag('link','g',17,['action':("showConsulter"),'id':(s3Asset?.id)],2)
printHtmlPart(5)
expressionOut.print(s3Asset?.mimeType?.encodeAsHTML())
printHtmlPart(6)
expressionOut.print(s3Asset?.length?.toKo())
printHtmlPart(7)
i++
}
}
printHtmlPart(8)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416192615606L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
