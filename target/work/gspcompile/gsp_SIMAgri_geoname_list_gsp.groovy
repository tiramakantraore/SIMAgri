import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_geoname_list_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/geoname/_list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',3,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(1)
expressionOut.print(createLink(uri: '/home'))
printHtmlPart(2)
invokeTag('message','g',7,['code':("default.home.label")],-1)
printHtmlPart(3)
createTagBody(1, {->
invokeTag('message','g',9,['code':("create.geoname")],-1)
})
invokeTag('link','g',9,['class':("create"),'action':("create")],1)
printHtmlPart(4)
invokeTag('message','g',14,['code':("list.geoname")],-1)
printHtmlPart(5)
if(true && (flash.message)) {
printHtmlPart(6)
expressionOut.print(flash.message)
printHtmlPart(7)
}
printHtmlPart(8)
invokeTag('remoteSortableColumn','util',22,['update':("listContent"),'action':("list"),'property':("name"),'title':(message(code: 'geoname.name.label', default: 'Name'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',25,['update':("listContent"),'action':("list"),'property':("asciiname"),'title':(message(code: 'geoname.asciiname.label', default: 'Asciiname'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',28,['update':("listContent"),'action':("list"),'property':("latitude"),'title':(message(code: 'geoname.latitude.label', default: 'Latitude'))],-1)
printHtmlPart(9)
invokeTag('remoteSortableColumn','util',31,['update':("listContent"),'action':("list"),'property':("longitude"),'title':(message(code: 'geoname.longitude.label', default: 'Longitude'))],-1)
printHtmlPart(10)
loop:{
int i = 0
for( geonameInstance in (geonameInstanceList) ) {
printHtmlPart(11)
expressionOut.print(fieldValue(bean: geonameInstance, field: "name"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: geonameInstance, field: "asciiname"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: geonameInstance, field: "latitude"))
printHtmlPart(12)
expressionOut.print(fieldValue(bean: geonameInstance, field: "longitude"))
printHtmlPart(13)
i++
}
}
printHtmlPart(14)
invokeTag('remotePaginate','util',53,['update':("listContent"),'action':("list"),'total':(geonameInstanceTotal)],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416306771539L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
