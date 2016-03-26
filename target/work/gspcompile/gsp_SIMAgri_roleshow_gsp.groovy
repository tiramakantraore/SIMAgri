import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_roleshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/role/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("adminLayout")],-1)
printHtmlPart(1)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'role.label', default: 'Role'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("show.role")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('message','g',16,['code':("title.role")],-1)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',20,['code':("list.role")],-1)
printHtmlPart(6)
})
invokeTag('link','g',21,['class':("list"),'action':("list")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(8)
invokeTag('message','g',26,['code':("create.role")],-1)
printHtmlPart(6)
})
invokeTag('link','g',27,['class':("create"),'action':("create")],2)
printHtmlPart(9)
invokeTag('message','g',36,['code':("show.role")],-1)
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
createTagBody(3, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',40,['class':("alert-info")],3)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (roleInstance?.authority)) {
printHtmlPart(13)
invokeTag('message','g',46,['code':("role.authority.label"),'default':("Authority")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',48,['bean':(roleInstance),'field':("authority")],-1)
printHtmlPart(15)
}
printHtmlPart(16)
if(true && (roleInstance?.description)) {
printHtmlPart(13)
invokeTag('message','g',53,['code':("role.description.label"),'default':("Description")],-1)
printHtmlPart(14)
invokeTag('fieldValue','g',55,['bean':(roleInstance),'field':("description")],-1)
printHtmlPart(15)
}
printHtmlPart(17)
createTagBody(2, {->
printHtmlPart(18)
invokeTag('hiddenField','g',62,['name':("id"),'value':(roleInstance?.id)],-1)
printHtmlPart(19)
createTagBody(3, {->
printHtmlPart(20)
invokeTag('message','g',66,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(21)
})
invokeTag('link','g',67,['class':("btn"),'action':("edit"),'id':(roleInstance?.id)],3)
printHtmlPart(22)
invokeTag('message','g',70,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(23)
})
invokeTag('form','g',73,[:],2)
printHtmlPart(24)
})
invokeTag('captureBody','sitemesh',78,[:],1)
printHtmlPart(25)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411512L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
