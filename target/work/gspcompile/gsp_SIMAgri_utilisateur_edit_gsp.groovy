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

class gsp_SIMAgri_utilisateur_edit_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_edit.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
if(true && (flash.messageErreur)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.messageErreur)
})
invokeTag('alert','bootstrap',37,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('message','g',44,['code':("edit.${suffixe}")],-1)
printHtmlPart(4)
expressionOut.print(utilisateurInstance)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',47,['class':("alert-info")],2)
printHtmlPart(6)
}
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(8)
createTagBody(3, {->
printHtmlPart(9)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(10)
expressionOut.print(error.field)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',54,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',55,['bean':(utilisateurInstance),'var':("error")],3)
printHtmlPart(14)
})
invokeTag('alert','bootstrap',57,['class':("alert-error")],2)
printHtmlPart(6)
})
invokeTag('hasErrors','g',58,['bean':(utilisateurInstance)],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(15)
invokeTag('message','g',63,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(16)
invokeTag('hiddenField','g',67,['name':("version"),'value':(utilisateurInstance?.version)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',68,['name':("ReseauxIds")],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',69,['name':("password"),'value':(utilisateurInstance?.password)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',70,['name':("theId"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',71,['name':("id"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',72,['name':("userType"),'value':(userType)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',73,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',74,['name':("isChange"),'value':(isChange)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',75,['name':("isCreation"),'value':(isCreation)],-1)
printHtmlPart(18)
expressionOut.print(g.message(code:"mesMarchesSuivi.label", default:"Mes marchés (suivi)"))
printHtmlPart(19)
createTagBody(2, {->
printHtmlPart(20)
expressionOut.print(g.message(code:"mesMarchesEnLigne.label", default:"Mes marchés (Mise en ligne)"))
printHtmlPart(19)
})
invokeTag('ifSuperViseur','sec',84,[:],2)
printHtmlPart(21)
invokeTag('render','g',95,['template':("identification"),'model':([isEditing:true])],-1)
printHtmlPart(22)
invokeTag('render','g',103,['template':("adresse")],-1)
printHtmlPart(23)
invokeTag('render','g',108,['template':("choixgroupes")],-1)
printHtmlPart(24)
invokeTag('checkBoxList','bill',116,['referenceCollection':(simagri.Region.list()),'containerClass':("my4Columns"),'title':(g.message(code:"regions.text", default:"Régions")),'instanceName':("regions"),'onClickCheck':("regionClick();")],-1)
printHtmlPart(25)
expressionOut.print(g.message(code:"tousLesMarches.text", default:"${g.message(code: "tousLesMarches.text", default:"Tous les marchés")}"))
printHtmlPart(26)
expressionOut.print(g.message(code:"aucunMarche.text", default:"${g.message(code: "aucunMarche.text", default:"Aucun marché")}"))
printHtmlPart(27)
invokeTag('checkBoxList','bill',130,['referenceCollection':(MarcheList),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(28)
createTagBody(2, {->
printHtmlPart(29)
invokeTag('checkBoxList','bill',150,['referenceCollection':(simagri.Region.list()),'containerClass':("my4Columns"),'title':(g.message(code:"regions.text", default:"Régions")),'instanceName':("regionsMEL"),'onClickCheck':("regionMELClick();")],-1)
printHtmlPart(30)
expressionOut.print(g.message(code:"tousLesMarches.text", default:"Tous les marchés"))
printHtmlPart(31)
expressionOut.print(g.message(code:"aucunMarche.text", default:"Aucun marché"))
printHtmlPart(32)
invokeTag('checkBoxList','bill',161,['referenceCollection':(MarcheList),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("marchesEcriture")],-1)
printHtmlPart(33)
})
invokeTag('ifSuperViseur','sec',161,[:],2)
printHtmlPart(34)
invokeTag('checkBoxList','bill',170,['referenceCollection':(CategorieProduit.findAllByActif(true)),'containerClass':("my4Columns"),'title':("Catégories de produits"),'instanceName':("categories"),'onClickCheck':("categorieClick();")],-1)
printHtmlPart(35)
invokeTag('checkBoxList','bill',182,['referenceCollection':(ProduitList),'title':("Produits"),'containerClass':("${ctnerTemplateProd} limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(36)
invokeTag('checkBoxList','bill',194,['referenceCollection':(MagazinList),'containerClass':("${ctnerTemplateMag} limitHeight"),'instanceName':("magazins")],-1)
printHtmlPart(37)
invokeTag('checkBoxList','bill',206,['referenceCollection':(ServicesList),'containerClass':("${ctnerTemplateService} "),'instanceName':("services")],-1)
printHtmlPart(38)
invokeTag('message','g',210,['code':("default.button.update.label"),'default':("Update")],-1)
printHtmlPart(39)
})
invokeTag('form','g',212,['name':("edit"),'class':("form-stacked-vertical"),'enctype':("multipart/form-data")],1)
printHtmlPart(40)
invokeTag('render','g',212,['template':("userjs")],-1)
printHtmlPart(41)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429133399094L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
