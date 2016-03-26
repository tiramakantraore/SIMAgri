import simagri.Utilisateur
import  simagri.Qualite
import  simagri.Lieux
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_colonneDroite_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_colonneDroite.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('field','f',5,['bean':("offreInstance"),'property':("modePaiement"),'required':("true"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',16,['bean':("offreInstance"),'property':("lieuLivraison"),'input-class':("form-control")],-1)
printHtmlPart(2)
invokeTag('field','f',28,['bean':("offreInstance"),'property':("qualite"),'input-class':("form-control")],-1)
printHtmlPart(3)
invokeTag('field','f',39,['bean':("offreInstance"),'property':("auteur"),'input-class':("form-control")],-1)
printHtmlPart(4)
invokeTag('field','f',51,['bean':("offreInstance"),'property':("contact"),'input-class':("form-control")],-1)
printHtmlPart(5)
invokeTag('field','f',56,['bean':("offreInstance"),'property':("conditions"),'input-class':("form-control")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419203300678L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
