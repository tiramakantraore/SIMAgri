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

class gsp_SIMAgri_utilisateur_create_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',19,['code':("create.${suffixe}")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(6)
expressionOut.print(error.field)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('message','g',29,['error':(error)],-1)
printHtmlPart(9)
})
invokeTag('eachError','g',29,['bean':(utilisateurInstance),'var':("error")],3)
printHtmlPart(10)
})
invokeTag('alert','bootstrap',30,['class':("alert-error")],2)
printHtmlPart(2)
})
invokeTag('hasErrors','g',32,['bean':(utilisateurInstance)],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('hiddenField','g',36,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(13)
invokeTag('hiddenField','g',37,['name':("isChange"),'value':(isChange)],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',38,['name':("isCreation"),'value':(isCreation)],-1)
printHtmlPart(14)
invokeTag('message','g',43,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(15)
expressionOut.print(g.message(code:"mesMarches.label", default:"Mes marchés"))
printHtmlPart(16)
invokeTag('render','g',61,['template':("identification"),'model':([isEditing:false])],-1)
printHtmlPart(17)
invokeTag('render','g',67,['template':("adresse")],-1)
printHtmlPart(18)
invokeTag('render','g',72,['template':("choixgroupes")],-1)
printHtmlPart(19)
invokeTag('checkBoxList','bill',91,['referenceCollection':(Region.list()),'containerClass':("my4Columns"),'title':(g.message(code:"regions.text", default:"Régions")),'instanceName':("regions"),'onClickCheck':("regionClick();")],-1)
printHtmlPart(20)
expressionOut.print(g.message(code:"tousLesMarches.text", default:"Tous les marchés"))
printHtmlPart(21)
expressionOut.print(g.message(code:"aucunMarche.text", default:"Aucun marché"))
printHtmlPart(22)
invokeTag('checkBoxList','bill',112,['referenceCollection':(CategorieProduit.findAllByActif(true)),'containerClass':("my4Columns"),'title':(g.message(code:"categoriesProduits.label", default:"Catégories de produits")),'instanceName':("categories"),'onClickCheck':("categorieClick();")],-1)
printHtmlPart(23)
invokeTag('checkBoxList','bill',142,['referenceCollection':(MagazinList),'containerClass':("${ctnerTemplateMag} "),'instanceName':("magazins")],-1)
printHtmlPart(24)
invokeTag('checkBoxList','bill',154,['referenceCollection':(ServicesList),'containerClass':("${ctnerTemplateService} "),'instanceName':("services")],-1)
printHtmlPart(25)
invokeTag('message','g',159,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(26)
})
invokeTag('form','g',160,['class':("form-stacked-horizontal"),'action':("create"),'name':("create"),'enctype':("multipart/form-data")],1)
printHtmlPart(27)
invokeTag('render','g',162,['template':("userjs")],-1)
printHtmlPart(28)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429132622031L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
