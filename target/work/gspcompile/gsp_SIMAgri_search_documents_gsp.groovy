import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_search_documents_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/search/_documents.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( document in (topNDocuments) ) {
printHtmlPart(1)
expressionOut.print(g.createLink(controller:"s3Asset", action:"showConsulter", id:document.id))
printHtmlPart(2)
expressionOut.print((document?.mimeType=='application/pdf')?'pdf_class':((document?.mimeType=='application/vnd.ms-excel')?'csv_class':((document?.mimeType=='application/rtf')?'rtf_class':'image_class')))
printHtmlPart(3)
expressionOut.print(document?.title)
printHtmlPart(4)
i++
}
}
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418930483699L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
