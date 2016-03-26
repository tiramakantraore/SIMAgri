import simagri.CategorieProduit
import  simagri.Region
import  simagri.Marche
import  simagri.Produit
import  simagri.Utilisateur
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_alerteForm_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_alerteForm.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',1,['code':("config.alerte")],-1)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('hiddenField','g',5,['name':("recevoirOffres"),'value':(alerteInstance?.recevoirOffres)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',6,['name':("recevoirPrix"),'value':(alerteInstance?.recevoirPrix)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',7,['name':("recevoirParEmail"),'value':(alerteInstance?.recevoirParEmail)],-1)
printHtmlPart(2)
invokeTag('hiddenField','g',8,['name':("recevoirParSMS"),'value':(alerteInstance?.recevoirParSMS)],-1)
printHtmlPart(3)
invokeTag('message','g',11,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',14,['name':("ReseauxIdsAlerte")],-1)
printHtmlPart(5)
invokeTag('message','g',22,['code':("marches.label"),'default':("Marchés")],-1)
printHtmlPart(6)
invokeTag('field','f',34,['bean':("alerteInstance"),'property':("nom"),'required':("true"),'input-class':("form-control")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',41,['id':("typeAlerte"),'name':("typeAlerte"),'value':("Prix")],-1)
printHtmlPart(8)
invokeTag('radioBoxList','bill',43,['referenceCollection':(['Prix','Offre']),'radioName':("filtertypeAlerte"),'defaultValue':("Prix"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclicktypeAlerte();")],-1)
printHtmlPart(9)
invokeTag('hiddenField','g',48,['id':("typeOffre"),'name':("typeOffre"),'value':("Achat")],-1)
printHtmlPart(10)
invokeTag('radioBoxList','bill',50,['referenceCollection':(['Achat','Vente']),'radioName':("filtertypeOffre"),'defaultValue':("Achat"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass")],-1)
printHtmlPart(11)
invokeTag('message','g',62,['code':("choisir.reseau")],-1)
printHtmlPart(12)
invokeTag('checkBoxList','bill',73,['referenceCollection':(CategorieProduit.findAllByActif(true)),'containerClass':("my4Columns"),'title':(g.message(code:"categoriesProduits.label", default:"Catégories de produits")),'instanceName':("categories"),'onClickCheck':("categorieClick();")],-1)
printHtmlPart(13)
invokeTag('checkBoxList','bill',87,['referenceCollection':(Produit.findAllByActif(true)),'containerClass':("my4Columns  limitHeight"),'instanceName':("products_alertes")],-1)
printHtmlPart(14)
invokeTag('checkBoxList','bill',99,['referenceCollection':(Region.list()),'containerClass':("my4Columns"),'title':(g.message(code:"regions.text", default:"Régions")),'instanceName':("regions"),'onClickCheck':("regionClick();")],-1)
printHtmlPart(15)
invokeTag('message','g',109,['code':("tousLesMarches.text"),'default':("Tous les marchés")],-1)
printHtmlPart(16)
invokeTag('message','g',112,['code':("aucunMarche.text"),'default':("Aucun marché")],-1)
printHtmlPart(17)
invokeTag('checkBoxList','bill',117,['referenceCollection':(Marche.list()),'containerClass':("my4Columns  limitHeight"),'instanceName':("markets_alertes")],-1)
printHtmlPart(18)
invokeTag('checkBoxList','bill',152,['referenceCollection':(Utilisateur.list().sort{it.nomComplet}),'containerClass':("my4Columns"),'instanceName':("destinataires_alertes")],-1)
printHtmlPart(19)
invokeTag('field','f',170,['bean':("alerteInstance"),'property':("lundi"),'input-class':("check")],-1)
printHtmlPart(20)
invokeTag('field','f',174,['bean':("alerteInstance"),'property':("mardi"),'input-class':("check")],-1)
printHtmlPart(21)
invokeTag('field','f',177,['bean':("alerteInstance"),'property':("mercredi"),'input-class':("check")],-1)
printHtmlPart(22)
invokeTag('field','f',184,['bean':("alerteInstance"),'property':("jeudi"),'input-class':("check")],-1)
printHtmlPart(23)
invokeTag('field','f',189,['bean':("alerteInstance"),'property':("vendredi"),'input-class':("check")],-1)
printHtmlPart(24)
invokeTag('field','f',194,['bean':("alerteInstance"),'property':("samedi"),'input-class':("check")],-1)
printHtmlPart(25)
invokeTag('field','f',202,['bean':("alerteInstance"),'property':("dimanche"),'input-class':("check")],-1)
printHtmlPart(26)
invokeTag('message','g',217,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(27)
})
invokeTag('form','g',217,['class':("form-horizontal"),'name':("createAlerte")],1)
printHtmlPart(2)
invokeTag('render','g',221,['template':("/applications/alertesjs")],-1)
printHtmlPart(2)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428774525269L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
