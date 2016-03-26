import simagri.S3Asset
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',7,['code':("list.s3Asset")],-1)
printHtmlPart(1)
if(true && (flash.message)) {
printHtmlPart(2)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',15,['class':("alert-info")],2)
printHtmlPart(3)
}
printHtmlPart(3)
invokeTag('filterButton','filterpane',17,['text':("Rechercher")],-1)
printHtmlPart(4)
invokeTag('remoteSortableColumn','util',22,['update':("listContent"),'action':("list"),'property':("title"),'title':("Titre")],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',24,['update':("listContent"),'action':("list"),'property':("mimeType"),'title':("Type document")],-1)
printHtmlPart(5)
invokeTag('remoteSortableColumn','util',26,['update':("listContent"),'action':("list"),'property':("length"),'title':(message(code: 's3Asset.length.label', default: 'Taille en ko'))],-1)
printHtmlPart(6)
loop:{
int i = 0
for( s3AssetInstance in (s3AssetList) ) {
printHtmlPart(7)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(8)
createClosureForHtmlPart(9, 2)
invokeTag('remoteLink','g',38,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(s3AssetInstance.id),'class':("btn-flat  btn-small")],2)
printHtmlPart(10)
expressionOut.print(fieldValue(bean: s3AssetInstance, field: "title"))
printHtmlPart(11)
expressionOut.print(s3AssetInstance.mimeType?.encodeAsHTML())
printHtmlPart(11)
expressionOut.print(s3AssetInstance.length.toKo())
printHtmlPart(12)
i++
}
}
printHtmlPart(13)
invokeTag('remotePaginate','util',53,['update':("listContent"),'action':("list"),'params':(filterParams),'total':(S3Asset.count())],-1)
printHtmlPart(14)
invokeTag('formats','export',55,['formats':(['csv', 'excel', 'pdf'])],-1)
printHtmlPart(15)
invokeTag('filterPane','filterpane',62,['dialog':("true"),'domain':("S3Asset"),'excludeProperties':("bucket,mimeType,localPath,localUrl,key,protocol,length,lastModified,percentTransferred,bytesPerSecond,bytesTransfered,options,hostName")],-1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993478L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
