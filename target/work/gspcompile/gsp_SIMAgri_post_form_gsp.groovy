import simagri.Post
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_post_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/post/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(hasErrors(bean: postInstance, field: 'author', 'error'))
printHtmlPart(1)
invokeTag('message','g',7,['code':("post.author.label"),'default':("Author")],-1)
printHtmlPart(2)
invokeTag('select','g',10,['id':("author"),'name':("author.id"),'from':(simagri.Utilisateur.list()),'optionKey':("id"),'required':(""),'value':(postInstance?.author?.id),'class':("many-to-one")],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'category', 'error'))
printHtmlPart(4)
invokeTag('message','g',15,['code':("post.category.label"),'default':("Category")],-1)
printHtmlPart(2)
invokeTag('textField','g',18,['name':("category"),'value':(postInstance?.category)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'comments', 'error'))
printHtmlPart(5)
invokeTag('message','g',23,['code':("post.comments.label"),'default':("Comments")],-1)
printHtmlPart(6)
for( c in (postInstance?.comments) ) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(c?.encodeAsHTML())
})
invokeTag('link','g',29,['controller':("comment"),'action':("show"),'id':(c.id)],2)
printHtmlPart(8)
}
printHtmlPart(9)
createTagBody(1, {->
expressionOut.print(message(code: 'default.add.label', args: [message(code: 'comment.label', default: 'Comment')]))
})
invokeTag('link','g',32,['controller':("comment"),'action':("create"),'params':(['post.id': postInstance?.id])],1)
printHtmlPart(10)
expressionOut.print(hasErrors(bean: postInstance, field: 'createdAt', 'error'))
printHtmlPart(11)
invokeTag('message','g',40,['code':("post.createdAt.label"),'default':("Created At")],-1)
printHtmlPart(2)
invokeTag('datePicker','g',43,['name':("createdAt"),'precision':("day"),'value':(postInstance?.createdAt)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'post', 'error'))
printHtmlPart(12)
invokeTag('message','g',48,['code':("post.post.label"),'default':("Post")],-1)
printHtmlPart(2)
invokeTag('textField','g',51,['name':("post"),'value':(postInstance?.post)],-1)
printHtmlPart(3)
expressionOut.print(hasErrors(bean: postInstance, field: 'title', 'error'))
printHtmlPart(13)
invokeTag('message','g',56,['code':("post.title.label"),'default':("Title")],-1)
printHtmlPart(2)
invokeTag('textField','g',59,['name':("title"),'value':(postInstance?.title)],-1)
printHtmlPart(14)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1415552520713L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
