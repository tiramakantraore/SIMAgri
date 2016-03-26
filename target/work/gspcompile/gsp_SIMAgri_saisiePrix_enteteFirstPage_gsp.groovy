import org.joda.time.Instant
import  simagri.Marche
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_saisiePrix_enteteFirstPage_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/saisiePrix/_enteteFirstPage.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('radioBoxList','bill',7,['referenceCollection':(userMarketsList),'instanceName':("market"),'containerClass':("${ctnerTemplate} limitHeight"),'labelClass':("labelClass50"),'onClickRadio':("marcheClick();")],-1)
printHtmlPart(2)
})
invokeTag('field','f',8,['property':("market"),'label':("Veuillez sélectionner le marché ")],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(formatDate(format:'dd/MM/yyyy',date:new Date()))
printHtmlPart(5)
})
invokeTag('field','f',22,['property':("date")],1)
printHtmlPart(6)
invokeTag('message','g',32,['code':("default.button.create.label"),'default':("Create")],-1)
printHtmlPart(7)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423410277L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
