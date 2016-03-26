import simagri.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_postshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/show.gsp" }
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
invokeTag('message','g',8,['code':("show.post")],-1)
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
invokeTag('message','g',16,['code':("title.post")],-1)
printHtmlPart(5)
createTagBody(2, {->
printHtmlPart(6)
invokeTag('message','g',20,['code':("list.post")],-1)
printHtmlPart(7)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(8)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',26,['code':("create.post")],-1)
printHtmlPart(7)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(10)
invokeTag('message','g',36,['code':("show.post")],-1)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (postInstance?.author)) {
printHtmlPart(14)
invokeTag('message','g',47,['code':("post.author.label"),'default':("Author")],-1)
printHtmlPart(15)
createTagBody(3, {->
expressionOut.print(postInstance?.author?.encodeAsHTML())
})
invokeTag('link','g',51,['controller':("utilisateur"),'action':("show"),'id':(postInstance?.author?.id)],3)
printHtmlPart(16)
}
printHtmlPart(17)
if(true && (postInstance?.category)) {
printHtmlPart(14)
invokeTag('message','g',59,['code':("post.category.label"),'default':("Category")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',63,['bean':(postInstance),'field':("category")],-1)
printHtmlPart(18)
}
printHtmlPart(17)
if(true && (postInstance?.comments)) {
printHtmlPart(14)
invokeTag('message','g',72,['code':("post.comments.label"),'default':("Comments")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',76,['bean':(postInstance),'field':("comments")],-1)
printHtmlPart(18)
}
printHtmlPart(17)
if(true && (postInstance?.createdAt)) {
printHtmlPart(14)
invokeTag('message','g',85,['code':("post.createdAt.label"),'default':("Created At")],-1)
printHtmlPart(15)
invokeTag('formatDate','g',89,['date':(postInstance?.createdAt)],-1)
printHtmlPart(19)
}
printHtmlPart(17)
if(true && (postInstance?.post)) {
printHtmlPart(14)
invokeTag('message','g',97,['code':("post.post.label"),'default':("Post")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',101,['bean':(postInstance),'field':("post")],-1)
printHtmlPart(18)
}
printHtmlPart(17)
if(true && (postInstance?.title)) {
printHtmlPart(14)
invokeTag('message','g',110,['code':("post.title.label"),'default':("Title")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',114,['bean':(postInstance),'field':("title")],-1)
printHtmlPart(18)
}
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
invokeTag('hiddenField','g',124,['name':("id"),'value':(postInstance?.id)],-1)
printHtmlPart(22)
createTagBody(3, {->
printHtmlPart(23)
invokeTag('message','g',128,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(24)
})
invokeTag('link','g',129,['class':("btn"),'action':("edit"),'id':(postInstance?.id)],3)
printHtmlPart(25)
})
invokeTag('form','g',132,[:],2)
printHtmlPart(26)
})
invokeTag('captureBody','sitemesh',137,[:],1)
printHtmlPart(27)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1416069512145L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
