import simagri.CategorieProduit
import  simagri.Region
import  simagri.Produit
import  simagri.Lieux
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_marche_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/marche/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(0)
invokeTag('field','f',3,['property':("code"),'required':("true"),'input-class':("many-to-one form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(0)
invokeTag('field','f',4,['property':("nom"),'required':("true"),'input-class':("many-to-one form-control"),'input-autocomplete':("off")],-1)
printHtmlPart(0)
invokeTag('field','f',5,['property':("region"),'input-noSelection':(['': '']),'input-class':("many-to-one form-control")],-1)
printHtmlPart(0)
invokeTag('field','f',6,['property':("location"),'input-noSelection':(['': '']),'input-class':("many-to-one form-control")],-1)
printHtmlPart(0)
invokeTag('field','f',7,['property':("actif"),'input-class':("check")],-1)
printHtmlPart(0)
})
invokeTag('with','f',8,['bean':("marcheInstance")],1)
printHtmlPart(1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1417645546825L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
