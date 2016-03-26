import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_layoutsaccueilLayout_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/accueilLayout.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',5,['default':(meta(name: 'app.name'))],-1)
})
invokeTag('captureTitle','sitemesh',5,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',5,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':("Système d'Informations sur les Marchés de produits agricoles ")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("author"),'content':("BAMIG")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("keywords"),'content':("SIM, Système d'informations  de marché, Marchés, Agricole")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("robots"),'content':("index,follow")],-1)
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',15,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("document-classification"),'content':("SIM,Agriculture, elevage")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',17,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("rating"),'content':("General")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',19,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("MSSmartTagsPreventParsing"),'content':("TRUE")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',21,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("Copyright"),'content':("SIMAgri")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',22,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("developer"),'content':("TRAORE JOEL HYACINTHE")],-1)
printHtmlPart(4)
invokeTag('captureMeta','sitemesh',26,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',26,['src':("application.js")],-1)
printHtmlPart(5)
invokeTag('javascript','asset',26,['src':("fp.js")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',28,['src':("application.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',29,['src':("fp.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',29,['src':("custom.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',30,['src':("mediaelementplayer2.css")],-1)
printHtmlPart(1)
invokeTag('resources','ckeditor',30,[:],-1)
printHtmlPart(1)
invokeTag('layoutHead','g',32,[:],-1)
printHtmlPart(6)
})
invokeTag('captureHead','sitemesh',37,[:],1)
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(8)
invokeTag('render','g',45,['template':("/layouts/mainHeader")],-1)
printHtmlPart(9)
invokeTag('render','g',49,['template':("/layouts/menu")],-1)
printHtmlPart(10)
invokeTag('render','g',57,['template':("/layouts/spinnerPage")],-1)
printHtmlPart(11)
invokeTag('layoutBody','g',58,[:],-1)
printHtmlPart(12)
invokeTag('render','g',60,['template':("/layouts/homeFooter")],-1)
printHtmlPart(13)
})
invokeTag('captureBody','sitemesh',60,[:],1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1457811490996L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
