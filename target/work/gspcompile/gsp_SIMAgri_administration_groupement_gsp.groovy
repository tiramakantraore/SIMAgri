import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_groupement_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_groupement.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( groupe in (groupes) ) {
printHtmlPart(1)
invokeTag('radio','g',6,['id':("${groupe}_${i}"),'name':("groupe"),'value':(groupe),'checked':(groupe == "Annee"),'class':("myRadiostyle"),'onclick':("ongroupeclick();")],-1)
printHtmlPart(2)
expressionOut.print(groupe)
printHtmlPart(3)
expressionOut.print(i)
printHtmlPart(4)
expressionOut.print(groupe)
printHtmlPart(5)
expressionOut.print(groupe)
printHtmlPart(6)
i++
}
}
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1413759249227L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
