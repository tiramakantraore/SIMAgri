import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_sondageForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_sondageForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(titreSondage)
printHtmlPart(1)
if(true && (titreSondage)) {
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
expressionOut.print(it.radio)
printHtmlPart(4)
expressionOut.print(it.label)
printHtmlPart(5)
})
invokeTag('radioGroup','g',13,['name':("sondage"),'onclick':("sondageClick();"),'labels':(detailsSondage?.choix),'values':(detailsSondage?.choix)],2)
printHtmlPart(6)
invokeTag('message','g',18,['code':("ResultatsSondage.label"),'default':("Resultats")],-1)
printHtmlPart(7)
}
else {
printHtmlPart(8)
}
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410984L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
