import simagri.Region
import  simagri.CategorieProduit
import  simagri.Service
import  simagri.Magazin
import  simagri.Produit
import  simagri.Marche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateureditProfile_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/editProfile.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("accueilLayout")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("edit.${suffixe}")],-1)
printHtmlPart(3)
expressionOut.print(utilisateurInstance)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',41,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(5)
if(true && (flash.messageErreur)) {
printHtmlPart(6)
createTagBody(3, {->
expressionOut.print(flash.messageErreur)
})
invokeTag('alert','bootstrap',47,['class':("alert-info")],3)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',54,['code':("edit.${suffixe}")],-1)
printHtmlPart(9)
expressionOut.print(utilisateurInstance)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',57,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(11)
createTagBody(3, {->
printHtmlPart(13)
createTagBody(4, {->
printHtmlPart(14)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',64,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',64,['bean':(utilisateurInstance),'var':("error")],4)
printHtmlPart(19)
})
invokeTag('alert','bootstrap',65,['class':("alert-error")],3)
printHtmlPart(11)
})
invokeTag('hasErrors','g',67,['bean':(utilisateurInstance)],2)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(20)
invokeTag('message','g',73,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(21)
invokeTag('hiddenField','g',77,['name':("version"),'value':(utilisateurInstance?.version)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',78,['name':("ReseauxIds")],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',79,['name':("password"),'value':(utilisateurInstance?.password)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',80,['name':("theId"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',81,['name':("id"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',82,['name':("userType"),'value':(userType)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',83,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',84,['name':("isChange"),'value':(isChange)],-1)
printHtmlPart(22)
invokeTag('hiddenField','g',85,['name':("isCreation"),'value':(isCreation)],-1)
printHtmlPart(23)
expressionOut.print(g.message(code:"mesMarches.label","Mes marchés"))
printHtmlPart(24)
invokeTag('render','g',102,['template':("identification"),'model':([isEditing:true])],-1)
printHtmlPart(25)
invokeTag('render','g',110,['template':("adresse")],-1)
printHtmlPart(26)
invokeTag('render','g',115,['template':("choixgroupes")],-1)
printHtmlPart(27)
invokeTag('checkBoxList','bill',123,['referenceCollection':(Region.list()),'containerClass':("my4Columns"),'title':(g.message(code:"regions.text","Régions")),'instanceName':("regions"),'onClickCheck':("regionClick();")],-1)
printHtmlPart(28)
expressionOut.print(g.message(code:"tousLesMarches.text", default:"Tous les marchés"))
printHtmlPart(29)
expressionOut.print(g.message(code:"aucunMarche.text", default:"Aucun marché"))
printHtmlPart(30)
invokeTag('checkBoxList','bill',137,['referenceCollection':(MarcheList),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(31)
invokeTag('checkBoxList','bill',145,['referenceCollection':(CategorieProduit.findAllByActif(true)),'containerClass':("my4Columns"),'title':(g.message(code:"categoriesProduits.label", default:"Catégories de produit")),'instanceName':("categories"),'onClickCheck':("categorieClick();")],-1)
printHtmlPart(32)
invokeTag('checkBoxList','bill',158,['referenceCollection':(ProduitList),'title':("Produits"),'containerClass':("${ctnerTemplateProd} limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(33)
invokeTag('checkBoxList','bill',171,['referenceCollection':(MagazinList),'containerClass':("${ctnerTemplateMag} limitHeight"),'instanceName':("magazins")],-1)
printHtmlPart(34)
invokeTag('checkBoxList','bill',183,['referenceCollection':(ServicesList),'containerClass':("${ctnerTemplateService} "),'instanceName':("services")],-1)
printHtmlPart(35)
invokeTag('message','g',190,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(36)
})
invokeTag('form','g',193,['name':("edit"),'class':("form-stacked-vertical"),'enctype':("multipart/form-data")],2)
printHtmlPart(37)
invokeTag('render','g',197,['template':("userjs")],-1)
printHtmlPart(0)
})
invokeTag('captureBody','sitemesh',197,[:],1)
printHtmlPart(38)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429133604833L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
