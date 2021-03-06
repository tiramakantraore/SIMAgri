import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_prixDetail_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_prixDetail.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',8,['code':("offre.prixUnitaire.label"),'default':("Prix Unitaire")],-1)
printHtmlPart(1)
invokeTag('field','g',11,['type':("number"),'step':("any"),'required':("true"),'id':("prixUnitaireDetail"),'name':("prixUnitaire"),'value':(fieldValue(bean: offreInstance, field: 'prixUnitaire')),'class':("col-sm-12 col-md-12")],-1)
printHtmlPart(2)
invokeTag('message','g',17,['code':("offre.prixTotal.label"),'default':("Prix Total")],-1)
printHtmlPart(3)
invokeTag('field','g',20,['type':("number"),'step':("any"),'id':("prixTotalDetail"),'name':("prixTotal"),'value':(fieldValue(bean: offreInstance, field: 'prixTotal')),'class':("col-sm-10 col-md-10"),'readonly':("true")],-1)
printHtmlPart(4)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418310953258L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
