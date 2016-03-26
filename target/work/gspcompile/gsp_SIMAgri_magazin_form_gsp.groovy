import simagri.Lieux
import  simagri.Produit
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_magazin_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/magazin/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',3,['property':("code"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',4,['property':("nom"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',5,['property':("nomContact"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',6,['property':("numTelBureau"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',7,['property':("adressePhysique"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',8,['property':("email"),'input-class':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',9,['property':("actif"),'input-class':("check")],-1)
printHtmlPart(1)
invokeTag('field','f',10,['property':("localite"),'input-noSelection':(['': '']),'input-class':("many-to-one form-control")],-1)
printHtmlPart(0)
})
invokeTag('with','f',1,['bean':("magazinInstance")],1)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422486172950L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
