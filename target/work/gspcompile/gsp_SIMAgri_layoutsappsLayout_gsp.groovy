import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layoutsappsLayout_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/appsLayout.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',6,['default':(meta(name: 'app.name'))],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':("Système d'Informations sur les Marchés de produits agricoles ")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("author"),'content':("BAMIG")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',11,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("keywords"),'content':("SIM, Système d'informations  de marché, Marchés, Agricole")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',13,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("robots"),'content':("index,follow")],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',16,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("document-classification"),'content':("SIM,Agriculture, elevage")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',18,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("rating"),'content':("General")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',20,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("MSSmartTagsPreventParsing"),'content':("TRUE")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',22,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("Copyright"),'content':("SIM Agri")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',24,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("developer"),'content':("http://www.bamig.com")],-1)
printHtmlPart(4)
invokeTag('captureMeta','sitemesh',28,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(1)
invokeTag('javascript','asset',28,['src':("application.js")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',28,['src':("application")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',29,['src':("custom")],-1)
printHtmlPart(2)
invokeTag('layoutHead','g',29,[:],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',50,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('render','g',55,['template':("/layouts/mainHeader")],-1)
printHtmlPart(8)
invokeTag('render','g',60,['template':("/layouts/menu"),'model':([activeMenu:'Apps'])],-1)
printHtmlPart(9)
invokeTag('layoutBody','g',63,[:],-1)
printHtmlPart(10)
invokeTag('render','g',64,['template':("/layouts/homeFooter")],-1)
printHtmlPart(11)
})
invokeTag('captureBody','sitemesh',65,[:],1)
printHtmlPart(12)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429144372071L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
