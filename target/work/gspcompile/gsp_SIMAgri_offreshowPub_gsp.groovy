import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offreshowPub_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/showPub.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'offre.label', default: 'Offre'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.offre")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
if(true && (flash.message)) {
printHtmlPart(4)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',15,['class':("alert-info")],3)
printHtmlPart(4)
}
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('display','f',18,['bean':("offreInstance"),'property':("auteur")],-1)
printHtmlPart(5)
invokeTag('display','f',19,['bean':("offreInstance"),'property':("typeOffre")],-1)
printHtmlPart(5)
invokeTag('display','f',20,['bean':("offreInstance"),'property':("modePaiement")],-1)
printHtmlPart(5)
invokeTag('display','f',21,['bean':("offreInstance"),'property':("date")],-1)
printHtmlPart(5)
invokeTag('display','f',22,['bean':("offreInstance"),'property':("delaiEnJours")],-1)
printHtmlPart(5)
invokeTag('display','f',23,['bean':("offreInstance"),'property':("dateExpiration")],-1)
printHtmlPart(5)
invokeTag('display','f',24,['bean':("offreInstance"),'property':("origine")],-1)
printHtmlPart(5)
invokeTag('display','f',25,['bean':("offreInstance"),'property':("lieuStockage")],-1)
printHtmlPart(5)
invokeTag('display','f',26,['bean':("offreInstance"),'property':("lieuLivraison")],-1)
printHtmlPart(5)
invokeTag('display','f',27,['bean':("offreInstance"),'property':("produit")],-1)
printHtmlPart(5)
invokeTag('display','f',28,['bean':("offreInstance"),'property':("qualite")],-1)
printHtmlPart(5)
invokeTag('display','f',29,['bean':("offreInstance"),'property':("prixUnitaire")],-1)
printHtmlPart(5)
invokeTag('display','f',30,['bean':("offreInstance"),'property':("quantite")],-1)
printHtmlPart(5)
invokeTag('display','f',31,['bean':("offreInstance"),'property':("prixTotalGros")],-1)
printHtmlPart(5)
invokeTag('display','f',32,['bean':("offreInstance"),'property':("conditions")],-1)
printHtmlPart(5)
invokeTag('display','f',33,['bean':("offreInstance"),'property':("delaiLivraison")],-1)
printHtmlPart(5)
invokeTag('display','f',34,['bean':("offreInstance"),'property':("nbJoursExpiration")],-1)
printHtmlPart(5)
invokeTag('display','f',35,['bean':("offreInstance"),'property':("operateur")],-1)
printHtmlPart(6)
invokeTag('hiddenField','g',39,['name':("id"),'value':(offreInstance?.id)],-1)
printHtmlPart(7)
createTagBody(3, {->
printHtmlPart(8)
invokeTag('message','g',43,['code':("default.button.close.label"),'default':("Retour")],-1)
printHtmlPart(9)
})
invokeTag('link','g',44,['class':("btn"),'controller':("home"),'action':("accueil")],3)
printHtmlPart(10)
})
invokeTag('form','g',47,[:],2)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',52,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419280149830L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
