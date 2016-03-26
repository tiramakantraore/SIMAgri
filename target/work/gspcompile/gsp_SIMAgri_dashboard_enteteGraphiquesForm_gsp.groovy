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

class gsp_SIMAgri_dashboard_enteteGraphiquesForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/_enteteGraphiquesForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(g.message(code:"notes.text", default:"Notes"))
printHtmlPart(1)
if(true && (isNewNote)) {
printHtmlPart(2)
}
printHtmlPart(3)
expressionOut.print(g.message(code:"actualites.text", default:"Actualités"))
printHtmlPart(1)
if(true && (isNewInfo)) {
printHtmlPart(4)
}
printHtmlPart(5)
invokeTag('hiddenField','g',37,['id':("orientationTabPrix"),'name':("orientationTabPrix"),'value':("Simple")],-1)
printHtmlPart(6)
invokeTag('radioBoxList','bill',39,['referenceCollection':(['Simple',"${g.message(code:"croise.text", default:"Croisé")}"]),'radioName':("filterorientationTabPrix"),'defaultValue':("Simple"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickOrientationTabPrix();")],-1)
printHtmlPart(7)
expressionOut.print(g.message(code:"selectionnerProd.text", default:"Sélectionnez le produit"))
printHtmlPart(8)
invokeTag('hiddenField','g',81,['id':("produitSelected"),'name':("produitSelected")],-1)
printHtmlPart(9)
invokeTag('radioBoxList','bill',88,['referenceCollection':(userProductsList),'containerClass':(ctnerTemplateProd),'instanceName':("produit"),'onClickRadio':("produitChoisiClick();"),'emptyText':("Il n y a pas de produits, veuillez vérifier votre profil")],-1)
printHtmlPart(10)
invokeTag('render','g',102,['template':("prix"),'model':("model")],-1)
printHtmlPart(11)
invokeTag('hiddenField','g',121,['id':("mesureSelected"),'name':("mesureSelected")],-1)
printHtmlPart(12)
invokeTag('radioBoxList','bill',129,['referenceCollection':(Mesure.findAllByIsConvertible(true)),'instanceName':("mesure"),'containerClass':("my4Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickMesureOption();"),'emptyText':("Il n y a pas de mesures, veuillez vérifier votre profil")],-1)
printHtmlPart(13)
invokeTag('render','g',166,['template':("offres"),'model':("model")],-1)
printHtmlPart(14)
invokeTag('hiddenField','g',184,['id':("mesureSelectedStock"),'name':("mesureSelectedStock")],-1)
printHtmlPart(12)
invokeTag('radioBoxList','bill',190,['referenceCollection':(Mesure.findAllByIsConvertible(true)),'instanceName':("mesureStock"),'containerClass':("my4Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickMesureStockOption();"),'emptyText':("Il n y a pas de mesures, veuillez vérifier votre profil")],-1)
printHtmlPart(15)
expressionOut.print(g.message(code:"selectionnerProd.text", default:"Sélectionnez le produit"))
printHtmlPart(16)
invokeTag('hiddenField','g',230,['id':("produitStockSelected"),'name':("produitStockSelected")],-1)
printHtmlPart(17)
invokeTag('radioBoxList','bill',237,['referenceCollection':(userProductsList),'containerClass':(ctnerTemplateProd),'instanceName':("produitStock"),'onClickRadio':("produitStockChoisiClick();"),'emptyText':("Il n y a pas de produits, veuillez vérifier votre profil")],-1)
printHtmlPart(18)
invokeTag('render','g',250,['template':("stocks"),'model':("model")],-1)
printHtmlPart(19)
invokeTag('render','g',257,['template':("notesList"),'model':(['lesNotes':topNotes])],-1)
printHtmlPart(20)
invokeTag('remotePaginate','util',267,['total':(topNotes?.size()?:0),'action':("filterNotes"),'update':("actualitesUpdate"),'max':("5"),'alwaysShowPageSizes':("true"),'pageSizes':([5, 10,15,20,100])],-1)
printHtmlPart(21)
invokeTag('render','g',273,['template':("actualitesList"),'model':(['lesInfos':topNInfos])],-1)
printHtmlPart(22)
invokeTag('remotePaginate','util',282,['total':(topNInfos?.size()?:0),'action':("filterInfos"),'update':("actualitesUpdate"),'max':("5"),'alwaysShowPageSizes':("true"),'pageSizes':([5, 10,15,20,100])],-1)
printHtmlPart(23)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1458763929741L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
