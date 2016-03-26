import simagri.Performance
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_performance_validerPerformance_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/performance/_validerPerformance.gsp" }
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
invokeTag('message','g',7,['code':("habil.contrat.performance.label")],-1)
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
invokeTag('message','g',13,['code':("habil.contrat.performance.label")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(1)
invokeTag('hiddenField','g',18,['name':("selectedList")],-1)
printHtmlPart(6)
invokeTag('message','g',21,['code':("select.performance.label"),'default':("Sélectionnez la performance")],-1)
printHtmlPart(7)
invokeTag('select','g',24,['id':("performance"),'name':("performance"),'from':(Performance.list()),'noSelection':(['': 'Choisissez une performance']),'class':("form-control")],-1)
printHtmlPart(8)
invokeTag('message','g',57,['code':("valider.label"),'default':("Valider")],-1)
printHtmlPart(9)
invokeTag('message','g',67,['code':("quitter.label"),'default':("Quitter")],-1)
printHtmlPart(10)
})
invokeTag('form','g',74,['class':("form-horizontal"),'action':("validerPerformance")],2)
printHtmlPart(11)
expressionOut.print(createLink(controller:'performance', action:'populate'))
printHtmlPart(12)
expressionOut.print(createLink(controller:"performance",action:"listOfPerformance"))
printHtmlPart(13)
expressionOut.print(createLink(controller:'performance', action:'valider'))
printHtmlPart(14)
expressionOut.print(createLink(controller:'performance', action:'populate'))
printHtmlPart(15)
expressionOut.print(createLink(controller:"performance", action:"autoriser"))
printHtmlPart(16)
expressionOut.print(createLink(controller:'performance', action:'populate'))
printHtmlPart(17)
expressionOut.print(createLink(controller:'home', action:'accueil'))
printHtmlPart(18)
expressionOut.print(createLink(controller:'performance', action:'refuser'))
printHtmlPart(19)
expressionOut.print(createLink(controller:'performance', action:'populate'))
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',228,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1442328650342L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
