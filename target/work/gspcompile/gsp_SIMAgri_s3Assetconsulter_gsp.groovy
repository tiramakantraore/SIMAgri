import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Assetconsulter_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/consulter.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("list.s3Asset")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',19,['code':("list.s3Asset")],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',22,['class':("alert-info")],3)
printHtmlPart(8)
}
printHtmlPart(8)
invokeTag('filterButton','filterpane',24,['text':("Rechercher")],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',27,['update':("listContent"),'action':("list"),'property':("title"),'title':("Title")],-1)
printHtmlPart(10)
invokeTag('remoteSortableColumn','util',34,['update':("listContent"),'action':("list"),'property':("description"),'title':("Description")],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',38,['update':("listContent"),'action':("list"),'property':("mimeType"),'title':("Type document")],-1)
printHtmlPart(11)
invokeTag('remoteSortableColumn','util',43,['update':("listContent"),'action':("list"),'property':("length"),'title':(message(code: 's3Asset.length.label', default: 'Taille en ko'))],-1)
printHtmlPart(12)
loop:{
int i = 0
for( s3Asset in (s3AssetList) ) {
printHtmlPart(13)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(14)
createTagBody(3, {->
expressionOut.print(s3AssetInstance.title?.encodeAsHTML())
})
invokeTag('link','g',52,['action':("showConsulter"),'id':(s3AssetInstance.id)],3)
printHtmlPart(15)
expressionOut.print(s3AssetInstance.description?.encodeAsHTML())
printHtmlPart(16)
expressionOut.print(s3AssetInstance.mimeType?.encodeAsHTML())
printHtmlPart(16)
expressionOut.print(s3AssetInstance.length/1024)
printHtmlPart(17)
i++
}
}
printHtmlPart(18)
invokeTag('remotePaginate','util',60,['update':("listContent"),'action':("list"),'total':(S3Asset.count()),'params':(filterParams)],-1)
printHtmlPart(19)
invokeTag('formats','export',60,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(20)
invokeTag('filterPane','filterpane',66,['dialog':("true"),'domain':("org.grails.s3.S3Asset"),'excludeProperties':("bucket,mimeType,protocol,length,lastModified,percentTransferred,bytesPerSecond,bytesTransfered,options,hostName")],-1)
printHtmlPart(3)
})
invokeTag('captureBody','sitemesh',67,[:],1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416192615015L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
