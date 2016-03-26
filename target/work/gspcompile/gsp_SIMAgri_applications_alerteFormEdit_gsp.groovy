import simagri.Marche
import  simagri.Produit
import  simagri.Utilisateur
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_applications_alerteFormEdit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/applications/_alerteFormEdit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',2,['code':("modif.alerte")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',7,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(3)
expressionOut.print(createLink(controller:'applications', action:'deleteAlerte'))
printHtmlPart(4)
invokeTag('message','g',12,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(5)
invokeTag('hiddenField','g',15,['name':("ReseauxIdsAlerte")],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',16,['name':("recevoirOffres"),'value':(alerteInstance?.recevoirOffres)],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',17,['name':("recevoirPrix"),'value':(alerteInstance?.recevoirPrix)],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',18,['name':("recevoirParEmail"),'value':(alerteInstance?.recevoirParEmail)],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',19,['name':("recevoirParSMS"),'value':(alerteInstance?.recevoirParSMS)],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',20,['name':("id"),'value':(alerteInstance?.id)],-1)
printHtmlPart(7)
invokeTag('field','f',38,['bean':("alerteInstance"),'property':("nom"),'required':("true"),'input-class':("form-control")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',45,['id':("typeAlerte"),'name':("typeAlerte"),'value':("Prix")],-1)
printHtmlPart(9)
invokeTag('radioBoxList','bill',47,['referenceCollection':(['Prix','Offre']),'radioName':("filtertypeAlerte"),'defaultValue':("Prix"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclicktypeAlerte();")],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',52,['id':("typeOffre"),'name':("typeOffre"),'value':("Achat")],-1)
printHtmlPart(11)
invokeTag('radioBoxList','bill',54,['referenceCollection':(['Achat','Vente']),'radioName':("filtertypeOffre"),'defaultValue':("Achat"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass")],-1)
printHtmlPart(12)
invokeTag('message','g',66,['code':("choisir.reseau")],-1)
printHtmlPart(13)
invokeTag('checkBoxList','bill',84,['referenceCollection':(Produit.findAllByActif(true)),'containerClass':("my4Columns  limitHeight"),'instanceName':("products_alertes")],-1)
printHtmlPart(14)
invokeTag('checkBoxList','bill',105,['referenceCollection':(Marche.list()),'containerClass':("my4Columns  limitHeight"),'instanceName':("markets_alertes")],-1)
printHtmlPart(15)
invokeTag('checkBoxList','bill',140,['referenceCollection':(Utilisateur.findAllById(0)),'containerClass':("my4Columns"),'instanceName':("destinataires_alertes")],-1)
printHtmlPart(16)
invokeTag('field','f',158,['bean':("alerteInstance"),'property':("lundi"),'input-class':("check")],-1)
printHtmlPart(17)
invokeTag('field','f',162,['bean':("alerteInstance"),'property':("mardi"),'input-class':("check")],-1)
printHtmlPart(18)
invokeTag('field','f',165,['bean':("alerteInstance"),'property':("mercredi"),'input-class':("check")],-1)
printHtmlPart(19)
invokeTag('field','f',172,['bean':("alerteInstance"),'property':("jeudi"),'input-class':("check")],-1)
printHtmlPart(20)
invokeTag('field','f',177,['bean':("alerteInstance"),'property':("vendredi"),'input-class':("check")],-1)
printHtmlPart(21)
invokeTag('field','f',182,['bean':("alerteInstance"),'property':("samedi"),'input-class':("check")],-1)
printHtmlPart(22)
invokeTag('field','f',190,['bean':("alerteInstance"),'property':("dimanche"),'input-class':("check")],-1)
printHtmlPart(23)
invokeTag('message','g',206,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(24)
invokeTag('message','g',211,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(25)
})
invokeTag('form','g',217,['class':("form-horizontal"),'name':("createAlerte")],1)
printHtmlPart(6)
invokeTag('render','g',218,['template':("/applications/alertesjs")],-1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1456865055365L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
