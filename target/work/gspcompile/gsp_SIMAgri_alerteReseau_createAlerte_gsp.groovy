import simagri.Marche
import  simagri.Produit
import  simagri.CategorieProduit
import  simagri.Utilisateur
import  org.springframework.validation.FieldError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_alerteReseau_createAlerte_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/alerteReseau/_createAlerte.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',7,['code':("title.alerteReseau")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',12,['code':("list.alerte")],-1)
printHtmlPart(3)
})
invokeTag('link','g',13,['class':("list"),'action':("list")],1)
printHtmlPart(4)
invokeTag('message','g',19,['code':("choisir.reseau")],-1)
printHtmlPart(5)
invokeTag('message','g',36,['code':("create.alerte")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',41,['class':("alert-info")],2)
printHtmlPart(7)
}
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(9)
createTagBody(3, {->
printHtmlPart(10)
if(true && (error in FieldError)) {
printHtmlPart(11)
expressionOut.print(error.field)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',48,['error':(error)],-1)
printHtmlPart(14)
})
invokeTag('eachError','g',48,['bean':(alerteInstance),'var':("error")],3)
printHtmlPart(15)
})
invokeTag('alert','bootstrap',48,['class':("alert-error")],2)
printHtmlPart(7)
})
invokeTag('hasErrors','g',49,['bean':(alerteInstance)],1)
printHtmlPart(16)
createTagBody(1, {->
printHtmlPart(17)
invokeTag('hiddenField','g',56,['name':("ReseauxIdsAlerte")],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',58,['name':("recevoirOffres"),'value':(alerteInstance?.recevoirOffres)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',60,['name':("recevoirPrix"),'value':(alerteInstance?.recevoirPrix)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',62,['name':("recevoirParEmail"),'value':(alerteInstance?.recevoirParEmail)],-1)
printHtmlPart(17)
invokeTag('hiddenField','g',64,['name':("recevoirParSMS"),'value':(alerteInstance?.recevoirParSMS)],-1)
printHtmlPart(18)
invokeTag('message','g',70,['code':("alerte.typeAlerte.label"),'default':("Type Alerte ")],-1)
printHtmlPart(19)
invokeTag('select','g',76,['id':("typeAlerte"),'name':("typeAlerte"),'from':(alerteInstance.constraints.typeAlerte.inList),'required':(""),'value':(alerteInstance?.typeAlerte),'valueMessagePrefix':("alerte.typeAlerte")],-1)
printHtmlPart(20)
invokeTag('message','g',85,['code':("alerte.typeOffre.label"),'default':("Type Offre ")],-1)
printHtmlPart(19)
invokeTag('select','g',92,['name':("typeOffre"),'from':(alerteInstance.constraints.typeOffre.inList),'required':(""),'value':(alerteInstance?.typeOffre),'valueMessagePrefix':("alerte.typeOffre")],-1)
printHtmlPart(21)
invokeTag('message','g',103,['code':("alerte.categories.label"),'default':("Categories ")],-1)
printHtmlPart(19)
if(true && (isCreation)) {
printHtmlPart(22)
invokeTag('select','g',110,['name':("categories"),'id':("categoriesAlerte"),'from':(CategorieProduit.list()),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(23)
}
else {
printHtmlPart(22)
invokeTag('select','g',113,['name':("categories"),'id':("categoriesAlerte"),'from':(CategorieProduit.list()),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'value':(categoriesId),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(23)
}
printHtmlPart(24)
invokeTag('message','g',122,['code':("alerte.produits.label"),'default':("Produits ")],-1)
printHtmlPart(19)
if(true && (isCreation)) {
printHtmlPart(22)
invokeTag('select','g',130,['name':("produits"),'id':("produitsAlerte"),'from':(Produit.list()),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(25)
}
else {
printHtmlPart(22)
invokeTag('select','g',133,['name':("produits"),'id':("produitsAlerte"),'from':(Produit.list()),'value':(produitsId),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(26)
}
printHtmlPart(27)
invokeTag('message','g',143,['code':("alerte.markets.label"),'default':("Markets")],-1)
printHtmlPart(28)
if(true && (isCreation)) {
printHtmlPart(22)
invokeTag('select','g',150,['name':("markets"),'id':("marketsAlerte"),'from':(Marche.list()),'multiple':("multiple"),'noSelection':(['': 'Aucun marché']),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(29)
}
else {
printHtmlPart(22)
invokeTag('select','g',154,['name':("markets"),'id':("marketsAlerte"),'from':(Marche.list()),'value':(marchesId),'multiple':("multiple"),'noSelection':(['': 'Aucun marché']),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(23)
}
printHtmlPart(30)
invokeTag('message','g',166,['code':("alerte.destinataires.label"),'default':("Destinataires")],-1)
printHtmlPart(31)
if(true && (isCreation)) {
printHtmlPart(22)
invokeTag('select','g',173,['id':("destinatairesAlerte"),'name':("destinataires"),'from':(Utilisateur.list()?.sort{it.nomComplet}),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(23)
}
else {
printHtmlPart(22)
invokeTag('select','g',176,['id':("destinatairesAlerte"),'name':("destinataires"),'from':(Utilisateur.list()?.sort{it.nomComplet}),'multiple':("multiple"),'optionKey':("id"),'size':("5"),'value':(alerteInstance?.destinataires*.id),'class':("many-to-many col-sm-1 col-md-1")],-1)
printHtmlPart(23)
}
printHtmlPart(32)
invokeTag('message','g',187,['code':("alerte.suspendre.label"),'default':("Suspendre")],-1)
printHtmlPart(33)
invokeTag('checkBox','g',191,['name':("suspendre"),'value':(alerteInstance?.suspendre)],-1)
printHtmlPart(34)
invokeTag('message','g',209,['code':("alerte.lundi.label"),'default':("lundi")],-1)
printHtmlPart(35)
invokeTag('checkBox','g',214,['name':("lundi"),'value':(alerteInstance?.lundi)],-1)
printHtmlPart(36)
invokeTag('message','g',224,['code':("alerte.mardi.label"),'default':("mardi")],-1)
printHtmlPart(35)
invokeTag('checkBox','g',229,['name':("mardi"),'value':(alerteInstance?.mardi)],-1)
printHtmlPart(37)
invokeTag('message','g',238,['code':("alerte.mercredi.label"),'default':("mercredi")],-1)
printHtmlPart(38)
invokeTag('checkBox','g',243,['name':("mercredi"),'value':(alerteInstance?.mercredi)],-1)
printHtmlPart(39)
invokeTag('message','g',257,['code':("alerte.jeudi.label"),'default':("jeudi")],-1)
printHtmlPart(38)
invokeTag('checkBox','g',262,['name':("jeudi"),'value':(alerteInstance?.jeudi)],-1)
printHtmlPart(40)
invokeTag('message','g',272,['code':("alerte.vendredi.label"),'default':("vendredi")],-1)
printHtmlPart(38)
invokeTag('checkBox','g',277,['name':("vendredi"),'value':(alerteInstance?.vendredi)],-1)
printHtmlPart(41)
invokeTag('message','g',287,['code':("alerte.samedi.label"),'default':("Samedi")],-1)
printHtmlPart(38)
invokeTag('checkBox','g',292,['name':("samedi"),'value':(alerteInstance?.samedi)],-1)
printHtmlPart(42)
invokeTag('message','g',306,['code':("alerte.dimanche.label"),'default':("dimanche")],-1)
printHtmlPart(43)
invokeTag('checkBox','g',311,['name':("dimanche"),'value':(alerteInstance?.dimanche)],-1)
printHtmlPart(44)
})
invokeTag('form','g',313,['class':("well small form-horizontal"),'name':("createAlerte"),'action':("create")],1)
printHtmlPart(45)
invokeTag('message','g',320,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(46)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411420L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
