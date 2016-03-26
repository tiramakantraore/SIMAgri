import simagri.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_postlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'post.label', default: 'Post'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("list.post")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
invokeTag('require','r',10,['module':("export")],-1)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',18,['code':("title.post")],-1)
printHtmlPart(6)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('message','g',23,['code':("create.post")],-1)
printHtmlPart(8)
})
invokeTag('link','g',24,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',33,['code':("list.post")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',37,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',45,['code':("post.author.label"),'default':("Author")],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',47,['update':("listContent"),'action':("list"),'property':("category"),'title':(message(code: 'post.category.label', default: 'Category'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',49,['update':("listContent"),'action':("list"),'property':("createdAt"),'title':(message(code: 'post.createdAt.label', default: 'Created At'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',51,['update':("listContent"),'action':("list"),'property':("post"),'title':(message(code: 'post.post.label', default: 'Post'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',53,['update':("listContent"),'action':("list"),'property':("title"),'title':(message(code: 'post.title.label', default: 'Title'))],-1)
printHtmlPart(15)
for( postInstance in (postInstanceList) ) {
printHtmlPart(16)
createClosureForHtmlPart(17, 3)
invokeTag('link','g',62,['action':("show"),'id':(postInstance.id),'class':("btn btn-small")],3)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: postInstance, field: "author"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: postInstance, field: "category"))
printHtmlPart(19)
invokeTag('formatDate','g',69,['date':(postInstance.createdAt)],-1)
printHtmlPart(19)
expressionOut.print(fieldValue(bean: postInstance, field: "post"))
printHtmlPart(19)
expressionOut.print(fieldValue(bean: postInstance, field: "title"))
printHtmlPart(20)
}
printHtmlPart(21)
invokeTag('remotePaginate','util',81,['update':("listContent"),'action':("list"),'total':(postInstanceTotal),'params':(filterParams)],-1)
printHtmlPart(22)
invokeTag('filterButton','filterpane',82,['text':("Rechercher")],-1)
printHtmlPart(23)
invokeTag('filterPane','filterpane',88,['dialog':("true"),'domain':("simagri.Post")],-1)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',89,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1418040993861L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
