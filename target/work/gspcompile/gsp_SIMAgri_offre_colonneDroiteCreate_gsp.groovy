import simagri.Qualite
import  simagri.Lieux
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_colonneDroiteCreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_colonneDroiteCreate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('select','g',6,['id':("modePaiement"),'name':("modePaiement"),'from':(offreInstance.constraints.modePaiement.inList),'valueMessagePrefix':("offre.modePaiement"),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',7,['bean':("offreInstance"),'property':("modePaiement"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('select','g',18,['id':("lieuLivraison"),'name':("lieuLivraison"),'from':(Lieux.list()),'optionKey':("id"),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',19,['bean':("offreInstance"),'property':("lieuLivraison")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('select','g',31,['id':("qualite"),'name':("qualite"),'from':(Qualite.list()),'optionKey':("id"),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(6)
})
invokeTag('field','f',33,['bean':("offreInstance"),'property':("qualite")],1)
printHtmlPart(7)
invokeTag('hiddenField','g',43,['name':("auteur")],-1)
printHtmlPart(8)
invokeTag('message','g',45,['code':("offre.auteur.text"),'default':("Auteur")],-1)
printHtmlPart(9)
expressionOut.print(g.message(code: 'offre.auteur.placeHolder.label', default: 'Les 4 prem. caractères, exple:Sawa... ou 7032...'))
printHtmlPart(10)
createClosureForHtmlPart(11, 1)
invokeTag('field','f',62,['bean':("offreInstance"),'property':("contact")],1)
printHtmlPart(12)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('textArea','g',73,['name':("conditions"),'cols':("80"),'rows':("5"),'value':(offreInstance?.conditions),'placeHolder':(g.message(code: 'offre.conditions.placeHolder.label', default: 'Prix négociable selon le volume')),'class':("form-control")],-1)
printHtmlPart(13)
})
invokeTag('field','f',74,['bean':("offreInstance"),'property':("conditions")],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418685894994L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
