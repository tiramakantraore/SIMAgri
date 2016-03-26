import simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('hiddenField','g',3,['name':("theId"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',4,['id':("userType"),'name':("userType"),'value':(userType)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',5,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',6,['id':("isFirstLoad"),'name':("isFirstLoad"),'value':(isFirstLoad)],-1)
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
invokeTag('message','g',15,['code':("create.${suffixe}")],-1)
printHtmlPart(4)
})
invokeTag('remoteLink','g',16,['onLoading':("showSpinner();"),'params':("{userType:\$('#userType').val()}"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],2)
printHtmlPart(5)
invokeTag('message','g',23,['code':("list.${suffixe}")],-1)
printHtmlPart(6)
invokeTag('message','g',33,['code':("filtrerParReseau.text"),'default':("Filtrer par réseau")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',41,['id':("reseauSelected"),'name':("reseauSelected")],-1)
printHtmlPart(8)
invokeTag('radioBoxList','bill',43,['referenceCollection':(reseauList),'instanceName':("reseau"),'containerClass':("${ctnerTemplate} limitHeight"),'labelClass':("labelClass"),'onClickRadio':("onclickReseauOption();"),'emptyText':("Il n y a pas de réseaux")],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',55,['class':("alert-info")],3)
printHtmlPart(10)
}
printHtmlPart(11)
invokeTag('filterButton','filterpane',58,['text':("Rechercher"),'params':(filterParams)],-1)
printHtmlPart(12)
invokeTag('render','g',60,['template':("tableau")],-1)
printHtmlPart(13)
invokeTag('formats','export',63,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(14)
invokeTag('filterPane','filterpane',76,['dialog':("true"),'associatedProperties':("entreprise.nom,activite.nom,reseauPrincipal.nom"),'excludeProperties':("username,homePage,status,password,passwordExpired,accountLocked,accountExpired,enabled,photo,postCode,physicalAddress,secondPhone,country,town,poste,comment,dateOfBirth,reseaux"),'domain':("simagri.Utilisateur")],-1)
printHtmlPart(15)
})
invokeTag('form','g',76,['name':("list")],1)
printHtmlPart(16)
expressionOut.print(createLink(controller:'utilisateur', action:'renderListWithReseau'))
printHtmlPart(17)
expressionOut.print(createLink(controller:'utilisateur', action:'edit'))
printHtmlPart(18)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650296L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
