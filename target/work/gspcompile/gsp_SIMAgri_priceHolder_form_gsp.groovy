import simagri.Produit
import  simagri.Marche
import  simagri.Mesure
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_priceHolder_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/priceHolder/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('field','f',4,['property':("produit")],-1)
printHtmlPart(1)
invokeTag('field','f',5,['property':("marche")],-1)
printHtmlPart(1)
invokeTag('field','f',6,['property':("date")],-1)
printHtmlPart(1)
invokeTag('field','f',7,['property':("prixGros")],-1)
printHtmlPart(1)
invokeTag('field','f',8,['property':("prixDetail")],-1)
printHtmlPart(1)
invokeTag('field','f',9,['property':("mesureGros")],-1)
printHtmlPart(1)
invokeTag('field','f',10,['property':("mesureDetail")],-1)
printHtmlPart(1)
invokeTag('field','f',11,['property':("auteur")],-1)
printHtmlPart(1)
invokeTag('field','f',12,['property':("isRejected")],-1)
printHtmlPart(1)
invokeTag('field','f',13,['property':("commentaire")],-1)
printHtmlPart(1)
invokeTag('field','f',14,['property':("commentaireAdministrateur")],-1)
printHtmlPart(2)
})
invokeTag('with','f',15,['bean':("priceHolderInstance")],1)
printHtmlPart(0)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1425002478648L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
