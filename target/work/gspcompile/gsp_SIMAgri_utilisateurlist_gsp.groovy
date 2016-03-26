import simagri.Reseau
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateurlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':(request?.isPjax?'pjaxListContent':'accueilLayout')],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("miseEnLigne.label")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
invokeTag('resources','ckeditor',13,[:],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',14,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(3)
createTagBody(2, {->
printHtmlPart(1)
invokeTag('hiddenField','g',17,['name':("theId"),'value':(utilisateurInstance?.id)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',18,['id':("userType"),'name':("userType"),'value':(userType)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',19,['name':("suffixe"),'value':(suffixe)],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',20,['id':("isFirstLoad"),'name':("isFirstLoad"),'value':(isFirstLoad)],-1)
printHtmlPart(4)
createTagBody(3, {->
printHtmlPart(5)
invokeTag('message','g',29,['code':("create.${suffixe}")],-1)
printHtmlPart(6)
})
invokeTag('remoteLink','g',30,['onLoading':("showSpinner();"),'params':("{userType:\$('#userType').val()}"),'onComplete':("hideSpinner()"),'action':("create"),'update':("listContent"),'method':("GET"),'class':("create")],3)
printHtmlPart(7)
invokeTag('message','g',37,['code':("list.${suffixe}")],-1)
printHtmlPart(8)
invokeTag('hiddenField','g',54,['id':("reseauSelected"),'name':("reseauSelected")],-1)
printHtmlPart(9)
invokeTag('radioBoxList','bill',56,['referenceCollection':(reseauList),'instanceName':("reseau"),'containerClass':("${ctnerTemplate} limitHeight"),'labelClass':("labelClass"),'onClickRadio':("onclickReseauOption();"),'emptyText':("Il n y a pas de réseaux")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(4, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',68,['class':("alert-info")],4)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('filterButton','filterpane',71,['text':("Rechercher"),'params':(filterParams)],-1)
printHtmlPart(13)
invokeTag('render','g',73,['template':("tableau")],-1)
printHtmlPart(14)
invokeTag('formats','export',76,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(15)
invokeTag('filterPane','filterpane',89,['dialog':("true"),'associatedProperties':("entreprise.nom,activite.nom,reseauPrincipal.nom"),'excludeProperties':("username,homePage,status,password,passwordExpired,accountLocked,accountExpired,enabled,photo,postCode,physicalAddress,secondPhone,country,town,poste,comment,dateOfBirth,reseaux"),'domain':("simagri.Utilisateur")],-1)
printHtmlPart(16)
})
invokeTag('form','g',89,['name':("list")],2)
printHtmlPart(17)
expressionOut.print(createLink(controller:'utilisateur', action:'renderListWithReseau'))
printHtmlPart(18)
expressionOut.print(createLink(controller:'utilisateur', action:'edit'))
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',161,[:],1)
printHtmlPart(20)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328756820L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
