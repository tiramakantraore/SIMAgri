import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('render','g',1,['template':("/partials/showHeader"),'model':([beanName:'offre',isEdit:true,canCreate:false,titre:'Voir une offre'])],-1)
printHtmlPart(0)
if(true && (flash.message)) {
printHtmlPart(1)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',9,['class':("alert-info")],2)
printHtmlPart(2)
}
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('display','f',13,['bean':("offreInstance"),'property':("auteur")],-1)
printHtmlPart(1)
invokeTag('display','f',14,['bean':("offreInstance"),'property':("typeOffre")],-1)
printHtmlPart(1)
invokeTag('display','f',15,['bean':("offreInstance"),'property':("modePaiement")],-1)
printHtmlPart(1)
invokeTag('display','f',16,['bean':("offreInstance"),'property':("date")],-1)
printHtmlPart(1)
invokeTag('display','f',17,['bean':("offreInstance"),'property':("delaiEnJours")],-1)
printHtmlPart(1)
invokeTag('display','f',18,['bean':("offreInstance"),'property':("dateExpiration")],-1)
printHtmlPart(1)
invokeTag('display','f',19,['bean':("offreInstance"),'property':("origine")],-1)
printHtmlPart(1)
invokeTag('display','f',20,['bean':("offreInstance"),'property':("lieuStockage")],-1)
printHtmlPart(1)
invokeTag('display','f',21,['bean':("offreInstance"),'property':("lieuLivraison")],-1)
printHtmlPart(1)
invokeTag('display','f',22,['bean':("offreInstance"),'property':("produit")],-1)
printHtmlPart(1)
invokeTag('display','f',23,['bean':("offreInstance"),'property':("qualite")],-1)
printHtmlPart(1)
invokeTag('display','f',24,['bean':("offreInstance"),'property':("prixUnitaire")],-1)
printHtmlPart(1)
invokeTag('display','f',25,['bean':("offreInstance"),'property':("quantite")],-1)
printHtmlPart(1)
invokeTag('display','f',26,['bean':("offreInstance"),'property':("prixTotalGros")],-1)
printHtmlPart(4)
invokeTag('display','f',28,['bean':("offreInstance"),'property':("delaiLivraison")],-1)
printHtmlPart(1)
invokeTag('display','f',29,['bean':("offreInstance"),'property':("nbJoursExpiration")],-1)
printHtmlPart(1)
invokeTag('display','f',30,['bean':("offreInstance"),'property':("operateur")],-1)
printHtmlPart(1)
invokeTag('display','f',31,['bean':("offreInstance"),'property':("conditions")],-1)
printHtmlPart(1)
invokeTag('display','f',32,['bean':("offreInstance"),'property':("commentaire")],-1)
printHtmlPart(1)
invokeTag('hiddenField','g',33,['name':("update"),'value':(update)],-1)
printHtmlPart(5)
invokeTag('render','g',36,['template':("/partials/btnShow"),'model':([update:update,instance:offreInstance])],-1)
printHtmlPart(2)
})
invokeTag('form','g',37,[:],1)
printHtmlPart(6)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1419207088222L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
