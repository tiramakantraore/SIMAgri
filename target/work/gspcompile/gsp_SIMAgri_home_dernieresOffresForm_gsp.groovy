import simagri.Offre
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_home_dernieresOffresForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/home/_dernieresOffresForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( offre in (Offre.list()) ) {
printHtmlPart(1)
expressionOut.print(offre)
printHtmlPart(2)
}
printHtmlPart(3)
if(true && (topNOffres?.size()==0)) {
printHtmlPart(4)
}
printHtmlPart(5)
createClosureForHtmlPart(6, 1)
invokeTag('link','g',22,['controller':("offreValidee"),'action':("listEnCours"),'class':("btn-flat  btn-success btn-medium")],1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415053986289L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
