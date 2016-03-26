import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_prixGros_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_prixGros.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textField','g',8,['type':("number"),'step':("any"),'id':("prixUnitaireGros"),'name':("prixUnitaireGros"),'value':(offreInstance?.prixUnitaireGros),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',8,['bean':("offreInstance"),'property':("prixUnitaireGros")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('textField','g',14,['type':("number"),'step':("any"),'id':("prixTotalGros"),'name':("prixTotalGros"),'value':(offreInstance?.prixTotalGros),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',14,['bean':("offreInstance"),'property':("prixTotalGros")],1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419579208046L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
