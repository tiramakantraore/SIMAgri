import simagri.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_postindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/index.gsp" }
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
invokeTag('message','g',8,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(6)
invokeTag('message','g',14,['code':("default.home.label")],-1)
printHtmlPart(7)
createTagBody(2, {->
invokeTag('message','g',15,['code':("default.new.label"),'args':([entityName])],-1)
})
invokeTag('link','g',15,['class':("create"),'action':("create")],2)
printHtmlPart(8)
invokeTag('message','g',19,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
expressionOut.print(flash.message)
printHtmlPart(11)
}
printHtmlPart(12)
invokeTag('message','g',27,['code':("post.author.label"),'default':("Author")],-1)
printHtmlPart(13)
invokeTag('remoteSortableColumn','util',29,['update':("listContent"),'action':("list"),'property':("category"),'title':(message(code: 'post.category.label', default: 'Category'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',31,['update':("listContent"),'action':("list"),'property':("createdAt"),'title':(message(code: 'post.createdAt.label', default: 'Created At'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',33,['update':("listContent"),'action':("list"),'property':("post"),'title':(message(code: 'post.post.label', default: 'Post'))],-1)
printHtmlPart(14)
invokeTag('remoteSortableColumn','util',35,['update':("listContent"),'action':("list"),'property':("title"),'title':(message(code: 'post.title.label', default: 'Title'))],-1)
printHtmlPart(15)
loop:{
int i = 0
for( postInstance in (postInstanceList) ) {
printHtmlPart(16)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(17)
createTagBody(3, {->
expressionOut.print(fieldValue(bean: postInstance, field: "author"))
})
invokeTag('link','g',43,['action':("show"),'id':(postInstance.id)],3)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: postInstance, field: "category"))
printHtmlPart(18)
invokeTag('formatDate','g',47,['date':(postInstance.createdAt)],-1)
printHtmlPart(18)
expressionOut.print(fieldValue(bean: postInstance, field: "post"))
printHtmlPart(18)
expressionOut.print(fieldValue(bean: postInstance, field: "title"))
printHtmlPart(19)
i++
}
}
printHtmlPart(20)
invokeTag('remotePaginate','util',58,['update':("listContent"),'action':("list"),'total':(postInstanceCount ?: 0)],-1)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',61,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416192615082L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
