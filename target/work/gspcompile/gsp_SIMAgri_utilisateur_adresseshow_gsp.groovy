import simagri.Activite
import  simagri.Entreprise
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_adresseshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_adresseshow.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('display','f',6,['bean':("utilisateurInstance"),'property':("country")],-1)
printHtmlPart(1)
invokeTag('display','f',13,['bean':("utilisateurInstance"),'property':("email")],-1)
printHtmlPart(2)
invokeTag('display','f',24,['bean':("utilisateurInstance"),'property':("town")],-1)
printHtmlPart(3)
invokeTag('display','f',31,['bean':("utilisateurInstance"),'property':("physicalAddress")],-1)
printHtmlPart(4)
invokeTag('display','f',40,['bean':("utilisateurInstance"),'property':("secondPhone")],-1)
printHtmlPart(5)
invokeTag('display','f',49,['bean':("utilisateurInstance"),'property':("entreprise"),'input-class':("form-control")],-1)
printHtmlPart(6)
invokeTag('display','f',57,['bean':("utilisateurInstance"),'property':("activite"),'input-class':("form-control")],-1)
printHtmlPart(6)
invokeTag('display','f',65,['bean':("utilisateurInstance"),'property':("poste"),'input-class':("form-control")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415811535881L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
