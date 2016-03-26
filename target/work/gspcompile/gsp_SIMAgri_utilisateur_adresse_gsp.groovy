import simagri.Activite
import  simagri.Entreprise
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_utilisateur_adresse_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/utilisateur/_adresse.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('countrySelect','g',7,['name':("country"),'value':(utilisateurInstance?.country),'default':("grails.defaultCountry"),'from':(['bfa','ben','civ', 'gha','gnb', 'mli','ner','sen','tgo']),'class':("form-control"),'noSelection':(['': 'Choisissez un pays'])],-1)
printHtmlPart(2)
})
invokeTag('field','f',8,['bean':("utilisateurInstance"),'property':("country")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(utilisateurInstance?.email)
printHtmlPart(5)
})
invokeTag('field','f',20,['bean':("utilisateurInstance"),'property':("email")],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(utilisateurInstance?.town)
printHtmlPart(5)
})
invokeTag('field','f',35,['bean':("utilisateurInstance"),'property':("town")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('textArea','g',46,['name':("physicalAddress"),'cols':("80"),'rows':("5"),'maxlength':("40"),'value':(utilisateurInstance?.physicalAddress),'placeholder':("Saisir ici l'adresse physique"),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',47,['bean':("utilisateurInstance"),'property':("physicalAddress")],1)
printHtmlPart(9)
createTagBody(1, {->
printHtmlPart(10)
expressionOut.print(utilisateurInstance?.secondPhone)
printHtmlPart(5)
})
invokeTag('field','f',60,['bean':("utilisateurInstance"),'property':("secondPhone")],1)
printHtmlPart(11)
invokeTag('field','f',70,['bean':("utilisateurInstance"),'property':("activite"),'input-class':("form-control")],-1)
printHtmlPart(12)
invokeTag('field','f',80,['bean':("utilisateurInstance"),'property':("entreprise"),'input-class':("form-control")],-1)
printHtmlPart(13)
invokeTag('field','f',93,['bean':("utilisateurInstance"),'property':("poste"),'input-class':("form-control")],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457418975290L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
