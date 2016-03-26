import simagri.Marche
import  simagri.Performance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_marche_validerMarcheEnqueteur_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/marche/_validerMarcheEnqueteur.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("habil.marcheEnqueteur.label")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',13,['code':("habil.marcheEnqueteur.label")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(1)
invokeTag('hiddenField','g',18,['name':("selectedList")],-1)
printHtmlPart(6)
invokeTag('message','g',21,['code':("select.marche.label"),'default':("Sélectionnez le marché")],-1)
printHtmlPart(7)
invokeTag('select','g',23,['id':("marche"),'name':("marche"),'from':(Marche.list()),'noSelection':(['': 'Choisissez un marché']),'class':("form-control")],-1)
printHtmlPart(8)
invokeTag('message','g',56,['code':("valider.label"),'default':("Valider")],-1)
printHtmlPart(9)
invokeTag('message','g',66,['code':("quitter.label"),'default':("Quitter")],-1)
printHtmlPart(10)
})
invokeTag('form','g',73,['class':("form-horizontal"),'action':("validerMarcheEnqueteur")],2)
printHtmlPart(11)
expressionOut.print(createLink(controller:'marche', action:'populate'))
printHtmlPart(12)
expressionOut.print(createLink(controller:"marche",action:"listOfMarche"))
printHtmlPart(13)
expressionOut.print(createLink(controller:'marche', action:'valider'))
printHtmlPart(14)
expressionOut.print(createLink(controller:'marche', action:'populate'))
printHtmlPart(15)
expressionOut.print(createLink(controller:"marche", action:"autoriser"))
printHtmlPart(16)
expressionOut.print(createLink(controller:'marche', action:'populate'))
printHtmlPart(17)
expressionOut.print(createLink(controller:'home', action:'accueil'))
printHtmlPart(18)
expressionOut.print(createLink(controller:'marche', action:'refuser'))
printHtmlPart(19)
expressionOut.print(createLink(controller:'marche', action:'populate'))
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',227,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328105153L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
