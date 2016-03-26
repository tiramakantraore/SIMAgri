import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_statistiquesInscrits_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_statistiquesInscrits.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('formatNumber','g',7,['number':(nombreReseaux),'format':("###,##0")],-1)
printHtmlPart(1)
invokeTag('formatNumber','g',8,['number':(nombreGroupe),'format':("###,##0")],-1)
printHtmlPart(2)
invokeTag('formatNumber','g',9,['number':(nombreSousGroupe),'format':("###,##0")],-1)
printHtmlPart(3)
invokeTag('formatNumber','g',10,['number':(nombreReelMembres),'format':("###,##0")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423226416929L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
