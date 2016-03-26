import simagri.Marche
import  simagri.Produit
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_reseau_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/reseau/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('field','f',3,['bean':("reseauInstance"),'property':("nom"),'required':("true"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('field','f',4,['bean':("reseauInstance"),'property':("email"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('field','f',5,['bean':("reseauInstance"),'property':("siteWeb"),'input-class':("form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('field','f',6,['bean':("reseauInstance"),'property':("active"),'input-class':("check"),'input-autocomplete':("off")],-1)
printHtmlPart(1)
invokeTag('field','f',7,['bean':("reseauInstance"),'property':("estReseau"),'input-class':("check"),'input-autocomplete':("off")],-1)
printHtmlPart(2)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1448078626181L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
