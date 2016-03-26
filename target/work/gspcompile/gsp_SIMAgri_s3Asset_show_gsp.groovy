import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_SIMAgri_s3Asset_show_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/s3Asset/_show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('message','g',5,['code':("title.s3Asset")],-1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('message','g',7,['code':("list.s3Asset")],-1)
printHtmlPart(3)
})
invokeTag('link','g',8,['class':("list"),'action':("list")],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
invokeTag('message','g',18,['code':("create.s3Asset")],-1)
printHtmlPart(3)
})
invokeTag('link','g',19,['class':("create"),'action':("create")],1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
createTagBody(2, {->
expressionOut.print(flash.message)
})
invokeTag('alert','bootstrap',24,['class':("alert-info")],2)
printHtmlPart(8)
}
printHtmlPart(7)
createTagBody(1, {->
printHtmlPart(7)
invokeTag('display','f',26,['bean':("s3AssetInstance"),'property':("title")],-1)
printHtmlPart(7)
invokeTag('display','f',27,['bean':("s3AssetInstance"),'property':("description")],-1)
printHtmlPart(9)
invokeTag('createLinkTo','s3',31,['asset':(s3AssetInstance)],-1)
printHtmlPart(10)
createTagBody(2, {->
printHtmlPart(11)
invokeTag('message','g',45,['code':("default.button.edit.label"),'default':("Edit")],-1)
printHtmlPart(12)
})
invokeTag('remoteLink','g',45,['onLoading':("showSpinner();"),'onComplete':("hideSpinner()"),'action':("edit"),'update':("listContent"),'method':("GET"),'id':(s3AssetInstance.id),'class':("btn")],2)
printHtmlPart(13)
expressionOut.print(createLink(controller:'s3Asset', action:'delete'))
printHtmlPart(14)
invokeTag('message','g',55,['code':("default.button.delete.label"),'default':("Delete")],-1)
printHtmlPart(15)
})
invokeTag('form','g',56,[:],1)
printHtmlPart(16)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1423423411655L
public static final String EXPRESSION_CODEC = 'none'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
