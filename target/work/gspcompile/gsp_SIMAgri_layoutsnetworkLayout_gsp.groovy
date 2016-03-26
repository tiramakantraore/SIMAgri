import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layoutsnetworkLayout_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/networkLayout.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',6,['default':(meta(name: 'app.name'))],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("initial-scale = 1.0")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':("Système d'Informations sur les Marchés de produits agricoles ")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("author"),'content':("BAMIG")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("keywords"),'content':("SIM, Système d'informations  de marché, Marchés, Agricole")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',14,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("robots"),'content':("index,follow")],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("document-classification"),'content':("SIM,Agriculture, elevage")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',19,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("rating"),'content':("General")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',21,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("MSSmartTagsPreventParsing"),'content':("TRUE")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',23,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("Copyright"),'content':("SIM Agri")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',25,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("developer"),'content':("http://www.bamig.com")],-1)
printHtmlPart(4)
invokeTag('javascript','asset',34,['src':("application.js")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',34,['src':("application")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',35,['src':("custom")],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',37,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('render','g',41,['template':("/layouts/mainHeader")],-1)
printHtmlPart(7)
invokeTag('render','g',41,['template':("/layouts/menu")],-1)
printHtmlPart(8)
invokeTag('layoutBody','g',50,[:],-1)
printHtmlPart(9)
invokeTag('render','g',52,['template':("/layouts/homeFooter")],-1)
printHtmlPart(7)
})
invokeTag('captureBody','sitemesh',52,[:],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429144555939L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
