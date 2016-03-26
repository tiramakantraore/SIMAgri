import simagri.Mesure
import  simagri.S3Asset
import  simagri.Produit
import  simagri.Marche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_administration_enteteGraphiquesForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administration/_enteteGraphiquesForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('hiddenField','g',28,['id':("orientationTabPrix"),'name':("orientationTabPrix"),'value':("Simple")],-1)
printHtmlPart(1)
invokeTag('radioBoxList','bill',30,['referenceCollection':(['Simple','Croisé']),'radioName':("filterorientationTabPrix"),'defaultValue':("Simple"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickOrientationTabPrix();")],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',65,['id':("produitSelected"),'name':("produitSelected")],-1)
printHtmlPart(3)
invokeTag('radioBoxList','bill',69,['referenceCollection':(userProductsList),'containerClass':("${ctnerTemplateProd} limitHeightProd"),'instanceName':("produit"),'onClickRadio':("produitChoisiClick();"),'emptyText':("Il n y a pas de produits, veuillez vérifier votre profil")],-1)
printHtmlPart(4)
invokeTag('render','g',85,['template':("prix"),'model':("model")],-1)
printHtmlPart(5)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1424971353159L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
