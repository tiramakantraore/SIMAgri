import simagri.Lieux
import simagri.Mesure
import  simagri.Produit
import  simagri.CategorieProduit
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_offre_colonneGaucheCreate_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_colonneGaucheCreate.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('select','g',10,['id':("typeOffre"),'name':(property),'from':(offreInstance.constraints.typeOffre.inList),'class':("form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',11,['bean':("offreInstance"),'property':("typeOffre"),'required':("true")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('select','g',21,['id':("categorieProduit"),'name':("categorieProduit"),'from':(CategorieProduit.list()),'optionKey':("id"),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(5)
})
invokeTag('field','f',24,['bean':("offreInstance"),'property':("categorieProduit")],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('select','g',35,['id':("produit"),'name':("produit"),'from':(Produit.list()),'optionKey':("id"),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(5)
})
invokeTag('field','f',38,['bean':("offreInstance"),'property':("produit"),'required':("true")],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('select','g',55,['id':("lieuStockage"),'name':("lieuStockage"),'from':(Lieux.list()),'optionKey':("id"),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',56,['bean':("offreInstance"),'property':("lieuStockage")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
expressionOut.print(formatDate(format:'dd/MM/yyyy',date:offreInstance?.date?:new Date()))
printHtmlPart(10)
})
invokeTag('field','f',80,['bean':("offreInstance"),'property':("date")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
expressionOut.print(formatDate(format:'dd/MM/yyyy',date:offreInstance?.dateExpiration?:new Date()))
printHtmlPart(13)
})
invokeTag('field','f',90,['bean':("offreInstance"),'property':("dateExpiration")],1)
printHtmlPart(14)
createClosureForHtmlPart(15, 1)
invokeTag('field','f',93,['bean':("offreInstance"),'property':("quantite")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('select','g',105,['id':("mesure"),'name':("mesure"),'from':(Mesure.list()),'optionKey':("id"),'class':("many-to-one form-control")],-1)
printHtmlPart(2)
})
invokeTag('field','f',105,['bean':("offreInstance"),'property':("mesure")],1)
printHtmlPart(16)
invokeTag('render','g',107,['template':("/offre/prixGrosCreate")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428771380983L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
