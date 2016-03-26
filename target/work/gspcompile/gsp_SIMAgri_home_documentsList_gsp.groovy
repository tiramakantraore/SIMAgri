import simagri.S3Asset
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_documentsList_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_documentsList.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( s3Asset in (documentList) ) {
printHtmlPart(1)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
expressionOut.print(s3Asset?.title?.encodeAsHTML())
printHtmlPart(4)
})
invokeTag('link','g',15,['controller':("s3Asset"),'action':("showConsulter"),'id':(s3Asset?.id)],2)
printHtmlPart(5)
i++
}
}
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416192614240L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
