import simagri.Service
import  simagri.UtilisateurMagazin
import  simagri.UtilisateurProduit
import  simagri.UtilisateurMarche
import  simagri.Magazin
import  simagri.Produit
import  simagri.Marche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_show.gsp" }
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
invokeTag('alert','bootstrap',27,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
invokeTag('message','g',33,['code':("show.${suffixe}")],-1)
printHtmlPart(4)
expressionOut.print(utilisateurInstance)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',41,['class':("alert-info")],2)
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
invokeTag('message','g',49,['error':(error)],-1)
printHtmlPart(13)
})
invokeTag('eachError','g',50,['bean':(utilisateurInstance),'var':("error")],3)
printHtmlPart(14)
})
invokeTag('alert','bootstrap',51,['class':("alert-error")],2)
printHtmlPart(6)
})
invokeTag('hasErrors','g',52,['bean':(utilisateurInstance)],1)
printHtmlPart(15)
createTagBody(1, {->
printHtmlPart(16)
invokeTag('message','g',59,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',65,['name':("version"),'value':(utilisateurInstance?.version)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',66,['name':("ReseauxIds")],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',68,['name':("password"),'value':(utilisateurInstance?.password)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',69,['name':("theId"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',70,['name':("userType"),'value':(userType)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',70,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(18)
invokeTag('hiddenField','g',70,['name':("isChange"),'value':(isChange)],-1)
printHtmlPart(19)
invokeTag('render','g',88,['template':("identificationshow"),'model':([isShowing:true])],-1)
printHtmlPart(20)
invokeTag('render','g',92,['template':("adresseshow")],-1)
printHtmlPart(21)
invokeTag('render','g',98,['template':("choixgroupes")],-1)
printHtmlPart(22)
invokeTag('checkBoxList','bill',114,['referenceCollection':(MarcheList),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("markets")],-1)
printHtmlPart(23)
createTagBody(2, {->
printHtmlPart(24)
invokeTag('checkBoxList','bill',129,['referenceCollection':(MarcheList),'containerClass':("${ctnerTemplate} limitHeight"),'instanceName':("marchesEcriture")],-1)
printHtmlPart(25)
})
invokeTag('ifSuperViseur','sec',131,[:],2)
printHtmlPart(26)
invokeTag('checkBoxList','bill',142,['referenceCollection':(ProduitList),'containerClass':("${ctnerTemplateProd} limitHeight"),'instanceName':("produits")],-1)
printHtmlPart(27)
invokeTag('checkBoxList','bill',156,['referenceCollection':(MagazinList),'containerClass':("${ctnerTemplateMag} limitHeight"),'instanceName':("magazins")],-1)
printHtmlPart(28)
invokeTag('checkBoxList','bill',168,['referenceCollection':(ServicesList),'containerClass':("${ctnerTemplateService} "),'instanceName':("services")],-1)
printHtmlPart(29)
invokeTag('message','g',171,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(30)
})
invokeTag('form','g',172,['class':("form-stacked-vertical"),'name':("show")],1)
printHtmlPart(31)
invokeTag('render','g',174,['template':("usershowjs")],-1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1425487576436L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
