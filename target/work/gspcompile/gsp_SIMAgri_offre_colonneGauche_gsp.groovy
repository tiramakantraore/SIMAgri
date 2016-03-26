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

class gsp_SIMAgri_offre_colonneGauche_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/offre/_colonneGauche.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('field','f',8,['bean':("offreInstance"),'property':("typeOffre"),'required':("true"),'input-clas':("form-control")],-1)
printHtmlPart(1)
invokeTag('field','f',17,['bean':("offreInstance"),'property':("categorieProduit"),'input-class':("form-control")],-1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('select','g',29,['id':("produit"),'name':("produit"),'from':(Produit.list()),'optionKey':("id"),'required':(""),'value':(offreInstance?.produit?.id),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(4)
})
invokeTag('field','f',32,['bean':("offreInstance"),'property':("produit"),'required':("true")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('select','g',44,['id':("origine"),'name':("origine"),'from':(Lieux.list()),'optionKey':("id"),'value':(offreInstance?.origine?.id),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(4)
})
invokeTag('field','f',47,['bean':("offreInstance"),'property':("origine")],1)
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('select','g',58,['id':("lieuStockage"),'name':("lieuStockage"),'from':(Lieux.list()),'optionKey':("id"),'value':(offreInstance?.lieuStockage?.id),'noSelection':(['': '']),'class':("many-to-one form-control")],-1)
printHtmlPart(7)
})
invokeTag('field','f',59,['bean':("offreInstance"),'property':("lieuStockage")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
invokeTag('textField','g',75,['name':("date"),'value':(formatDate(format:'dd/MM/yyyy',date:offreInstance?.date?:new Date())),'class':("form-control"),'autocomplete':("off")],-1)
printHtmlPart(10)
})
invokeTag('field','f',79,['bean':("offreInstance"),'property':("date")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
invokeTag('textField','g',86,['name':("dateExpiration"),'value':(formatDate(format:'dd/MM/yyyy',date:offreInstance?.dateExpiration?:new Date())),'class':("form-control")],-1)
printHtmlPart(10)
})
invokeTag('field','f',90,['bean':("offreInstance"),'property':("dateExpiration")],1)
printHtmlPart(13)
createTagBody(1, {->
printHtmlPart(14)
invokeTag('textField','g',99,['type':("number"),'step':("1"),'id':("quantite"),'name':("quantite"),'value':(offreInstance?.quantite),'class':("form-control")],-1)
printHtmlPart(15)
})
invokeTag('field','f',101,['bean':("offreInstance"),'property':("quantite")],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('select','g',106,['id':("mesure"),'name':("mesure"),'from':(Mesure.list()),'optionKey':("id"),'value':(offreInstance?.mesure?.id),'class':("many-to-one form-control")],-1)
printHtmlPart(7)
})
invokeTag('field','f',107,['bean':("offreInstance"),'property':("mesure")],1)
printHtmlPart(16)
invokeTag('render','g',113,['template':("/offre/prixGros")],-1)
printHtmlPart(17)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428771917604L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
