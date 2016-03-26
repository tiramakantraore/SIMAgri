import simagri.Region
import  simagri.CategorieProduit
import  simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_dashboard_dashboard_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/dashboard/_dashboard.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('checkBoxList','bill',25,['referenceCollection':(CategorieProduit.findAllByActif(true)),'containerClass':("my1Columns"),'instanceName':("categories"),'onClickCheck':("categorieClick();")],-1)
printHtmlPart(1)
invokeTag('checkBoxList','bill',53,['referenceCollection':(userProductsList),'containerClass':("my1Columns"),'instanceName':("produits"),'onClickCheck':("produitClick();"),'emptyText':("Il n y a pas de produits, veuillez vérifier votre profil")],-1)
printHtmlPart(2)
expressionOut.print(g.message(code:"regions.text", default:"Régions"))
printHtmlPart(3)
invokeTag('checkBoxList','bill',73,['referenceCollection':(Region.list()),'containerClass':("my1Columns"),'instanceName':("regions"),'onClickCheck':("regionClick();")],-1)
printHtmlPart(4)
expressionOut.print(g.message(code:"choixMarches.text", default:"Choix des marchés"))
printHtmlPart(5)
expressionOut.print(g.message(code:"tousLesMarches.text", default:"Tous les marchés"))
printHtmlPart(6)
expressionOut.print(g.message(code:"aucunMarche.text", default:"Aucun marché"))
printHtmlPart(7)
invokeTag('checkBoxList','bill',100,['referenceCollection':(userMarketsList),'containerClass':("my1Columns"),'instanceName':("marches"),'onClickCheck':("marcheClick();"),'emptyText':("Il n y a pas de marché, veuillez vérifier votre profil")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',123,['id':("magazinsSelected"),'name':("magazinsSelected")],-1)
printHtmlPart(9)
invokeTag('checkBoxList','bill',126,['referenceCollection':(userMagazinList),'containerClass':("my1Columns"),'instanceName':("magazins"),'onClickCheck':("magazinClick();"),'emptyText':("Il n y a pas de magazin, veuillez vérifier votre profil")],-1)
printHtmlPart(10)
invokeTag('hiddenField','g',145,['id':("typePrix"),'name':("typePrix"),'value':("Detail")],-1)
printHtmlPart(11)
invokeTag('radioBoxList','bill',146,['referenceCollection':(["Detail","Gros",""]),'radioName':("filterTypePrix"),'defaultValue':("Detail"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickTypePrix();")],-1)
printHtmlPart(12)
invokeTag('hiddenField','g',163,['id':("typeOffre"),'name':("typeOffre"),'value':("Achat")],-1)
printHtmlPart(11)
invokeTag('radioBoxList','bill',166,['referenceCollection':(['Achat','Vente',""]),'radioName':("filterTypeOffre"),'defaultValue':("Achat"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickTypeOffre();")],-1)
printHtmlPart(13)
expressionOut.print(g.message(code:"periode.text", default:"Période"))
printHtmlPart(14)
invokeTag('hiddenField','g',182,['id':("periodeSelected"),'name':("periodeSelected"),'value':("Le mois")],-1)
printHtmlPart(11)
invokeTag('radioBoxList','bill',188,['referenceCollection':(['Aujourd\'hui','La semaine','Le mois','Le trimestre',"${g.message(code: "annee.text", default:"L''annnée")}"]),'radioName':("filterPeriodeOption"),'defaultValue':("Le mois"),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickPeriodeOption();")],-1)
printHtmlPart(15)
expressionOut.print(g.message(code:"filtrerParReseau.text", default:"Filtrer par réseau"))
printHtmlPart(16)
invokeTag('hiddenField','g',215,['id':("reseauSelected"),'name':("reseauSelected")],-1)
printHtmlPart(17)
invokeTag('radioBoxList','bill',220,['referenceCollection':(Reseau.findAllByEstReseauAndActive(true,true)),'instanceName':("reseau"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickReseauOption();"),'emptyText':("Il n y a pas de réseaux")],-1)
printHtmlPart(18)
expressionOut.print(g.message(code:"filtrerParMotCle.text", default:"Filtrer par mot clé"))
printHtmlPart(19)
invokeTag('hiddenField','g',237,['id':("columnSelected"),'name':("columnSelected"),'value':("Auteur")],-1)
printHtmlPart(11)
invokeTag('radioBoxList','bill',240,['referenceCollection':(['Auteur','Note',""]),'radioName':("filterOption"),'defaultValue':(g.message(code:"telephone.text", default:"Téléphone")),'isArray':("true"),'containerClass':("my1Columns"),'labelClass':("labelClass"),'onClickRadio':("onclickOption();")],-1)
printHtmlPart(20)
if(true && (flash.welcomemessage)) {
printHtmlPart(21)
createTagBody(2, {->
expressionOut.print(flash.welcomemessage)
})
invokeTag('alert','bootstrap',261,['class':("alert-info")],2)
printHtmlPart(22)
}
printHtmlPart(23)
invokeTag('render','g',262,['template':("/dashboard/enteteGraphiquesForm")],-1)
printHtmlPart(24)
invokeTag('hiddenField','g',263,['id':("nbMonthLastPrices"),'name':("nbMonthLastPrices"),'value':(nombreMoisDerniersPrix)],-1)
printHtmlPart(25)
invokeTag('hiddenField','g',264,['id':("categorieTarifaire"),'name':("categorieTarifaire")],-1)
printHtmlPart(26)
invokeTag('render','g',265,['template':("/dashboard/mydashboardjs")],-1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457807901179L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
