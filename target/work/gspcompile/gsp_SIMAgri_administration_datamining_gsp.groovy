import simagri.Marche
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_datamining_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_datamining.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('counterMonthlyGraph','g',35,['countMonth':("6"),'name':("visits"),'caption':("{0} Visites"),'caption1':("1 Visite")],-1)
printHtmlPart(1)
invokeTag('render','g',51,['template':("periode"),'model':([periode_suffix:'Prix'])],-1)
printHtmlPart(2)
invokeTag('render','g',65,['template':("periode"),'model':([periode_suffix:'Offre'])],-1)
printHtmlPart(3)
invokeTag('render','g',95,['template':("periode")],-1)
printHtmlPart(4)
invokeTag('render','g',99,['template':("groupement")],-1)
printHtmlPart(5)
invokeTag('render','g',102,['template':("aggregats")],-1)
printHtmlPart(6)
invokeTag('render','g',121,['template':("periode"),'model':([periode_suffix:'SMS'])],-1)
printHtmlPart(7)
invokeTag('render','g',149,['template':("statistiquesSMSOperateurs")],-1)
printHtmlPart(8)
invokeTag('render','g',159,['template':("adminjs")],-1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416851058122L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
