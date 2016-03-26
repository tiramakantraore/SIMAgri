import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_partials_dynamicBlocks_add_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/partials/dynamicBlocks/_add.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(id)
printHtmlPart(1)
expressionOut.print(id)
printHtmlPart(2)
if(true && (!addBtnId)) {
printHtmlPart(3)
expressionOut.print(id)
printHtmlPart(4)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(raw(addBtnId ? "\$('#$addBtnId')" : "\$('#add_$id')"))
printHtmlPart(7)
expressionOut.print(id)
printHtmlPart(8)
expressionOut.print(elem)
printHtmlPart(9)
expressionOut.print(min)
printHtmlPart(10)
expressionOut.print(max)
printHtmlPart(11)
expressionOut.print(onComplete)
printHtmlPart(8)
expressionOut.print(limitReachedMsg)
printHtmlPart(8)
expressionOut.print(removeBtnLabel)
printHtmlPart(12)
expressionOut.print(removeOffset)
printHtmlPart(12)
expressionOut.print(removeBtnId)
printHtmlPart(12)
expressionOut.print(tabprefix)
printHtmlPart(13)
})
invokeTag('script','asset',24,['type':("text/javascript")],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442325557566L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
