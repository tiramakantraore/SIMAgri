import simagri.S3Asset
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3AssetdocumentsRecherche_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/documentsRecherche.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("list.s3Asset")],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',14,['code':("list.s3Asset")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',20,['class':("alert-info")],3)
printHtmlPart(8)
}
printHtmlPart(8)
invokeTag('filterButton','filterpane',21,['text':("Rechercher")],-1)
printHtmlPart(8)
invokeTag('render','g',22,['template':("documents")],-1)
printHtmlPart(8)
invokeTag('formats','export',24,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(9)
invokeTag('remotePaginate','util',28,['update':("documents"),'action':("filter"),'total':(S3Asset.count()),'max':("5"),'alwaysShowPageSizes':("true"),'pageSizes':([5, 10,15,20,100])],-1)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',28,[:],1)
printHtmlPart(11)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1431856184120L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
