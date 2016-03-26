import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_quizz_detail_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/quizz/_detail.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(i)
printHtmlPart(1)
if(true && (hidden)) {
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('render','g',5,['template':("/questionChoixMultiple/createQCM"),'model':(['quizz_detail':null,'i':'_clone','hidden':true])],-1)
printHtmlPart(4)
invokeTag('image','asset',9,['src':("skin/icon_delete.png"),'style':("vertical-align:middle;width:60%;height:60%"),'alt':("Suppr.")],-1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1411867490008L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
